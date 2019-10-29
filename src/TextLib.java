import oracle.jrockit.jfr.StringConstantPool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TextLib {

    // Takes the filename and pulls up the file and reads it as a String
    public static String readFileAsString(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line.trim()+"\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }

    // Read the Answers file and store everything about them into a String
    public static ArrayList<Answer> readAnswersDoc(String filename){
        ArrayList<Answer> Answers = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileReader(filename));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();

                Answer A = new Answer(text);
                Answers.add(A);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return Answers;
    }

    // Reads the syllables file and stores the word and its respective number of
    public static ArrayList<Sword> readSyllablesFile(String filename){
        ArrayList<Sword> Words = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileReader(filename));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] info = line.split("=");
                String word = info[0].trim();
                int syllables = calcSyllables(info[1]);


                Sword w = new Sword(word, syllables);
                Words.add(w);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return Words;
    }

    private static int calcSyllables(String s){
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if(s.substring(i,i+1).equals("*")) count++;
        }
        return count;
    }

    // Splits the whole block string into sentances


    private static String getWordFromLine(String line) {
        return line.substring(0, line.indexOf("="));
    }
}
