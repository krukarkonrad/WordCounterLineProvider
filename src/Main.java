import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String filePatch = "C:\\vaTast\\src\\zadanie.txt";

        WordListing wl = new WordListing();
        List<PatternStats> toPrint = wl.listWords(filePatch);
        for (PatternStats each : toPrint){
            System.out.println(each.getWord() + " - " +
                    each.getAmountOfAppearances() + " - pozycje - > " +
                    each.getLinesContains());
        }
    }
}
