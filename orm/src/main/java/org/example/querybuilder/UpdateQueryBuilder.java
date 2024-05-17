package org.example.querybuilder;

import org.example.model.Column;
import org.example.model.DataConfigContainer;
import org.example.model.Keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateQueryBuilder {

    private static UpdateQueryBuilder updateQueryBuilder;
    private final QueryGenerator queryGenerator;

    private UpdateQueryBuilder() {
        queryGenerator = QueryGenerator.getInstance();
    }

    public static UpdateQueryBuilder getInstance() {
        return Objects.isNull(updateQueryBuilder) ? new UpdateQueryBuilder() : updateQueryBuilder;
    }

    public String getUpdateQuery(final DataConfigContainer dataConfigContainer) {
        final String setQuery = buildSetQuery(dataConfigContainer.getColumnList());
        final String whereCondition = queryGenerator.getWhereQuery(dataConfigContainer.getWhereClauses());

        return buildFinalQuery(dataConfigContainer.getTableName(), setQuery, whereCondition);
    }

    public String buildSetQuery(final List<Column> columnList) {
        final List<String> resultSet = new ArrayList<>();

        for(final Column column: columnList) {
            resultSet.add(String.join(" ", column.getColumnName(), Keywords.EQUALS.getValue(),
                    Keywords.PLACEHOLDER.getValue()));
        }

        return String.join(", ", resultSet);
    }

    public String buildFinalQuery(final String tableName, final String column, final String whereCondition) {
        return String.join(" ", Keywords.UPDATE.getValue(), tableName, Keywords.SET.getValue(),
                column, whereCondition);
    }
}
