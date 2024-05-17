package org.example.model;

public class SubQueryClause {

    private DataConfigContainer dataConfigContainer;
    private SubQueryDef subQueryDef;

    public SubQueryDef getSubQueryDef() {
        return subQueryDef;
    }

    public void setSubQueryDef(final SubQueryDef subQueryDef) {
        this.subQueryDef = subQueryDef;
    }

    public DataConfigContainer getDataConfigContainer() {
        return dataConfigContainer;
    }

    public void setDataConfigContainer(final DataConfigContainer dataConfigContainer) {
        this.dataConfigContainer = dataConfigContainer;
    }
}
