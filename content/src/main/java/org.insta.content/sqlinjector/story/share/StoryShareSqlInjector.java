package org.insta.content.sqlinjector.story.share;

import org.example.querybuilder.DeleteQueryBuilder;
import org.example.querybuilder.InsertQueryBuilder;
import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.WhereClause;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL injector for managing story share in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete story share.
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
public class StoryShareSqlInjector {

    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryShareSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final StoryShareSqlInjector storyLikeSqlInjector = new StoryShareSqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryShareSqlInjector class.
     * </p>
     *
     * @return The singleton instance of StoryShareSqlInjector class.
     */
    public static StoryShareSqlInjector getInstance() {
        return InstanceHolder.storyLikeSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the story share table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "story_share"));

        dataConfigContainer.setTableName("story_share");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the story share table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("story_id", "story_share"));
        columnList.add(new Column("shared_by", "story_share"));

        dataConfigContainer.setColumnList(columnList);
        dataConfigContainer.setTableName("story_share");

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }
}
