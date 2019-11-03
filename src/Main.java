import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        ArrayList<Question> Trials = new ArrayList<>();

        Trials.add(TextLib.readAnswersDoc("data/Questions/Question1.txt"));
        Trials.add(TextLib.readAnswersDoc("data/Questions/Question2.txt"));
        Trials.add(TextLib.readAnswersDoc("data/Questions/Question3.txt"));
        Trials.add(TextLib.readAnswersDoc("data/Questions/Question4.txt"));

        for (Question q: Trials) {
            printScores(q);

        }

        ArrayList<ScoreAnswer> answers1 = organizeAsScoreAnswer(Trials.get(0).getAnswers(), Score(Trials.get(0)));
        ArrayList<ScoreAnswer> answers2 = organizeAsScoreAnswer(Trials.get(1).getAnswers(), Score(Trials.get(1)));
        ArrayList<ScoreAnswer> answers3 = organizeAsScoreAnswer(Trials.get(2).getAnswers(), Score(Trials.get(2)));
        ArrayList<ScoreAnswer> answers4 = organizeAsScoreAnswer(Trials.get(3).getAnswers(), Score(Trials.get(3)));


        System.out.println(Correctness(Trials.get(0).getAnswers()), answers1.get(0).a);

        //System.out.println(answers1.get(0).a.getText() + answers1.get(0).score );
        //System.out.println(answers1.get(1).a.getText() + answers1.get(1).score);
        //System.out.println(answers1.get(2).a.getText() + answers1.get(2).score);







    }

    private static double Correctness(ArrayList<Answer> answers1, ArrayList<Answer> answers2) {
        double count = 0;
        for (int i = 0; i < answers1.size(); i++) {
            if(answers1.get(i).getText().equals(answers2.get(i).getText())){
                count++;
            }
        }
        return count;
    }

    private static void printScores(Question q) {
        System.out.println("The question is: " + q.getQuestion());
        for (int i = 0; i < q.getAnswers().size(); i++) {
            System.out.println("Answer " + (i+1) + ": " + q.getAnswers().get(i).getText());

        }
        ArrayList<Double> scores = Score(q);
        System.out.println("Their respective scores are: " + scores);
        System.out.println();
    }

    private static ArrayList<Double> Score(Question q) {
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < q.getAnswers().size(); i++) {
            int dq = 0;
            if(q.getAnswers().get(i).CalcWords().size() <= 5){
                dq = - 50;
            }

            double weighedRValue = (100 - q.getAnswers().get(i).getReadability());
            double weighedSentRatio = (q.getAnswers().get(i).wordsSentRatio());
            double weighedSwearWords = (q.getAnswers().get(i).countSpecificWords("data/swearWords.txt")) * 50;
            double weighedCueWordsCount = q.getAnswers().get(i).countSpecificWords("data/cueWords.txt");
            double weighedNumSameWordsInQuestSent = q.similarityWithAnswers() * 2;

            double calc = dq + weighedRValue + weighedSentRatio + weighedNumSameWordsInQuestSent - weighedSwearWords + weighedCueWordsCount;
            list.add(calc);
        }
        return list;
    }


    private static ArrayList<ScoreAnswer> organizeAsScoreAnswer(ArrayList<Answer> answers, ArrayList<Double> scores){
        ArrayList<ScoreAnswer> list = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            ScoreAnswer a = new ScoreAnswer(scores.get(i), answers.get(i));
            list.add(a);
        }

        reOrderbybest(list);

        return list;
    }

    private static void reOrderbybest(ArrayList<ScoreAnswer> a){
        int n = a.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                if(a.get(j).score < a.get(j+1).score){
                    Collections.swap(a, j,j+1);
                }
            }
        }


    }



    private static double correctness(ArrayList<String> correct, ArrayList<String> answers){
        int count = 0;
        for (int i = 0; i < correct.size(); i++) {
            if(correct.get(i).equals(answers.get(i))) count++;
        }
        return count;
    }

}