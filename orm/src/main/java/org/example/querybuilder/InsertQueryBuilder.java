package org.example.querybuilder;

import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.Keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsertQueryBuilder {

    private static InsertQueryBuilder insertQueryBuilder;
    private final QueryGenerator queryGenerator;

    private InsertQueryBuilder() {
        queryGenerator = QueryGenerator.getInstance();
    }

    public static InsertQueryBuilder getInstance() {
        return Objects.isNull(insertQueryBuilder) ? new InsertQueryBuilder() : insertQueryBuilder;
    }

    public String buildInsertQuery(final DataConfigContainer dataConfigContainer) {
        final String columnQuery = queryGenerator.setBrackets(buildColumnQuery(dataConfigContainer.getColumnList()));
        final String valueQuery = queryGenerator.setBrackets(queryGenerator.setPlaceholder(columnQuery));

        return buildFinalQuery(dataConfigContainer.getTableName(), columnQuery, valueQuery);
    }


    public String buildColumnQuery(final List<Column> columns) {
        final List<String> resultSet = new ArrayList<>();

        if(columns != null) {
            for (final Column column : columns) {
                resultSet.add(column.getColumnName());
            }
        }

        return String.join(", ", resultSet);
    }

    public String buildFinalQuery(final String tableName, final String columnQuery, final String valueQuery) {
        return String.join(" ", Keywords.INSERT.getValue(), Keywords.INTO.getValue(),
                tableName, columnQuery, Keywords.VALUES.getValue(), valueQuery);
    }
}
