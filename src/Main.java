import codeparser.CodeParser;

public class Main {
    public static void main(String[] args) {
        CodeParser codeParser = new CodeParser("/Users/rogan003/Desktop/Fakultet/2. semestar/OOP/Projekat/projekat_sv_36_2022");

        codeParser.methodsWithHighestComplexityScores();
        codeParser.methodsNotInCamelCase();
    }
}