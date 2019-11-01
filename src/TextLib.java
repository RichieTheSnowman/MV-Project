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
    public static Question readAnswersDoc(String filename){
        ArrayList<Answer> Answers = new ArrayList<>();
        Scanner scanner;
        String q = "";

        try {
            int a = 0;
            scanner = new Scanner(new FileReader(filename));
            while (scanner.hasNextLine()) {
                if (a == 0) {
                    q = scanner.nextLine();
                    a = 1;
                    scanner.nextLine();
                    continue;
                }

                String answer = scanner.nextLine();

                Answer A = new Answer(answer);
                Answers.add(A);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        Question question = new Question(q, Answers);
        return question;
    }

    public static ArrayList<String> readDoc(String filename){
        ArrayList<String> words = new ArrayList<>();
        Scanner scanner;


        try {
            scanner = new Scanner(new FileReader(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line.trim();

                words.add(line);
            }

        scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return words;

    }

    // Reads the syllables file and stores the word and its respective number of



}
