import java.util.ArrayList;


public class Question {
    private String question;
    private ArrayList<Answer> answers;

    public Question(String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public int similarityWithAnswers() {
        int count = 0;
        String[] words = calcWords();
        for (Answer a : answers) {
            for (int i = 0; i < words.length; i++) {
                if (a.getWords().equals(words[i])) {
                    count++;
                }
            }


        }
        return count;
    }

    public String[] calcWords() {
        String str = question;
        str.replaceAll(".", " ");
        str.replaceAll(",", " ");
        str.replaceAll("/?", " ");
        str.replaceAll("!", " ");

        String[] words = str.split(" ");

        return words;

    }
}
