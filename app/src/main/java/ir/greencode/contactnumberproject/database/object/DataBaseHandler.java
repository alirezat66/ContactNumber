package ir.greencode.contactnumberproject.database.object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "contactDb";

    // inja miaym esme table va esme row ha ra tarif mikonim

    private static final String TABLE_NAME = "contactPhone";
    private static final String CONTACT_ID = "contactId";
    private static final String CONTACT_F_NAME = "fname";
    private static final String CONTACT_L_NAME = "lname";
    private static final String CONTACT_PHONE = "phone";
    private static final String CREATE_CONTACT_QUERY = "CREATE TABLE " +TABLE_NAME +" ( "+CONTACT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+CONTACT_F_NAME+" TEXT,"+CONTACT_L_NAME +" TEXT,"+CONTACT_PHONE+" TEXT)";

    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACT_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }
    public long insertContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACT_F_NAME,contact.getfName());
        values.put(CONTACT_L_NAME,contact.getlName());
        values.put(CONTACT_PHONE,contact.getPhone());
        long a =  db.insert(TABLE_NAME,null,values);
        db.close();
        return a;
    }
    public void editContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        String editquery = "Update " + TABLE_NAME +" set "+CONTACT_F_NAME +" = '"+ contact.getfName() +"',"+CONTACT_L_NAME +" = '"+contact.getlName() +"',"+CONTACT_PHONE+" = '"+ contact.getPhone()+"' where "+CONTACT_ID +" = '" + contact.getId() +"'";
        db.execSQL(editquery);
        db.close();
    }
    public void delete(long id){
        SQLiteDatabase db = getWritableDatabase();
        String deleteQuery = "Delete from "+TABLE_NAME +" where " +CONTACT_ID +" = '" + id +"'";
        db.execSQL(deleteQuery);
        db.close();
    }
    public List<Contact> selectAllContacts(){
        SQLiteDatabase db = getWritableDatabase();
        List<Contact> localList = new ArrayList<>();
        String selectQuerry = "select * from "+TABLE_NAME +" ORDER BY "+ CONTACT_F_NAME +" DESC";
        Cursor myCursor = db.rawQuery(selectQuerry,null);
        if(myCursor.moveToNext()){
            do {
                localList.add(new Contact(myCursor.getLong(0), myCursor.getString(1), myCursor.getString(2), myCursor.getString(3)));
            }while (myCursor.moveToNext());
        }
        db.close();
        return localList;
    }















}
