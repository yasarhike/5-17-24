package org.insta.authentication.querystructureinjector.address;

import org.example.querybuilder.DeleteQueryBuilder;
import org.example.querybuilder.InsertQueryBuilder;
import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.WhereClause;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL injector for managing user address.
 * </p>
 *
 * <p>
 * This class provides methods for generating SQL queries to insert and delete user address.
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
public class AddressSqlInjector {

    private static AddressSqlInjector addressSqlInjector;
    private final DeleteQueryBuilder deleteQueryBuilder;
    private final InsertQueryBuilder insertQueryBuilder;

    /**
     * <p>
     * Private constructor to restrict object creation outside the class
     * </p>
     */
    private AddressSqlInjector() {
        deleteQueryBuilder = DeleteQueryBuilder.getInstance();
        insertQueryBuilder = InsertQueryBuilder.getInstance();
    }

    /**
     * <p>
     * Creates a singleton instance of AddressSqlInjector class if it is not already created.
     * </p>
     *
     * @return the singleton instance of AddressSqlInjector class.
     */
    public static AddressSqlInjector getInstance() {
        return InstanceHolder.addressSqlInjector;
    }

    /**
     * <p>
     * Builds and returns a SQL delete query for the address table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getDeleteQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<WhereClause> whereClauseList = new ArrayList<>();

        whereClauseList.add(new WhereClause("id", "address"));

        dataConfigContainer.setTableName("address");
        dataConfigContainer.setWhereClauses(whereClauseList);

        return deleteQueryBuilder.buildDeleteQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Builds and returns a SQL insert query for the address table.
     * </p>
     *
     * @return The SQL delete query string.
     */
    public String getCreateQuery() {
        final DataConfigContainer dataConfigContainer = new DataConfigContainer();
        final List<Column> columnList = new ArrayList<>();

        columnList.add(new Column("door_no", "address"));
        columnList.add(new Column("state", "address"));
        columnList.add(new Column("user_id", "address"));

        dataConfigContainer.setColumnList(columnList);
        dataConfigContainer.setTableName("address");

        return insertQueryBuilder.buildInsertQuery(dataConfigContainer);
    }

    /**
     * <p>
     * Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final AddressSqlInjector addressSqlInjector = new AddressSqlInjector();
    }
}
