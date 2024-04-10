package codecomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public abstract class CodeComponent {
    protected List<CodeComponent> nestedComponents;

    protected ComponentComplexityScore componentComplexityScore;
    public ComponentComplexityScore getComponentComplexityScore () {
        return this.componentComplexityScore;
    }

    protected String name;
    public String getName () {
        return name;
    }

    public CodeComponent () {
        this.nestedComponents = new ArrayList<CodeComponent>();
        this.componentComplexityScore = new ComponentComplexityScore();
    }

    public CodeComponent (String name) {
        this();
        this.name = name;
    }

    public void evaluateComplexity() {
        this.evaluateComplexityRecursive(this.componentComplexityScore);
    }

    public void evaluateComplexityRecursive(ComponentComplexityScore componentComplexityScore) {

    }

    public boolean evaluateNamingConvention() {
        String camelCaseRegex = "^[a-z]+(?:[A-Z][a-z0-9]*)*$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(camelCaseRegex);

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(this.name);

        // Check if the input matches the camel case regex
        return matcher.matches();
    }
}
