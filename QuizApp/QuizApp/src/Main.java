package main;

import service.AdminService;
import service.FileService;
import service.QuizService;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        AdminService adminService = new AdminService(fileService);
        QuizService quizService = new QuizService(fileService);
        UserService userService = new UserService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("*** Main Menu: ***");
            if (userService.getLoggedInUser() == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("=> Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        userService.register();
                        break;
                    case 2:
                        userService.login();
                        break;
                    case 3:
                        System.out.println("Exiting ...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("1. Admin");
                System.out.println("2. Take Quiz");
                System.out.println("3. Logout");
                System.out.println("4. Exit");
                System.out.print("=> Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        if (userService.getLoggedInUser().isAdmin()) {
                            adminService.showAdminMenu();
                        } else {
                            System.out.println("Access denied. Admins only.");
                        }
                        break;
                    case 2:
                        quizService.takeQuiz();
                        break;
                    case 3:
                        userService.logout();
                        break;
                    case 4:
                        System.out.println("Exiting ...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
