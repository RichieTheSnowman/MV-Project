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

        ArrayList<ScoreAnswer> answers1 = reOrderbybest(Trials.get(0).getAnswers(), Score(Trials.get(0)));
        ArrayList<ScoreAnswer> answers2 = reOrderbybest(Trials.get(2).getAnswers(), Score(Trials.get(2)));
        ArrayList<ScoreAnswer> answers3 = reOrderbybest(Trials.get(3).getAnswers(), Score(Trials.get(3)));






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


    private static ArrayList<ScoreAnswer> reOrderbybest(ArrayList<Answer> answers, ArrayList<Double> scores){
        ArrayList<ScoreAnswer> reOrder = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            ScoreAnswer a = new ScoreAnswer(scores.get(i), answers.get(i));
            reOrder.add(a);
        }

        return reOrder;
    }





    private static Double[] ListtoArray(ArrayList<Double>scores){
        Double[] output = new Double[scores.size()];
        for (int i = 0; i < scores.size(); i++) {
            output[i] = scores.get(i);
        }
        return output;
    }



    private static int getIndexOfGreatest(ArrayList<Double> scores){
        int largest = 0;
        for (int i = 0; i < scores.size(); i++) {
            if(scores.get(largest) < scores.get(i)) largest = i;
        }
        return largest;
    }


    private static double correctness(ArrayList<String> correct, ArrayList<String> answers){
        int count = 0;
        for (int i = 0; i < correct.size(); i++) {
            if(correct.get(i).equals(answers.get(i))) count++;
        }
        return count;
    }

}