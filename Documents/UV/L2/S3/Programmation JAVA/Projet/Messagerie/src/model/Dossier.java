package model;

import java.util.ArrayList;
import java.util.List;

public class Dossier {
    private String nom;
    private List<Message> messages;

    public Dossier(String nom) {
        this.nom = nom;
        this.messages = new ArrayList<>();
    }

    public void ajouterMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getNom() {
        return nom;
    }
}