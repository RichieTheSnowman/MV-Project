import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //Question q = new Question("data/questionExample.txt");
        Question Q1 = TextLib.readAnswersDoc("data/Question1.txt");
        Question Q2 = TextLib.readAnswersDoc("data/Question2.txt");
        Question Q3 = TextLib.readAnswersDoc("data/Question3.txt");
        Question Q4 = TextLib.readAnswersDoc("data/Question4.txt");

        System.out.println(Q1.getAnswers().get(0).getText());
        System.out.println(weight(Q1));


    }

   private static ArrayList<Double> weight(Question q) {
        ArrayList<Double> list = new ArrayList<>();
       for (int i = 0; i < q.getAnswers().size(); i++) {
           double weighedRValue = (100 - q.getAnswers().get(i).getReadability()) * 0.55;
           double weighedSentRatio = (q.getAnswers().get(i).wordsSentRatio() * 0.25);
           double weighedNumSameWordsInQuestSent = q.similarityWithAnswers()* 0.1;
           //double weighedCueWordsCount = a.cueWordsCount();
           double calc = weighedRValue+weighedSentRatio+weighedNumSameWordsInQuestSent;
           list.add(calc);
       }



        return list;
    }
}
