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
        // I think StringBuilder would work to build a sentence from word by word
        StringBuilder sentence = new StringBuilder();
        String currentWord = randomWord(BEGINS_SENTENCE);

        while (true) {
            //add a word to the sentence StringBuilder
            //and if the word ends in punctuation the sentence is complete
            sentence.append(currentWord);
            if (endsWithPunctuation(currentWord)) {
                break;

            }
            //otherwise add a space and go on to the next word
            sentence.append(" ");
            currentWord = randomWord(currentWord);


        }
        return sentence.toString();

    }

    public void addFromFile(String filename) throws FileNotFoundException {
        try{
            // need to open file to read whats in it
            FileReader file = new FileReader(filename);
            Scanner scanFile = new Scanner(file);

            //read each line and bring it to addLine so addLine can brake it
            //down into words
            while(scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                addLine(line);
            }
        //needs catch to catch if file is not found
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
        // Generate a random index from the list
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
        //Skip empty lines because they would create invalid words
        if(line.isEmpty()){
            return;
        }
        // Split the line into each separate words base on the space
        String [] wordSplitter = line.split(" ");

        //Loop through each word in the line and send it to addWord()
        for (int i = 0; i < wordSplitter.length; i++) {
            String word = wordSplitter[i].trim();
            //add the word if it is not empty
            if(!word.isEmpty()) {
                addWord(word);
            }

        }


    }

    public static boolean endsWithPunctuation(String words) {
        try {
            //get the last character of the word
            char lastChar = words.charAt(words.length()-1);

            // Check if the character matches with any punctuation marks
            for (int i = 0; PUNCTUATION_MARKS.length() > i; i++) {
                if(lastChar == PUNCTUATION_MARKS.charAt(i)){
                    return true;

                }
            }
            return false;


        } catch (Exception e) {
            // If an error occurs print the word causing the issue
            System.out.println("Error checking Punctuation in words" + words);
            return false;
        }
    }

}
