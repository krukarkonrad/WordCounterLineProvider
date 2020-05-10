import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordListing {

    List<PatternStats> patternStats = new ArrayList<>();

    public List<PatternStats> listWords(String filePath) {
        int lineCounter = 0;
        String regex = "\\b[^\\d\\P{L}]+\\b";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                lineCounter++;

                List<String> tempLine = new ArrayList<>();
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(sc.nextLine());
                while (m.find()) {
                    tempLine.add(m.group(0));
                }

                for (String each : tempLine) {
                    int tempIndex = checkIfExist(each);

                    if (tempIndex < 0){
                        PatternStats tempPatternStats = new PatternStats(each);
                        tempPatternStats.addLineToList(lineCounter);
                        patternStats.add(tempPatternStats);
                    } else {
                        if(!patternStats.get(tempIndex).getLinesContains().contains(lineCounter))
                            patternStats.get(tempIndex).addLineToList(lineCounter);

                        patternStats.get(tempIndex).increaseAOA();
                    }
                }
                tempLine.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return patternStats;
    }

    private int checkIfExist(String word) {
        for (int i = 0; i < patternStats.size(); i++){
            if(patternStats.get(i).getWord().equals(word))
                return i;
        }
        return -1;
    }
}
