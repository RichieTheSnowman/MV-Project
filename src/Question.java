import java.util.ArrayList;
import java.util.StringTokenizer;

public class Question {
    private String text;
    private ArrayList<String> words;

    public Question(String filename){
        this.text = TextLib.readFileAsString(filename);
        this.words = CalcWords();
    }

    public int similarityWithAnswers(ArrayList<Answer> answers){
        int count = 0;
        for(Answer a : answers){
            for (int i = 0; i < words.size(); i++) {
                if(a.contains(CalcWords().get(i))){
                    count++;
                }
            }

        }
        return count;
    }

    public ArrayList<String> CalcWords(){
        ArrayList<String> words = new ArrayList<>();
        String t = text;
        t.replaceAll(".", " ");
        t.replaceAll(",", " ");
        t.replaceAll("/?", " ");
        t.replaceAll("!", " ");

        StringTokenizer tokenizer = new StringTokenizer(text, " ");

        int count = 0;
        while(tokenizer.hasMoreTokens()){
            words.add(tokenizer.nextToken());
            tokenizer.nextToken();
        }

        return words;

    }
}
