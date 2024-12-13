package service;

import model.User;
import java.util.*;

public class UserService {
    private List<User> users;
    private User loggedInUser;
    private Scanner scanner;

    public UserService() {
        this.users = new ArrayList<>();
        this.loggedInUser = null;
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Is this an admin account? (yes/no): ");
        boolean isAdmin = scanner.nextLine().equalsIgnoreCase("yes");

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please try a different username.");
                return;
            }
        }

        users.add(new User(username, password, isAdmin));
        System.out.println("Registration successful.");
    }

    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful. Welcome, " + username + "!");
                return;
            }
        }

        System.out.println("Invalid username or password. Please try again.");
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println("Logged out successfully.");
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
