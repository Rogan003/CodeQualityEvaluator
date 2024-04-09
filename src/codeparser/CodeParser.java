package codeparser;

import codecomponents.CodeComponent;

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
        for(CodeComponent codeComponent : codeComponents)
        {
            // Does codeComponents have all components or are some nested? Think about that
            // Maybe it isnt important here because we need only methods?
        }

        // Output percentage
    }
}
