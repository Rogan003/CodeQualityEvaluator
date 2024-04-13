package codeparser;

import exceptions.NoMethodsFoundException;
import method.Method;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

// The core class - CodeParser
// Keeps the list of methods found in all the java files in the given directory
// Has methods to get all the java files from the given directory, parse them,
// add all the methods to his list of methods,
// get the three most complex methods and get percentage of method names that are not in camelCase
public class CodeParser {
    private List<Method> methods;

    /**
     * Constructor - finds all the java files inside the given directory, parses them
     * and finds and adds all the methods from them
     * @param directory - directory which has the java files that should be evaluated
     */
    public CodeParser (File directory) {
        this.methods = new ArrayList<Method>();

        List<String> javaFilesPaths = new ArrayList<String>();
        getAllJavaFilesPaths(directory, javaFilesPaths);

        for (String filePath : javaFilesPaths) {
            parseFile(filePath);
        }
    }

    /**
     * Method that adds all the java file paths from the given directory,
     * using recursive calls to get all the files inside every nested directory too
     * @param directory - directory that is being checked
     * @param javaFilesPaths - list of all the found java file paths
     */
    private void getAllJavaFilesPaths (File directory, List<String> javaFilesPaths) {
        File[] filesAndDirectories = directory.listFiles();

        if (filesAndDirectories == null) return;

        for(File directoryOrFile : filesAndDirectories) {
            String directoryOrFilePath = directoryOrFile.getAbsolutePath();

            if (directoryOrFile.isFile() && directoryOrFilePath.endsWith(".java")) {
                javaFilesPaths.add(directoryOrFilePath);
            }
            else if (directoryOrFile.isDirectory()) {
                getAllJavaFilesPaths(directoryOrFile, javaFilesPaths);
            }
        }
    }

    /**
     * Method for parsing the file on the given file path and adding all the methods from that file
     * to the list of methods
     * Firstly the file goes through preprocessing, then it is being parsed by the JavaParser
     * and all the methods found in the file are added to the list of methods
     * @param filePath - path to the file
     */
    private void parseFile (String filePath) {
        String preprocessedFileContent = preprocessFile(filePath);
        CompilationUnit compilationUnit = StaticJavaParser.parse(preprocessedFileContent);

        compilationUnit.findAll(MethodDeclaration.class).forEach(f -> this.methods.add(new Method(f)));
    }

    /**
     * Preprocessing reads and stores all the java code as a string, and erases all found string templates,
     * because JavaParser cannot currently parse them
     * and they are not important for this program
     * @param filePath - path to the file that should be preprocessed
     * @return String that has the preprocessed file content
     */
    private String preprocessFile (String filePath) {
        String fileContent = getFileContent(filePath);
        return this.eraseStringTemplates(fileContent);
    }

    /**
     * @param filePath - path of the file whose content are needed
     * @return String that has all the content from the file on the given file path
     */
    private String getFileContent (String filePath) {
        FileInputStream fileInputStream;
        StringBuilder fileContent = new StringBuilder();

        try {
            fileInputStream = new FileInputStream(filePath);

            int byteRead;
            while ((byteRead = fileInputStream.read()) != -1) {
                fileContent.append((char) byteRead);
            }
            fileInputStream.close();
        }
        catch (IOException e) {
            System.out.printf("WARNING: Preprocessing file failed: %s!\n\n", filePath);
            return "";
        }

        return fileContent.toString();
    }

    /**
     * Method that is erasing all the string templates from the given java file content using regex
     * @param fileContent - java file content
     * @return Java file content without string templates
     */
    private String eraseStringTemplates (String fileContent) {
        String regexForStringTemplates = "STR\\.\".*\"";

        Pattern pattern = Pattern.compile(regexForStringTemplates);

        // Replacing all the string templates found with regex in the file content with empty string
        // and returning file content
        return pattern.matcher(fileContent).replaceAll("\"\"");
    }

    /**
     * Method that sorts all the methods based on their complexity scores
     * and returns 3 methods with the highest scores (the amount, 3, can be changed through code)
     * @return List of 3 methods with the highest complexity scores
     * @throws NoMethodsFoundException - Exception for when no methods are found in java files
     */
    public List<Method> methodsWithHighestComplexityScores () throws NoMethodsFoundException {
        if (this.methods.isEmpty()) {
            throw new NoMethodsFoundException();
        }

        final int numberOfOutputs = Math.min(this.methods.size(), 3);

        // Evaluate complexity score for every method
        for (Method method : this.methods) {
            method.evaluateComplexity();
        }

        // Sort the methods based on their complexity scores
        this.methods.sort(Comparator.comparingInt(method ->
                ((Method) method).getComplexityScore().getFinalScore()).reversed());

        List<Method> methodsWithHighestComplexityScores = new ArrayList<Method>();

        for (int i = 0;i < numberOfOutputs;i++) {
            Method method = this.methods.get(i);
            methodsWithHighestComplexityScores.add(method);
        }

        return methodsWithHighestComplexityScores;
    }

    /**
     * Method that calculates and outputs the percentage of methods that do not adhere to the camelCase convention
     * @return Percentage of methods that do not adhere to the camelCase convention
     * @throws NoMethodsFoundException - Exception for when no methods are found in java files
     */
    public double percentageOfMethodsNotInCamelCase () throws NoMethodsFoundException {
        int numberOfMethods = this.methods.size();

        if (numberOfMethods == 0) {
            throw new NoMethodsFoundException();
        }

        int numberOfInvalidMethodNames = 0;

        // Calculating the amount of invalid method names
        for(Method method : this.methods) {
            if (!method.evaluateNamingConvention()) {
                numberOfInvalidMethodNames++;
            }
        }

        return ((double) numberOfInvalidMethodNames / (double) numberOfMethods) * 100;
    }
}
