package codeparser;

import codecomponents.CodeComponent;
import codecomponents.Method;
import codecomponents.Variable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CodeParser {
    private List<CodeComponent> codeComponents;

    public CodeParser (String directoryPath) {
        this.codeComponents = new ArrayList<CodeComponent>();

        List<String> javaFiles = new ArrayList<String>();
        getAllJavaFiles(directoryPath, javaFiles);

        for (String filePath : javaFiles) {
            parseFile(filePath);
        }
    }

    // Adds all the java file paths from the given directoryPath
    // uses recursive calls to get all the files inside the given directory
    private void getAllJavaFiles (String directoryPath, List<String> javaFiles) {
        File directory = new File(directoryPath);

        File[] filesAndDirectories = directory.listFiles();

        if (filesAndDirectories == null) return;

        for(File directoryOrFile : filesAndDirectories) {
            String directoryOrFilePath = directoryOrFile.getAbsolutePath();

            if (directoryOrFile.isFile() && directoryOrFilePath.endsWith(".java")) {
                javaFiles.add(directoryOrFilePath);
            }
            else if (directoryOrFile.isDirectory()) {
                getAllJavaFiles(directoryOrFilePath, javaFiles);
            }
        }
    }

    private void parseFile(String filePath) {

    }

    public void MethodsWithHighestComplexityScores () {
        for (CodeComponent codeComponent : codeComponents) {

        }

        // Output 3 methods with highest complexity scores
    }

    public void MethodsNotInCamelCase () {
        double numberOfMethods = 0, numberOfInvalidMethodNames = 0;
        for(CodeComponent codeComponent : codeComponents) {
            if (codeComponent.getClass() == Method.class || codeComponent.getClass() == Variable.class) {
                numberOfMethods++;

                if (!codeComponent.EvaluateNamingConvention()) {
                    numberOfInvalidMethodNames++;
                }
            }
        }

        if (numberOfMethods > 0) {
            System.out.println(STR."Percentage of methods that do not adhere to the camelCase convention: \{
                    (numberOfInvalidMethodNames / numberOfMethods) * 100}");
        }
        else {
            System.out.println("Found number of methods is 0!");
        }
    }
}
