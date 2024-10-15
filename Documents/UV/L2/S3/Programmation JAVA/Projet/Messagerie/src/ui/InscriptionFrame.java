package ui;

import model.Utilisateur;
import service.UserService;
import javax.swing.*;
import java.awt.*;

public class InscriptionFrame {
    private JFrame frame;
    private final Utilisateur utilisateur;
    private final UserService userService;

    public InscriptionFrame(UserService userService, Utilisateur utilisateur) {
        this.userService = userService;
        this.utilisateur = utilisateur;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Menu Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JLabel welcomeLabel = new JLabel("Bienvenue, " + utilisateur.getNom() + " !");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(welcomeLabel);

        JButton voirMessagesRecusButton = new JButton("Voir mes messages reçus");
        JButton envoyerMessageButton = new JButton("Envoyer un message");
        JButton voirMessagesEnvoyesButton = new JButton("Voir mes messages envoyés");
        JButton deconnexionButton = new JButton("Déconnexion");

        frame.add(voirMessagesRecusButton);
        frame.add(envoyerMessageButton);
        frame.add(voirMessagesEnvoyesButton);
        frame.add(deconnexionButton);

        voirMessagesRecusButton.addActionListener(e -> voirMessagesRecus());
        envoyerMessageButton.addActionListener(e -> envoyerMessage());
        voirMessagesEnvoyesButton.addActionListener(e -> voirMessagesEnvoyes());
        deconnexionButton.addActionListener(e -> {
            frame.dispose();
            // Retourne à la connexion
        });

        frame.setVisible(true);
    }

    private void envoyerMessage() {
        // Crée une nouvelle instance de MessagerieFrame pour envoyer un message
        new MessagerieFrame(utilisateur, userService, false); // false pour envoyer un message
    }

    private void voirMessagesRecus() {
        new MessagerieFrame(utilisateur, userService, true); // true pour messages reçus
    }

    // Optionnel : Méthode pour voir les messages envoyés
    private void voirMessagesEnvoyes() {
        // Implémente la logique ici si nécessaire
    }
}