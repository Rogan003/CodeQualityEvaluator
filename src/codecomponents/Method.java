package codecomponents;

import java.util.ArrayList;
import java.util.List;

public class Method extends CodeComponent {
    private Type returnType;
    public Type getReturnType() {
        return returnType;
    }
    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    private List<Variable> parameters;
    public List<Variable> getParameters() {
        return parameters;
    }
    public void setParameters(List<Variable> parameters) {
        this.parameters = parameters;
    }

    public Method () {
        super();
        this.parameters = new ArrayList<Variable>();
    }

    public Method (String name, Type returnType, List<Variable> parameters) {
        super(name);
        this.returnType = returnType;
        this.parameters = parameters; // reference? shouldnt be a problem since I wont operate with the other var?
    }
}
