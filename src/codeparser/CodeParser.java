package codeparser;

import codecomponents.Method;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CodeParser {
    private List<Method> methods;

    public CodeParser (File directory) {
        this.methods = new ArrayList<Method>();

        List<String> javaFiles = new ArrayList<String>();
        getAllJavaFiles(directory, javaFiles);

        for (String filePath : javaFiles) {
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
            CompilationUnit compilationUnit = StaticJavaParser.parse(new FileInputStream(filePath));

            compilationUnit.findAll(MethodDeclaration.class).forEach(f -> this.methods.add(new Method(f)));

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (Exception e) {
            System.out.println("Error while parsing input files!\n");
        }
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
