package foodfinder.hslu.ch.foodfinderapp.database;

public class ProductTable extends MyTable{

    private final static String TABLE_NAME = "product";
    private final static String TABLE_COLUMN_ID = "id";
    private final static String TABLE_COLUMN_DESCRIPTION = "description";
    private final static String TABLE_COLUMN_CATEGORY = "category";

    public ProductTable(){
        this.setTableName(TABLE_NAME);
        this.addColumn(TABLE_COLUMN_ID);
        this.addColumn(TABLE_COLUMN_DESCRIPTION);
        this.addColumn(TABLE_COLUMN_CATEGORY);
    }


    public String createTable(){
        return "CREATE TABLE " + TABLE_NAME + "("
                + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY," + TABLE_COLUMN_DESCRIPTION + " TEXT,"
                + TABLE_COLUMN_CATEGORY + " INTEGER" + ")";
    }


}
