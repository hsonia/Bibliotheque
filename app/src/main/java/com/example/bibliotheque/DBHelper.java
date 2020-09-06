package com.example.bibliotheque;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bilioteque.db";
    public static final String BOOKS_TABLE_NAME = "livres";
    private static String DATABASE_PATH = "";

    private static final int DB_VERSION = 1;
    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;
    private HashMap hp;
    public DBHelper(Context context)
    {

        super(context, DATABASE_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }
    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
                if (dbFile.exists())
                    dbFile.delete();
                mIOException.printStackTrace();
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getResources().openRawResource(R.raw.bilioteque);
        OutputStream mOutput = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    public boolean insertBooks (String ptitre, String pauteur, String pmotCles, String presume)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titre", ptitre);
        contentValues.put("auteur", pauteur);
        contentValues.put("motCles", pmotCles);
        contentValues.put("resume", presume);
        db.insert("Books", null, contentValues);
        return true;
    }
    /*public Books RechercherBooksByTitre(String ptitre){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =
                db.rawQuery( "select * from Books where titre='"+ptitre+"'", null );
        res.moveToFirst();
        Books b = null;
// on parcours le résultat et on crée pour chaque ligne un objet Rdv
        if(res.isAfterLast() == false){
            //r= new Books()
            b= new Books();
// on crée un nouveau objet Books
            b.setId(res.getInt(0)); // on mis son ID
            b.setTitre(res.getString(1)); // on mis son Titre
            b.setAuteur(res.getString(2));
// on mis son Auteur
            b.setMotCles(res.getString(3)); // on mis ça MotCles
            res.moveToNext();
        }
        return b;
    }*/

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, BOOKS_TABLE_NAME);
        return numRows;
    }

    public boolean updateBooks (int id, String titre, String auteur, String motCles, String resume)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titre", titre);
        contentValues.put("auteur", auteur);
        contentValues.put("motCles", motCles);
        contentValues.put("resume", resume);
        db.update("Books", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteBook (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Books",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Books> getAllLivres()
    {
//on crée un liste vide
        ArrayList<Books> array_list = new ArrayList<Books>();
        SQLiteDatabase db = this.getReadableDatabase();
// on lance la requête
        Cursor res = db.rawQuery( "select * from "+BOOKS_TABLE_NAME+" ORDER BY titre ASC", null );
        res.moveToFirst();
        Books b;
// on parcours le résultat et on crée pour chaque ligne un objet Books
        while(res.isAfterLast() == false){
            b= new Books();
// on crée un nouveau objet Books
            b.setId(res.getInt(0)); // on mis son ID
            b.setTitre(res.getString(1)); // on mis son Titre
            b.setAuteur(res.getString(2));
// on mis son Auteur
            b.setMotCles(res.getString(3)); // on mis ça MoteCles
            b.setResume(res.getString(4));
            array_list.add(b);
            res.moveToNext();
        }
// on renvoi le résultat.
        return array_list;
    }
    public ArrayList<Books> RechercherBooks(String ptitre){
        ArrayList<Books> array_list1 = new ArrayList<Books>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =db.rawQuery( "select * from "+BOOKS_TABLE_NAME+" where titre like ? or motCles like ? or auteur like ? or resume like ? ORDER BY titre ASC", new String[] { "%" + ptitre + "%", "%" + ptitre + "%", "%" + ptitre + "%", "%" + ptitre + "%" });
        res.moveToFirst();
        Books b;
// on parcours le résultat et on crée pour chaque ligne un objet Rdv
        while(res.isAfterLast() == false){
            b= new Books();
// on crée un nouveau objet Books
            b.setId(res.getInt(0)); // on mis son ID
            b.setTitre(res.getString(1)); // on mis son Titre
            b.setAuteur(res.getString(2));
// on mis son Auteur
            b.setMotCles(res.getString(3)); // on mis ça MotCles
            b.setResume(res.getString(4));
            array_list1.add(b);
            res.moveToNext();
        }
        return array_list1;
    }
}