import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordListingWithMap {

    private Map<String, PatternStats> patternStatsMap = new HashMap<>();

    public List<PatternStats> listWords(String filePath) {
        patternStatsMap.clear();
        int lineCounter = 0;
        String regex = "\\b[^\\d\\P{L}]+\\b";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                lineCounter++;
                List<String> tempLine = loadLineToList(sc, regex);
                //String[] tempLine = sc.nextLine().split("[\\pP\\s&&[^']]+");
                for (String each : tempLine) {
                    if (patternStatsMap.containsKey(each)) {
                        increaseExist(each, lineCounter);
                    } else {
                        createNewAddToMap(each, lineCounter);
                    }
                }
                tempLine.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PatternStats> patternStats = new ArrayList<>(patternStatsMap.values());
        Collections.sort(patternStats);
        return patternStats;
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

    private void createNewAddToMap(String word, int line) {
        PatternStats tempPatternStats = new PatternStats(word);
        tempPatternStats.addLineToList(line);
        patternStatsMap.put(word, tempPatternStats);
    }

    private void increaseExist(String word, int line) {
        if (!patternStatsMap.get(word).getLinesContains().contains(line))
            patternStatsMap.get(word).addLineToList(line);

        patternStatsMap.get(word).increaseAOA();
    }
}