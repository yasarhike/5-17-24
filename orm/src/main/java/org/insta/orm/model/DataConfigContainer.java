package org.insta.orm.model;

import java.util.List;

/**
 * <p>
 * Encapsulates the configuration data required for building SQL queries.
 * This class includes the table name, a list of columns, join clauses, and where clauses.
 * It provides getter and setter methods for these attributes to facilitate query construction.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class DataConfigContainer {

    private String tableName;
    private List<Column> columnList;
    private List<JoinClause> joinClauseList;
    private List<WhereClause> whereClauses;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(final List<Column> columnList) {
        this.columnList = columnList;
    }

    public List<JoinClause> getJoinClauseList() {
        return joinClauseList;
    }

    public void setJoinClauseList(List<JoinClause> joinClauseList) {
        this.joinClauseList = joinClauseList;
    }

    public List<WhereClause> getWhereClauses() {
        return whereClauses;
    }

    public void setWhereClauses(final List<WhereClause> whereClauses) {
        this.whereClauses = whereClauses;
    }
}

