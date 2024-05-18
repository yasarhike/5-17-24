package org.insta.orm.querybuilder;

import org.insta.orm.model.Column;
import org.insta.orm.model.Keywords;
import org.insta.orm.model.Table;
import org.insta.orm.model.WhereClause;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Singleton class responsible for building SQL WHERE queries.
 * </p>
 *
 * <p>
 * This class provides methods to construct WHERE clauses for SQL queries based on the provided
 * {@link Table} and {@link WhereClause} objects. It handles placeholders, column names, and operators
 * to generate the WHERE part of SQL queries.
 * </p>
 *
 * <p>
 * The class follows the singleton pattern to ensure only one instance is created, and it provides methods
 * to generate WHERE clauses for standard queries and subqueries.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class WhereQueryBuilder {

    /**
     * <p>
     * Private constructor to prevent external instantiation.
     * </p>
     */
    private WhereQueryBuilder() {
    }

    /**
     * <p>
     * Returns the singleton instance of {@code WhereQueryBuilder}.
     * </p>
     *
     * @return the singleton instance of {@code WhereQueryBuilder}
     */
    public static WhereQueryBuilder getInstance() {
        return InstanceHolder.WHERE_QUERY_BUILDER;
    }

    /**
     * <p>
     * Constructs the column part of the WHERE clause based on the specified table.
     * </p>
     *
     * @param table the table containing the columns for the WHERE clause
     * @return the constructed column query as a {@code String}
     */
    public String getColumnQuery(final Table table) {
        final List<Column> columns = table.getColumnList();
        final List<String> resultSet = new ArrayList<>();

        for (final Column column : columns) {
            resultSet.add(String.join(".", column.getTableName(), column.getColumnName()));
        }

        return String.join(",", resultSet);
    }

    /**
     * <p>
     * Constructs the WHERE clause of the SQL query based on the specified list of WHERE clauses.
     * </p>
     *
     * @param whereClauses the list of WHERE clauses
     * @return the constructed WHERE clause as a {@code String}
     */
    public String getWhereQuery(final List<WhereClause> whereClauses) {
        final List<String> resultSet = new ArrayList<>();

        if (whereClauses != null) {
            for (final WhereClause whereClause : whereClauses) {
                if (Objects.isNull(whereClause.getOperator())) {
                    resultSet.add(setColumnPlaceholder(whereClause.getColumnName(), whereClause.getTableName()));
                } else {
                    resultSet.add(String.join(" ", setColumnPlaceholder(whereClause.getColumnName(),
                            whereClause.getTableName()), whereClause.getOperator()));
                }
            }
        }

        return buildFinalQuery(String.join(" ", resultSet));
    }

    /**
     * <p>
     * Constructs the WHERE clause for subqueries based on the specified list of WHERE clauses.
     * </p>
     *
     * @param whereClauses the list of WHERE clauses
     * @return the constructed WHERE clause for subqueries as a {@code String}
     */
    public String getWhereQueryForSubQuery(final List<WhereClause> whereClauses) {
        final List<String> resultSet = new ArrayList<>();

        if (whereClauses != null) {
            for (final WhereClause whereClause : whereClauses) {
                if (Objects.isNull(whereClause.getOperator())) {
                    resultSet.add(String.join(" ", whereClause.getTableName() + "." + whereClause.getColumnName(), Keywords.EQUALS.getValue()
                            , whereClause.getColumnName()));
                } else {
                    resultSet.add(String.join(" ", setColumnPlaceholder(whereClause.getColumnName(),
                            whereClause.getTableName()), whereClause.getOperator()));
                }
            }
        }

        return buildFinalQuery(String.join(" ", resultSet));
    }

    /**
     * <p>
     * Builds the final WHERE clause of the SQL query.
     * </p>
     *
     * @param query the WHERE clause to be built
     * @return the constructed WHERE clause as a {@code String}
     */
    public String buildFinalQuery(final String query) {
        return String.join(" ", Keywords.WHERE.getValue(), query);
    }

    /**
     * <p>
     * Sets the placeholder for column values in the SQL query.
     * </p>
     *
     * @param columnName the name of the column
     * @param tableName  the name of the table
     * @return the constructed placeholder as a {@code String}
     */
    public String setColumnPlaceholder(final String columnName, final String tableName) {
        return String.join(" ", tableName + "." + columnName, Keywords.EQUALS.getValue(), Keywords.PLACEHOLDER.getValue());
    }

    /**
     * <p>
     * Sets the placeholder for values in the SQL query.
     * </p>
     *
     * @param query the SQL query
     * @return the constructed placeholder as a {@code String}
     */
    public String setPlaceholder(final String query) {
        final List<String> columnFields = Arrays.asList(query.split(","));
        final List<String> resultSet = new ArrayList<>();

        for (int index = 0; index < columnFields.size(); index++) {
            resultSet.add(Keywords.PLACEHOLDER.getValue());
        }

        return String.join(" ,", resultSet);
    }

    /**
     * <p>
     * Sets brackets around the specified SQL query.
     * </p>
     *
     * @param query the SQL query
     * @return the SQL query enclosed in brackets as a {@code String}
     */
    public String setBrackets(final String query) {
        return String.join("", "(", query, ")");
    }

    /**
     * <p>
     * Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final WhereQueryBuilder WHERE_QUERY_BUILDER = new WhereQueryBuilder();
    }
}
