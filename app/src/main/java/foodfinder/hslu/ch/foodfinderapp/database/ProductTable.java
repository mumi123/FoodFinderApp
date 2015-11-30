package foodfinder.hslu.ch.foodfinderapp.database;

public class ProductTable {

    private final static String TABLE_NAME = "product";
    private final static String TABLE_COLUMN_ID = "id";
    private final static String TABLE_COLUMN_DESCRIPTION = "description";
    private final static String TABLE_COLUMN_CATEGORY = "category";

    public ProductTable(){}

    public static String createTable(){
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_COLUMN_DESCRIPTION + " TEXT, "
                + TABLE_COLUMN_CATEGORY + " INTEGER, "
                + "FOREIGN KEY("+ TABLE_COLUMN_CATEGORY +") REFERENCES " +CategoryTable.getTableName()+"("+ CategoryTable.getTableColumnId() + "));";
    }

    public static String dropTable(){
        return "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }

}
