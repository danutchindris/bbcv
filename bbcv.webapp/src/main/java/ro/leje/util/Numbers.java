package ro.leje.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Danut Chindris
 * @since June 4, 2017
 */
public class Numbers {

    private static final Integer[][] SEQUENCE = new Integer[][] {
            {4, 4, 4},
            {4, 8},
            {8, 4},
            {12}
    };

    public static List<Integer> getCardSequence(final int length) {
        final List<Integer> result = new ArrayList<>();
        int counter = 0;
        while (counter < length) {
            int random = ThreadLocalRandom.current().nextInt(0, SEQUENCE.length);
            for (int i = 0; i < SEQUENCE[random].length; i++) {
                result.add(SEQUENCE[random][i]);
                counter++;
            }
        }
        return result;
    }
}
