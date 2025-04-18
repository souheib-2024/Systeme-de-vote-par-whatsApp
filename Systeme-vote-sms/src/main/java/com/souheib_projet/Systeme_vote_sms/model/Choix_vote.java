package com.souheib_projet.Systeme_vote_sms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Choix_vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroVote;
    private String nomVote;

    @Lob
    private byte[] image; // Contenu binaire de l'image
    private String imageName; // Nom original de l'image (ex. photo1.png)
    private String imageMimeType; // Type MIME (ex. image/png)
    private Long imageSize; // Taille en octets

 
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getNumeroVote() { return numeroVote; }
    public void setNumeroVote(int numeroVote) { this.numeroVote = numeroVote; }

    public String getNomVote() { return nomVote; }
    public void setNomVote(String nomVote) { this.nomVote = nomVote; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    public Long getImageSize() {
        return imageSize;
    }

    public void setImageSize(Long imageSize) {
        this.imageSize = imageSize;
    }

}
