package org.example.querybuilder;

import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.JoinQueryBuilder;
import org.example.model.Keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectQueryBuilder {

    private static SelectQueryBuilder selectQueryBuilder;
    private final JoinQueryBuilder joinQueryBuilder;
    private final QueryGenerator queryGenerator;

    private SelectQueryBuilder() {
        joinQueryBuilder = JoinQueryBuilder.getInstance();
        queryGenerator = QueryGenerator.getInstance();
    }

    public static SelectQueryBuilder getInstance() {
        return Objects.isNull(selectQueryBuilder) ? new SelectQueryBuilder() : selectQueryBuilder;
    }

    public String buildSelectQuery(final DataConfigContainer dataConfigContainer) {
        final String columnQuery = buildColumnQuery(dataConfigContainer);
        final String joinQuery = joinQueryBuilder.buildJoinQuery(dataConfigContainer.getJoinClauseList(), "account");
        final String whereQuery = queryGenerator.getWhereQuery(dataConfigContainer.getWhereClauses());

        return buildFinalQuery(columnQuery, joinQuery, whereQuery, dataConfigContainer.getTableName());
    }

    public String buildColumnQuery(final DataConfigContainer dataConfigContainer) {
        final List<String> resultSet = new ArrayList<>();

        if (dataConfigContainer.getColumnList() != null) {
            for (final Column column : dataConfigContainer.getColumnList()) {
                resultSet.add(String.join("", column.getTableName(), ".", column.getColumnName()));
            }
        }
        return String.join(", ", resultSet);
    }

    public String buildFinalQuery(final String columnQuery, final String joinQuery, final String whereQuery, final String tableName) {
        return String.join(" ", Keywords.SELECT.getValue(), columnQuery.isEmpty() ? "*" : columnQuery
                , Keywords.FROM.getValue(), tableName, joinQuery, whereQuery);
    }
}
