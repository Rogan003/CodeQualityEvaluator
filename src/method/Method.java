package method;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalBlockStmt;
import com.github.javaparser.ast.stmt.*;

import java.util.regex.*;

// Class that simulates a method/function found in the code
// Keeps the method complexity score and the method declaration,
// which is an object of MethodDeclaration class of JavaParser,
// and it has all important parsed details about the method, like method name and body
// Has methods for evaluating complexity and evaluating the camelCase name convention
public class Method {
    private final MethodDeclaration declaration;
    private MethodComplexityScore complexityScore;
    public MethodComplexityScore getComplexityScore() {
        return this.complexityScore;
    }

    /**
     * Constructor that takes in the method declaration that was parsed and creates the starting complexity score
     * @param declaration - parsed method declaration
     */
    public Method (MethodDeclaration declaration) {
        this.declaration = declaration;
        this.complexityScore = new MethodComplexityScore();
    }

    public String getMethodName () {
        return declaration.getName().toString();
    }

    // Evaluates method complexity through a recursive method and calculates the final score of the method
    public void evaluateComplexity () {
        evaluateComplexityRecursive(declaration.getBody().orElse(null));

        this.complexityScore.calculateFinalScore();
    }

    /**
     * Recursive method to evaluate the complexity of the method
     * It should be recursive, because some of the code components (nodes) inside the method body also have
     * their own body, which can have loops and/or conditional statements (example: nested loop)
     * It goes through every code component of the given body (start: method body) and changes complexity score
     * if necessary and also calls itself recursively if that node has its own body
     * @param codeComponentBody - Body of the node we are evaluating in this call
     */
    public void evaluateComplexityRecursive (BlockStmt codeComponentBody) {
        // If the body is empty or doesn't exist, stop this method call
        if (codeComponentBody == null) return;

        // Traverse the body code components
        for (Node codeComponent : codeComponentBody.getChildNodes()) {
            boolean isLoop = codeComponent instanceof ForStmt || codeComponent instanceof WhileStmt
                    || codeComponent instanceof DoStmt || codeComponent instanceof ForEachStmt;

            // If this node is a conditional statement or a loop, increment the appropriate complexity score
            if (isLoop) {
                this.complexityScore.incrementNumberOfLoops();
            } else if (codeComponent instanceof IfStmt || codeComponent instanceof SwitchStmt) {
                this.complexityScore.incrementNumberOfConditionalStatements();
            }

            // If this node has a body, call this method recursively on that body
            if (codeComponent instanceof NodeWithOptionalBlockStmt<?>) {
                evaluateComplexityRecursive(((NodeWithOptionalBlockStmt<?>) codeComponent).getBody().orElse(null));
            } else if (codeComponent instanceof NodeWithBody<?>) {
                evaluateComplexityRecursive(((NodeWithBody<?>) codeComponent).getBody().asBlockStmt());
            }
        }
    }

    // Evaluates camelCase convention through the regex
    public boolean evaluateNamingConvention () {
        String camelCaseRegex = "^[a-z]+(?:[A-Z][a-z0-9]*|[0-9]*)*";

        Pattern pattern = Pattern.compile(camelCaseRegex);

        Matcher matcher = pattern.matcher(this.getMethodName());

        // Check if the input matches the camelCase regex
        return matcher.matches();
    }
}
