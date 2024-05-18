package org.insta.orm.querybuilder;

import org.insta.orm.model.DataConfigContainer;
import org.insta.orm.model.Keywords;

/**
 * <p>
 * Singleton class responsible for building SQL DELETE queries.
 * </p>
 *
 * <p>
 * This class provides methods to construct a DELETE SQL query based on the given
 * {@link DataConfigContainer}. It uses a {@link WhereQueryBuilder} to generate the
 * WHERE clause for the query.
 * </p>
 *
 * <p>
 * The class follows the singleton pattern to ensure only one instance is created,
 * and it provides a method to generate a DELETE query for a specified
 * {@link DataConfigContainer}.
 * </p>
 *
 * <p>
 * The DELETE query is constructed by assembling the table name and the WHERE condition.
 * </p>
 *
 * <p>
 * This class is thread-safe due to its singleton implementation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class DeleteQueryBuilder {

    private final WhereQueryBuilder whereQueryBuilder;

    /**
     * <p>
     * Private constructor to prevent external instantiation.
     * </p>
     */
    private DeleteQueryBuilder() {
        whereQueryBuilder = WhereQueryBuilder.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of {@code DeleteQueryBuilder}.
     * </p>
     *
     * @return the singleton instance of {@code DeleteQueryBuilder}
     */
    public static DeleteQueryBuilder getInstance() {
        return InstanceHolder.deleteQueryBuilder;
    }

    /**
     * <p>
     * Constructs the SQL DELETE query based on the specified {@link DataConfigContainer}.
     * </p>
     *
     * @param dataConfigContainer the data configuration container
     * @return the constructed SQL DELETE query as a {@code String}
     */
    public String buildDeleteQuery(final DataConfigContainer dataConfigContainer) {
        final String tableName = dataConfigContainer.getTableName();
        final String whereCondition = whereQueryBuilder.getWhereQuery(dataConfigContainer.getWhereClauses());

        return buildFinalQuery(tableName, whereCondition);
    }

    /**
     * <p>
     * Constructs the final DELETE query based on the specified table name and WHERE condition.
     * </p>
     *
     * @param tableName      the table name
     * @param whereCondition the WHERE condition
     * @return the constructed SQL DELETE query as a {@code String}
     */
    public String buildFinalQuery(final String tableName, final String whereCondition) {
        return String.join(" ", Keywords.DELETE.getValue(), Keywords.FROM.getValue(),
                tableName, whereCondition);
    }

    /**
     * <p>
     * Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder();
    }
}
