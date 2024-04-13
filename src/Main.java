import codeparser.CodeParser;
import method.Method;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Insert the path to the directory with java files (write default for the default directory): ");

        Scanner scanner = new Scanner(System.in);
        String directoryPath = scanner.nextLine();

        System.out.print("\n");

        // Assigning the default path
        if (directoryPath.equalsIgnoreCase("default")) {
            directoryPath = "data/projekat_sv_36_2022";
        }

        File directory = new File(directoryPath);

        // Ending the program and printing the message if the directory doesn't exist
        if (!directory.exists()) {
            System.out.println("Directory doesn't exist!");
            return;
        }

        CodeParser codeParser = new CodeParser(directory);

        // Running the 2 important output methods
        try {
            List<Method> methodsWithHighestComplexityScore = codeParser.methodsWithHighestComplexityScores();

            int methodPlacement = 1;
            System.out.println("Methods with highest complexity scores: ");
            for (Method method : methodsWithHighestComplexityScore) {
                System.out.println(STR."\{methodPlacement + 1}. method: \{method.getMethodName()}");
                System.out.println(method.getComplexityScore());

                methodPlacement++;
            }
            System.out.print("\n");
        }
        catch (Exception e) {
            System.out.println("Found number of methods for complexity check is 0!");
        }

        try {
            System.out.printf("Percentage of methods that do not adhere to the camelCase convention: %.2f%%",
                    codeParser.percentageOfMethodsNotInCamelCase());
        }
        catch (Exception e) {
            System.out.println("Found number of methods for naming check is 0!");
        }
    }
}