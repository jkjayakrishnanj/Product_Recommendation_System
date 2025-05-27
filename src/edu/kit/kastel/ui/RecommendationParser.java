package edu.kit.kastel.ui;

import edu.kit.kastel.model.FinalTerm;
import edu.kit.kastel.model.IntersectionTerm;
import edu.kit.kastel.model.Term;
import edu.kit.kastel.model.UnionTerm;

/**
 * Recursive descent parser for recommendation expressions.
 *
 * @author uupyx
 */
public class RecommendationParser {

    private static final int NO_LENGTH = 0;

    private static final String ERROR_EXTRA_CHARACTERS = "Extra characters at end of input";
    private static final String ERROR_EXPECTED_KEYWORD = "Expected ";
    private static final String ERROR_EXPECTED_STRATEGY = "Expected strategy S1, S2, or S3";
    private static final String ERROR_EXPECTED_PRODUCT_ID = "Expected product id";
    private static final String ERROR_INVALID_PRODUCT_ID = "Invalid product id";
    private static final String ERROR_EXPECTED_COMMA = "Expected comma";
    private static final String ERROR_EXPECTED_STRING_FORMAT = "Expected \"%s\"";

    private static final String KEYWORD_INTERSECTION = "INTERSECTION";
    private static final String KEYWORD_UNION = "UNION";
    private static final String KEYWORD_S1 = "S1";
    private static final String KEYWORD_S2 = "S2";
    private static final String KEYWORD_S3 = "S3";
    private static final String SYMBOL_OPEN_PAREN = "(";
    private static final String SYMBOL_CLOSE_PAREN = ")";
    private static final char SYMBOL_COMMA = ',';

    private final String input;
    private int pos;

    /**
     * Constructs a RecommendationParser.
     *
     * @param input the recommendation expression.
     */
    public RecommendationParser(String input) {
        this.input = input;
    }

    /**
     * Parses the input expression.
     *
     * @return the parsed Term.
     * @throws ParseException if parsing fails.
     */
    public Term parse() throws ParseException {
        Term t = parseTerm();
        skipWhitespace();
        if (pos != input.length()) {
            throw new ParseException(ERROR_EXTRA_CHARACTERS);
        }
        return t;
    }

    private Term parseTerm() throws ParseException {
        skipWhitespace();
        if (peekStartsWith(KEYWORD_INTERSECTION)) {
            consumeKeywordAndBracket(KEYWORD_INTERSECTION);
            Term left = parseTerm();
            skipWhitespace();
            consumeComma();
            Term right = parseTerm();
            skipWhitespace();
            consume(SYMBOL_CLOSE_PAREN);
            return new IntersectionTerm(left, right);
        } else if (peekStartsWith(KEYWORD_UNION)) {
            consumeKeywordAndBracket(KEYWORD_UNION);
            Term left = parseTerm();
            skipWhitespace();
            consumeComma();
            Term right = parseTerm();
            skipWhitespace();
            consume(SYMBOL_CLOSE_PAREN);
            return new UnionTerm(left, right);
        } else {
            return parseFinal();
        }
    }

    private void consumeKeywordAndBracket(String keyword) throws ParseException {
        skipWhitespace();
        if (!input.startsWith(keyword, pos)) {
            throw new ParseException(ERROR_EXPECTED_KEYWORD + keyword);
        }

        pos += keyword.length();
        skipWhitespace();
        consume(SYMBOL_OPEN_PAREN);
    }

    private FinalTerm parseFinal() throws ParseException {
        skipWhitespace();
        String strat = parseStrategy();
        skipWhitespace();
        int pid = parseProductId();
        return new FinalTerm(strat, pid);
    }

    private String parseStrategy() throws ParseException {
        skipWhitespace();
        if (peekStartsWith(KEYWORD_S1)) {
            consume(KEYWORD_S1);
            return KEYWORD_S1;
        } else if (peekStartsWith(KEYWORD_S2)) {
            consume(KEYWORD_S2);
            return KEYWORD_S2;
        } else if (peekStartsWith(KEYWORD_S3)) {
            consume(KEYWORD_S3);
            return KEYWORD_S3;
        }
        throw new ParseException(ERROR_EXPECTED_STRATEGY);
    }

    private int parseProductId() throws ParseException {
        skipWhitespace();
        StringBuilder sb = new StringBuilder();
        while ((pos < input.length()) && Character.isDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        if (sb.length() == NO_LENGTH) {
            throw new ParseException(ERROR_EXPECTED_PRODUCT_ID);
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw new ParseException(ERROR_INVALID_PRODUCT_ID);
        }
    }

    private void consumeComma() throws ParseException {
        skipWhitespace();
        if ((pos < input.length()) && (input.charAt(pos) == SYMBOL_COMMA)) {
            pos++;
        } else {
            throw new ParseException(ERROR_EXPECTED_COMMA);
        }
    }

    private void consume(String s) throws ParseException {
        skipWhitespace();
        if (input.startsWith(s, pos)) {
            pos += s.length();
        } else {
            throw new ParseException(String.format(ERROR_EXPECTED_STRING_FORMAT, s));
        }
    }

    private boolean peekStartsWith(String s) {
        skipWhitespace();
        return input.startsWith(s, pos);
    }

    private void skipWhitespace() {
        while ((pos < input.length()) && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }
}
