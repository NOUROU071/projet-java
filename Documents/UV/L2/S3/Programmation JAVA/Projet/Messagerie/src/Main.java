import service.UserService;
import ui.ConnexionFrame;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de UserService
        UserService userService = new UserService();

        // Lancer l'interface de connexion
        new ConnexionFrame(userService); // Ouvrir l'interface de connexion
    }
}