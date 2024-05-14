package method;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalBlockStmt;
import com.github.javaparser.ast.stmt.*;

import javax.swing.plaf.nimbus.State;
import java.util.regex.*;

// Class that simulates a method/function found in the code
// Keeps the method complexity score and the method declaration,
// which is an object of MethodDeclaration class of JavaParser,
// and has all important parsed details about the method, like method name and body
// Has methods for evaluating complexity and evaluating the camelCase name convention
public class Method {
    private final MethodDeclaration declaration;
    private MethodComplexityScore complexityScore;
    public MethodComplexityScore getComplexityScore() {
        return this.complexityScore;
    }

    /**
     * Constructor that takes in the method declaration that was parsed and creates the starting complexity score
     * @param parsedDeclaration - parsed method declaration
     */
    public Method (MethodDeclaration parsedDeclaration) {
        this.declaration = parsedDeclaration;
        this.complexityScore = new MethodComplexityScore();
    }

    public String getMethodName () {
        return declaration.getName().toString();
    }

    // Evaluates method complexity through a recursive method and then calculates the final score of the method
    public void evaluateComplexity () {
        //evaluateComplexityRecursive(declaration.getBody().orElse(null));
        this.declaration.findAll(ForStmt.class).forEach(f -> this.complexityScore.incrementNumberOfLoops());
        this.declaration.findAll(ForEachStmt.class).forEach(f -> this.complexityScore.incrementNumberOfLoops());
        this.declaration.findAll(WhileStmt.class).forEach(f -> this.complexityScore.incrementNumberOfLoops());
        this.declaration.findAll(DoStmt.class).forEach(f -> this.complexityScore.incrementNumberOfLoops());
        this.declaration.findAll(IfStmt.class).forEach(f -> this.complexityScore.incrementNumberOfConditionalStatements());
        this.declaration.findAll(SwitchStmt.class).forEach(f -> this.complexityScore.incrementNumberOfConditionalStatements());

        this.complexityScore.calculateFinalScore();
    }

    // Evaluates camelCase convention through the regex pattern check
    public boolean evaluateNamingConvention () {
        String camelCaseRegex = "^[a-z]+(?:[A-Z][a-z0-9]*|[0-9]*)*";

        Pattern pattern = Pattern.compile(camelCaseRegex);

        Matcher matcher = pattern.matcher(this.getMethodName());

        // Check if the input matches the camelCase regex
        return matcher.matches();
    }
}
