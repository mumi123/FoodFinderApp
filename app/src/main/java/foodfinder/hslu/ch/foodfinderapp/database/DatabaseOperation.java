package foodfinder.hslu.ch.foodfinderapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOperation {

    DatabaseHandler myDB;

    public DatabaseOperation(DatabaseHandler myDB){
        this.myDB = myDB;
    }

    //CRUD Operations

    public void insert(){
        // Gets the data repository in write mode
        SQLiteDatabase db = myDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        /*
        values.put(MyColumns.COLUMN_NAME_ENTRY_ID, 1);
        values.put(MyColumns.COLUMN_NAME_TITLE, "Mein Titel");

        long newRowId;
        newRowId = db.insert(
                MyColumns.TABLE_NAME,
                null,
                values);
        db.close();
        */
    }

    public void select(){

        SQLiteDatabase db = myDB.getWritableDatabase();

        /*
        Cursor c = db.query(
                MyColumns.TABLE_NAME,
                MyColumns.COLUMN_NAME_TITLE,               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        */
    }

}
