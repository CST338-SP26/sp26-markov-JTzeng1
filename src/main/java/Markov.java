import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Markov {
    public static final String BEGINS_SENTENCE = "__$";
    private String prevWord;
    private HashMap<String, ArrayList<String>> words;
    private static final String PUNCTUATION_MARKS =  ".!?$";

    public Markov() {
        //The constructor for this class will initialize the HashMap words with the key BEGINS_SENTENCE and a value of a new ArrayList.
        //This method will also set prevWord = BEGINS_SENTENCE.
        prevWord = BEGINS_SENTENCE;
        words  = new HashMap<>();
        words.put(BEGINS_SENTENCE, new ArrayList<>());


    }

    public String getSentence() {
        return prevWord;
    }

    public void addFromFile(String filename) throws FileNotFoundException {
        try{
            FileReader file = new FileReader(filename);
            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                addLine(line);
            }


        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }


    }

    public void addWord(String word) {
        if(word.isEmpty()) {
            return;
        }
        String key;
        if(endsWithPunctuation(prevWord)) {
            key = BEGINS_SENTENCE;
        } else {
            key = prevWord;

        }
        if(!words.containsKey(key)) {
            words.put(key, new ArrayList<>());
        }
        words.get(key).add(word);
        prevWord = word;
    }

    public String randomWord(String key) {
        return this.words.get(key).get(0);
    }

    public String toString(){
        return this.getSentence();
    }

    public HashMap<String, ArrayList<String>> getWords() {
        return words;
    }

    public void addLine(String line) {
        if(line.isEmpty()){
            return;
        }

        String [] parts = line.split(" ");
        for(String word : parts){

            word = word.trim();
            if(!word.isEmpty()) {
                addWord(word);
            }

        }


    }

    public static boolean endsWithPunctuation(String words) {
        try {
            char lastChar = words.charAt(words.length()-1);

            for (int i = 0; PUNCTUATION_MARKS.length() > i; i++) {
                if(lastChar == PUNCTUATION_MARKS.charAt(i)){
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error checking Punctuation in words" + words);
            return false;
        }
    }

}
