package foodfinder.hslu.ch.foodfinderapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import foodfinder.hslu.ch.foodfinderapp.entity.Category;
import foodfinder.hslu.ch.foodfinderapp.entity.Product;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "FoodFinder.db";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Wenn die Datenbank schon besteht, wird diese Methode nicht ausgeführt.
        //Es muss die Versionsnummer verändert werden!

        //Datenbank erstellen
        db.execSQL(CategoryTable.createTable());
        db.execSQL(ProductTable.createTable());

        //Datensätze befüllen für Category
        String category[] = CategoryTable.initRecord();

        for (int i=0; i < category.length; i++)
        {
            db.execSQL(category[i]);
        }

        String product[] = ProductTable.initRecord();

        for (int i=0; i < product.length; i++)
        {
            db.execSQL(product[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CategoryTable.dropTable());
        db.execSQL(ProductTable.dropTable());
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public long persistProduct(Product product, Category category){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductTable.getTableColumnDescription(), product.getName());
        values.put(ProductTable.getTableColumnCategory(), product.getCatID());
        values.put(ProductTable.getTableColumnCategory(), category.getId()); //Reference to Category

        // insert row
        long productId = db.insert(ProductTable.getTableName(), null, values);

        return productId;
    }

    public long persistCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CategoryTable.getTableColumnDescription(), category.getName());

        // insert row
        long categoryId = db.insert(CategoryTable.getTableName(), null, values);

        return categoryId;
    }

    public Category getCategoryById(int categoryId){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + CategoryTable.getTableName() + " WHERE "
                + CategoryTable.getTableColumnId() + " = " + categoryId;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            c.moveToFirst();
        }

        Category category = new Category();
        category.setId(c.getInt(c.getColumnIndex(CategoryTable.getTableColumnId())));
        category.setName((c.getString(c.getColumnIndex(CategoryTable.getTableColumnDescription()))));

        return category;
    }

    public Category getCategoryByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + CategoryTable.getTableName() + " WHERE "
                + CategoryTable.getTableColumnDescription() + " = " + name;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            c.moveToFirst();
        }

        Category category = new Category();
        category.setId(c.getInt(c.getColumnIndex(CategoryTable.getTableColumnId())));
        category.setName((c.getString(c.getColumnIndex(CategoryTable.getTableColumnDescription()))));

        return category;
    }



}
