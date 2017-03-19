package fr.monsieurfreezee.markovgenerator;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Calculator {

    public static void main(String args[]) throws IOException {
        HashMap<String, Integer> probabilities = new HashMap<>();

        // File to scan is program args[0]
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/" + args[0]), Charset.forName("UTF-8")));
        reader.readLine(); // Discard file header

        String line;
        while ((line = reader.readLine()) != null) {
            String word = line.split("\t")[0];
            if (word.length() >= 2 && !word.contains("-") && !word.contains(" ")) {
                System.err.print(word + ": \t\t\t");

                for (int i = 0; i < word.length() + 1; i++) {
                    String letters = charAt(word, i - 2) + "" + charAt(word, i - 1) + ">" + charAt(word, i);

                    int characterOccurences = 1;
                    try { characterOccurences = probabilities.get(letters); }
                    catch (NullPointerException ignored) { }
                    probabilities.put(letters, characterOccurences + 1);

                    System.err.print(letters + " (" + characterOccurences + ")  \t");
                }
                System.err.println("");
            }
        }
    }

    private static char charAt(String str, int index) {
        try {
            return str.charAt(index);
        } catch (StringIndexOutOfBoundsException e) {
            return '!';
        }
    }
}

