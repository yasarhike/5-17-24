package org.insta.content.sqlinjector.story.like;

import org.insta.orm.querybuilder.DeleteQueryBuilder;
import org.insta.orm.querybuilder.InsertQueryBuilder;
import org.insta.orm.model.Column;
import org.insta.orm.model.DataConfigContainer;
import org.insta.orm.model.WhereClause;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL injector for managing story like in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete story like.
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
public class StoryLikeSqlInjector {

    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryLikeSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final StoryLikeSqlInjector storyLikeSqlInjector = new StoryLikeSqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryLikeSqlInjector class.
     * </p>
     *
     * @return The singleton instance of StoryLikeSqlInjector class.
     */
    public static StoryLikeSqlInjector getInstance() {
        return InstanceHolder.storyLikeSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the story like table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "story_like"));

        dataConfigContainer.setTableName("story_like");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the story like table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("story_id", "story_like"));
        columnList.add(new Column("liked_by", "story_like"));

        dataConfigContainer.setColumnList(columnList);
        dataConfigContainer.setTableName("story_like");

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }
}
