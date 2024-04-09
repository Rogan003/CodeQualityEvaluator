package codecomponents;

import componentcomplexityevaluator.ComponentComplexityEvaluator;

import java.util.ArrayList;
import java.util.List;

public abstract class CodeComponent implements ComponentComplexityEvaluator {
    private List<CodeComponent> nestedComponents;

    public CodeComponent () {
        this.nestedComponents = new ArrayList<CodeComponent>();
    }

    public abstract String toString();
}
