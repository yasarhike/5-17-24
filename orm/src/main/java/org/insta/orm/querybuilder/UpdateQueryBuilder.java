package org.insta.orm.querybuilder;

import org.insta.orm.model.Column;
import org.insta.orm.model.DataConfigContainer;
import org.insta.orm.model.Keywords;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Singleton class responsible for building SQL UPDATE queries.
 * </p>
 * *
 * <p>
 * This class provides methods to construct an UPDATE SQL query based on the given
 * {@link DataConfigContainer}. It uses a {@link WhereQueryBuilder} to generate the
 * WHERE clause for the query.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class UpdateQueryBuilder {

    private final WhereQueryBuilder whereQueryBuilder;

    /**
     * <p>
     * Private constructor to prevent instantiation.
     * </p>
     */
    private UpdateQueryBuilder() {
        whereQueryBuilder = WhereQueryBuilder.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of {@code UpdateQueryBuilder}.
     * </p>
     *
     * @return the singleton instance of {@code UpdateQueryBuilder}
     */
    public static UpdateQueryBuilder getInstance() {
        return InstanceHolder.updateQueryBuilder;
    }

    /**
     * <p>
     * Builds an UPDATE SQL query based on the specified {@link DataConfigContainer}.
     * </p>
     *
     * @param dataConfigContainer the data configuration container containing the table name,
     *                            columns to update, and WHERE clause information
     * @return the constructed UPDATE SQL query as a {@code String}
     */
    public String getUpdateQuery(final DataConfigContainer dataConfigContainer) {
        final String setQuery = buildSetQuery(dataConfigContainer.getColumnList());
        final String whereCondition = whereQueryBuilder.getWhereQuery(dataConfigContainer.getWhereClauses());

        return buildFinalQuery(dataConfigContainer.getTableName(), setQuery, whereCondition);
    }

    /**
     * <p>
     * Builds the SET clause of the UPDATE SQL query.
     * </p>
     *
     * @param columnList the list of columns to be updated
     * @return the constructed SET clause as a {@code String}
     */
    public String buildSetQuery(final List<Column> columnList) {
        final List<String> resultSet = new ArrayList<>();

        for (final Column column : columnList) {
            resultSet.add(String.join(" ", column.getColumnName(), Keywords.EQUALS.getValue(),
                    Keywords.PLACEHOLDER.getValue()));
        }

        return String.join(", ", resultSet);
    }

    /**
     * <p>
     * Builds the final UPDATE SQL query string using the specified table name, SET clause,
     * and WHERE condition.
     * </p>
     *
     * @param tableName      the name of the table to be updated
     * @param column         the SET clause specifying columns and values to update
     * @param whereCondition the WHERE condition to specify which records to update
     * @return the constructed UPDATE SQL query as a {@code String}
     */
    public String buildFinalQuery(final String tableName, final String column, final String whereCondition) {
        return String.join(" ", Keywords.UPDATE.getValue(), tableName, Keywords.SET.getValue(),
                column, whereCondition);
    }

    /**
     * <p>
     * Static inner class to hold the singleton instance of {@code UpdateQueryBuilder}.
     * </p>
     */
    private static class InstanceHolder {
        private static final UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder();
    }
}
