package codecomponents;

public class MethodComplexityScore {
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

    private int finalScore;
    public int getFinalScore () {
        return this.finalScore;
    }
    public void calculateFinalScore () {
        // for now its just this simple calculation
        this.finalScore = this.numberOfLoops + this.numberOfConditionalStatements;
    }

    public MethodComplexityScore() {
        this.numberOfClasses = 0;
        this.numberOfComments = 0;
        this.numberOfConditionalStatements = 0;
        this.numberOfLoops = 0;
        this.numberOfMethods = 0;
        this.numberOfVariables = 0;
        this.finalScore = 0;
    }

    public String toString() {
        String outputFinalScore = STR."\tFinal score: \{this.finalScore}\n";
        String outputNumberOfClasses = STR."\tNumber of classes: \{this.numberOfClasses}\n";
        String outputNumberOfComments = STR."\tNumber of comments: \{this.numberOfComments}\n";
        String outputNumberOfConditionalStatements = STR."\tNumber of ifs/switches: \{this.numberOfConditionalStatements}\n";
        String outputNumberOfLoops = STR."\tNumber of loops: \{this.numberOfLoops}\n";
        String outputNumberOfMethods = STR."\tNumber of methods: \{this.numberOfMethods}\n";
        String outputNumberOfVariables = STR."\tNumber of variables: \{this.numberOfVariables}\n";

        return STR."\{outputFinalScore}\{outputNumberOfLoops}\{outputNumberOfConditionalStatements}" +
        STR."\{outputNumberOfClasses}\{outputNumberOfComments}\{outputNumberOfMethods}\{outputNumberOfVariables}";
    }
}
