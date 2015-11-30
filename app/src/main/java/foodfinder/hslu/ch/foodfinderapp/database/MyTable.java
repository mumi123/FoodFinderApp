package foodfinder.hslu.ch.foodfinderapp.database;

import java.util.ArrayList;

public abstract class MyTable {

    private String tableName;
    private String columnName;
    private ArrayList<String> column = new ArrayList<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String TABLE_NAME) {
        this.tableName = TABLE_NAME;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void addColumn(String name){
        this.column.add(name);
    }

    public void removeColumn(String name){
        this.column.remove(name);
    }

    public ArrayList<String> getColumn(){
        return this.column;
    }

}
