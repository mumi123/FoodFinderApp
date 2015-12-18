package foodfinder.hslu.ch.foodfinderapp.database;

public class ProductTable {

    private final static String TABLE_NAME = "product";
    private final static String TABLE_COLUMN_ID = "id";
    private final static String TABLE_COLUMN_DESCRIPTION = "description";
    private final static String TABLE_COLUMN_CATEGORY = "category_id";

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

    public static String[] initRecord(){
        //Produkte
        //********
        String product[] = new String[3];

        product[0] = "INSERT INTO product("+TABLE_COLUMN_DESCRIPTION+","+TABLE_COLUMN_CATEGORY+") VALUES ('Tabasco', 11);";
        product[1] = "INSERT INTO product("+TABLE_COLUMN_DESCRIPTION+","+TABLE_COLUMN_CATEGORY+") VALUES ('Zucker', 13);";
        product[2] = "INSERT INTO product("+TABLE_COLUMN_DESCRIPTION+","+TABLE_COLUMN_CATEGORY+") VALUES ('Ravioli', 1);";

        return product;
    }

    public static String getTableName(){
        return TABLE_NAME;
    }

    public static String getTableColumnId(){
        return TABLE_COLUMN_ID;
    }

    public static String getTableColumnDescription(){
        return TABLE_COLUMN_DESCRIPTION;
    }

    public static String getTableColumnCategory(){
        return TABLE_COLUMN_CATEGORY;
    }
}
