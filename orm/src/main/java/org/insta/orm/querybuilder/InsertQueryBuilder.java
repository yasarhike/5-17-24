package org.insta.orm.querybuilder;

import org.insta.orm.model.Column;
import org.insta.orm.model.DataConfigContainer;
import org.insta.orm.model.Keywords;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Singleton class responsible for building SQL INSERT queries.
 * </p>
 *
 * <p>
 * This class provides methods to construct an INSERT SQL query based on the given
 * {@link DataConfigContainer}. It uses a {@link WhereQueryBuilder} to generate
 * placeholders for column values in the query.
 * </p>
 *
 * <p>
 * The class follows the singleton pattern to ensure only one instance is created,
 * and it provides a method to generate an INSERT query for a specified
 * {@link DataConfigContainer}.
 * </p>
 *
 * <p>
 * The INSERT query is constructed by assembling the table name, column names, and
 * placeholder values for the INSERT operation.
 * </p>
 *
 * <p>
 * This class is thread-safe due to its singleton implementation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class InsertQueryBuilder {

    private final WhereQueryBuilder whereQueryBuilder;

    /**
     * <p>
     * Private constructor to prevent external instantiation.
     * </p>
     */
    private InsertQueryBuilder() {
        whereQueryBuilder = WhereQueryBuilder.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of {@code InsertQueryBuilder}.
     * </p>
     *
     * @return the singleton instance of {@code InsertQueryBuilder}
     */
    public static InsertQueryBuilder getInstance() {
        return InstanceHolder.insertQueryBuilder;
    }

    /**
     * <p>
     * Constructs the SQL INSERT query based on the specified {@link DataConfigContainer}.
     * </p>
     *
     * @param dataConfigContainer the data configuration container
     * @return the constructed SQL INSERT query as a {@code String}
     */
    public String buildInsertQuery(final DataConfigContainer dataConfigContainer) {
        final String columnQuery = whereQueryBuilder.setBrackets(buildColumnQuery(dataConfigContainer.getColumnList()));
        final String valueQuery = whereQueryBuilder.setBrackets(whereQueryBuilder.setPlaceholder(columnQuery));

        return buildFinalQuery(dataConfigContainer.getTableName(), columnQuery, valueQuery);
    }

    /**
     * <p>
     * Constructs the column part of the INSERT query based on the specified list of columns.
     * </p>
     *
     * @param columns the list of columns
     * @return the constructed column query as a {@code String}
     */
    public String buildColumnQuery(final List<Column> columns) {
        final List<String> resultSet = new ArrayList<>();

        if (columns != null) {
            for (final Column column : columns) {
                resultSet.add(column.getColumnName());
            }
        }

        return String.join(", ", resultSet);
    }

    /**
     * <p>
     * Constructs the final INSERT query based on the specified table name, column query, and value query.
     * </p>
     *
     * @param tableName   the table name
     * @param columnQuery the column query
     * @param valueQuery  the value query
     * @return the constructed SQL INSERT query as a {@code String}
     */
    public String buildFinalQuery(final String tableName, final String columnQuery, final String valueQuery) {
        return String.join(" ", Keywords.INSERT.getValue(), Keywords.INTO.getValue(),
                tableName, columnQuery, Keywords.VALUES.getValue(), valueQuery);
    }

    /**
     * <p>
     * Static inner class to hold the singleton instance of {@code InsertQueryBuilder}.
     * </p>
     */
    private static class InstanceHolder {

        private static final InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder();
    }
}
