package org.insta.orm.model;

/**
 * <p>
 * Represents a database column, optionally associated with a specific table.
 * This class provides methods to set and get the column name and the table name.
 * It is used in query building and data configuration processes where columns need to be specified.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class Column {

    private String columnName;
    private String tableName;

    public Column(final String columnName) {
        this.columnName = columnName;
    }

    public Column(final String columnName, final String tableName) {
        this.columnName = columnName;
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }
}
