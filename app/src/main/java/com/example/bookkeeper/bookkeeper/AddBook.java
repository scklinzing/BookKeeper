package com.example.bookkeeper.bookkeeper;

/**
 * Created by Shelley on 7/20/2017.
 *
 * This class checks to see if a book is in the library, and adds it if it is not.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddBook extends ActionBarActivity {

    // for log identification
    private static final String TAG = "AddBook";

    private DatabaseHandler db;

    // fields in the form we want to grab
    private EditText updateISBN;
    private EditText updateTitle;
    private EditText updateAuthor;
    private EditText updateDate;
    private EditText updateComments;
    private RadioButton radioRead;
    //private RadioButton radioWantToRead;
    private RadioButton radioReading;
    private RadioButton isOwnedYes;
    private RadioButton isOwnedNo;
    private RadioButton radioOne;
    private RadioButton radioTwo;
    private RadioButton radioThree;
    private RadioButton radioFour;
    private RadioButton radioFive;

    // used to store data in form
    private String ISBN; //will not edit
    private String title;  //will not edit
    private String author;  //will not edit
    private String status = "2"; // want to read
    private String isOwned = "no"; // by default
    private String rating = "0"; // no rating by default
    private String dateRead;
    private String comments;

    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.d(TAG, "Made it to onCreate().");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_book);

            db = new DatabaseHandler(this);

            // pull data from passed intent
            Intent intent = getIntent();
            Log.d(TAG, "Made it past the intent");

            ISBN = intent.getStringExtra(BookSummary.EXTRA_BOOK_ISBN);
            author = intent.getStringExtra(BookSummary.EXTRA_BOOK_AUTHOR);
            title = intent.getStringExtra(BookSummary.EXTRA_BOOK_TITLE);
            Log.d(TAG, "Got all intent extras!");

            // save fields to set values
            updateTitle = (EditText) findViewById(R.id.updateTitle);
            updateAuthor = (EditText) findViewById(R.id.updateAuthor);
            updateISBN = (EditText) findViewById(R.id.updateISBN);
            updateDate = (EditText) findViewById(R.id.editDate);
            updateComments = (EditText) findViewById(R.id.editComments);
            radioRead = (RadioButton) findViewById(R.id.radioRead);
            //radioWantToRead = (RadioButton) findViewById(R.id.radioWantToRead);
            radioReading = (RadioButton) findViewById(R.id.radioReading);
            isOwnedYes = (RadioButton) findViewById(R.id.isOwnedYes);
            isOwnedNo = (RadioButton) findViewById(R.id.isOwnedNo);
            radioOne = (RadioButton) findViewById(R.id.radioOne);
            radioTwo = (RadioButton) findViewById(R.id.radioTwo);
            radioThree = (RadioButton) findViewById(R.id.radioThree);
            radioFour = (RadioButton) findViewById(R.id.radioFour);
            radioFive = (RadioButton) findViewById(R.id.radioFive);

            // set text values to the new updated values
            updateISBN.setText(ISBN);
            updateAuthor.setText(author);
            updateTitle.setText(title);
            radioRead.setChecked(true);
            isOwnedNo.setChecked(true);
            //radioPublic.setChecked(true);

            Log.d(TAG, "Got all the values");

        } catch (Exception e) {
            Log.d(TAG, "Caught an exception in onCreate()");
            e.printStackTrace();
        }
    }

    /**
     * Add a new Book to the database.
     * @param view the current View to add a Book.
     */
    public void addBook(View view){
        Log.d(TAG, "Made it to addBook(View view)!");
        // check to see if book exists in Book table
        String ISBN = updateISBN.getText().toString();
        if(db.exists(ISBN) == false) {
            try {
                // create a Book object
                Book book = createBookFromView(view);
                // add the book to the database
                db.addBook(book);

                // print to say it was added
                Toast.makeText(getApplicationContext(), "Book added to Library.",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.d(TAG, "Exception in addBook(View view).");
                e.printStackTrace();
            }
        } else {
            // print to say the Book already exists in the Library.
            Toast.makeText(getApplicationContext(), "Error: book already exists in Library.",
                    Toast.LENGTH_LONG).show();
        }


    }

    public Book createBookFromView(View view) {
        Log.d(TAG, "Made it to createBookFromView(View view)!");
        Book book;

        // ISBN
        if(!ISBN.equals(updateISBN.getText().toString())){
            ISBN = updateISBN.getText().toString();
        }

        // author
        if(!author.equals(updateAuthor.getText().toString())){
            author = updateAuthor.getText().toString();
        }

        // title
        if(!title.equals(updateTitle.getText().toString())){
            title = updateTitle.getText().toString();
        }

        // status -- wishlist is default
        if (radioRead.isChecked()){
            status = "1";
        } else if (radioReading.isChecked()){
            status = "3";
        }

        // is owned?
        if(isOwnedYes.isChecked()){
            isOwned = "true";
        } else {
            isOwned = "false";
        }

        // rating
        if(radioOne.isChecked()){
            rating = "1";
        } else if(radioTwo.isChecked()){
            rating = "2";
        } else if(radioThree.isChecked()){
            rating = "3";
        } else if(radioFour.isChecked()){
            rating = "4";
        } else if(radioFive.isChecked()){
            rating = "5";
        }

        // date read
        // dateRead = "0000-00-00";
        if(updateDate.length() > 0){
            dateRead = updateDate.getText().toString();
        }

        // comments
        comments = " ";
        if(updateComments.length() > 0){
            comments = updateComments.getText().toString();
        }
        Log.d(TAG, "Comment length = " + comments.length());

        // create the Book object
        book = new Book(ISBN, title, author, status, Integer.parseInt(rating), dateRead,
                comments, isOwned);

        return book;
    }

    /**
     * Return to the Library list.
     */
    private void returnToMain() {
        Log.d(TAG, "Attempting to return to Main Activity.");
        Intent intent = new Intent( this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}