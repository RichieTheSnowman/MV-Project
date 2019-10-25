import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class Answer {
    private String text;
    private ArrayList<String> sentences;
    private ArrayList<String> words;
    private ArrayList<Fword> WordBucket;
    private double FkReadAbility;

    public Answer(String text){
        this.text = text;
        this.sentences = CalcSentences();
        this.words = CalcWords();
        this.WordBucket = createWordBucket();
        this.FkReadAbility = Readability.FKReadability(sentences);
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
        for (int i = 0; i < sentences.size(); i++) {
            String[] line = sentences.get(i).split(" ");
            for (int j = 0; j < line.length; j++) {
                words.add(line[i]);
            }
        }
        return words;
    }

    public boolean contains(String word){
        return words.contains(word);
    }

    private ArrayList<Fword> createWordBucket(){
        ArrayList<Fword> output = new ArrayList<>();
        for (String word: words) {
            if(!output.contains(word)){
                Fword w = new Fword(word, 1);
                output.add(w);
            } else if(output.contains(word)){
                int j = getIndex(output, word);
                output.get(j).increment();
            }
        }
        return output;
    }

    private int getIndex(ArrayList<Fword> words,String word){
        for (int i = 0; i < words.size(); i++) {
            if(words.equals(word)) return i;
        }
        return -1;
    }
}
