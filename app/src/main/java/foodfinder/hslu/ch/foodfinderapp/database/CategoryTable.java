package foodfinder.hslu.ch.foodfinderapp.database;

public class CategoryTable {

    private final static String TABLE_NAME = "category";
    private final static String TABLE_COLUMN_ID = "id";
    private final static String TABLE_COLUMN_DESCRIPTION = "description";

    public CategoryTable(){}

    public static String createTable(){
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_COLUMN_DESCRIPTION + " TEXT"
                + ");";
    }

    public static String dropTable(){
        return "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }

    public static String getTableName(){
        return TABLE_NAME;
    }

    public static String getTableColumnId(){
        return TABLE_COLUMN_ID;
    }
}
