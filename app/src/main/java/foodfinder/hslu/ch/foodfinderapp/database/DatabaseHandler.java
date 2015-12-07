package foodfinder.hslu.ch.foodfinderapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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



}
