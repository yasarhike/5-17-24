package org.insta.content.sqlinjector.post.like;

import org.insta.orm.querybuilder.DeleteQueryBuilder;
import org.insta.orm.querybuilder.InsertQueryBuilder;
import org.insta.orm.model.Column;
import org.insta.orm.model.DataConfigContainer;
import org.insta.orm.model.WhereClause;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL injector for managing post like in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete post like.
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
public class PostLikeSqlInjector {

    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostLikeSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostLikeSqlInjector postLikeSqlInjector = new PostLikeSqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeSqlInjector class.
     * </p>
     *
     * @return The singleton instance of PostLikeSqlInjector class.
     */
    public static PostLikeSqlInjector getInstance() {
        return InstanceHolder.postLikeSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the post like table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "post_like"));

        dataConfigContainer.setTableName("post_like");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the post like table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("post_id", "post_like"));
        columnList.add(new Column("liked_by", "post_like"));

        dataConfigContainer.setTableName("post_like");
        dataConfigContainer.setColumnList(columnList);

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }
}
