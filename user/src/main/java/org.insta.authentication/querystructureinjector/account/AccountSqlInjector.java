package org.insta.authentication.querystructureinjector.account;

import org.example.querybuilder.DeleteQueryBuilder;
import org.example.querybuilder.InsertQueryBuilder;
import org.example.querybuilder.SelectQueryBuilder;
import org.example.querybuilder.UpdateQueryBuilder;
import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.JoinClause;
import org.example.model.JoinType;
import org.example.model.WhereClause;
import org.insta.authentication.dao.UserAccountDAO;
import org.insta.authentication.dao.UserAccountDAOImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * SQL injector for managing account in the database.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete user account.
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
public class AccountSqlInjector {

    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;
    private final UpdateQueryBuilder updateQueryBuilder;
    private final SelectQueryBuilder selectQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict object creation outside the class
     * </p>
     */
    private AccountSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
        updateQueryBuilder = UpdateQueryBuilder.getInstance();
        selectQueryBuilder = SelectQueryBuilder.getInstance();
    }

    /**
     * <p>
     * Creates a singleton instance of AccountSqlInjector class if it is not already created.
     * </p>
     *
     * @return the singleton instance of AddressSqlInjector class.
     */
    public static AccountSqlInjector getInstance() {
        return InstanceHolder.accountSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the account table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "account"));

        dataConfigContainer.setTableName("account");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the account table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getInsertQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("name"));
        columnList.add(new Column("mobile"));
        columnList.add(new Column("email"));
        columnList.add(new Column("password"));

        dataConfigContainer.setTableName("account");
        dataConfigContainer.setColumnList(columnList);

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL update query for the account table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getUpdateQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        columnList.add(new Column("name"));
        columnList.add(new Column("mobile"));
        columnList.add(new Column("email"));
        columnList.add(new Column("password"));

        whereClauseList.add(new WhereClause("id", "account"));

        dataConfigContainer.setTableName("account");
        dataConfigContainer.setColumnList(columnList);
        dataConfigContainer.setWhereClauses(whereClauseList);

        return updateQueryBuilder.getUpdateQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL retrieve query for the account table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getRetrieveQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();
        final List<JoinClause> joinClauseList = new ArrayList<>();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        joinClauseList.add(new JoinClause("account", "id", "address", "user_id", JoinType.LEFT));
        whereClauseList.add(new WhereClause("id", "account"));

        dataConfigContainer.setTableName("account");
        dataConfigContainer.setColumnList(columnList);
        dataConfigContainer.setJoinClauseList(joinClauseList);
        dataConfigContainer.setWhereClauses(whereClauseList);

        return selectQueryBuilder.buildSelectQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL retrieve query for the account table for mobile.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getSelectForMobile() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();
        final List<WhereClause> whereClauses = new ArrayList<>();

        columnList.add(new Column("mobile", "account"));
        whereClauses.add(new WhereClause("mobile", "account"));

        dataConfigContainer.setTableName("account");
        dataConfigContainer.setWhereClauses(whereClauses);
        dataConfigContainer.setColumnList(columnList);

        return selectQueryBuilder.buildSelectQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL retrieve query for the account for mobile.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getSelectForEmail() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();
        final List<WhereClause> whereClauses = new ArrayList<>();

        columnList.add(new Column("email", "account"));
        whereClauses.add(new WhereClause("email", "account"));

        dataConfigContainer.setTableName("account");
        dataConfigContainer.setWhereClauses(whereClauses);
        dataConfigContainer.setColumnList(columnList);

        return selectQueryBuilder.buildSelectQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL retrieve query for the account table for user name.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getSelectForName() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();
        final List<WhereClause> whereClauses = new ArrayList<>();

        columnList.add(new Column("name", "account"));
        whereClauses.add(new WhereClause("name", "account"));

        dataConfigContainer.setTableName("account");
        dataConfigContainer.setWhereClauses(whereClauses);
        dataConfigContainer.setColumnList(columnList);

        return selectQueryBuilder.buildSelectQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final AccountSqlInjector accountSqlInjector = new AccountSqlInjector();
    }
}
