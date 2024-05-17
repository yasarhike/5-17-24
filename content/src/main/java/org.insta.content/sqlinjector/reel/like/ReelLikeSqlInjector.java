package org.insta.content.sqlinjector.reel.like;

import org.example.querybuilder.DeleteQueryBuilder;
import org.example.querybuilder.InsertQueryBuilder;
import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.WhereClause;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL injector for managing reel like in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete reel like.
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
public class ReelLikeSqlInjector {

    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelLikeSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelLikeSqlInjector reelLikeSqlInjector = new ReelLikeSqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelLikeSqlInjector class.
     * </p>
     *
     * @return The singleton instance of ReelLikeSqlInjector class.
     */
    public static ReelLikeSqlInjector getInstance() {
        return InstanceHolder.reelLikeSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the reel like table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "reel_share"));

        dataConfigContainer.setTableName("reel_like");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the reel like table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("reel_id", "reel_like"));
        columnList.add(new Column("liked_by", "reel_like"));

        dataConfigContainer.setTableName("reel_like");
        dataConfigContainer.setColumnList(columnList);

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }
}
