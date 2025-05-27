package edu.kit.kastel.model;

/**
 * Enum representing various predicates with their validation logic and inverse relationships.
 *
 * @author uupyx
 */
public enum Predicate {
    /**
     * The 'contains' relationship.
     */
    CONTAINS("contains") {
        @Override
        public boolean validate(Node source, Node target) {
            return source instanceof Category;
        }
    },
    /**
     * The 'contained-in' relationship.
     */
    CONTAINED_IN("contained-in") {
        @Override
        public boolean validate(Node source, Node target) {
            return target instanceof Category;
        }
    },
    /**
     * The 'part-of' relationship.
     */
    PART_OF("part-of") {
        @Override
        public boolean validate(Node source, Node target) {
            return source instanceof Product && target instanceof Product;
        }
    },
    /**
     * The 'has-part' relationship.
     */
    HAS_PART("has-part") {
        @Override
        public boolean validate(Node source, Node target) {
            return source instanceof Product && target instanceof Product;
        }
    },
    /**
     * The 'successor-of' relationship.
     */
    SUCCESSOR_OF("successor-of") {
        @Override
        public boolean validate(Node source, Node target) {
            return source instanceof Product && target instanceof Product;
        }
    },
    /**
     * The 'predecessor-of' relationship.
     */
    PREDECESSOR_OF("predecessor-of") {
        @Override
        public boolean validate(Node source, Node target) {
            return source instanceof Product && target instanceof Product;
        }
    };

    static {
        CONTAINS.inverse = CONTAINED_IN;
        CONTAINED_IN.inverse = CONTAINS;
        PART_OF.inverse = HAS_PART;
        HAS_PART.inverse = PART_OF;
        SUCCESSOR_OF.inverse = PREDECESSOR_OF;
        PREDECESSOR_OF.inverse = SUCCESSOR_OF;
    }

    private static final String LABEL_REPLACEMENT = "-";

    private final String name;
    private Predicate inverse;

    Predicate(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the predicate.
     *
     * @return the name of the predicate.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the inverse predicate.
     *
     * @return the inverse predicate.
     */
    public Predicate getInverse() {
        return inverse;
    }

    /**
     * Validates the relationship between source and target nodes.
     *
     * @param source the source node.
     * @param target the target node.
     * @return true if the relationship is valid, false otherwise.
     */
    public abstract boolean validate(Node source, Node target);

    /**
     * Gets the ordinal value of the predicate.
     *
     * @return the ordinal value + 1.
     */
    public int getOrder() {
        int order = ordinal();
        order++;
        return order;
    }

    /**
     * Gets the label of the predicate.
     *
     * @return the label.
     */
    public String getLabel() {
        return name.replace(LABEL_REPLACEMENT, "");
    }

    /**
     * Get a predicate by its name.
     *
     * @param name The name of the predicate.
     * @return The predicate which was found.
     */
    public static Predicate get(String name) {
        for (Predicate predicate : values()) {
            if (predicate.name.equals(name)) {
                return predicate;
            }
        }

        return null;
    }
}