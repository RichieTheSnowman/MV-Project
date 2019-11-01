import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Question Q1 = TextLib.readAnswersDoc("data/Question1.txt");
        Question Q2 = TextLib.readAnswersDoc("data/Question2.txt");
        Question Q3 = TextLib.readAnswersDoc("data/Question3.txt");
        Question Q4 = TextLib.readAnswersDoc("data/Question4.txt");

        printScores(Q1);
        printScores(Q2);
        printScores(Q3);
        printScores(Q4);
    }

    private static void printScores(Question q) {
        System.out.println("The question is: " + q.getQuestion());
        for (int i = 0; i < q.getAnswers().size(); i++) {
            System.out.println("Answer " + (i+1) + ": " + q.getAnswers().get(i).getText());

        }
        System.out.println("Their respective scores are: " + weight(q));
        System.out.println();
    }


    private static ArrayList<Double> weight(Question q) {
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < q.getAnswers().size(); i++) {
            double weighedRValue = (100 - q.getAnswers().get(i).getReadability()) * 0.25;
            double weighedSentRatio = (q.getAnswers().get(i).wordsSentRatio() * 0.5);
            double weighedSwearWords = (q.getAnswers().get(i).countSpecificWords("data/swearWords.txt")) * 5;
            double weighedCueWordsCount = q.getAnswers().get(i).countSpecificWords("data/cueWords.txt");
            double weighedNumSameWordsInQuestSent = q.similarityWithAnswers() * 5;

            double calc = weighedRValue + weighedSentRatio + weighedNumSameWordsInQuestSent - weighedSwearWords + weighedCueWordsCount;
            list.add(calc);
        }


        return list;
    }
}
