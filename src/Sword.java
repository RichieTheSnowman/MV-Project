public class Sword {
    private String word;
    private int syllables;

    public Sword(String word, int syllables) {
        this.word = word;
        this.syllables = syllables;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getSyllables() {
        return syllables;
    }

    public void setSyllables(int syllables) {
        this.syllables = syllables;
    }
}
