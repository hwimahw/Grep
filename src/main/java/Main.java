import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
         Scanner scanner = new Scanner(new FileInputStream("./src/main/resources/input.txt"));
        // quantityOfWordsInTextFile(scanner);
    //    quantityOfWordInTextFile(scanner, "word");
      //  symbolQuantity(scanner);
        jGrep(scanner, "\\d.\\d");
    }

    private static String deleteSpacesFromStart(String string){
        char[] charArray = string.toCharArray();
        int i = 0;
        while(i < charArray.length && charArray[i] == ' '){
            i++;
        }
        return string.substring(i, string.length());
    }

    public static void quantityOfWordsInTextFile(Scanner scanner){
        String string = null;
        String[] wordsInString = null;
        int quantityOfWordsInFile = 0;
        while(scanner.hasNextLine()){
            string = scanner.nextLine();
            string = deleteSpacesFromStart(string);
            wordsInString = string.split(" +");
            if(!wordsInString.equals("")) {
                quantityOfWordsInFile = quantityOfWordsInFile + wordsInString.length;
            }
        }
        System.out.println(quantityOfWordsInFile);
    }

    public static void quantityOfWordInTextFile(Scanner scanner, String word){
        String string = null;
        String[] wordsInString = null;
        int quantityOfWordInFile = 0;
        while(scanner.hasNextLine()){
            string = scanner.nextLine();
            string = deleteSpacesFromStart(string);
            wordsInString = string.split(" +");
            for(int i = 0; i < wordsInString.length; i++){
                if(wordsInString[i].equals(word)){
                    quantityOfWordInFile++;
                }
            }
        }
        System.out.println(quantityOfWordInFile);
    }

    public static void symbolQuantity(Scanner scanner){
        String string = null;
        String[] symbolsInString = null;
        Map map = new HashMap();
        char[] checkedSmbl = null;
        while(scanner.hasNextLine()){
            string = scanner.nextLine();
            checkedSmbl = new char[string.length()];
            symbolsInString = string.split("");
            for(int i = 0; i < symbolsInString.length; i++){
                int quantityOfSymbolInString = 0;
                if(String.valueOf(checkedSmbl).indexOf(symbolsInString[i]) == -1) {
                    checkedSmbl[i] = symbolsInString[i].charAt(0);
                    Pattern pattern = Pattern.compile(symbolsInString[i]);
                    Matcher matcher = pattern.matcher(string);
                    while (matcher.find()) {
                        quantityOfSymbolInString++;
                    }

                    map.put(symbolsInString[i], (Integer)map.getOrDefault(symbolsInString[i], 0) + quantityOfSymbolInString);
                }
            }

        }

        for (Object key : map.keySet()) {
            System.out.println("Символ = " + key + ", Количество = " +  map.get(key));
        }
    }

    public static void jGrep(Scanner sc, String regex){
        //String regex = "\\d.\\d";
        int numberOfString = 1;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;
        while(sc.hasNextLine()){
            matcher = pattern.matcher(sc.nextLine());
            while(matcher.find()){
                System.out.println(numberOfString + " " + matcher.group() + " " + matcher.start());
            }
            numberOfString++;
        }
    }


}
