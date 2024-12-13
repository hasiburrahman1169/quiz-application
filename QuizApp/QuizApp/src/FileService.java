package service;

import model.Question;
import java.io.*;
import java.util.*;

public class FileService {
    private static final String FILE_PATH = "questions.txt";

    public List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String questionText = parts[0];
                String[] options = Arrays.copyOfRange(parts, 1, 5);
                int correctAnswerIndex = Integer.parseInt(parts[5]);
                questions.add(new Question(questionText, options, correctAnswerIndex));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File 'questions.txt' not found.");
            System.out.println("Would you like to create a new file? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                createFile();
                System.out.println("File created successfully. Please add questions.");
            } else {
                System.out.println("Exiting application.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return questions;
    }

    public void saveQuestions(List<Question> questions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Question question : questions) {
                String[] options = question.getOptions();
                bw.write(question.getQuestionText() + ";" +
                        String.join(";", options) + ";" +
                        question.getCorrectAnswerIndex());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void createFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
}
