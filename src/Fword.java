public class Fword {
    String word;
    int freq;

    public Fword(String word, int freq){
        this.word = word;
        this.freq = freq;
    }

    public void setWord(String word){
        this.word = word;
    }

    public void setFreq(int freq){
        this.freq = freq;
    }

    public String getWord(){
        return word;
    }

    public int getFreq(){
        return freq;
    }

    public void increment(){
        freq++;
    }
}
