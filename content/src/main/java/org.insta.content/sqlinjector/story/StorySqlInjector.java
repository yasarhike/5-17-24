package org.insta.content.sqlinjector.story;

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
 * SQL injector for managing story in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete story.
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
public class StorySqlInjector {

    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;
    private final SelectQueryBuilder selectQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StorySqlInjector() {
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

        private static final StorySqlInjector storySqlInjector = new StorySqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of StorySqlInjector class.
     * </p>
     *
     * @return The singleton instance of StorySqlInjector class.
     */
    public static StorySqlInjector getInstance() {
        return InstanceHolder.storySqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the story table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "story"));

        dataConfigContainer.setTableName("story");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }


    /**
     * <p>
     * Builds and returns a SQL insert query for the story table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("user_id", "story"));
        columnList.add(new Column("caption", "story"));
        columnList.add(new Column("is_private", "story"));
        columnList.add(new Column("music", "story"));
        columnList.add(new Column("media", "story"));

        dataConfigContainer.setTableName("story");
        dataConfigContainer.setColumnList(columnList);

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL select query for retrieving data from the story table.
     * </p>
     *
     * @return The SQL select query string.
     */
    public String getSelectQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();
        final List<WhereClause> whereClauseList = new ArrayList<>();
        final List<JoinClause> joinClauseList = new ArrayList<>();

        columnList.add(new Column("id", "story"));
        columnList.add(new Column("user_id", "story"));
        columnList.add(new Column("name", "account"));
        columnList.add(new Column("caption", "story"));
        columnList.add(new Column("is_private", "story"));
        columnList.add(new Column("music", "story"));
        columnList.add(new Column("created_at", "story"));

        whereClauseList.add(new WhereClause("id", "story"));
        joinClauseList.add(new JoinClause("story", "user_id", "account", "id", JoinType.LEFT));
        dataConfigContainer.setTableName("reels");
        dataConfigContainer.setColumnList(columnList);
        dataConfigContainer.setWhereClauses(whereClauseList);
        dataConfigContainer.setJoinClauseList(joinClauseList);

        return selectQueryBuilder.buildSelectQuery(dataConfigContainer);
    }
}
