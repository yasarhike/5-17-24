package org.insta.orm.model;

/**
 * <p>
 * Representing the different types of Keywords in sql.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public enum Keywords {

    INSERT("insert"), UPDATE("update"), DELETE("delete"), FROM("from"),
    WHERE("where"), EQUALS("="), PLACEHOLDER("?"),
    INTO("into"), VALUES("values"), SET("set"),
    ON("on"), COLUMN("column"), SELECT("select");

    private final String value;

    Keywords(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
