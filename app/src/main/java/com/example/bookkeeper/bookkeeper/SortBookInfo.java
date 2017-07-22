package com.example.bookkeeper.bookkeeper;

/**
 * Created by Shelley on 7/21/2017.
 *
 * This Class helps sort the Books.
 */

import android.annotation.SuppressLint;
import java.util.Comparator;

@SuppressLint("DefaultLocale")
public class SortBookInfo {
    static final Comparator<BookInfo> TITLE_SORT = new Comparator<BookInfo>() {
        public int compare(BookInfo a, BookInfo b) {
            return a.getBookName().toLowerCase()
                    .compareTo(b.getBookName().toLowerCase());
        }
    };
    static final Comparator<BookInfo> AUTHOR_SORT = new Comparator<BookInfo>() {
        public int compare(BookInfo a, BookInfo b) {
            int comp = a.getBookAuthor().toLowerCase()
                    .compareTo(b.getBookAuthor().toLowerCase());
            if (comp != 0)
                return comp;
            return a.getBookName().toLowerCase()
                    .compareTo(b.getBookName().toLowerCase());
        }
    };
    static final Comparator<BookInfo> RATING_SORT = new Comparator<BookInfo>() {
        public int compare(BookInfo a, BookInfo b) {
            if (a.getRating() > b.getRating())
                return 1;
            if (a.getRating() < b.getRating())
                return -1;
            return a.getBookName().toLowerCase()
                    .compareTo(b.getBookName().toLowerCase());
        }
    };
    static final Comparator<BookInfo> STATUS_SORT = new Comparator<BookInfo>() {
        public int compare(BookInfo a, BookInfo b) {
            if (a.getBookStatus() > b.getBookStatus())
                return 1;
            if (a.getBookStatus() < b.getBookStatus())
                return -1;
            return a.getBookName().toLowerCase()
                    .compareTo(b.getBookName().toLowerCase());
        }
    };

    static final Comparator<BookInfo> OWNED_SORT = new Comparator<BookInfo>() {
        public int compare(BookInfo a, BookInfo b) {
            if (a.getBookIsOwned().equals("yes"))
                return 1;
            if (b.getBookIsOwned().equals("yes"))
                return -1;
            return a.getBookName().toLowerCase()
                    .compareTo(b.getBookName().toLowerCase());
        }
    };

}
