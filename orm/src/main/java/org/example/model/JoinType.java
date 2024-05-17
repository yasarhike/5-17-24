package org.example.model;

/**
 * <p>
 * Enum representing the different types of SQL join operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public enum JoinType {

    INNER("inner"), OUTER("outer"), JOIN("join"), LEFT("left join");

    private final String value;

    JoinType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
