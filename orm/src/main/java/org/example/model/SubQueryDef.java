package org.example.model;

public class SubQueryDef {

    private Keywords subQueryLocation;
    private String prevNodeName;

    public SubQueryDef(final Keywords subQueryLocation, final String prevNodeName) {
        this.subQueryLocation = subQueryLocation;
        this.prevNodeName= prevNodeName;
    }

    public void setSubQueryLocation(final Keywords subQueryLocation) {
        this.subQueryLocation = subQueryLocation;
    }

    public Keywords getSubQueryLocation() {
        return subQueryLocation;
    }

    public String getPrevNodeName() {
        return prevNodeName;
    }

    public void setPrevNodeName(final String prevNodeName) {
        this.prevNodeName = prevNodeName;
    }
}
