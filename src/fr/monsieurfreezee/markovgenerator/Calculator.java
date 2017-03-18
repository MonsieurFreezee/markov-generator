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
            for (int i = 0; i < line.length() - 1; i++) {

                // Discard
            }

//            probabilities.put(firstLetters, probabilities.get(firstLetters) + 1);
        }
    }
}

