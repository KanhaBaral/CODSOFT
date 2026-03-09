import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Get the number of subjects
        System.out.println("========== STUDENT GRADE CALCULATOR ==========");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Validate input
        if (numSubjects <= 0) {
            System.out.println("Error: Number of subjects must be greater than 0");
            scanner.close();
            return;
        }

        // Array to store marks for each subject
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        // Input: Take marks for each subject
        System.out.println("\nEnter marks (out of 100) for each subject:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();

            // Validate marks
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Error: Marks must be between 0 and 100. Please try again.");
                i--;
                continue;
            }

            totalMarks += marks[i];
        }

        // Calculate: Average Percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Calculate: Grade based on average percentage
        char grade = calculateGrade(averagePercentage);

        // Display: Results
        System.out.println("\n========== RESULTS ==========");
        System.out.println("Total Marks: " + totalMarks + "/" + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
        System.out.println("=============================\n");

        scanner.close();
    }

    // Method to calculate grade based on average percentage
    public static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
