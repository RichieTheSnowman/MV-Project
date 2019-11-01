import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Readability {


    // Figure out readability
    public static double FKReadability(ArrayList<String> sentences, ArrayList<String> words) {
        double numwords = words.size();
        double numsentences = sentences.size(); // sentences.size();
        double numsyllables = totalsyllables(sentences);


        double finalcalc = 206.835 - 1.015 * (numwords / numsentences) - 84.6 * (numsyllables / numwords);
        return finalcalc;
    }


    public static String[] breakSentenceintoWords(String sentence) {
        return sentence.split(" ");
    }

    private static int totalsyllables(ArrayList<String> sentences) {
        int totalSyllables = 0;

        for (String sentence : sentences) {
            String[] a = breakSentenceintoWords(sentence);


            for (int i = 0; i < a.length; i++) {
                totalSyllables += syllablesFor(a[i]);
            }

        }

        return totalSyllables;
    }

    private static int syllablesFor(String testWord) {
        boolean inVowelChain = false;
        int boundaries = 0;

        for (int i = 0; i < testWord.length(); i++) {
            String letter = testWord.substring(i, i + 1);
            if (isVowel(letter)) {
                if (!inVowelChain) {
                    inVowelChain = true;
                    boundaries++;
                }
            } else {
                inVowelChain = false;
            }
        }

        return boundaries;
    }

    private static boolean isVowel(String letter) {
        return "aeiouy".contains(letter);
    }

}