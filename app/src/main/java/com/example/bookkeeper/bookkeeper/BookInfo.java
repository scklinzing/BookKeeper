package com.example.bookkeeper.bookkeeper;

/**
 * Created by Shelley on 7/21/2017.
 *
 * This class contains information about each book.
 * It is used to populate the main activity / xml areas.
 */

import java.util.Date;

public class BookInfo {

    private int rating;
    private String bookName;
    private String bookAuthor;
    private int bookStatus;
    private String bookIsOwned;
    private String userComment;
    private Date readDate;
    /*Status
     * 1= Read
     * 2= Want to Read/ wish list
     * 3= Currently Reading
     */
    private String bookID;

    public String getBookIsOwned(){
        return bookIsOwned;
    }
    public String getUserComment(){
        return userComment;
    }
    public Date getDateRead(){
        return readDate;
    }
    public int getRating(){
        return rating;
    }
    public String getBookName(){
        return bookName;
    }
    public String getBookAuthor(){
        return bookAuthor;
    }
    public int getBookStatus(){
        return bookStatus;
    }
    public void setRating(int a){
        rating = a;
    }
    public void setStatus(int a){
        bookStatus = a;
    }
    public String getBookID(){
        return bookID;
    }
    public BookInfo(String id, String name, String author, int rating, int status, String isOwned, Date date, String comment ){
        bookID = id;
        bookName = name;
        bookAuthor = author;
        this.rating = rating;
        bookStatus = status;
        bookIsOwned = isOwned;
        userComment = comment;
        readDate = date;
    }

}