package codecomponents;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalBlockStmt;
import com.github.javaparser.ast.stmt.*;

import java.util.regex.*;

// Class that simulates a method/function found in the code
// Keeps the method complexity score and the method declaration,
// which is an object of MethodDeclaration class of JavaParser
// and it has all important parsed details about the method
// Has methods for evaluating complexity and evaluating the camelCase name convention
public class Method {
    private MethodDeclaration methodDeclaration;
    private MethodComplexityScore methodComplexityScore;
    public MethodComplexityScore getMethodComplexityScore () {
        return this.methodComplexityScore;
    }

    /**
     * Constructor that takes in the method declaration that was parsed and creates the starting complexity score
     * @param methodDeclaration - parsed method declaration
     */
    public Method (MethodDeclaration methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
        this.methodComplexityScore = new MethodComplexityScore();
    }

    public String getMethodName () {
        return methodDeclaration.getName().toString();
    }

    // Evaluates method complexity through a recursive method and calculates the final score of the method
    public void evaluateComplexity () {
        evaluateComplexityRecursive(methodDeclaration.getBody().orElse(null));

        this.methodComplexityScore.calculateFinalScore();
    }

    /**
     * Recursive method to evaluate the complexity of the method
     * It should be recursive, because some of the code components (nodes) inside the method body also have
     * their own body, which can have loops and/or conditional statements (example: nested loop)
     * It goes through every node of the given body (start: method body) and changes complexity score if necessary
     * and also calls itself recursively if that node has its own body
     * @param codeComponentBody - Body of the node we are evaluating in this call
     */
    public void evaluateComplexityRecursive (BlockStmt codeComponentBody) {
        // If the body is empty or doesn't exist, stop this method call
        if (codeComponentBody == null) return;

        // Traverse the body nodes
        for (Node node : codeComponentBody.getChildNodes()) {
            boolean isLoop = node instanceof ForStmt || node instanceof WhileStmt || node instanceof DoStmt
                    || node instanceof ForEachStmt;

            // If this node is a conditional statement or a loop, increment the appropriate complexity score
            if (isLoop) {
                this.methodComplexityScore.incrementNumberOfLoops();
            } else if (node instanceof IfStmt || node instanceof SwitchStmt) {
                this.methodComplexityScore.incrementNumberOfConditionalStatements();
            }

            // If this node has a body, call this method recursively on that body
            if (node instanceof NodeWithOptionalBlockStmt<?>) {
                evaluateComplexityRecursive(((NodeWithOptionalBlockStmt<?>) node).getBody().orElse(null));
            } else if (node instanceof NodeWithBody<?>) {
                evaluateComplexityRecursive(((NodeWithBody<?>) node).getBody().asBlockStmt());
            }
        }
    }

    // Evaluates camelCase convention through the regex
    public boolean evaluateNamingConvention () {
        String camelCaseRegex = "^[a-z]+(?:[A-Z][a-z0-9]*|[0-9]*)*";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(camelCaseRegex);

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(this.getMethodName());

        // Check if the input matches the camelCase regex
        return matcher.matches();
    }
}
