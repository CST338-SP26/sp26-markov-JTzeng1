import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;



public class Markov {
    public static final String BEGINS_SENTENCE = "__$";
    private String prevWord;
    private HashMap<String, ArrayList<String>> words;
    private static String PUNCTUATION_MARKS =  ".!?$";

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

    public void addFromFile(String filename) {

    }

    public void addWord(String word) {
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

    }

    public static boolean endsWithPunctuation(String sentence) {
        return sentence.contains(PUNCTUATION_MARKS);
    }

}
