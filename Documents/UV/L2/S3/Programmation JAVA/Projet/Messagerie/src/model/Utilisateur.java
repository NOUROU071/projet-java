package model;
import db.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    private int id; // Identifiant de l'utilisateur
    private String nom;
    private String email;
    private String motDePasse;
    private List<Message> messagesRecus; // Liste pour stocker les messages reçus

    // Constructeur pour créer un utilisateur
    public Utilisateur(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.messagesRecus = new ArrayList<>();
    }

    // Constructeur sans id pour la création d'un nouvel utilisateur
    public Utilisateur(String nom, String email, String motDePasse) {
        this(-1, nom, email, motDePasse); // Appelle l'autre constructeur avec un id par défaut
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    // Méthode pour recevoir un message
    public void recevoirMessage(Message message) {
        messagesRecus.add(message);
    }

    // Méthode pour récupérer les messages reçus depuis la base de données
    public List<Message> getMessagesRecus() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE destinataire_email = ?";

        try (Connection conn = Database.getConnection(); // Assurez-vous que Database.getConnection() est bien défini
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.email); // Utilise l'email de l'utilisateur
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String expediteurEmail = rs.getString("expediteur_email");
                String sujet = rs.getString("sujet");
                String contenu = rs.getString("contenu");
                Message message = new Message(expediteurEmail, expediteurEmail, this.email, sujet, contenu);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Affiche l'erreur
        }
        return messages; // Retourne la liste des messages reçus
    }
}