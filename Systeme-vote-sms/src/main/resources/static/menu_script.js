document.addEventListener("DOMContentLoaded", function () {
    // Gestion des liens actifs dans le menu
    const navLinks = document.querySelectorAll(".menu li a");
    const currentPage = window.location.pathname.split("/").pop() || "accueil"; // Page actuelle ou "accueil"

    navLinks.forEach(link => {
        const linkPage = link.getAttribute("href").split("/").pop();

        if (linkPage === currentPage) {
            link.classList.add("active");
        }
    });

    // Gestion du menu déroulant
    const adminIcon = document.querySelector(".admin-icon");
    const dropdownMenu = document.querySelector(".dropdown-menu");

    // Affiche ou masque le menu déroulant au clic sur l'icône
    adminIcon.addEventListener("click", function (e) {
        e.stopPropagation(); // Empêche le clic de se propager
        dropdownMenu.classList.toggle("show"); // Ajoute ou enlève la classe "show"
    });

    // Ferme le menu si on clique ailleurs
    document.addEventListener("click", function (e) {
        if (!adminIcon.contains(e.target) && !dropdownMenu.contains(e.target)) {
            dropdownMenu.classList.remove("show"); // Cache le menu
        }
    });
});