import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/*
Title: Markov
Abstract: The program creates random sentences using a Markov chain by reading words
from a file and storing them in a HashMap that tracks which words follow each other.
It then generates sentences by randomly selecting words until a word ending with punctuation is reached.
Author: Justin Tzeng;
Date: 03/10/2026

 */
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
        StringBuilder sentence = new StringBuilder();
        String currentWord = randomWord(BEGINS_SENTENCE);

        while (true) {
            sentence.append(currentWord);

            if (endsWithPunctuation(currentWord)) {
                break;

            }
            sentence.append(" ");
            currentWord = randomWord(currentWord);


        }
        return sentence.toString();

    }

    public void addFromFile(String filename) throws FileNotFoundException {
        try{
            FileReader file = new FileReader(filename);
            Scanner scanFile = new Scanner(file);

            while(scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
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
        ArrayList<String> WordList = words.get(key);
        Random random = new Random();
        int randomizer = random.nextInt(WordList.size());
        return WordList.get(randomizer);

    }

    public String toString(){
        return words.toString();
    }

    public HashMap<String, ArrayList<String>> getWords() {
        return words;
    }

    public void addLine(String line)  {
        if(line.isEmpty()){
            return;
        }
        String [] wordSplitter = line.split(" ");

        for (int i = 0; i < wordSplitter.length; i++) {
            String word = wordSplitter[i].trim();

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
