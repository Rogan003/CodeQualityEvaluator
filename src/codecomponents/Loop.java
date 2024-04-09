package codecomponents;

public class Loop  extends CodeComponent {
    private String condition;
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Loop () {
        super();
    }

    public Loop (String name, String condition) {
        super(name);
        this.condition = condition;
    }
}
