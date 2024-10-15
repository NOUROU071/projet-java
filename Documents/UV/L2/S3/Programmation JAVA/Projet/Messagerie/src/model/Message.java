package model;

public class Message {
    private String expediteur; // Nom de l'expéditeur
    private String expediteurEmail; // Email de l'expéditeur
    private String destinataireEmail;
    private String destinataire;
    private String sujet;
    private String contenu;

    public Message(String expediteur, String expediteurEmail, String destinataire, String sujet, String contenu) {
        if (expediteur == null || expediteur.isEmpty()) {
            throw new IllegalArgumentException("L'expéditeur ne peut pas être vide.");
        }
        if (expediteurEmail == null || expediteurEmail.isEmpty()) {
            throw new IllegalArgumentException("L'email de l'expéditeur ne peut pas être vide.");
        }
        if (destinataire == null || destinataire.isEmpty()) {
            throw new IllegalArgumentException("Le destinataire ne peut pas être vide.");
        }
        if (sujet == null || sujet.isEmpty()) {
            throw new IllegalArgumentException("Le sujet ne peut pas être vide.");
        }
        if (contenu == null || contenu.isEmpty()) {
            throw new IllegalArgumentException("Le contenu ne peut pas être vide.");
        }

        this.expediteur = expediteur;
        this.expediteurEmail = expediteurEmail;
        this.destinataire = destinataire;
        this.sujet = sujet;
        this.contenu = contenu;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public String getExpediteurEmail() {
        return expediteurEmail;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getSujet() {
        return sujet;
    }

    public String getContenu() {
        return contenu;
    }

    @Override
    public String toString() {
        return "Message{" +
                "expediteur='" + expediteur + '\'' +
                ", expediteurEmail='" + expediteurEmail + '\'' +
                ", destinataire='" + destinataire + '\'' +
                ", sujet='" + sujet + '\'' +
                ", contenu='" + contenu + '\'' +
                '}';
    }

    // Méthode pour obtenir le destinataire sous forme de char[]
    public char[] getDestinataireEmail() {
        if (destinataireEmail == null) {
            return new char[0]; // Retourne un tableau vide si l'email est null
        }
        return destinataireEmail.toCharArray();
    }
}