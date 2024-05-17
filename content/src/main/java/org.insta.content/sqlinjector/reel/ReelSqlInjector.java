package org.insta.content.sqlinjector.reel;

import org.example.querybuilder.DeleteQueryBuilder;
import org.example.querybuilder.InsertQueryBuilder;
import org.example.querybuilder.SelectQueryBuilder;
import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.JoinClause;
import org.example.model.JoinType;
import org.example.model.SubQueryClause;
import org.example.model.WhereClause;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * SQL injector for managing reel in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete reel.
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
public class ReelSqlInjector {

    private static ReelSqlInjector reelSqlInjector;
    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;
    private final SelectQueryBuilder selectQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelSqlInjector() {
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

        private static final ReelSqlInjector reelSqlInjector = new ReelSqlInjector();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelSqlInjector class.
     * </p>
     *
     * @return The singleton instance of ReelSqlInjector class.
     */
    public static ReelSqlInjector getInstance() {
        return InstanceHolder.reelSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the reel table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "reels"));

        dataConfigContainer.setTableName("reels");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the reel table.
     * </p>
     *
     * @return The SQL insert query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("user_id", "reels"));
        columnList.add(new Column("caption", "reels"));
        columnList.add(new Column("duration", "reels"));

        dataConfigContainer.setTableName("reels");
        dataConfigContainer.setColumnList(columnList);

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL select query for retrieving data from the reel table.
     * </p>
     *
     * @return The SQL select query string.
     */
    public String getSelectQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();
        final List<WhereClause> whereClauseList = new ArrayList<>();
        final List<SubQueryClause> subQueryClauses = new ArrayList<>();
        final List<JoinClause> joinClauseList = new ArrayList<>();

        columnList.add(new Column("id", "reels"));
        columnList.add(new Column("user_id", "reels"));
        columnList.add(new Column("name", "account"));
        columnList.add(new Column("caption", "reels"));
        columnList.add(new Column("is_private", "reels"));
        columnList.add(new Column("created_at", "reels"));
        columnList.add(new Column("duration", "reels"));

        whereClauseList.add(new WhereClause("id", "reels"));
        joinClauseList.add(new JoinClause("reels", "user_id", "account", "id", JoinType.LEFT));
        dataConfigContainer.setTableName("reels");
        dataConfigContainer.setColumnList(columnList);
        dataConfigContainer.setWhereClauses(whereClauseList);
        dataConfigContainer.setJoinClauseList(joinClauseList);

        return selectQueryBuilder.buildSelectQuery(dataConfigContainer);
    }
}
