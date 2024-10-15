package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import  model.*;
import service.UserService;
import model.Utilisateur;

public class MainFrame {
    private JFrame frame;
    private Utilisateur utilisateur;
    private UserService userService;

    public MainFrame(UserService userService, Utilisateur utilisateur) {
        this.userService = userService;
        this.utilisateur = utilisateur;
        initialize();
    }

    public MainFrame() {

    }

    private void initialize() {
        frame = new JFrame("Interface Utilisateur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bienvenue, " + utilisateur.getNom() + " !");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton voirMessagesButton = new JButton("Voir mes messages");
        JButton envoyerMessageButton = new JButton("Envoyer un message");
        JButton deconnexionButton = new JButton("Déconnexion");

        buttonPanel.add(voirMessagesButton);
        buttonPanel.add(envoyerMessageButton);
        buttonPanel.add(deconnexionButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        voirMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voirMessages();
            }
        });

        envoyerMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                envoyerMessage();
            }
        });

        deconnexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deconnexion();
            }
        });

        frame.setVisible(true);
    }

//    private void voirMessages() {
//        // Implémenter la logique pour afficher les messages
//        StringBuilder messages = new StringBuilder("Voici vos messages :\n");
//        List<Message> messagesRecus = utilisateur.getMessagesRecus(); // Appel à la méthode
//
//        if (messagesRecus.isEmpty()) {
//            messages.append("Aucun message reçu.");
//        } else {
//            for (Message message : messagesRecus) {
//                messages.append(message.toString()).append("\n"); // Assurez-vous que toString() est bien défini dans Message
//            }
//        }
//        JOptionPane.showMessageDialog(frame, messages.toString(), "Messages", JOptionPane.INFORMATION_MESSAGE);
//    }
private void voirMessages() {
    // Implémenter la logique pour afficher les messages
    StringBuilder messages = new StringBuilder("Voici vos messages :\n\n");
    List<Message> messagesRecus = utilisateur.getMessagesRecus(); // Appel à la méthode

    if (messagesRecus.isEmpty()) {
        messages.append("Aucun message reçu.");
    } else {
        for (Message message : messagesRecus) {
            messages.append("📧 **Expéditeur** : ").append(message.getExpediteurEmail()).append("\n")
                    .append("➡️ **Destinataire** : ").append(message.getDestinataireEmail()).append("\n")
                    .append("📝 **Sujet** : ").append(message.getSujet()).append("\n")
                    .append("🗨️ **Contenu** : ").append(message.getContenu()).append("\n")
                    .append("--------------------------------------------------\n"); // Ligne de séparation
        }
    }
    JOptionPane.showMessageDialog(frame, messages.toString(), "Messages", JOptionPane.INFORMATION_MESSAGE);
}



    private void envoyerMessage() {
        String destinataire = JOptionPane.showInputDialog(frame, "Entrez le destinataire :");
        String contenu = JOptionPane.showInputDialog(frame, "Entrez votre message :");
        String sujet = "Nouveau Message"; // Sujet par défaut

        // Crée un message
        Message message = new Message(utilisateur.getEmail(), utilisateur.getEmail(), destinataire, sujet, contenu);

        // Envoie le message via UserService
        boolean messageEnvoye = userService.envoyerMessage(message);

        // Vérifie si le message a bien été envoyé
        if (messageEnvoye) {
            JOptionPane.showMessageDialog(frame, "+ Message envoyé à " + destinataire, "Succès", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "- Échec de l'envoi du message", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deconnexion() {
        frame.dispose(); // Ferme la fenêtre
        System.out.println("Déconnexion...");
    }

    public void setVisible(boolean b) {
    }
}