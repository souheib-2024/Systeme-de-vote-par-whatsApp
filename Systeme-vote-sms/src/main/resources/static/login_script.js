// Attend que tout le HTML soit chargé avant d'exécuter la fonction
document.addEventListener("DOMContentLoaded", function () {
    const progressBar = document.getElementById("progressBar");
    const loginForm = document.getElementById("loginForm");
    const loginButton = document.getElementById("loginButton");
    const username = document.getElementById("username");
    const password = document.getElementById("password");
    const errorMessage = document.getElementById("errorMessage");
    const togglePassword = document.getElementById("togglePassword");

    // Changer le favicon dynamiquement
    function changeFavicon(src) {
        let link = document.querySelector("link[rel='icon']") || document.createElement("link");
        link.rel = "icon";
        link.href = src;
        document.head.appendChild(link);
    }

    // Toggle visibilité du mot de passe
    togglePassword.addEventListener("click", function () {
        if (password.type === "password") {
            password.type = "text";
            togglePassword.classList.replace("bx-toggle-left", "bx-toggle-right");
        } else {
            password.type = "password";
            togglePassword.classList.replace("bx-toggle-right", "bx-toggle-left");
        }
    });

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

    // Gestion du formulaire de connexion
    loginForm.addEventListener("submit", function (e) {
        e.preventDefault();

        // Réinitialisation des erreurs
        username.classList.remove("input-error");
        password.classList.remove("input-error");
        errorMessage.style.display = "none";

        // Vérification des champs
        if (username.value.trim() === "" || password.value.trim() === "") {
            errorMessage.style.display = "block";
            username.classList.add("input-error");
            password.classList.add("input-error");
            return;
        }

        // Animation de chargement
        loginButton.classList.add("loading");
        loginButton.disabled = true;
        document.title = "Connexion en cours...";
        startLoading(); // On démarre la barre de progression

      
    
    });
});