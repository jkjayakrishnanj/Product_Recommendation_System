package edu.kit.kastel.model;

/**
 * Represents a product node.
 *
 * @author uupyx
 */
public class Product extends Node {

    private static final String DISPLAY_SEPARATOR = ":";
    private static final String SORTKEY_FORMAT = "%s %04d";

    /** The product identifier. */
    public final int id;

    /**
     * Constructs a product.
     *
     * @param name the product name.
     * @param id the product ID.
     */
    public Product(String name, int id) {
        super(name);
        this.id = id;
    }

    /**
     * Returns the display name.
     *
     * @return display name.
     */
    @Override
    public String getDisplayName() {
        return name.toLowerCase() + DISPLAY_SEPARATOR + id;
    }

    /**
     * Returns the sort key.
     *
     * @return sort key.
     */
    @Override
    public String getSortKey() {
        return String.format(SORTKEY_FORMAT, name.toLowerCase(), id);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product p = (Product) o;
        return name.equalsIgnoreCase(p.name) && id == p.id;
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode() * 31 + id;
    }
}
