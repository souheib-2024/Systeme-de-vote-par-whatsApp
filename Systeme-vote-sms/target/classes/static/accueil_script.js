document.addEventListener("DOMContentLoaded", function () {
    const progressBar = document.getElementById("progressBar");
    const createButton = document.getElementById("createButtonID");
    const eventForm = document.getElementById("eventForm");
    const eventStartDateInput = document.getElementById("dateStartID");
    const eventEndDateInput = document.getElementById("dateEndID");
    const startDate = document.getElementById("dateStartID");
    const endDate = document.getElementById("dateEndID");

    //ON contrôle sur les dates
    function validateDates(){
        const startDate = new Date(eventStartDateInput.value);
        const endDate = new Date(eventEndDateInput.value);

        if (endDate < startDate){
            alert("⚠️ La date de fin ne peut pas être antérieure à la date de début !");
            eventEndDateInput.value =""; // Réinitialise la date de fin
        }
    }

    // Vérifie les dates à chaque modification
    eventStartDateInput.addEventListener("change", validateDates);
    eventEndDateInput.addEventListener("change", validateDates);

    startDate.addEventListener("change", function(){
        endDate.min = startDate.value;// Empêche de choisir une date de fin avant la date de début
    });

    // Changer le favicon dynamiquement
    function changeFavicon(src) {
        let link = document.querySelector("link[rel='icon']") || document.createElement("link");
        link.rel = "icon";
        link.href = src;
        document.head.appendChild(link);
    }

    changeFavicon("favicon.ico");

    // Barre de progression 
    function startLoading() {
        progressBar.style.width = "0%";
        progressBar.style.opacity = "1";
        let width = 0;
    
        function animateProgress() {
            if (width < 85) {
                width += Math.random() * 10;
                progressBar.style.width = width + "%";
                setTimeout(animateProgress, 150);
            }
        }
        animateProgress();
    }
    
    function finishLoading() {
        progressBar.style.width = "100%";
        setTimeout(() => {
            progressBar.style.opacity = "0";
            setTimeout(() => {
                progressBar.style.width = "0%";
            }, 500);
        }, 500);
    }

    if (eventForm){
        eventForm.addEventListener("submit", function(event){
            // Vérification des champs requis
            const eventName = document.getElementById("eventID").value.trim();
            const eventDescription = document.getElementById("descriptionID").value.trim();
            const eventStartDate = document.getElementById("dateStartID").value.trim();
            const eventEndDate = document.getElementById("dateEndID").value.trim();

            if (!eventName || !eventDescription || !eventStartDate || !eventEndDate){
                alert("veuillez remplir les champs obligatoires !");
                event.preventDefault();// Annule l'envoi du formulaire
                return;
            }

            // Si les champs sont valides, démarrer l'animation et soumettre après un court délai
            event.preventDefault();// Empêche la soumission immédiate
            document.title = "Création en cour..."
            startLoading();
            createButton.disabled = true;

            setTimeout(()=>{
                finishLoading();
                document.title = "Acceuil";
                createButton.disabled = false;

                eventForm.submit(); // Soumission du formulaire après chargement
            }, 2400);//Délai de chargement
        });
    }
    
});