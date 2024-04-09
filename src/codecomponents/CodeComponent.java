package codecomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public abstract class CodeComponent {
    protected List<CodeComponent> nestedComponents;
    protected String name;

    public String getName () {
        return name;
    }

    public CodeComponent () {
        this.nestedComponents = new ArrayList<CodeComponent>();
    }

    public CodeComponent (String name) {
        this();
        this.name = name;
    }

    public ComponentComplexityScore EvaluateComplexity () {
        ComponentComplexityScore componentComplexityScore = new ComponentComplexityScore();

        // Recursive algorithm that is going to go through every nested component and get the component complexity score

        return componentComplexityScore;
    }

    public boolean EvaluateNamingConvention () {
        String camelCaseRegex = "^[a-z]+(?:[A-Z][a-z]*)*$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(camelCaseRegex);

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(this.name);

        // Check if the input matches the camel case regex
        return matcher.matches();
    }
}
