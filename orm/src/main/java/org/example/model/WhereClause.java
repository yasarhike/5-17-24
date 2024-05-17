package org.example.model;

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

    public void setOperator(final String operator) {
        this.operator = operator;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
