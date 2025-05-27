package edu.kit.kastel.model;

/**
 * Represents a category node.
 *
 * @author uupyx
 */
public class Category extends Node {

    /**
     * Constructs a category.
     *
     * @param name the category name.
     */
    public Category(String name) {
        super(name);
    }

    /**
     * Returns the display name.
     *
     * @return display name.
     */
    @Override
    public String getDisplayName() {
        return name.toLowerCase();
    }

    /**
     * Returns the sort key.
     *
     * @return sort key.
     */
    @Override
    public String getSortKey() {
        return name.toLowerCase();
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
        if (!(o instanceof Category)) {
            return false;
        }
        Category c = (Category) o;
        return name.equalsIgnoreCase(c.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
