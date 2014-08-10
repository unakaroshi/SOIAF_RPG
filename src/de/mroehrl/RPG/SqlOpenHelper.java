package de.mroehrl.RPG;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SqlOpenHelper extends SQLiteOpenHelper {
	
	private static final String DBNAME = "SOIAF_RPG";
	private static final int VERSION   = 1;
	
	public SqlOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createDatabase(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
	
	public long insertDefaultCharacter(SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		values.put(DBContract.CharacterEntry.NAME, "No Name");
		values.put(DBContract.CharacterEntry.MONEY, 2000);
		return db.insert(DBContract.CharacterEntry.TABLE_NAME, null, values);
	}
	
	private void createDatabase(SQLiteDatabase db) {
	//	db.execSQL(DBContract.SQL_DELETE_CHARACTER_TABLE);
		db.execSQL(DBContract.SQL_CREATE_CHARACTER_TABLE);
		insertDefaultCharacter(db);		
	}
	
	public final class DBContract {		
		// To prevent someone from accidentally instantiating the contract class,
	    // give it an empty constructor.
	    public DBContract() {}

	    /* Inner class that defines the table contents */
	    public abstract class CharacterEntry implements BaseColumns {
	        public static final String TABLE_NAME = "Character";
	        public static final String ID         = "characterid";
	        public static final String NAME       = "name";
	        public static final String MONEY      = "money";        
	    }

	    private static final String VARCHAR_TYPE = " VARCHAR(255)";
	    private static final String INTEGER_TYPE = " INT";
	    private static final String COMMA_SEP    = ",";
	    
	    public static final String SQL_CREATE_CHARACTER_TABLE =
	        "CREATE TABLE " + CharacterEntry.TABLE_NAME + " (" +
	        CharacterEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + COMMA_SEP +
	        CharacterEntry.NAME + VARCHAR_TYPE + COMMA_SEP +
	        CharacterEntry.MONEY + INTEGER_TYPE + 
	        //COMMA_SEP +        
	        " )";
	    
	    
	    public static final String SQL_DELETE_CHARACTER_TABLE =
	        "DROP TABLE IF EXISTS " + CharacterEntry.TABLE_NAME;
	}	
}
