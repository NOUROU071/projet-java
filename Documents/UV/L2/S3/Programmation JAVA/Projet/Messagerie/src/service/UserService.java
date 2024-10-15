package service;

import model.Utilisateur;
import db.Database;
import java.sql.*;

import model.Message;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    public boolean creerCompte(String nom, String email, String motDePasse) { // Ajout de motDePasse ici
        String sql = "INSERT INTO utilisateurs (nom, email, mot_de_passe) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.setString(3, motDePasse); // Utilisation de motDePasse ici
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Affiche l'erreur
            return false; // En cas d'erreur (ex. email déjà utilisé)
        }
    }
    public Utilisateur seConnecter(String email, String motDePasse) {
        String sql = "SELECT * FROM utilisateurs WHERE email = ? AND mot_de_passe = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, motDePasse);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Créer un objet Utilisateur avec l'id récupéré
                Utilisateur user = new Utilisateur(
                        rs.getInt("id"),  // Récupérer l'id de la base
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                );
                return user; // Retourne l'utilisateur trouvé
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Affiche l'erreur
        }
        return null; // Si aucun utilisateur n'est trouvé
    }

    public void envoyerMessage(String destinataireEmail, Message message) {
        for (Utilisateur user : utilisateurs) {
            if (user.getEmail().equals(destinataireEmail)) {
                user.recevoirMessage(message); // Ajoute le message à la boîte de réception
                break;
            }
        }
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public boolean envoyerMessage(Message message) {
        String sql = "INSERT INTO messages (expediteur_email, destinataire_email, sujet, contenu) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(); // Assurez-vous que Database.getConnection() est bien défini
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, message.getExpediteurEmail());
            pstmt.setString(2, message.getDestinataire());
            pstmt.setString(3, message.getSujet());
            pstmt.setString(4, message.getContenu());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Retourne true si au moins une ligne a été insérée
        } catch (SQLException e) {
            e.printStackTrace(); // Affiche l'erreur
            return false; // Retourne false en cas d'erreur
        }
    }


}