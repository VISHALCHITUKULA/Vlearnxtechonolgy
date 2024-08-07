package Vilearnx;

import java.util.ArrayList;
import java.util.Scanner;

public class TASK2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();
        while (true) {
            System.out.print("Enter a grade (and next type 'done' to finish): ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                double grade = Double.parseDouble(input);
                if (grade < 0 || grade > 100) {
                    System.out.println("Please enter a grade between 0 and 100.");
                } else {
                    grades.add(grade);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical grade or 'done'.");
            }
        }

        double average = calculateAverage(grades);
        char letterGrade = calculateLetterGrade(average);
        double gpa = calculateGPA(average);

        System.out.printf("Average Grade: %.2f%n", average);
        System.out.println("Letter Grade: " + letterGrade);
        System.out.printf("GPA: %.2f%n", gpa);

        scanner.close();
    }

    private static double calculateAverage(ArrayList<Double> grades) {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    private static char calculateLetterGrade(double average) {
        if (average >= 90) {
            return 'A';
        } else if (average >= 80) {
            return 'B';
        } else if (average >= 70) {
            return 'C';
        } else if (average >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    private static double calculateGPA(double average) {
        if (average >= 90) {
            return 4.0;
        } else if (average >= 80) {
            return 3.0;
        } else if (average >= 70) {
            return 2.0;
        } else if (average >= 60) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
