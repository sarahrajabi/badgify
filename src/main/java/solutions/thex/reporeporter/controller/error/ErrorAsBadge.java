package solutions.thex.reporeporter.controller.error;

import solutions.thex.reporeporter.svg.resolver.badge.LTRLinkResolver;

import java.io.IOException;
import java.util.Map;

/**
 * Resolves an error badge template with the given error status and message.
 * It extends @{@link solutions.thex.reporeporter.svg.resolver.badge.LTRLinkResolver}.
 *
 * @author Soroush Shemshadi
 * @version 1.0.0
 * @since 1.0.0
 */
public class ErrorAsBadge extends LTRLinkResolver {

    private final String svg;

    public ErrorAsBadge(int status, String error) throws IOException {
        this.svg = resolve(Map.of(//
                "theme", "simple",//
                "size", "m",//
                "direction", "ltr",//
                "link", "#",//
                "title", status + ": " + error,//
                "logo", "triangle-exclamation",//
                "bg", "c4160a",//
                "color", "rgb(255, 255, 255)"));
    }

    public String toString() {
        return svg;
    }

}
