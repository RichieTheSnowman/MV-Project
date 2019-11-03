public class ScoreAnswer {
    double score;
    Answer a;

    public ScoreAnswer( double score, Answer a){
        this.a = a;
        this.score = score;
    }

    public double getScore(){
        return this.score;
    }

    public Answer getAnswer(){
        return this.a;
    }

    public void setAnswer(Answer answer) {
        this.a = answer;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
