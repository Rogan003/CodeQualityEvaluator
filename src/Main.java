import codeparser.CodeParser;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Insert the path to the directory with java files (write default for the default): ");

        Scanner scanner = new Scanner(System.in);
        String directoryPath = scanner.nextLine();

        if (directoryPath.equalsIgnoreCase("default")) {
            directoryPath = "src/data/projekat_sv_36_2022";
        }

        File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.out.println("Directory doesn't exist!");
            return;
        }

        CodeParser codeParser = new CodeParser(directory);

        codeParser.methodsWithHighestComplexityScores();
        codeParser.methodsNotInCamelCase();
    }
}