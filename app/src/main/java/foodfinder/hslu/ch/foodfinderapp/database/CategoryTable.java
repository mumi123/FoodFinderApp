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

    public static String getTableColumnDescription() {
        return TABLE_COLUMN_DESCRIPTION;
    }

    public static String[] initRecord(){
        //Kategorien
        //**********
        String category[] = new String[14];

        category[0] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Getreide');";
        category[1] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Hülsenfrüchte');";
        category[2] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Obst');";
        category[3] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Gemüse');";
        category[4] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Nüsse');";
        category[5] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Fleisch');";
        category[6] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Fischprodukte');";
        category[7] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Milchprodukte');";
        category[8] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Salate');";
        category[9] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Öle und Fette');";
        category[10] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Gewürze');";
        category[11] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Kräuter');";
        category[12] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Süssmittel');";
        category[13] = "INSERT INTO category("+TABLE_COLUMN_DESCRIPTION+") VALUES ('Sonstiges');";

        return category;
    }
}
