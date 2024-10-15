package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;
import service.*;

public class MessagerieFrame {
    private JFrame frame;
    private Utilisateur utilisateur;
    private UserService userService; // Ajout d'un UserService
    private boolean isReceivingMessages; // true pour voir les messages reçus, false pour envoyer

    public MessagerieFrame(Utilisateur utilisateur, UserService userService, boolean isReceivingMessages) {
        this.utilisateur = utilisateur;
        this.userService = userService; // Initialisation du UserService
        this.isReceivingMessages = isReceivingMessages;
        initialize();
    }

    private void initialize() {
        frame = new JFrame(isReceivingMessages ? "Messages Reçus" : "Envoyer un Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        if (isReceivingMessages) {
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            StringBuilder messages = new StringBuilder("Messages reçus :\n");
            for (Message message : utilisateur.getMessagesRecus()) {
                messages.append("De : ").append(message.getExpediteurEmail()).append("\n")
                        .append("Contenu : ").append(message.getContenu()).append("\n\n");
            }
            textArea.setText(messages.toString());
            frame.add(new JScrollPane(textArea));
        } else {
            JPanel panel = new JPanel(new GridLayout(3, 2));
            JLabel destinataireLabel = new JLabel("Destinataire :");
            JTextField destinataireField = new JTextField();
            JLabel contenuLabel = new JLabel("Message :");
            JTextArea contenuArea = new JTextArea();
            JButton envoyerButton = new JButton("Envoyer");

            panel.add(destinataireLabel);
            panel.add(destinataireField);
            panel.add(contenuLabel);
            panel.add(contenuArea);
            panel.add(envoyerButton);

            frame.add(panel);

            envoyerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String destinataire = destinataireField.getText();
                    String contenu = contenuArea.getText();
                    String sujet = "Nouveau Message"; // Sujet par défaut

                    // Crée un message
                    Message message = new Message(utilisateur.getEmail(), utilisateur.getEmail(), destinataire, sujet, contenu);
                    userService.envoyerMessage(message); // Envoie le message

                    // Notification de succès
                    JOptionPane.showMessageDialog(frame, "Message envoyé à " + destinataire, "Succès", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            });
        }

        frame.setVisible(true);
    }
}