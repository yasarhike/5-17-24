package org.example.querybuilder;

import org.example.model.DataConfigContainer;
import org.example.model.Keywords;

import java.util.Objects;

public class DeleteQueryBuilder {

    private static DeleteQueryBuilder deleteQueryBuilder;
    private final QueryGenerator queryGenerator;

    private DeleteQueryBuilder() {
        queryGenerator = QueryGenerator.getInstance();
    }

    public static DeleteQueryBuilder getInstance() {
        return Objects.isNull(deleteQueryBuilder) ? new DeleteQueryBuilder() : deleteQueryBuilder;
    }

    public String buildDeleteQuery(final DataConfigContainer dataConfigContainer) {
        final String tableName = dataConfigContainer.getTableName();
        final String whereCondition = queryGenerator.getWhereQuery(dataConfigContainer.getWhereClauses());

        return buildFinalQuery(tableName, whereCondition);
    }

    public String buildFinalQuery(final String tableName, final String whereCondition) {
        return String.join(" ", Keywords.DELETE.getValue(), Keywords.FROM.getValue(),
                tableName, whereCondition);
    }
}
