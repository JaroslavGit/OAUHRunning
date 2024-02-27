package cz.oauh.oauhrunning;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DBNAME ="OARUN";
    private final static String MISTAT = "Misto";
    private final static String BEHT = "Beh";

    public DBHelper(Context con)
    {
     super(con, DBNAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("CREATE TABLE "+MISTAT + " (id integer PRIMARY KEY AUTOINCREMENT, nazev string) ");
     db.execSQL("CREATE TABLE "+BEHT + " (id integer PRIMARY KEY AUTOINCREMENT,  datum DATE, pocetM int, startID integer, cilID integer, FOREIGN KEY(startID) REFERENCES "+MISTAT + "(id), FOREIGN KEY(cilID) REFERENCES "+MISTAT+"(id))");
    }

    public boolean vlozMisto(String noveMisto)
    {
     SQLiteDatabase db = this.getWritableDatabase();
     SQLiteStatement sql = db.compileStatement("INSERT INTO "+MISTAT+"(nazev) VALUES(?)");
     sql.bindString(1, noveMisto);
     return (sql.executeInsert()==1);
    }

    public void naplnMista() {
        if (vratPocetMist() == 0) {
            vlozMisto("Uherské Hradiště");
            vlozMisto("Uherský Brod");
            vlozMisto("Uherský Ostroh");
        }
    }

    public long vratPocetMist()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, MISTAT);
    }

    public Cursor vratMista()
    {
       SQLiteDatabase db = this.getReadableDatabase();
       return db.rawQuery("SELECT * FROM "+MISTAT, null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
