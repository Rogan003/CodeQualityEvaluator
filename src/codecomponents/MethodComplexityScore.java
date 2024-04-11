package codecomponents;

public class MethodComplexityScore {
    private int numberOfConditionalStatements;
    public void increaseNumberOfConditionalStatements () {
        this.numberOfConditionalStatements++;
    }

    private int numberOfLoops;
    public void increaseNumberOfLoops () {
        this.numberOfLoops++;
    }

    private int finalScore;
    public int getFinalScore () {
        return this.finalScore;
    }

    public void calculateFinalScore () {
        this.finalScore = this.numberOfLoops + this.numberOfConditionalStatements;
    }

    public MethodComplexityScore() {
        this.numberOfConditionalStatements = 0;
        this.numberOfLoops = 0;
        this.finalScore = 0;
    }

    public String toString() {
        String outputFinalScore = STR."\tFinal score: \{this.finalScore}\n";
        String outputNumberOfConditionalStatements = STR."\tNumber of ifs/switches: \{this.numberOfConditionalStatements}\n";
        String outputNumberOfLoops = STR."\tNumber of loops: \{this.numberOfLoops}\n";

        return STR."\{outputFinalScore}\{outputNumberOfLoops}\{outputNumberOfConditionalStatements}";
    }
}
