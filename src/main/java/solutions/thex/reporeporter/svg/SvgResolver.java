package solutions.thex.reporeporter.svg;

import solutions.thex.reporeporter.svg.resolver.badge.util.ColorResolver;
import solutions.thex.reporeporter.svg.resolver.badge.util.DefaultColor;
import solutions.thex.reporeporter.svg.resolver.badge.util.colorResolver.DefaultColorResolver;
import solutions.thex.reporeporter.svg.resolver.badge.util.colorResolver.RandomColorResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Resolves an SVG generator class based on given parameters from user by resolve method.
 * Different SVG types can implement this interface to provide their own SVG generator.
 * For example see {@link solutions.thex.reporeporter.svg.resolver.badge.LTRLinkResolver}.
 *
 * @author Soroush Shemshadi
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class SvgResolver {

    public abstract String resolve(Map<String, String> params) throws IOException;

    protected String resolveLogo(String logo, String color) {
        String file = retrieveLogoFile(logo);
        file = fillColor(color, file);
        file = replaceScapeChars(file);
        return file;
    }

    protected String resolveBG(String bg) {
        if (DefaultColor.getColor(bg) != null) {
            return DefaultColorResolver.resolve(bg);
        } else if ("random".equals(bg)) {
            return RandomColorResolver.resolve();
        }
        return ColorResolver.resolve(bg);
    }

    protected String resolveColor(String color, String bg) {
        if ("rgb(255, 255, 255)".equals(color)) {
            if (DefaultColor.GREEN.toString().equals(bg)) {
                return DefaultColor.BLACK.toString();
            } else if (DefaultColor.YELLOW.toString().equals(bg)) {
                return DefaultColor.BLACK.toString();
            } else if (DefaultColor.ORANGE.toString().equals(bg)) {
                return DefaultColor.BLACK.toString();
            } else if (DefaultColor.GRAY.toString().equals(bg)) {
                return DefaultColor.BLACK.toString();
            } else if (DefaultColor.LAVENDER.toString().equals(bg)) {
                return DefaultColor.BLACK.toString();
            } else if (DefaultColor.CYAN.toString().equals(bg)) {
                return DefaultColor.BLACK.toString();
            } else if (DefaultColor.WHITE.toString().equals(bg)) {
                return DefaultColor.BLACK.toString();
            }
            return DefaultColor.WHITE.toString();
        }
        return ColorResolver.resolve(color);
    }

    private String retrieveLogoFile(String logo) {
        return new BufferedReader(//
                new InputStreamReader(//
                        Objects.requireNonNull(//
                                Thread.currentThread().getContextClassLoader()//
                                        .getResourceAsStream("static/logos/" + logo + ".svg")),
                        StandardCharsets.UTF_8)).lines()//
                .collect(Collectors.joining("\n"));
    }

    private String fillColor(String color, String file) {
        String start = file.substring(0, file.indexOf("<path") + 6);
        String end = file.substring(file.indexOf("<path") + 5);
        color = "fill=\"" + ColorResolver.resolve(color) + "\"";
        return start + color + end;
    }

    private String replaceScapeChars(String file) {
        file = file.replaceAll("\"", "&quot;");
        file = file.replaceAll("'", "&apos;");
        file = file.replaceAll("<", "&lt;");
        file = file.replaceAll(">", "&gt;");
        file = file.replaceAll("&", "&amp;");
        return file;
    }

    protected String resolveTextLength(String title, String size) {
        return switch (size) {
            case "s" -> String.valueOf((int) Math.ceil(title.length() * 6.4117647) * 10);
            case "m" -> String.valueOf((int) Math.ceil(title.length() * 7.05882353) * 10);
            case "l" -> String.valueOf((int) Math.ceil(title.length() * 8.2352941) * 10);
            default -> "";
        };
    }

    protected String resolveWidth(String size, String title) {
        return switch (size) {
            case "s" -> String.valueOf((int) Math.ceil((title.length() * 6.4117647) + 29));
            case "m" -> String.valueOf((int) Math.ceil((title.length() * 7.05882353) + 33));
            case "l" -> String.valueOf((int) Math.ceil((title.length() * 8.2352941) + 40));
            default -> "";
        };
    }

    protected String resolveHeight(String size) {
        return switch (size) {
            case "s" -> "24";
            case "m" -> "29";
            case "l" -> "35";
            default -> "";
        };
    }

}
