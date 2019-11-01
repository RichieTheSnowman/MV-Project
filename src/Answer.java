import javax.xml.soap.Text;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.*;

public class Answer {
    private String text;
    private ArrayList<String> sentences;
    private ArrayList<String> words;
    private double FkReadAbility;

    public Answer(String text){
        this.text = text;
        this.sentences = CalcSentences();
        this.words = CalcWords();
        this.FkReadAbility = Readability.FKReadability(sentences,words);
    }

    public String getText(){
        return text;
    }



    public double getReadability(){
        return FkReadAbility;
    }

    public int getNumSentences(){
        return sentences.size();
    }

    public ArrayList<String> getSentences(){
        return sentences;
    }

    private ArrayList<String> CalcSentences(){
        ArrayList<String> output = new ArrayList<>();

        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while(boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length()>0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length()>0)
            output.add(sentence);

        return output;
    }

    public int getNumWords(){
        return words.size();
    }

    public ArrayList<String> getWords(){
        return words;
    }

    private ArrayList<String> CalcWords(){
        ArrayList<String> words = new ArrayList<>();
        for (String sentence: sentences) {
            String[] word = sentence.split(". ");
            ArrayList<String> result = stripandfixed(word);
            for (String w: result){
                words.add(w);
            }
        }
        return words;
    }

    public double wordsSentRatio(){
        return (double) CalcWords().size() / CalcSentences().size();
    }

    public boolean contains(String word){
        return words.contains(word);
    }


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

    /*public int countSwearWords(){

    }*/


}
