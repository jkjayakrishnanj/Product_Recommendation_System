package edu.kit.kastel.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.kastel.model.Category;
import edu.kit.kastel.model.Node;
import edu.kit.kastel.model.Product;

/**
 * Utility class for parsing node tokens.
 *
 * @author uupyx
 */
public final class NodeParser {

    private static final String SYMBOL_OPEN_PAREN = "(";
    private static final String REGEX_PRODUCT = "([a-zA-Z0-9]+)\\s*\\(\\s*id\\s*=\\s*([0-9]+)\\s*\\)";
    private static final int GROUP_NAME_INDEX = 1;
    private static final int GROUP_ID_INDEX = 2;
    private static final String REGEX_CATEGORY = "[a-zA-Z0-9]+";

    /**
     * Private constructor to prevent instantiation.
     */
    private NodeParser() {
        // Prevent instantiation
    }

    /**
     * Parses a token into a Node.
     *
     * @param token the token.
     * @return a Product or Category, or null if parsing fails.
     */
    public static Node parse(String token) {
        String trimmedToken = token.trim();
        if (trimmedToken.contains(SYMBOL_OPEN_PAREN)) {
            Pattern pattern = Pattern.compile(REGEX_PRODUCT);
            Matcher matcher = pattern.matcher(trimmedToken);
            if (matcher.matches()) {
                String name = matcher.group(GROUP_NAME_INDEX);
                int id = Integer.parseInt(matcher.group(GROUP_ID_INDEX));
                return new Product(name, id);
            } else {
                return null;
            }
        } else {
            if (trimmedToken.matches(REGEX_CATEGORY)) {
                return new Category(trimmedToken);
            }
            return null;
        }
    }
}
