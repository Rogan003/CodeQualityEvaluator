package codecomponents;

public class ComponentComplexityScore {
    private int numberOfClasses;
    public int getNumberOfClasses () {
        return this.numberOfClasses;
    }
    public void increaseNumberOfClasses () {
        this.numberOfClasses++;
    }

    private int numberOfComments;
    public int getNumberOfComments () {
        return this.numberOfComments;
    }
    public void increaseNumberOfComments () {
        this.numberOfComments++;
    }

    private int numberOfConditionalStatements;
    public int getNumberOfConditionalStatements () {
        return this.numberOfConditionalStatements;
    }
    public void increaseNumberOfConditionalStatements () {
        this.numberOfConditionalStatements++;
    }

    private int numberOfLoops;
    public int getNumberOfLoops () {
        return this.numberOfLoops;
    }
    public void increaseNumberOfLoops () {
        this.numberOfLoops++;
    }

    private int numberOfMethods;
    public int getNumberOfMethods () {
        return this.numberOfMethods;
    }
    public void increaseNumberOfMethods () {
        this.numberOfMethods++;
    }

    private int numberOfVariables;
    public int getNumberOfVariables () {
        return this.numberOfVariables;
    }
    public void increaseNumberOfVariables () {
        this.numberOfVariables++;
    }

    public ComponentComplexityScore() {
        this.numberOfClasses = 0;
        this.numberOfComments = 0;
        this.numberOfConditionalStatements = 0;
        this.numberOfLoops = 0;
        this.numberOfMethods = 0;
        this.numberOfVariables = 0;
    }
}
