package codecomponents;

import java.util.ArrayList;
import java.util.List;

public class ConditionalStatement extends CodeComponent {
    private List<String> conditions;
    public List<String> getConditions() {
        return conditions;
    }
    public void addCondition(String condition) {
        this.conditions.add(condition);
    }

    public ConditionalStatement () {
        super();
    }

    public ConditionalStatement (String name) {
        super(name);
        this.conditions = new ArrayList<String>();
    }
}
