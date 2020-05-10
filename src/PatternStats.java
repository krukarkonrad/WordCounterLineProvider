import java.util.ArrayList;
import java.util.List;

public class PatternStats {
    private String word;
    private int amountOfAppearances = 0;
    private List<Integer> linesContains = new ArrayList<>();

    public PatternStats(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getAmountOfAppearances() {
        return amountOfAppearances;
    }

    public void setAmountOfAppearances(int amountOfAppearances) {
        this.amountOfAppearances = amountOfAppearances;
    }

    public List<Integer> getLinesContains() {
        return linesContains;
    }

    public void setLinesContains(List<Integer> linesContains) {
        this.linesContains = linesContains;
    }
}
