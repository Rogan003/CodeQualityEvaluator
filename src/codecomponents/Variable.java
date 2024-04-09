package codecomponents;

public class Variable extends CodeComponent {
    private Type type;
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    private String value;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    private boolean isFinal;
    public boolean isFinal() {
        return isFinal;
    }
    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public Variable () {
        super();
    }

    public Variable (String name, Type type, String value, boolean isFinal) {
        super(name);
        this.type = type;
        this.value = value;
        this.isFinal = isFinal;
    }
}
