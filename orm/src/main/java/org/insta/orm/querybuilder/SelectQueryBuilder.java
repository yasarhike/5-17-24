package org.insta.orm.querybuilder;

import org.insta.orm.model.Column;
import org.insta.orm.model.DataConfigContainer;
import org.insta.orm.model.Keywords;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Singleton class responsible for building SQL SELECT queries.
 * </p>
 *
 * <p>
 * This class provides methods to construct a SELECT SQL query based on the given
 * {@link DataConfigContainer}. It uses a {@link JoinQueryBuilder} to generate the
 * JOIN clause and a {@link WhereQueryBuilder} to generate the WHERE clause for the query.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class SelectQueryBuilder {

    private final JoinQueryBuilder joinQueryBuilder;
    private final WhereQueryBuilder whereQueryBuilder;

    /**
     * <p>
     * Private constructor to prevent instantiation.
     * </p>
     */
    private SelectQueryBuilder() {
        joinQueryBuilder = JoinQueryBuilder.getInstance();
        whereQueryBuilder = WhereQueryBuilder.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of {@code SelectQueryBuilder}.
     *
     * @return the singleton instance of {@code SelectQueryBuilder}
     */
    public static SelectQueryBuilder getInstance() {
        return InstanceHolder.selectQueryBuilder;
    }

    /**
     * <p>
     * Builds a SELECT SQL query based on the specified {@link DataConfigContainer}.
     * </p>
     *
     * @param dataConfigContainer the data configuration container containing the table name,
     *                            columns to select, JOIN clauses, and WHERE clause information
     * @return the constructed SELECT SQL query as a {@code String}
     */
    public String buildSelectQuery(final DataConfigContainer dataConfigContainer) {
        final String columnQuery = buildColumnQuery(dataConfigContainer);
        final String joinQuery = joinQueryBuilder.buildJoinQuery(dataConfigContainer.getJoinClauseList(), "account");
        final String whereQuery = whereQueryBuilder.getWhereQuery(dataConfigContainer.getWhereClauses());

        return buildFinalQuery(columnQuery, joinQuery, whereQuery, dataConfigContainer.getTableName());
    }

    /**
     * <p>
     * Builds the column part of the SELECT SQL query.
     * </p>
     *
     * @param dataConfigContainer the data configuration container containing the columns to select
     * @return the constructed column query as a {@code String}
     */
    public String buildColumnQuery(final DataConfigContainer dataConfigContainer) {
        final List<String> resultSet = new ArrayList<>();

        if (dataConfigContainer.getColumnList() != null) {
            for (final Column column : dataConfigContainer.getColumnList()) {
                resultSet.add(String.join("", column.getTableName(), ".", column.getColumnName()));
            }
        }
        return String.join(", ", resultSet);
    }

    /**
     * <p>
     * Builds the final SELECT SQL query string using the specified column query, JOIN query,
     * WHERE condition, and table name.
     * </p>
     *
     * @param columnQuery the column part of the SELECT query
     * @param joinQuery   the JOIN part of the SELECT query
     * @param whereQuery  the WHERE condition of the SELECT query
     * @param tableName   the name of the table to select data from
     * @return the constructed SELECT SQL query as a {@code String}
     */
    public String buildFinalQuery(final String columnQuery, final String joinQuery, final String whereQuery, final String tableName) {
        return String.join(" ", Keywords.SELECT.getValue(), columnQuery.isEmpty() ? "*" : columnQuery
                , Keywords.FROM.getValue(), tableName, joinQuery, whereQuery);
    }

    /**
     * <p>
     * Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder();
    }
}
