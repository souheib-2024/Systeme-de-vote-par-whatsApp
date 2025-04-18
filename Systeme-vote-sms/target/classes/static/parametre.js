document.addEventListener("DOMContentLoaded", function () {
    let nextButton = document.querySelector(".nextButton");
    let progressBar = document.getElementById("progressBar");

    mettreAJourLien(); // Vérifie au chargement si le lien doit être désactivé

    // Ajoute les boutons "Modifier" et "Supprimer" aux lignes existantes
    const lignes = document.querySelectorAll("#tableBody tr");
    lignes.forEach(ajouterBoutonsLigne);

    nextButton.addEventListener("click", function (event) {
        event.preventDefault();
        startLoading();

        setTimeout(() => {
            window.location.href = nextButton.getAttribute("href");
        }, 1000);
    });
});

// Fonction pour mettre à jour le lien "Démarrer"
function mettreAJourLien() {
    let tableBody = document.getElementById("tableBody");
    let nextButton = document.querySelector(".nextButton");

    if (tableBody.children.length < 2) {
        nextButton.classList.add("disabled");
        nextButton.removeAttribute("href");
    } else {
        nextButton.classList.remove("disabled");
        nextButton.setAttribute("href", "accueil.html");
    }
}

// Fonction pour ajouter une nouvelle ligne (si tu veux en ajouter dynamiquement)
function ajouterValeur() {
    let input = document.getElementById("inputValeur");
    let valeur = input.value.trim();

    if (valeur !== "") {
        let tableBody = document.getElementById("tableBody");
        let newRow = tableBody.insertRow();

        let cell1 = newRow.insertCell(0);
        let cell2 = newRow.insertCell(1);

        cell1.textContent = valeur;
        cell2.textContent = "motdepasse"; // valeur par défaut ou à changer

        ajouterBoutonsLigne(newRow);

        input.value = "";
        input.focus();

        mettreAJourLien();
    }
}

// Fonction pour rendre une cellule éditable
function rendreEditable(cell) {
    let valeurActuelle = cell.textContent;
    let input = document.createElement("input");
    input.type = "text";
    input.value = valeurActuelle;
    input.classList.add("edit-input");

    cell.innerHTML = "";
    cell.appendChild(input);
    input.focus();

    input.addEventListener("blur", function () {
        cell.textContent = input.value || valeurActuelle;
    });

    input.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            cell.textContent = input.value || valeurActuelle;
        }
    });
}

// Fonction pour ajouter les boutons Modifier et Supprimer à une ligne
function ajouterBoutonsLigne(row) {
    // Bouton Modifier
    const btnModifier = document.createElement("button");
    btnModifier.textContent = "✏️";
    btnModifier.className = "edit-btn";
    btnModifier.onclick = function () {
        rendreEditable(row.cells[0]);
        rendreEditable(row.cells[1]);
    };

    // Cellule Modifier
    const cellModifier = row.insertCell(2);
    cellModifier.appendChild(btnModifier);

    // Bouton Supprimer
    const btnSupprimer = document.createElement("button");
    btnSupprimer.textContent = "❌";
    btnSupprimer.className = "delete-btn";
    btnSupprimer.onclick = function () {
        row.remove();
        mettreAJourLien();
    };

    // Cellule Supprimer
    const cellSupprimer = row.insertCell(3);
    cellSupprimer.appendChild(btnSupprimer);
}

// ====================
// Animation de la barre de progression
// ====================
function startLoading() {
    let progressBar = document.getElementById("progressBar");
    progressBar.style.width = "0%";
    progressBar.style.opacity = "1";
    let width = 0;

    function animateProgress() {
        if (width < 100) {
            width += Math.random() * 20;
            progressBar.style.width = width + "%";
            setTimeout(animateProgress, 100);
        } else {
            finishLoading();
        }
    }
    animateProgress();
}

function finishLoading() {
    let progressBar = document.getElementById("progressBar");
    progressBar.style.width = "100%";

    setTimeout(() => {
        progressBar.style.opacity = "0";
        setTimeout(() => {
            progressBar.style.width = "0%";
        }, 3000);
    });
}