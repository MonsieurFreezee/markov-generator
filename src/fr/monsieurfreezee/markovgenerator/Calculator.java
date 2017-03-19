package fr.monsieurfreezee.markovgenerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Calculator {

    public static void main(String args[]) throws IOException {
        HashMap<String, Integer> map = computeMap(args[0]);
        TreeMap<String, Integer> sorted_map = new TreeMap<>(new ValueComparator(map));
        sorted_map.putAll(map);
        System.out.println(sorted_map);
    }

    static HashMap<String, Integer> computeMap(String file) throws IOException {
        HashMap<String, Integer> probabilities = new HashMap<>();


        // File to scan is program args[0]
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/" + file), Charset.forName("UTF-8")));
        reader.readLine(); // Discard file header

        String line;
        while ((line = reader.readLine()) != null) {
            final String word = line.split("\t")[0];
            if (word.length() >= 2 && !word.contains("-") && !word.contains(" ")) {
                if (Generator.DEBUG) System.err.print(word + ": \t\t\t");

                for (int i = 0; i < word.length() + 1; i++) {
                    String letters = charAt(word, i - 2) + "" + charAt(word, i - 1) + ">" + charAt(word, i);

                    int characterOccurrences = 1;
                    try { characterOccurrences = probabilities.get(letters); }
                    catch (NullPointerException ignored) { }
                    probabilities.put(letters, characterOccurrences + 1);

                    if (Generator.DEBUG) System.err.print(letters + " (" + characterOccurrences + ")  \t");
                }
                if (Generator.DEBUG) System.err.println("");
            }
        }

        if (Generator.DEBUG) probabilities.forEach((S, I) -> System.err.println(S + " (" + I + ')'));

        return probabilities;
    }

    private static char charAt(String str, int index) {
        try {
            return str.charAt(index);
        } catch (StringIndexOutOfBoundsException e) {
            return '!';
        }
    }

    static class ValueComparator implements Comparator<String> {
        Map<String, Integer> base;

        ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}

