package org.insta.content.sqlinjector.post;

import org.insta.orm.querybuilder.DeleteQueryBuilder;
import org.insta.orm.querybuilder.InsertQueryBuilder;
import org.insta.orm.querybuilder.SelectQueryBuilder;
import org.insta.orm.model.Column;
import org.insta.orm.model.DataConfigContainer;
import org.insta.orm.model.JoinClause;
import org.insta.orm.model.JoinType;
import org.insta.orm.model.WhereClause;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL injector for managing post in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert, retrieve and delete post comments.
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
public class PostSqlInjector {

    private static PostSqlInjector postSqlInjector;
    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;
    private final SelectQueryBuilder selectQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
        selectQueryBuilder = SelectQueryBuilder.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostSqlInjector postSqlInjector = new PostSqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of PostSqlInjector class.
     * </p>
     *
     * @return The singleton instance of PostSqlInjector class.
     */
    public static PostSqlInjector getInstance() {
        return InstanceHolder.postSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the post table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "post"));

        dataConfigContainer.setTableName("post");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the post table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("user_id", "post"));
        columnList.add(new Column("caption", "post"));
        columnList.add(new Column("is_private", "post"));
        columnList.add(new Column("type", "post"));

        dataConfigContainer.setTableName("post");
        dataConfigContainer.setColumnList(columnList);

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL select query for retrieving data from the post table.
     * </p>
     *
     * @return The SQL select query string.
     */
    public String getRetrieveQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();
        final List<WhereClause> whereClauseList = new ArrayList<>();
        final List<JoinClause> joinClauseList = new ArrayList<>();

        columnList.add(new Column("id", "post"));
        columnList.add(new Column("user_id", "post"));
        columnList.add(new Column("name", "account"));
        columnList.add(new Column("caption", "post"));
        columnList.add(new Column("type", "post"));
        columnList.add(new Column("is_private", "post"));
        columnList.add(new Column("created_at", "post"));

        whereClauseList.add(new WhereClause("id", "post"));
        joinClauseList.add(new JoinClause("post", "user_id", "account", "id", JoinType.LEFT));
        dataConfigContainer.setTableName("post");
        dataConfigContainer.setColumnList(columnList);
        dataConfigContainer.setWhereClauses(whereClauseList);
        dataConfigContainer.setJoinClauseList(joinClauseList);

        return selectQueryBuilder.buildSelectQuery(dataConfigContainer);
    }
}
