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

public class CodeParser {
    private List<Method> methods;

    public CodeParser (File directory) {
        this.methods = new ArrayList<Method>();

        List<String> javaFilePaths = new ArrayList<String>();
        getAllJavaFiles(directory, javaFilePaths);

        for (String filePath : javaFilePaths) {
            parseFile(filePath);
        }
    }

    // Adds all the java file paths from the given directoryPath
    // uses recursive calls to get all the files inside the given directory
    private void getAllJavaFiles (File directory, List<String> javaFiles) {
        File[] filesAndDirectories = directory.listFiles();

        if (filesAndDirectories == null) return;

        for(File directoryOrFile : filesAndDirectories) {
            String directoryOrFilePath = directoryOrFile.getAbsolutePath();

            if (directoryOrFile.isFile() && directoryOrFilePath.endsWith(".java")) {
                javaFiles.add(directoryOrFilePath);
            }
            else if (directoryOrFile.isDirectory()) {
                getAllJavaFiles(directoryOrFile, javaFiles);
            }
        }
    }

    private void parseFile(String filePath) {
        try {
            String preprocessedFileContent = preprocessFile(filePath);
            CompilationUnit compilationUnit = StaticJavaParser.parse(preprocessedFileContent);

            compilationUnit.findAll(MethodDeclaration.class).forEach(f -> this.methods.add(new Method(f)));
        } catch (Exception e) {
            System.out.printf("ERROR: Error while parsing input file: %s!\n\n", filePath);
        }
    }

    private String preprocessFile(String filePath) {
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

        String regexForStringTemplates = "STR\\.\".*\"";

        Pattern pattern = Pattern.compile(regexForStringTemplates);

        String preprocessedFileContents = pattern.matcher(contents).replaceAll("\"\"");

        return preprocessedFileContents;
    }

    public void methodsWithHighestComplexityScores() {
        final int numberOfOutputs = Math.min(this.methods.size(), 3);

        for (Method method : this.methods) {
            method.evaluateComplexity();
        }

        this.methods.sort(Comparator.comparingInt(o -> ((Method) o).getMethodComplexityScore().getFinalScore()).reversed());

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
