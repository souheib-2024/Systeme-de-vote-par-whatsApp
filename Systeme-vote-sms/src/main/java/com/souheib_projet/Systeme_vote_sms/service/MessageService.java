package com.souheib_projet.Systeme_vote_sms.service;
import org.springframework.http.HttpHeaders;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.souheib_projet.Systeme_vote_sms.model.Choix_vote;
import com.souheib_projet.Systeme_vote_sms.model.Message;
import com.souheib_projet.Systeme_vote_sms.repository.MessageRepository;


@Service
public class MessageService {

    @Value("${whatsapp.api.phone_number_id}")
    private String phoneNumberId;

    @Value("${webhook.verify.token}")
    private String verifyToken;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private Choix_voteService choixVoteService; // Service pour interagir avec les options de vote

    // Méthode pour sauvegarder un message
    public void saveMessage(String sender, String content, LocalDateTime timestamp) {
        Message message = new Message(sender, content, timestamp);
        messageRepository.save(message);
    }

    // Méthode pour traiter les messages des participants
    public void processMessage(String sender, String content) {
        // Récupère tous les numéros de vote disponibles depuis la base
        List<String> choixValides = choixVoteService.findAllVoteNumbers();

        if (choixValides.contains(content.trim())) {
            saveMessage(sender, content, LocalDateTime.now());

            String confirmation = "Bravo 🎉, ton vote pour le choix : " + content + " a été pris en compte.";
            sendAutomaticReply(sender, confirmation);
        } else {
            String erreur = "Bienvenue 😊. Voici les choix disponibles pour voter :";
            sendAutomaticReply(sender, erreur);
           sendVotingOptions(sender); // Envoi des options en image
        }
    }

    // Méthode pour envoyer une réponse automatique
    public void sendAutomaticReply(String to, String messageContent) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = new HashMap<>();
        body.put("messaging_product", "whatsapp");
        body.put("to", to);
        body.put("type", "text");
        body.put("text", Map.of("body", messageContent));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + verifyToken);
        headers.add("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        try {
            String url = "https://graph.facebook.com/v22.0/" + phoneNumberId + "/messages";
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            System.out.println("Message envoyé à : " + to);
            System.out.println("Réponse de l'API : " + response.getBody());
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
        }
    }
 // Méthode pour envoyer les options de vote avec des images
    @SuppressWarnings("deprecation")
    public void sendVotingOptions(String to) {
        RestTemplate restTemplate = new RestTemplate();

        // Récupération des options depuis l'URL publique
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> options = restTemplate.getForObject(
                "https://2458-195-85-248-21.ngrok-free.app/votes/options",
                List.class
        );

        if (options == null || options.isEmpty()) {
            System.err.println("⚠️ Aucune option disponible à envoyer !");
            return;
        }

        for (Map<String, Object> option : options) {
            try {
                // Récupérer le nom de l'image
                String imageName = (String) option.get("imageLink");
                System.out.println("Image Link: " + imageName);

                // Construire l'URL de l'image
                String imageUrl = imageName;

                // Légende de l'option (texte à envoyer)
                String caption = "Option " + option.get("numeroVote") + ": " + option.get("nomVote");

                // Vérification de la validité de l'URL de l'image
                try {
                    new URL(imageUrl).toURI(); // Valider l'URL
                    System.out.println("🔗 Lien image valide : " + imageUrl);
                } catch (Exception e) {
                    System.err.println("❌ Lien invalide ou inaccessible : " + imageUrl);
                    imageUrl = "https://2458-195-85-248-21.ngrok-free.app/images/c1.jpg"; // Image de remplacement
                    caption += " (image de test)";  // Ajout d'un message pour informer que l'image est de remplacement
                    System.out.println("🔄 Image remplacée par une image de test : " + imageUrl);
                }

                // Envoi du message avec texte et image
                sendImageWithCaption(to, imageUrl, caption);

            } catch (Exception e) {
                System.err.println("🚨 Exception lors de l'envoi de l'option : " + option);
                System.err.println("Message d'erreur : " + e.getMessage());
            }
        }
    }

    // Méthode pour envoyer un message image avec une légende (texte) associée
    public void sendImageWithCaption(String to, String imageUrl, String caption) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = new HashMap<>();
        body.put("messaging_product", "whatsapp");
        body.put("to", to);
        body.put("type", "image");
        body.put("image", Map.of(
                "link", imageUrl,        // URL de l'image à envoyer
                "caption", caption       // Légende de l'option avec numéro et nom
        ));
        System.out.println("Body Request: " + body);

        // Envoi de la requête via RestTemplate
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + verifyToken);
        headers.add("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        String url = "https://graph.facebook.com/v22.0/" + phoneNumberId + "/messages";
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // Logs
        System.out.println("✔️ Option envoyée : " + caption);
        System.out.println("📡 Status Code: " + response.getStatusCodeValue());
        System.out.println("📝 Response Body: " + response.getBody());

        if (!response.getStatusCode().is2xxSuccessful()) {
            System.err.println("❌ Problème lors de l'envoi de l'image !");
            System.err.println("↩️ Corps de la réponse : " + response.getBody());
        }
    }




}