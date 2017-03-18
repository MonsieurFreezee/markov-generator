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
            if (word.length() >= 2) {
                System.err.println(word + ": ");
                System.err.print("!!>" + word.charAt(0) + "  \t");
                System.err.print("!" + word.charAt(0) + ">" + word.charAt(1) + "  \t");
                for (int i = 0; i < word.length() - 1; i++) {
                    String letters = word.charAt(i) + "" + word.charAt(i + 1);
                    String followedBy = null;
                    try {
                        followedBy = word.charAt(i + 2) + "";
                    } catch (StringIndexOutOfBoundsException e) {
                    }

                    System.err.print(letters + ">" + (followedBy == null ? "!" : followedBy) + "  \t");
                }
                System.err.println("");

//            probabilities.put(firstLetters, probabilities.get(firstLetters) + 1);
            }
        }
    }
}

