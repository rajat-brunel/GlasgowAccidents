package com.example.glasgowaccidents;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends  SQLiteOpenHelper {

    private static String DB_NAME = "accidents_data.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    private int bytes_copied = 0;
    private static int buffer_size = 1024;
    private int blocks_copied = 0;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);

        this.myContext = context;
        // Check for and create (copy DB from assets) when constructing the DBHelper
        //this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        //Log.e("Path 1", DB_PATH+DB_NAME);
        if (!checkDataBase()){
            bytes_copied = 0;
            blocks_copied = 0;
            createDataBase();
        }
    }

    /**
     * Creates an empty database on the system and rewrites it with the asset database.
     * */

    public void createDataBase() {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            // do nothing - database already exists
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                File db = new File(myContext.getDatabasePath(DB_NAME).getPath());
                if (db.exists()){
                    db.delete();
                }
                e.printStackTrace();
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        File db = new File(myContext.getDatabasePath(DB_NAME).getPath()); //Get the file name of the database
        Log.d("DBPATH","DB Path is " + db.getPath()); //TODO remove for Live App
        if (db.exists()) return true; // If it exists then return doing nothing

        // Get the parent (directory in which the database file would be)
        File dbdir = db.getParentFile();
        // If the directory does not exits then make the directory (and higher level directories)
        if (!dbdir.exists()) {
            db.getParentFile().mkdirs();
            dbdir.mkdirs();
        }
        return false;
    }

    /**
     * Copies the database from the local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */

    private void copyDataBase() throws IOException {

        final String TAG = "COPYDATABASE";
        Log.d(TAG,"Initiated Copy of the database file " + DB_NAME + " from the assets folder."); //TODO remove for Live App

        InputStream myInput = myContext.getAssets().open(DB_NAME); // Open the Asset file
        String dbpath  = myContext.getDatabasePath(DB_NAME).getPath();
        Log.d(TAG,"Asset file " + DB_NAME + " found so attmepting to copy to " + dbpath); //TODO remove for Live App

        File outfile = new File(myContext.getDatabasePath(DB_NAME).toString());
        if (!outfile.getParentFile().exists()) {
            outfile.getParentFile().mkdirs();
        }
        OutputStream myOutput = new FileOutputStream(outfile);
        byte[] buffer = new byte[buffer_size];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            blocks_copied++;
            myOutput.write(buffer, 0, length);
            bytes_copied += length;
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        //Log.d("DBCONFIGURE","Database has been configured "); //TODO remove for Live App
        db.disableWriteAheadLogging(); //<<<<<<<<<< un-comment to force journal mode
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d("DBOPENED","Database has been opened."); //TODO remove for live App
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, null, null, null, null, null, null);
    }

}
