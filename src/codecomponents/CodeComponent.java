package codecomponents;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;

import java.util.regex.*;

public class CodeComponent {
    private MethodDeclaration methodDeclaration;
    private ComponentComplexityScore componentComplexityScore;
    public ComponentComplexityScore getComponentComplexityScore () {
        return this.componentComplexityScore;
    }

    public CodeComponent (MethodDeclaration methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
        this.componentComplexityScore = new ComponentComplexityScore();
    }

    public String getMethodName() {
        return methodDeclaration.getName().toString();
    }

    public void evaluateComplexity() {
        BlockStmt methodBody = methodDeclaration.getBody().orElse(null);

        if (methodBody == null) return;

        // Traverse the body of the method
        for (Node node : methodBody.getChildNodes()) {
            boolean isLoop = node instanceof ForStmt || node instanceof WhileStmt || node instanceof DoStmt
                    || node instanceof ForEachStmt;

            if (isLoop) {
                this.componentComplexityScore.increaseNumberOfLoops();
            } else if (node instanceof IfStmt || node instanceof SwitchStmt) {
                this.componentComplexityScore.increaseNumberOfConditionalStatements();
            }
        }

        this.componentComplexityScore.calculateFinalScore();
    }

    public boolean evaluateNamingConvention() {
        String camelCaseRegex = "^[a-z]+(?:[A-Z][a-z0-9]*)*$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(camelCaseRegex);

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(this.getMethodName());

        // Check if the input matches the camel case regex
        return matcher.matches();
    }
}
