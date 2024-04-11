package codeparser;

import codecomponents.CodeComponent;
import codecomponents.Method;
import codecomponents.Variable;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
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
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(new FileInputStream(filePath));

            compilationUnit.findAll(MethodDeclaration.class).forEach(this::CountConditionalStatements);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void CountConditionalStatements (MethodDeclaration method) {
        BlockStmt methodBody = method.getBody().orElse(null);

        if (methodBody == null) return;

        int loopCount = 0;
        int conditionalStatementCount = 0;

        // Traverse the body of the method
        for (Node node : methodBody.getChildNodes()) {
            if (node instanceof ForStmt || node instanceof WhileStmt || node instanceof DoStmt) {
                loopCount++;
            } else if (node instanceof IfStmt || node instanceof SwitchStmt) {
                conditionalStatementCount++;
            }
        }

        // Print the method name and counts
        System.out.println(STR."Method: \{method.getName()}");
        System.out.println(STR."Loops: \{loopCount}");
        System.out.println(STR."Conditional statements: \{conditionalStatementCount}");
        System.out.println();
    }

    public void methodsWithHighestComplexityScores() {
        final int numberOfOutputs = Math.min(this.codeComponents.size(), 3);

        for (CodeComponent codeComponent : this.codeComponents) {
            codeComponent.evaluateComplexity();
        }

        // is it smart to sort this list? I think it doesn't matter at least for this program
        this.codeComponents.sort(Comparator.comparingInt(o -> o.getComponentComplexityScore().getFinalScore()));

        System.out.println("Components with highest complexity scores: ");
        for (int i = 0;i < numberOfOutputs;i++) {
            CodeComponent component = this.codeComponents.get(i);
            System.out.println(STR."\{i}. component: \{component.getClass()} \{component.getName()}");
            System.out.println(component.getComponentComplexityScore());
        }
        System.out.print("\n");
    }

    public void methodsNotInCamelCase() {
        // change the name if you stay with variable check
        double numberOfMethods = 0, numberOfInvalidMethodNames = 0;
        for(CodeComponent codeComponent : this.codeComponents) {
            if (codeComponent.getClass() == Method.class || codeComponent.getClass() == Variable.class) {
                numberOfMethods++;

                if (!codeComponent.evaluateNamingConvention()) {
                    numberOfInvalidMethodNames++;
                }
            }
        }

        if (numberOfMethods > 0) {
            System.out.println(STR."Percentage of methods and variables that do not adhere to the camelCase convention: \{
                    (numberOfInvalidMethodNames / numberOfMethods) * 100}");
        }
        else {
            System.out.println("Found number of methods and variables is 0!");
        }
        System.out.print("\n");
    }
}
