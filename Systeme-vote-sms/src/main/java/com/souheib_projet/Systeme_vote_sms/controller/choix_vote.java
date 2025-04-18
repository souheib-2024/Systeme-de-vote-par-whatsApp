package com.souheib_projet.Systeme_vote_sms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.souheib_projet.Systeme_vote_sms.model.Choix_vote;
import com.souheib_projet.Systeme_vote_sms.service.Choix_voteService;

@Controller
@RequestMapping("/votes")
public class choix_vote {

    @Autowired
    private Choix_voteService voteService;

    // Sauvegarder les votes avec leurs images
    @PostMapping("/save")
    public ResponseEntity<String> saveVotes(
        @RequestParam("numeroVote") List<Integer> numerosVote,
        @RequestParam("nomVote") List<String> nomsVote,
        @RequestParam("image") List<MultipartFile> images
    ) {
        List<Choix_vote> votes = new ArrayList<>();

        for (int i = 0; i < numerosVote.size(); i++) {
            Choix_vote vote = new Choix_vote();
            vote.setNumeroVote(numerosVote.get(i));
            vote.setNomVote(nomsVote.get(i));

            try {
                MultipartFile imageFile = images.get(i);
                if (imageFile != null && !imageFile.isEmpty()) {
                    vote.setImage(imageFile.getBytes());
                    vote.setImageName(imageFile.getOriginalFilename());
                    vote.setImageMimeType(imageFile.getContentType());
                    vote.setImageSize(imageFile.getSize());

                    System.out.println("Image Name: " + vote.getImageName());
                    System.out.println("Image MIME Type: " + vote.getImageMimeType());
                    System.out.println("Image Size: " + vote.getImageSize());
                } else {
                    vote.setImage(null);
                    vote.setImageName(null);
                    vote.setImageMimeType(null);
                    vote.setImageSize(null);

                    System.out.println("Aucune image associée pour le vote : " + vote.getNomVote());
                }
            } catch (IOException e) {
                e.printStackTrace();
                vote.setImage(null);
            }

            votes.add(vote);
        }

        voteService.saveVotes(votes);
        return ResponseEntity.ok("Votes enregistrés avec succès !");
    }

    // Récupérer l'image par son nom
    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImageByName(@PathVariable String imageName) {
        Optional<Choix_vote> vote = voteService.findByImageName(imageName); // Rechercher par nom
        if (vote.isPresent() && vote.get().getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(vote.get().getImageMimeType())); // Utiliser le type MIME
            return new ResponseEntity<>(vote.get().getImage(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Récupérer les options de vote
    @GetMapping("/options")
    public ResponseEntity<List<Map<String, Object>>> getVotingOptions() {
        List<Choix_vote> votes = voteService.findAllVotes();
        List<Map<String, Object>> options = new ArrayList<>();

        for (Choix_vote vote : votes) {
            Map<String, Object> option = new HashMap<>();
            option.put("numeroVote", vote.getNumeroVote());
            option.put("nomVote", vote.getNomVote());
            if (vote.getImageName() != null) {
                // URL correcte des images
                option.put("imageLink", "https://2458-195-85-248-21.ngrok-free.app/votes/image/" + vote.getImageName());
            } else {
                option.put("imageLink", null);
            }
            options.add(option);
        }

        return ResponseEntity.ok(options);
    }
}
