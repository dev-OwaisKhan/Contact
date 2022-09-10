package DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
            String create  = "CREATE TABLE " + Param.TABLE_NAME + " ( " + Param.KEY_ID+ " INTEGER PRIMARY KEY, "
                    + Param.KEY_NAME + " TEXT, "+ Param.KEY_PHONE + " TEXT)";
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
            values.put(Param.KEY_NAME,contact.getName());
            values.put(Param.KEY_PHONE,contact.getPhone());

            // Adding/Inserting values into the table
            obj.insert(Param.TABLE_NAME,null,values);

            //Log message
            Log.d("test","added");

            // Closing the file
            obj.close();
        }

        /* Method to delete a contact in the list by using its id */
        public void delete(int id)
        {
            //Opening the file to make the changes
            SQLiteDatabase obj = this.getWritableDatabase();

            // Using delete method
            obj.delete(Param.TABLE_NAME,Param.KEY_ID + "=?",
                    new String[]{String.valueOf(id)} );

            //Log message to keep track of things
            Log.d("test","Deleted ");

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
            values.put(Param.KEY_NAME,contact.getName());
            values.put(Param.KEY_PHONE,contact.getPhone());

            // Using update method
            res = obj.update(Param.TABLE_NAME, values, Param.KEY_ID+ "=?",
                    new String[]{String.valueOf(contact.getId())});

            // Closing the file
            obj.close();
            return res;
        }

        /* Method to retrieve all the contacts */
        public List<Contact> all_contact()
        {
            // List to store the contacts
            List <Contact> contactList = new ArrayList<>();
            SQLiteDatabase obj = this.getReadableDatabase();

            // Query to select all the entries from the database
            String select = "SELECT * FROM "+Param.TABLE_NAME;

            // Cursor to iterate over the query
            Cursor cursor = obj.rawQuery(select,null);

            if (cursor.moveToFirst())
            {
                do {
                    Contact contact = new Contact();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhone(cursor.getString(2));
                }while(cursor.moveToNext());
            }
           Log.d("test","run oup");
            // Returning the list
            return contactList;
        }
    }



