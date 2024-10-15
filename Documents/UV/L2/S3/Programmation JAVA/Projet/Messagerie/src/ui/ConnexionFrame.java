package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.UserService;
import model.Utilisateur;

public class ConnexionFrame {
    private JFrame frame;
    private UserService userService;

    public ConnexionFrame(UserService userService) {
        this.userService = userService;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Connexion / Création de Compte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300); // Augmenté pour faire de la place
        frame.setLayout(new GridLayout(6, 2)); // Ajusté pour le nombre de champs

        // Champs pour le nom
        JLabel nomLabel = new JLabel("Nom :");
        JTextField nomField = new JTextField(); // Champ pour le nom
        // Champs pour l'email
        JLabel emailLabel = new JLabel("Email :");
        JTextField emailField = new JTextField();
        // Champs pour le mot de passe
        JLabel motDePasseLabel = new JLabel("Mot de passe :");
        JPasswordField motDePasseField = new JPasswordField();
        // Boutons
        JButton loginButton = new JButton("Se connecter");
        JButton createAccountButton = new JButton("Créer un compte");

        // Ajout des éléments au frame
        frame.add(nomLabel);
        frame.add(nomField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(motDePasseLabel);
        frame.add(motDePasseField);
        frame.add(loginButton);
        frame.add(createAccountButton);

        // Action pour le bouton de connexion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String motDePasse = new String(motDePasseField.getPassword());
                Utilisateur utilisateur = userService.seConnecter(email, motDePasse);
                if (utilisateur != null) {
                    frame.dispose();
                    new MainFrame(userService, utilisateur);
                } else {
                    JOptionPane.showMessageDialog(frame, "Échec de la connexion.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action pour le bouton de création de compte
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String motDePasse = new String(motDePasseField.getPassword());
                String nom = nomField.getText(); // Récupérer le nom

                // Validation des champs
                if (email.isEmpty() || motDePasse.isEmpty() || nom.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    userService.creerCompte(nom, email, motDePasse); // Appel à la méthode de création de compte
                    JOptionPane.showMessageDialog(frame, "Compte créé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de la création du compte : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}