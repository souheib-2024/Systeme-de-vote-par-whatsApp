package com.souheib_projet.Systeme_vote_sms.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.souheib_projet.Systeme_vote_sms.service.MessageService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private MessageService messageService;

    @Value("${webhook.verify.token}")
    private String verifyToken;

    // Valider le webhook (GET)
    @GetMapping
    public ResponseEntity<String> validerWebhook(@RequestParam("hub.mode") String mode,
                                                 @RequestParam("hub.challenge") String challenge,
                                                 @RequestParam("hub.verify_token") String receivedToken) {
        if (verifyToken.equals(receivedToken)) {
            return ResponseEntity.ok(challenge); // Retourne le challenge pour valider le webhook
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token de vérification invalide");
        }
    }

    // Recevoir un message (POST)
    @PostMapping
    public ResponseEntity<String> recevoirMessage(@RequestBody Map<String, Object> payload) {
        try {

            // Extraire les données principales
            List<Map<String, Object>> entries = (List<Map<String, Object>>) payload.get("entry");
            if (entries == null || entries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucune entrée trouvée.");
            }

            Map<String, Object> entry = entries.get(0); // Prendre la première entrée
            List<Map<String, Object>> changes = (List<Map<String, Object>>) entry.get("changes");
            if (changes == null || changes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun changement trouvé.");
            }

            Map<String, Object> change = changes.get(0); // Prendre le premier changement
            Map<String, Object> value = (Map<String, Object>) change.get("value");
            List<Map<String, Object>> messages = (List<Map<String, Object>>) value.get("messages");

            if (messages == null || messages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun message reçu.");
            }

            Map<String, Object> message = messages.get(0); // Prendre le premier message
            String sender = (String) message.get("from"); // Numéro de l'expéditeur
            String content = (String) ((Map<String, Object>) message.get("text")).get("body"); // Contenu du message
            String timestampStr = (String) message.get("timestamp"); // Timestamp (en secondes)

            // Convertir le timestamp en LocalDateTime
            long timestamp = Long.parseLong(timestampStr); // Convertir en long
            LocalDateTime receivedTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());

            // Gérer le message en fonction de son contenu
            messageService.processMessage(sender, content);

            return ResponseEntity.ok("Message traité avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement du message");
        }
    }
}