package com.example.bookkeeper.bookkeeper;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Shelley on 7/20/2017.
 */

public class Book {

    /* private variables */
    int id;			    // ID number for database
    String isbn;  	    // ISBN for book
    String title;  	    // book title
    String author;      // book author
    String status;      // read, 2 = reading, 3 = wishlist
    int rating;         // 1- 5 rating system
    String dateRead;    // date book was read, format: 0000-00-00
    String comments;    // user comments on book
    String owned;       // is the book owned or not? [true or false]

    /**
     * An empty constructor for a Book object.
     */
    public Book(){

    }

    /**
     * A constructor for a new Book object.
     * @param isbn The ISBN of the Book.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param status Have you read the book or not?
     * @param rating The rating 1 - 5 of the book.
     * @param dateRead The date you finished reading the book.
     * @param comments Comments about the book from the user.
     * @param owned Is the book owned or not?
     */
    public Book(String isbn, String title, String author, String status, int rating,
                String dateRead, String comments, String owned) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = status;
        this.rating = rating;
        this.dateRead = dateRead;
        this.comments = comments;
        this.owned = owned;

    }

    /**
     * @return the ISBN of the Book.
     */
    public String getIsbn(){
        return this.isbn;
    }

    /**
     * Manually set the ISBN number.
     * @param isbn the new ISBN number.
     */
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    /**
     * @return the ID of the book.
     */
    public int getId(){
        return this.id;
    }

    /**
     * Manually set the ID number.
     * @param id The new ID number.
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * @return the title of the Book.
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Manually set the title of the Book.
     * @param title the new title.
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * @return the author of the Book.
     */
    public String getAuthor(){
        return this.author;
    }

    /**
     * Manually set the author for the Book.
     * @param author the new author.
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * @return the status of the book (read, or unread)
     * read, reading, wishlist
     */
    public String getStatus(){
        return this.status;
    }

    /**
     * Manually set the status of the Book.
     * @param status the new status.
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * @return the rating of the Book.
     */
    public int getRating(){
        return this.rating;
    }

    /**
     * Manually set the rating for the Book.
     * @param rating the new rating.
     */
    public void setRating(int rating){
        this.rating = rating;
    }

    /**
     * @return the date the Book was read.
     */
    public String getDateRead(){
        return this.dateRead;
    }

    /**
     * Manually set the date the Book was read.
     * @param dateRead the new data the Book was read.
     */
    public void setDateRead(String dateRead){
        this.dateRead = dateRead;
    }

    /**
     * @return the comments on the Book.
     */
    public String getComments(){
        return this.comments;
    }

    /**
     * Set comments for the Book.
     * @param comments the comments to set for the Book.
     */
    public void setComments(String comments){
        this.comments = comments;
    }

    /**
     * @return true if the Book is owned, false if it is not.
     */
    public String getOwned(){
        return this.owned;
    }

    /**
     * Set if the Book is owned or not.
     * @param owned
     */
    public void setOwned(String owned){
        this.owned = owned;
    }

    /**
     * @return a String with all the parameters of the Book.
     */
    public String toString(){
        return "Book [ID: " + id + ", isbn: " + isbn + ", title: " + title + ", author: "
                + author + ", status: " + status + ", rating: " + rating + ", dateRead: "
                + dateRead + ", comments: " + comments + "]";
    }
}