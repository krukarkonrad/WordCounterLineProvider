import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordListing {

    private List<PatternStats> patternStats = new ArrayList<>();

    public List<PatternStats> listWords(String filePath) {
        patternStats.clear();
        int lineCounter = 0;
        String regex = "\\b[^\\d\\P{L}]+\\b";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                lineCounter++;
                List<String> tempLine = loadLineToList(sc, regex);

                for (String each : tempLine) {
                    int tempIndex = checkIfExist(each);

                    if (tempIndex < 0) {
                        createNewAddToList(each, lineCounter);
                    } else {
                        increaseExist(tempIndex, lineCounter);
                    }
                }
                tempLine.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(patternStats);
        return patternStats;
    }

    private int checkIfExist(String word) {
        for (int i = 0; i < patternStats.size(); i++) {
            if (patternStats.get(i).getWord().equals(word))
                return i;
        }
        return -1;
    }

    private List<String> loadLineToList(Scanner sc, String regex) {
        List<String> tempLine = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sc.nextLine());
        while (m.find()) {
            tempLine.add(m.group(0));
        }
        return tempLine;
    }

    private void createNewAddToList(String word, int line) {
        PatternStats tempPatternStats = new PatternStats(word);
        tempPatternStats.addLineToList(line);
        patternStats.add(tempPatternStats);
    }

    private void increaseExist(int index, int line) {
        if (!patternStats.get(index).getLinesContains().contains(line))
            patternStats.get(index).addLineToList(line);

        patternStats.get(index).increaseAOA();
    }
}