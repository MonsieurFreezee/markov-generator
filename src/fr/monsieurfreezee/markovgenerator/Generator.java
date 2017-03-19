package fr.monsieurfreezee.markovgenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    private static final int MINIMUM_LENGTH = 4;
    private static final int MAXIMUM_LENGTH = 12;
    private static final int GENERATED_WORDS = 20;
    static final boolean DEBUG = false;

    public static void main(String args[]) throws IOException {
        HashMap<String, Integer> map = Calculator.computeMap(args[0]);

        for (int i = 0; i < GENERATED_WORDS; i++) {
            final String[] word = {"!!"}; // '!' = empty character (we start with nothing)

            while (true) {
                HashMap<String, Integer> possibleLetters = new HashMap<>(); // <Possible Letter, Probability>
                final int[] totalProbability = {0};
                String wordLastLetters = word[0].substring(word[0].length() - 2);
                map.forEach((S, I) -> {
                    if (S.startsWith(wordLastLetters)) {
                        possibleLetters.put(S.substring(3), I);
                        if (Generator.DEBUG) System.out.println(wordLastLetters + " put " + S.substring(3) + ", " + I);
                        totalProbability[0] += I;
                    }
                });
                if (Generator.DEBUG) System.out.println("Final number: " + totalProbability[0]);

                final int[] randomNum = {ThreadLocalRandom.current().nextInt(0, totalProbability[0] + 1)};
                if (Generator.DEBUG) System.out.println("Random: " + randomNum[0]);

                final boolean[] done = {false};
                possibleLetters.forEach((S, I) -> {
                    if (!done[0]) {
                        randomNum[0] -= I;
                        if (randomNum[0] <= 0) {
                            word[0] += S;
                            done[0] = true;
                        }
                    }
                });

                boolean isFinished = word[0].substring(word[0].length() - 1).equals("!");
                if (isFinished) break;
            }

            String finalWord = word[0].substring(2, word[0].length() - 1);
            if (finalWord.length() < MINIMUM_LENGTH || finalWord.length() > MAXIMUM_LENGTH) {
                i--;
            }
            else System.out.println(finalWord);
        }
    }
}
