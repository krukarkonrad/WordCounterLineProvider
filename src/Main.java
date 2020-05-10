import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String filePatch = "C:\\vaTast\\src\\zadanie.txt";
        try {
            FileInputStream fis = new FileInputStream(filePatch);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
