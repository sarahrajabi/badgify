package solutions.thex.reporeporter.svg.resolver.badge.util.colorResolver;

import solutions.thex.reporeporter.svg.resolver.badge.util.ColorResolver;
import solutions.thex.reporeporter.svg.resolver.badge.util.DefaultColor;

import java.util.Random;

public class RandomColorResolver extends ColorResolver {

    public static String resolve() {
        return DefaultColor.values()[randomTill(DefaultColor.values().length - 1)].toString();
    }

    private static int randomTill(int end) {
        final Random r = new Random();
        return r.nextInt(end + 1);
    }

}
