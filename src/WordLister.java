import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WordLister {
    private static final String regex = "\\b[^\\d\\P{L}]+\\b";
    private static final Pattern p = Pattern.compile(regex);

    private int lineCounter;
    private Map<String, PatternStats> patternStatsMap;

    public WordLister() {
        patternStatsMap = new HashMap<>();
        lineCounter = 1;
    }

    public List<PatternStats> listWords(String filePath) {
        patternStatsMap.clear();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(l -> scanTextCollectWords(l, lineCounter++));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PatternStats> patternStats = new ArrayList<>(patternStatsMap.values());
        Collections.sort(patternStats);
        return patternStats;
    }

    private void scanTextCollectWords(String line, int lineCounter) {
        Matcher m = p.matcher(line);
        while (m.find()) {
            String tempWord = m.group(0);
            if (patternStatsMap.containsKey(tempWord)) {
                increaseExist(tempWord, lineCounter);
            } else {
                createNewAddToMap(tempWord, lineCounter);
            }
        }
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