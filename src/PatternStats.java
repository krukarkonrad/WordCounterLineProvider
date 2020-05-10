import java.util.ArrayList;
import java.util.List;

public class PatternStats implements Comparable<PatternStats>{
    private String word;
    private int amountOfAppearances = 1;
    private List<Integer> linesContains = new ArrayList<>();

    public PatternStats(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getAmountOfAppearances() {
        return amountOfAppearances;
    }

    public List<Integer> getLinesContains() {
        return linesContains;
    }

    public void increaseAOA(){
        this.amountOfAppearances++;
    }

    public void addLineToList(int lineNO){
        this.linesContains.add(lineNO);
    }

    @Override
    public int compareTo(PatternStats o) {
        int compareInt = this.word.toLowerCase().compareTo(o.word.toLowerCase());
        if(compareInt < 0) return -1;
        if(compareInt > 0) return  1;
        return 0;
    }
}
