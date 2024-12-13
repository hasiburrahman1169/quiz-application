package service;

import model.Question;
import java.util.*;

public class QuizService {
    private FileService fileService;
    private List<Question> questions;
    private Scanner scanner;

    public QuizService(FileService fileService) {
        this.fileService = fileService;
        this.questions = fileService.loadQuestions();
        this.scanner = new Scanner(System.in);
    }

    public void takeQuiz() {
        int score = 0;
        for (Question question : questions) {
            showQuestion(question);
            System.out.println();
            System.out.print("=> Your answer (1-4): ");
            int userAnswer = scanner.nextInt() - 1;
            System.out.println();

            if (userAnswer == question.getCorrectAnswerIndex()) {
                score++;
            }
        }
        showResults(score, questions.size());
    }

    private void showQuestion(Question question) {
        System.out.println(question.getQuestionText());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    private void showResults(int score, int totalQuestions) {
        System.out.println();
        System.out.println("Your Quiz Is Completed!");
        System.out.println();
        System.out.println("Your Score: " + score + " out of " + totalQuestions);
        double percentage = ((double) score / totalQuestions) * 100;
        System.out.println("Percentage: " + percentage + "%");
    }
}
