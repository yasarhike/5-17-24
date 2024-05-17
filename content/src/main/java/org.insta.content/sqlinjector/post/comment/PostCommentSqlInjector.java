package org.insta.content.sqlinjector.post.comment;

import org.example.querybuilder.DeleteQueryBuilder;
import org.example.querybuilder.InsertQueryBuilder;
import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.WhereClause;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL injector for managing post comments in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete post comments.
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
public class PostCommentSqlInjector {

    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostCommentSqlInjector postCommentSqlInjector = new PostCommentSqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentSqlInjector class.
     * </p>
     *
     * @return The singleton instance of PostCommentSqlInjector class.
     */
    public static PostCommentSqlInjector getInstance() {
        return InstanceHolder.postCommentSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the post comment table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "post_comment"));

        dataConfigContainer.setTableName("post_comment");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the post comment table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("post_id", "post_comment"));
        columnList.add(new Column("commented_by", "post_comment"));
        columnList.add(new Column("content", "post_comment"));

        dataConfigContainer.setTableName("post_comment");
        dataConfigContainer.setColumnList(columnList);

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }
}
