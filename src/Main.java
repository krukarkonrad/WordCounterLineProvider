public class Main {

    public static void main(String[] args) {
        final String filePatch = "C:\\vaTast\\src\\zadanie.txt";

        WordLister wlwm = new WordLister();
        wlwm.listWords(filePatch).forEach(System.out::println);
    }
}
