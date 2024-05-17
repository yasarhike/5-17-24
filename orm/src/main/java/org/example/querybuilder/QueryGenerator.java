package org.example.querybuilder;

import org.example.model.Column;
import org.example.model.Keywords;
import org.example.model.Table;
import org.example.model.WhereClause;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class QueryGenerator {

    private static QueryGenerator queryGenerator;

    private QueryGenerator() {
    }

    public static QueryGenerator getInstance() {
        return Objects.isNull(queryGenerator) ? new QueryGenerator() : queryGenerator;
    }

    public String getColumnQuery(final Table table) {
        final List<Column> columns = table.getColumnList();
        final List<String> resultSet = new ArrayList<>();

        for(final Column column : columns) {
            resultSet.add(String.join(".", column.getTableName(), column.getColumnName()));
        }

        return String.join(",", resultSet);
    }

    public String getWhereQuery(final List<WhereClause> whereClauses) {
       final List<String> resultSet = new ArrayList<>();

       if (whereClauses != null) {
           for (final WhereClause whereClause : whereClauses) {
               if (Objects.isNull(whereClause.getOperator())) {
                   resultSet.add(setColumnPlaceholder(whereClause.getColumnName(), whereClause.getTableName()));
               } else {
                   resultSet.add(String.join(" ", setColumnPlaceholder(whereClause.getColumnName(),
                           whereClause.getTableName()), whereClause.getOperator()));
               }
           }
       }

       return buildFinalQuery(String.join(" ", resultSet));
    }

    public String getWhereQueryForSubQuery(final List<WhereClause> whereClauses) {
        final List<String> resultSet = new ArrayList<>();

        if (whereClauses != null) {
            for (final WhereClause whereClause : whereClauses) {
                if (Objects.isNull(whereClause.getOperator())) {
                    resultSet.add(String.join(" ",whereClause.getTableName() + "." + whereClause.getColumnName(), Keywords.EQUALS.getValue()
                    , whereClause.getColumnName()));
                } else {
                    resultSet.add(String.join(" ", setColumnPlaceholder(whereClause.getColumnName(),
                            whereClause.getTableName()), whereClause.getOperator()));
                }
            }
        }

        return buildFinalQuery(String.join(" ", resultSet));
    }

    public String buildFinalQuery(final String query) {
        return String.join(" ", Keywords.WHERE.getValue(), query);
    }

    public String setColumnPlaceholder(final String columnName,final String tableName) {
        return String.join(" ", tableName + "." + columnName, Keywords.EQUALS.getValue(), Keywords.PLACEHOLDER.getValue());
    }

    public String setPlaceholder(final String query) {
        final List<String> columnFields = Arrays.asList(query.split(","));
        final List<String> resultSet = new ArrayList<>();

        for(int index = 0; index < columnFields.size(); index++) {
            resultSet.add(Keywords.PLACEHOLDER.getValue());
        }

        return String.join(" ,", resultSet);
    }

    public String setBrackets(final String query) {
        return String.join("", "(", query, ")");
    }
}
