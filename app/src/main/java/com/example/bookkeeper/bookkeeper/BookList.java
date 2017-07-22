package com.example.bookkeeper.bookkeeper;

/**
 * Created by Shelley on 7/21/2017.
 *
 * This Class creates a BookList and sets the sort preferences.
 */

import java.util.ArrayList;
import java.util.Collections;

public class BookList {
    private ArrayList<Book> booklist;

    public BookList(ArrayList<Book> list) {
        booklist = list;
    }

    public ArrayList<Book> getList() {
        return booklist;
    }

    public Book getISBN(String isbn) {
        for (int i = 0; i < booklist.size(); i++) {
            if (booklist.get(i).getBookID().equals(isbn)) {
                return booklist.get(i);
            }
        }
        return null;
    }

    public ArrayList<Book> getList(char[] filter, char sort, String title,
                                       String author) {
        ArrayList<Book> ret = new ArrayList<Book>();
        boolean add = true;
        Book element;
        // filter is a character array representing each possible filter ==
        // (int) 0 represents filter is not active
        // filter[0] char 1 2 3 0
        // filter[1] (int)rating -1 unrated, else 1-5
        // title, author will = null if no filter
        // Sort – 0 – standard title sort a- author, r- rating, s- status.

        int status;
        int rating;
		/*
		 * New: 1 = read, 2 = reading, 3 = wishlist
		 * Old: 1= wishlist 2= read 3= reading 0= view all
		 */
        if (filter[0] == '1')
            status = 1;
        else if (filter[0] == '2')
            status = 2;
        else if (filter[0] == '3')
            status = 3;
        else
            status = 0;

        switch (filter[1]) {
            case '1':
                rating = 1;
                break;
            case '2':
                rating = 2;
                break;
            case '3':
                rating = 3;
                break;
            case '4':
                rating = 4;
                break;
            case '5':
                rating = 5;
                break;
            case '0':
                rating = 0;
            default:
                // will not use rating in this case because it == 'n'
                rating = -1; // no rating
        }

        for (int i = 0; i < booklist.size(); i++) {
            add = true;
            element = booklist.get(i);
            // filter[0] status
            if (add == true && status != 0) {
                if (element.getStatus() != status) {
                    add = false;
                }
            }// end filter status

            // filter[1] rating
            if (add == true && filter[1] != 'n') {
                if (element.getRating() != rating) {
                    add = false;
                }
            }// end filter rating

            // filter Title
            if (add == true && title != null) {
                if (!element.getTitle().toLowerCase()
                        .contains(title.toLowerCase())) {
                    add = false;
                }
            }// end filter title

            // filter Author
            if (add == true && author != null) {
                if (!element.getAuthor().toLowerCase()
                        .contains(author.toLowerCase())) {
                    add = false;
                }
            }// end filter title
            if (add == true)
                ret.add(element);
        }// end for

        sort(ret, sort);
        return ret;
    }

    // collection sort TITLE, AUTHOR, RATING, STATUS
    private ArrayList<Book> sort(ArrayList<Book> proc, char sort) {
        // select a comparitor and sort the collection.
        if (sort == 't')
            Collections.sort(proc, SortBook.TITLE_SORT);
        else if (sort == 'a')
            Collections.sort(proc, SortBook.AUTHOR_SORT);
        else if (sort == 'r')
            Collections.sort(proc, SortBook.RATING_SORT);
        else if (sort == 's')
            Collections.sort(proc, SortBook.STATUS_SORT);
        return proc;
    }
}