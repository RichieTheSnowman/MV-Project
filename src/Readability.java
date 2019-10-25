import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Readability {
    private static ArrayList<Sword> SyllableWords;
    public static void main(String[] args) {
        SyllableWords = TextLib.readSyllablesFile("data/syllables.txt");

    }

    // Figure out readability
    public static double FKReadability(ArrayList<String> sentences){
        int numwords = totalwords(sentences);
        int numsentances = sentences.size();
        int numsyllables = totalsyllables(sentences);


        double finalcalc = 206.835 - 1.015*(numwords/numsentances) - 84.6*(numsyllables/numwords);
        return finalcalc;
    }

    public static int totalwords(ArrayList<String> sentences){
        int numwords = 0;
        for(String sentance: sentences){
            numwords += countWordsinSentance(sentance);
        }
        return numwords;
    }



    // Counts the words in the sentance, by seeing if the length of it is more than 0
    public static int countWordsinSentance(String sentence){
        String[] words = breakSentanceintoWords(sentence);
        int count = 0;
        for(String word: words){
            if(word.length() != 0) count++;
        }
        return count;
    }

    // uses .split() method
    public static String[] breakSentanceintoWords(String sentence){
        return sentence.split(" ");
    }

    private static int totalsyllables(ArrayList<String> sentences){
        int totalcount = 0;
        for(String sentence: sentences){
            String[] words = breakSentanceintoWords(sentence);
            ArrayList<String> fixedwords = stripandfixed(words);
            totalcount += getSyllablesforSentence(fixedwords);
        }
        return totalcount;
    }

    private static int getSyllablesforSentence(ArrayList<String> words){
        int count = 0;
        for(String word: words){
            count += syllablesforWord(word);
        }
        return count;
    }

    private static int syllablesforWord(String word){
        for (int i = 0; i < SyllableWords.size(); i++) {
            if(SyllableWords.get(i).getWord().equals(word)) return SyllableWords.get(i).getSyllables();
        }
        return 0;
    }

    // Force lower-case, and strips all puccuation
    public static ArrayList<String> stripandfixed(String[] words){
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            if(words[i].length() != 0){
                String word = words[i].toLowerCase();
                word = stripPuncuation(word);
                result.add(word);
            }
        }
        return result;
    }

    // Removes Puncuation
    public static String stripPuncuation(String word){
        String sword = "";
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i,i+1);
            if(isletter(letter)) sword = sword + letter;
        }
        return sword;
    }

    // Tests if the String is a letter
    private static boolean isletter(String letter) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        if(alphabet.contains(letter)) return true;
        return false;
    }
}
