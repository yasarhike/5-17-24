package org.insta.orm.querybuilder;

import org.insta.orm.model.JoinClause;
import org.insta.orm.model.Keywords;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Singleton class responsible for building SQL join queries.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class JoinQueryBuilder {

    /**
     * <p>
     * Private constructor to prevent instantiation from outside the class.
     * </p>
     */
    private JoinQueryBuilder() {
    }

    /**
     * <p>
     * Creates the singleton instance of the class.
     * </p>
     */
    public static JoinQueryBuilder getInstance() {
        return InstanceHolder.joinQueryBuilder;
    }

    /**
     * Builds the SQL join query string based on the provided list of {@link JoinClause} objects and the table name.
     *
     * @param joinClauseList the list of join clauses to be included in the join query.
     * @param tableName      the name of the table to join with.
     * @return the constructed join query string.
     */
    public String buildJoinQuery(final List<JoinClause> joinClauseList, final String tableName) {
        final List<String> resultSet = new ArrayList<>();

        if (joinClauseList != null) {
            for (final JoinClause joinClause : joinClauseList) {
                resultSet.add(String.join(" ", joinClause.getJoinType().getValue(), joinClause.getRightTable(),
                        Keywords.ON.getValue(), buildDotPart(joinClause.getLeftTable(), joinClause.getLeftColumn()),
                        Keywords.EQUALS.getValue(), buildDotPart(joinClause.getRightTable(), joinClause.getRightColumn())));
            }
        }

        return String.join(" ", resultSet);
    }

    /**
     * Builds a dot-separated string for column references in the format 'table.column'.
     *
     * @param columnTwo the name of the column.
     * @param columnOne the name of the column.
     * @return the dot-separated column reference string.
     */
    public String buildDotPart(final String columnOne, final String columnTwo) {
        return String.join("", columnOne, ".", columnTwo);
    }

    /**
     * <p>
     * Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final JoinQueryBuilder joinQueryBuilder = new JoinQueryBuilder();
    }
}
