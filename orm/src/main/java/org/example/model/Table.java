package org.example.model;

import java.util.List;

public class Table {

    private String tableName;
    private List<Column> columnList;

    public Table() {
    }

    public Table (final String tableName, final List<Column> columnList) {
        this.tableName = tableName;
        this.columnList = columnList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(final List<Column> columnList) {
        this.columnList = columnList;
    }

}
