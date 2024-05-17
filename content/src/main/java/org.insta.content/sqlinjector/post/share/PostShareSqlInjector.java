package org.insta.content.sqlinjector.post.share;

import org.example.querybuilder.DeleteQueryBuilder;
import org.example.querybuilder.InsertQueryBuilder;
import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.WhereClause;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL injector for managing post share in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete post share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see DeleteQueryBuilder For building delete queries.
 * @see InsertQueryBuilder For building insert queries.
 * @see DataConfigContainer Represents configuration data for SQL queries.
 * @see WhereClause Represents a WHERE clause in a SQL query.
 * @see Column Represents a column in a database table.
 */
public class PostShareSqlInjector {

    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostShareSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostShareSqlInjector postShareSqlInjector = new PostShareSqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of PostShareSqlInjector class.
     * </p>
     *
     * @return The singleton instance of PostShareSqlInjector class.
     */
    public static PostShareSqlInjector getInstance() {
        return InstanceHolder.postShareSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the post share table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "post_share"));

        dataConfigContainer.setTableName("post_share");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the post share table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("post_id", "post_share"));
        columnList.add(new Column("shared_by", "post_share"));

        dataConfigContainer.setTableName("post_share");
        dataConfigContainer.setColumnList(columnList);

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }
}
