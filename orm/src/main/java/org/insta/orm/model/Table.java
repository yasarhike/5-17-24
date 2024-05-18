package org.insta.orm.model;

import java.util.List;

/**
 * <p>
 * Represents a database table, containing a name and a list of columns.
 * This class provides methods to set and get the table name and the list of columns.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class Table {

    private String tableName;
    private List<Column> columnList;

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
