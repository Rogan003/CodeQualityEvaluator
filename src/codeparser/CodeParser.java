package codeparser;

import codecomponents.Method;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

// The most important class - CodeParser
// Keeps the list of methods found in all the java files in the given directory
public class CodeParser {
    private List<Method> methods;

    /**
     * Constructor - finds all the java files inside the given directory and parses them
     * @param directory - directory which has the java files that should be evaluated
     */
    public CodeParser (File directory) {
        this.methods = new ArrayList<Method>();

        List<String> javaFilePaths = new ArrayList<String>();
        getAllJavaFiles(directory, javaFilePaths);

        for (String filePath : javaFilePaths) {
            parseFile(filePath);
        }
    }

    /**
     * Method that adds all the java file paths from the given directory,
     * using recursive calls to get all the files inside every nested directory too
     * @param directory - directory that is being checked
     * @param javaFilesPaths - list of all the found java file paths
     */
    private void getAllJavaFiles (File directory, List<String> javaFilesPaths) {
        File[] filesAndDirectories = directory.listFiles();

        if (filesAndDirectories == null) return;

        for(File directoryOrFile : filesAndDirectories) {
            String directoryOrFilePath = directoryOrFile.getAbsolutePath();

            if (directoryOrFile.isFile() && directoryOrFilePath.endsWith(".java")) {
                javaFilesPaths.add(directoryOrFilePath);
            }
            else if (directoryOrFile.isDirectory()) {
                getAllJavaFiles(directoryOrFile, javaFilesPaths);
            }
        }
    }

    /**
     * Method for parsing the file on the given file path
     * Firstly the file goes through preprocessing, then it is being parsed by the JavaParser
     * and all the methods found in the file are added to the list of methods
     * @param filePath - path to the file
     */
    private void parseFile(String filePath) {
        try {
            String preprocessedFileContent = preprocessFile(filePath);
            CompilationUnit compilationUnit = StaticJavaParser.parse(preprocessedFileContent);

            compilationUnit.findAll(MethodDeclaration.class).forEach(f -> this.methods.add(new Method(f)));
        } catch (Exception e) {
            System.out.printf("ERROR: Error while parsing input file: %s!\n\n", filePath);
        }
    }

    /**
     * Preprocessing erases all found string templates,
     * because JavaParser cannot currently parse them
     * and they are not playing any role in this program
     * @param filePath - path to the file that should be preprocessed
     * @return String that has the preprocessed file content
     */
    private String preprocessFile(String filePath) {
        String fileContent = getFileContent(filePath);
        return this.eraseStringTemplates(fileContent);
    }

    /**
     * @param filePath - path of the file whose content are needed
     * @return String that has all the content from the file on the given file path
     */
    private String getFileContent (String filePath) {
        FileInputStream fis = null;
        StringBuilder contents = new StringBuilder();

        try {
            fis = new FileInputStream(filePath);

            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                contents.append((char) byteRead);
            }
            fis.close();
        }
        catch (IOException e) {
            System.out.printf("ERROR: Error while preprocessing file: %s!\n\n", filePath);
            return "";
        }

        return contents.toString();
    }

    /**
     * Method that is erasing all the string templates from given java file content using regex
     * @param fileContent - java file content
     * @return Java file content without string templates
     */
    private String eraseStringTemplates(String fileContent) {
        String regexForStringTemplates = "STR\\.\".*\"";

        Pattern pattern = Pattern.compile(regexForStringTemplates);

        // Replacing all the string templates found with regex in the file content with empty string
        // and returning file content
        return pattern.matcher(fileContent).replaceAll("\"\"");
    }

    // Method that sorts all the methods based on their complexity scores
    // and outputs 3 methods with the highest scores
    public void methodsWithHighestComplexityScores() {
        final int numberOfOutputs = Math.min(this.methods.size(), 3);

        // Evaluate every methods complexity score
        for (Method method : this.methods) {
            method.evaluateComplexity();
        }

        // Sort the methods based on their complexity scores
        this.methods.sort(Comparator.comparingInt(o -> ((Method) o).getMethodComplexityScore().getFinalScore()).reversed());

        // Output
        if (numberOfOutputs > 0) {
            System.out.println("Methods with highest complexity scores: ");
            for (int i = 0;i < numberOfOutputs;i++) {
                Method method = this.methods.get(i);
                System.out.println(STR."\{i + 1}. method: \{method.getMethodName()}");
                System.out.println(method.getMethodComplexityScore());
            }
            System.out.print("\n");
        }
        else {
            System.out.println("Found number of methods for complexity check is 0!");
        }
    }

    // Method that calculates and outputs the percentage of methods that do not adhere to the camelCase convention
    public void methodsNotInCamelCase() {
        int numberOfMethods = this.methods.size();
        int numberOfInvalidMethodNames = 0;

        for(Method method : this.methods) {
            if (!method.evaluateNamingConvention()) {
                numberOfInvalidMethodNames++;
            }
        }

        if (numberOfMethods > 0) {
            System.out.printf("Percentage of methods that do not adhere to the camelCase convention: %.2f%%",
                    ( (double) numberOfInvalidMethodNames / (double) numberOfMethods) * 100);
        }
        else {
            System.out.println("Found number of methods for naming check is 0!");
        }
        System.out.print("\n");
    }
}
