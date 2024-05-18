package org.insta.orm.model;

/**
 * <p>
 * Represents a SQL WHERE clause, containing a column name and an optional table name.
 * This class provides methods to set and get the column name and the table name.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class WhereClause {

    private String columnName;
    private String operator;
    private String tableName;

    public WhereClause(final String columnName, final String tableName) {
        this.columnName = columnName;
        this.tableName = tableName;
    }

    public WhereClause(final String columnName) {
        this (columnName, null);
    }

    public String getOperator() {
        return operator;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
