package org.example.model;

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
public class JoinClause {

    private String leftTable;
    private String rightTable;
    private String leftColumn;
    private String rightColumn;
    private JoinType joinType;

    public JoinClause(final String leftTable, final String leftColumn, final String righTable, final String rightColumn,
                      final JoinType joinType) {
        this.leftTable = leftTable;
        this.leftColumn = leftColumn;
        this.rightTable = righTable;
        this.rightColumn = rightColumn;
        this.joinType = joinType;
    }
    public JoinType getJoinType() {
        return joinType;
    }

    public String getRightColumn() {
        return rightColumn;
    }

    public String getLeftColumn() {
        return leftColumn;
    }

    public String getRightTable() {
        return rightTable;
    }

    public String getLeftTable() {
        return leftTable;
    }
}
