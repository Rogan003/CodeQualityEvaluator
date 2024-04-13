import codeparser.CodeParser;
import exceptions.NoMethodsFoundException;
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

        // Output methods with the highest complexity scores and
        // percentage of method names that aren't following camelCase
        try {
            List<Method> methodsWithHighestComplexityScore = codeParser.methodsWithHighestComplexityScores();

            int methodPosition = 1;
            System.out.println("Methods with highest complexity scores: ");
            for (Method method : methodsWithHighestComplexityScore) {
                System.out.println(STR."\{methodPosition}. method: \{method.getMethodName()}");
                System.out.println(method.getComplexityScore());

                methodPosition++;
            }
            System.out.print("\n");

            System.out.printf("Percentage of methods that do not adhere to the camelCase convention: %.2f%%\n",
                    codeParser.percentageOfMethodsNotInCamelCase());
        }
        catch (NoMethodsFoundException noMethodsFoundException) {
            System.out.println("Found number of methods for complexity and naming check is 0!");
        }
    }
}