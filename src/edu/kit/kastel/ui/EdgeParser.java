package edu.kit.kastel.ui;

import edu.kit.kastel.model.Edge;
import edu.kit.kastel.model.Node;
import edu.kit.kastel.model.Predicate;

/**
 * Utility class for parsing edge specification strings.
 *
 * @author uupyx
 */
public final class EdgeParser {

    private static final String REGEX_SUBJECT = "(?<subject>[a-zA-Z0-9]+(?:\\s*\\(\\s*id\\s*=\\s*[0-9]+\\s*\\))?)\\s+";
    private static final String REGEX_PREDICATE = "(?<predicate>contains|contained-in|part-of|has-part|successor-of|predecessor-of)\\s+";
    private static final String REGEX_OBJECT = "(?<object>[a-zA-Z0-9]+(?:\\s*\\(\\s*id\\s*=\\s*[0-9]+\\s*\\))?)\\s*$";
    private static final String PATTERN_REGEX = "^" + REGEX_SUBJECT + REGEX_PREDICATE + REGEX_OBJECT;

    private static final String GROUP_SUBJECT = "subject";
    private static final String GROUP_PREDICATE = "predicate";
    private static final String GROUP_OBJECT = "object";

    /**
     * Private constructor to prevent instantiation.
     */
    private EdgeParser() {
        // Prevent instantiation
    }

    /**
     * Parses an edge specification string.
     *
     * @param input the input string.
     * @return the parsed Edge, or null if parsing fails.
     */
    public static Edge parse(String input) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(PATTERN_REGEX);
        java.util.regex.Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            return null;
        }
        String subjectStr = matcher.group(GROUP_SUBJECT);
        String predicateStr = matcher.group(GROUP_PREDICATE);
        String objectStr = matcher.group(GROUP_OBJECT);
        Node subject = NodeParser.parse(subjectStr);
        Node object = NodeParser.parse(objectStr);
        if ((subject == null) || (object == null)) {
            return null;
        }
        return new Edge(subject, object, Predicate.get(predicateStr));
    }
}
