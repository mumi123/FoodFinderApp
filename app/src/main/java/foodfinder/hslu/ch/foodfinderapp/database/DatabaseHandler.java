package foodfinder.hslu.ch.foodfinderapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FoodFinder.db";

    ProductTable productTable = new ProductTable();

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Wenn die Datenbank schon besteht, wird diese Methode nicht ausgeführt.
        System.out.println("hallo2");
        System.out.println(productTable.createTable());
    }

    /*
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("hallo2");
        System.out.println(productTable.createTable());


        //db.execSQL(productTable.createTable());
    }
    */



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
