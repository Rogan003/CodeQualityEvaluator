package codecomponents;

// Class that models the complexity score of a method
// Keeps the number of conditional statements (if/switch statements), number of loops
// and also the final score which is the sum of the previous two
// Has methods to increment the number of conditional statements and loops and to calculate the final score
public class MethodComplexityScore {
    private int numberOfConditionalStatements;
    public void incrementNumberOfConditionalStatements() {
        this.numberOfConditionalStatements++;
    }

    private int numberOfLoops;
    public void incrementNumberOfLoops() {
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
        String outputNumberOfConditionalStatements = STR."\tNumber of if/switch statements: \{this.numberOfConditionalStatements}\n";
        String outputNumberOfLoops = STR."\tNumber of loops: \{this.numberOfLoops}\n";

        return STR."\{outputFinalScore}\{outputNumberOfLoops}\{outputNumberOfConditionalStatements}";
    }
}
