package service;

import model.Question;
import java.util.*;

public class AdminService {
    private FileService fileService;
    private List<Question> questions;
    private Scanner scanner;

    public AdminService(FileService fileService) {
        this.fileService = fileService;
        this.questions = fileService.loadQuestions();
        this.scanner = new Scanner(System.in);
    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("*** Admin Menu: ***");
            System.out.println("1. Add Question");
            System.out.println("2. Edit Question");
            System.out.println("3. Delete Question");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("=> Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            System.out.println();

            switch (choice) {
                case 1:
                    addQuestion();
                    return;

                case 2:
                    editQuestion();
                    return;

                case 3:
                    deleteQuestion();
                    return;

                case 4:
                {
                    fileService.saveQuestions(questions);
                    return;
                }

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addQuestion() {
        System.out.print("Enter the question: ");
        String questionText = scanner.nextLine();
        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            options[i] = scanner.nextLine();
        }
        System.out.print("Enter the correct option number (1-4): ");
        int correctAnswerIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // consume newline

        questions.add(new Question(questionText, options, correctAnswerIndex));
        System.out.println("Question added successfully.");
    }

    private void editQuestion() {
        System.out.print("Enter the question number to edit: ");
        int questionNumber = scanner.nextInt() - 1;
        scanner.nextLine();  // consume newline

        if (questionNumber >= 0 && questionNumber < questions.size()) {
            Question question = questions.get(questionNumber);
            System.out.println("Editing question: " + question.getQuestionText());
            System.out.print("Enter new question text (or press Enter to keep current): ");
            String questionText = scanner.nextLine();
            if (!questionText.isEmpty()) {
                question.setQuestionText(questionText);
            }

            String[] options = question.getOptions();
            for (int i = 0; i < 4; i++) {
                System.out.print("Enter new option " + (i + 1) + " (or press Enter to keep current): ");
                String option = scanner.nextLine();
                if (!option.isEmpty()) {
                    options[i] = option;
                }
            }

            System.out.print("Enter the new correct option number (1-4, or 0 to keep current): ");
            int correctAnswerIndex = scanner.nextInt() - 1;
            scanner.nextLine();  // consume newline
            if (correctAnswerIndex >= 0 && correctAnswerIndex < 4) {
                question.setCorrectAnswerIndex(correctAnswerIndex);
            }

            System.out.println("Question updated successfully.");
        } else {
            System.out.println("Invalid question number.");
        }
    }

    private void deleteQuestion() {
        System.out.print("Enter the question number to delete: ");
        int questionNumber = scanner.nextInt() - 1;
        scanner.nextLine();  // consume newline

        if (questionNumber >= 0 && questionNumber < questions.size()) {
            questions.remove(questionNumber);
            System.out.println("Question deleted successfully.");
        } else {
            System.out.println("Invalid question number.");
        }
    }
}
