import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PatternStats implements Comparable<PatternStats>{
    private String word;
    private int amountOfAppearances;
    private SortedSet<Integer> linesContains;

    public PatternStats(String word){
        this.amountOfAppearances = 1;
        this.linesContains = new TreeSet<>();
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getAmountOfAppearances() {
        return amountOfAppearances;
    }

    public SortedSet<Integer> getLinesContains() {
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
        return word.compareToIgnoreCase(o.word);
    }

    @Override
    public String toString(){
        return (word + " - "
                + amountOfAppearances
                + " - pozycje - > " + linesContains);
    }
}
