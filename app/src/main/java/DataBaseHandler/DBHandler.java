package DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Model.Contact;
import Params.Param;


public class DBHandler extends SQLiteOpenHelper  {
    /** Providing context of the database to the Class DBHandler*/
        public DBHandler(Context context)
        {
            super(context, Param.DATABASE_NAME, null, Param.DATABASE_VERSION);
        }

        /** Creating a table in the on create method*/
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            /* Writing the sql query */
            String create  = "CREATE TABLE " + Param.TABLE_NAME + "(" + Param.Key_id+ " INTEGER PRIMARY KEY, "
                    + Param.Key_name + " TEXT, "+ Param.Key_phone + " TEXT" + ")";
            sqLiteDatabase.execSQL(create);


            //Log message to keep tack of things
            Log.d("TEST","TABLE CREATED");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }

        /** Adding different operating by making methods */

        /* Method to add new contact to the table*/
        public void add(Contact contact)
        {
            //Opening the file to make the changes
            SQLiteDatabase obj = this.getWritableDatabase();

            // A variable to store the values which need to be inserted
            ContentValues values = new ContentValues();
            values.put(Param.Key_name,contact.getName());
            values.put(Param.Key_phone,contact.getPhone());

            // Adding/Inserting values into the table
            obj.insert(Param.TABLE_NAME,null,values);

            // Closing the file
            obj.close();
        }

        /* Method to delete a contact in the list by using its id */
        public void delete(int id)
        {
            //Opening the file to make the changes
            SQLiteDatabase obj = this.getWritableDatabase();

            // Using delete method
            obj.delete(Param.TABLE_NAME,Param.Key_id + "=?",
                    new String[]{String.valueOf(id)} );

            // Closing the file
            obj.close();
        }

        /* Method to update a saved contact */
        public int update(Contact contact)
        {
            int res;
            //Opening the file to make the changes
            SQLiteDatabase obj = this.getWritableDatabase();

            // A variable to store the values which need to be updated
            ContentValues values = new ContentValues();
            values.put(Param.Key_name,contact.getName());
            values.put(Param.Key_phone,contact.getPhone());

            // Using update method
            res = obj.update(Param.TABLE_NAME, values, Param.Key_id+ "=?",
                    new String[]{String.valueOf(contact.getId())});

            // Closing the file
            obj.close();
            return res;
        }

    }



