# Systeme-de-vote-par-whatsApp
Projet de stage

Interfaces Utilisateur (UI)

1. admin_form
- Description :
Interface destinée à l'authentification de l'administrateur à l'aide de son nom d'utilisateur et mot de passe. Les informations sont soit prédéfinies, soit stockées dans la table admin de la base de données.
- À faire :- Mettre en place la gestion des tentatives de connexion avec des informations d'authentification incorrectes (message d'erreur ou blocage après plusieurs tentatives).
- Ajouter une fonctionnalité pour gérer l'option "Mot de passe oublié" (envoi d'un e-mail ou réinitialisation via un formulaire sécurisé).

2. accueil
- Description :
Permet une vue d'ensemble du système de vote côté administrateur. Fournit un formulaire pour démarrer la création d’un évènement. Les informations de chaque évènement sont stockées dans une table evenement de la base de données.
- NB :- Chaque nouvel évènement doit être associé à un identifiant (id) unique, qui sera transféré à l'interface suivante pour lier les choix de vote.
- Implémenter la gestion des évènements déjà créés (par exemple, une liste d'évènements récents).

3. choixVote
- Description :
Interface permettant d'énumérer tous les choix de vote d’un évènement. Ces choix sont enregistrés dans la table choix_vote et mis à disposition des participants.
- Améliorations nécessaires :- Modifer la structure actuelle de la table choix_vote pour inclure une relation avec la table evenement (via une clé étrangère).
- Ajouter une validation des données pour éviter les doublons de choix de vote ou des entrées invalides.

4. liste_evenement
- Description :
Affiche la liste de tous les évènements créés avec leurs informations associées : choix de vote et résultats.
- NB :- Actuellement, cette interface affiche des informations statiques. Il faut remplacer ces données par celles tirées dynamiquement de la base de données.
- Ajouter une pagination si le nombre d’évènements devient trop important.

5. resultat
- Description :
Interface dédiée à l’affichage des résultats de vote pour chaque évènement. Idéalement, les résultats seront affichés sous forme de graphiques associés aux images des choix de vote.
- À faire :- Concevoir un algorithme pour traiter les votes reçus (analyse et agrégation des données).
- Ajouter une librairie de graphiques (par exemple, Chart.js ou Google Charts) pour un affichage visuel clair et interactif.
- Intégrer une fonctionnalité de téléchargement/export des résultats (par exemple, en CSV ou PDF).

6. parametre
- Description :
Interface permettant la gestion des administrateurs (ajouter, modifier ou supprimer un administrateur).
- À faire :- Ajouter une validation stricte des données saisies (pour éviter les doublons ou les incohérences).
- Proposer une interface intuitive pour visualiser et gérer les administrateurs existants.

Améliorations générales :
- Sécurité :- Utiliser des mots de passe hashés pour les administrateurs avec des techniques comme bcrypt ou Argon2.
- Protéger les points d'entrée (formulaires) contre les attaques par injection SQL ou XSS.

- Ergonomie :- Rendre les interfaces conviviales pour l’utilisateur (UI/UX), avec des validations côté client (JavaScript) et côté serveur.
- Ajouter des messages de confirmation ou de retour d'erreur clairs et explicites.


L'architecture du systeme(MVC) :
1. Couche Présentation ou ressources :  Cette couche comprend les interfaces utilisateur que les utilisateurs finaux utilisent, comme les interfaces HTML/CSS/JS.
2.  Couche Contrôleur :  Les contrôleurs Spring Boot agissent comme un pont entre la couche de présentation et les couches métier. Ils reçoivent les requêtes des utilisateurs (via des endpoints REST) et renvoient les réponses.
3. Couche Service : Cette couche contient la logique métier de l'application. C'est là que les règles et traitements spécifiques à ton système sont implémentés.
4. Couche Accès aux données (Repository/Data Layer) :Cette couche permet d’interagir avec la base de données. En Spring Boot, elle est souvent mise en œuvre avec Spring Data JPA.
5. Couche Base de données (Database Layer) :  Cette couche contient les tables et schémas définis pour stocker les données persistantes de ton système.
NB : nom de la base de données "appweb" SGBD : MySQL. 
Interaction entre les composants :
Voici comment les différentes couches interagissent dans ton système :
- Un utilisateur (administrateur ou participant) envoie une requête HTTP via une interface utilisateur (HTML/JS).
- La requête est capturée par un Controller dans Spring Boot (par exemple, EvenementController pour créer un évènement).
- Le Controller délègue la logique métier au Service correspondant (par exemple, EvenementService).
- Le Service interagit avec les Repositories pour lire ou écrire des données dans la base de données.
- Une réponse est renvoyée à la couche de présentation, qui affiche les résultats à l'utilisateur.
Technologies clés utilisées
- Spring Boot : Framework principal pour le backend.
- Spring Data JPA : Gestion des données avec des entités (Java).
- Thymeleaf ou Vue.js/React : Si des modèles dynamiques sont utilisés pour la vue.
- MySQL ou PostgreSQL : Base de données relationnelle.
- REST API : Communication entre le frontend et le backend via des endpoints.
+-------------------------------------------------+
|        Interface Utilisateur (HTML/JS)         |
+-------------------------------------------------+
                  |
                  v
+-------------------------------------------------+
|      Couche Contrôleur (Controllers REST)       |
+-------------------------------------------------+
                  |
                  v
+-------------------------------------------------+
|          Couche Service (Logique métier)        |
+-------------------------------------------------+
                  |
                  v
+-------------------------------------------------+
|     Couche Repository (Accès aux données)       |
+-------------------------------------------------+
                  |
                  v
+-------------------------------------------------+
|        Base de données (MySQL)       |
+-------------------------------------------------+

Le fichier applications.properties :
Ce fichier de configuration contrôle plusieurs aspects de ton application Spring Boot :
- Nom de l'application (pour les logs).
- Connexion à la base de données MySQL (environnement persistant).
- Configuration Hibernate/JPA (gestion de la base via des entités Java).
- Templates Thymeleaf (désactivation du cache pour le développement).
- Upload de fichiers (gestion des fichiers envoyés par les utilisateurs).
- API WhatsApp (intégration avec WhatsApp Business pour la gestion des votes).

NB : Pour accéder à l'API Meta pour WhatsApp, il est nécessaire de suivre ces étapes :
- Créer une application Meta en utilisant ton compte Facebook.
- Générer un nouveau jeton d'accès (token) en suivant les instructions fournies par la documentation Meta.
- Mettre à jour le token et l'identifiant de numéro de téléphone API WhatsApp (phone_number_id) dans les fichiers de configuration de ton applica







