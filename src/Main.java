import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Question q = new Question("data/question.txt");
        ArrayList<Answer> Answers = TextLib.readAnswersDoc("data/answers.csv");

        weight(q, Answers);

    }

    private static ArrayList<Double> weight(Question q, ArrayList<Answer> answers) {
        ArrayList<Double> list = new ArrayList<>();
        for(Answer a : answers){
            double weighedRValue = (100 - a.FkReadAbility) * 0.55;
            double weighedSentRatio = (a.wordsSentRatio() * 0.25);
            double weighedNumSameWordsInQuestSent = q.similarityWithAnswers(answers)* 0.1;
            //double weighedCueWordsCount = a.cueWordsCount();
            double calc = weighedRValue+weighedSentRatio+weighedNumSameWordsInQuestSent;
            list.add(calc);
        }

        return list;
    }
}
