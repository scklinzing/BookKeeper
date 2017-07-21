package com.example.bookkeeper.bookkeeper;

/**
 * Created by Shelley on 7/20/2017.
 * Todo fix comments
 * Todo error trap
 */

/**
 * Handles sqlite database activites - including:
 *  - creating new sqlite databse
 *  - adding book
 *  - getting book information
 *  - getting all books
 *  - update book details
 *  - deleting book
 */

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    /* all Static variables */
    // Logcat tag (for debugging)
    private static final String TAG = "DatabaseHelper";

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    private static final String DATABASE_NAME = "BookDB";

    // table name
    private static final String TABLE_BOOKS = "books";

    // Library table column names
    private static final String KEY_ID = "id";
    private static final String KEY_ISBN = "isbn";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_STATUS = "status";
    private static final String KEY_RATING = "rating";
    private static final String KEY_DATE_READ = "dateRead";
    private static final String KEY_COMMENTS = "comments";
    private static final String KEY_OWNED = "owned";

    /**
     * Constructor for the DatabaseHandler.
     * @param context
     */
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create all the tables in the database.
     * @param db the freshly created database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Build query to create books table
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ISBN + " TEXT, "+
                KEY_TITLE + " TEXT, "+
                KEY_AUTHOR + " TEXT, " +
                KEY_STATUS + " INTEGER, "+
                KEY_RATING + " INTEGER, " +
                KEY_DATE_READ + " TEXT, " +
                KEY_COMMENTS + " TEXT, " +
                KEY_OWNED + " BOOLEAN) ";
        // create table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    /**
     * Upgrade (update) the database verson with a newer version.
     * @param db the newer database content.
     * @param oldVersion the old version of the database.
     * @param newVersion the newer version of the database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);

        // create tables again with the newer version
        onCreate(db);
    }

    /**
     * Add a new Book to the book table in the database.
     * @param book the Book information to add to the database.
     */
    public void addBook(Book book){
        Log.d("addBook", book.toString());

        // get writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues and add items
        ContentValues values = new ContentValues();
        values.put(KEY_ISBN, book.getIsbn());
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_STATUS, book.getStatus());
        values.put(KEY_RATING, book.getRating());
        values.put(KEY_DATE_READ, book.getDateRead());
        values.put(KEY_COMMENTS, book.getComments());
        values.put(KEY_OWNED, book.getOwned());

        // insert the information into table
        db.insert(TABLE_BOOKS, null, values);

        // close the database when you are finished writing
        db.close();
    }

    /**
     * @param id the ID of the Book you want.
     * @return a single Book from the database.
     */
    public Book getBook(int id){

        // get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        // build the query
        String selectQuery = "SELECT * FROM " + TABLE_BOOKS + " WHERE " + KEY_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery,  null);

        // if we get results, start at the beginning
        if (c != null){
            c.moveToFirst();
        }

        // build and return book
        Book b = new Book();
        b.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        b.setIsbn(c.getString(c.getColumnIndex(KEY_ISBN)));
        b.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        b.setAuthor(c.getString(c.getColumnIndex(KEY_AUTHOR)));
        b.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
        b.setRating(c.getInt(c.getColumnIndex(KEY_RATING)));
        b.setDateRead(c.getString(c.getColumnIndex(KEY_DATE_READ)));
        b.setComments(c.getString(c.getColumnIndex(KEY_COMMENTS)));
        b.setOwned(c.getString(c.getColumnIndex(KEY_OWNED)));

        db.close();
        return b;
    }

    /**
     * @return a List of all the Books in the database.
     */
    public List<Book> getAllBooks(){
        List<Book> books = new LinkedList<Book>();

        // build the query
        String selectQuery = "SELECT * FROM " + TABLE_BOOKS;

        // get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery,  null);

        // loop through all rows and add them to List of Books
        Book book = null;
        if(c.moveToFirst()){
            do {
                book = new Book();
                book.setId(Integer.parseInt(c.getString(0)));
                book.setIsbn(c.getString(1));
                book.setTitle(c.getString(2));
                book.setAuthor(c.getString(3));
                book.setStatus(c.getString(4));
                book.setRating(Integer.parseInt(c.getString(5)));
                book.setDateRead(c.getString(6));
                book.setComments(c.getString(7));
                book.setOwned(c.getString(8)); // note that this is a Boolean value

                // adding to book list
                books.add(book);
            } while(c.moveToNext());
        }

        db.close();
        return books;
    }

    /**
     * Update a single Book in the table.
     * @param id the ID of the Book to be updated.
     * @param book the new Book object to use.
     * @return the update value from the database.
     */
    public int updateBook(int id, Book book){
        // get writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // build values and add values
        ContentValues values = new ContentValues();
        values.put(KEY_ISBN, book.getIsbn());
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_STATUS, book.getStatus());
        values.put(KEY_RATING, book.getRating());
        values.put(KEY_DATE_READ, book.getDateRead());
        values.put(KEY_COMMENTS, book.getComments());
        values.put(KEY_OWNED, book.getOwned());

        // convert ID to String
        String ID = Integer.toString(id);

        // update the database with the new book
        int i =  db.update(TABLE_BOOKS, values, KEY_ID + " = ?",
                new String[] { ID });

        // close database and return
        db.close();
        return i;
    }

    /**
     * Delete a single Book from the database.
     * @param id the ID of the Book to be deleted.
     */
    public void deleteBook(int id){
        // get writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        // convert ID to String
        String ID = Integer.toString(id);

        // delete the Book from the database
        db.delete(TABLE_BOOKS, KEY_ID + " = ?", new String[] { ID });

        // close the database
        db.close();
    }

    /**
     * Check if there is already a particular Book in your database.
     * @param isbn the ISBN of the Book you are checking.
     * @return true if the book does exist, false otherwise.
     */
    public Boolean exists(String isbn) {
        // get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor c = db.query(TABLE_BOOKS, null, KEY_ISBN + "=?",
                    new String[]{String.valueOf(isbn)}, null, null, null);

            if (c == null) { // if it is not in the table, return false
                db.close();
                return false;
            } else { // if it is in the table, return true
                db.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Error checking if ISBN number " + isbn + " exists in database.");
        }

        return null;
    }
}