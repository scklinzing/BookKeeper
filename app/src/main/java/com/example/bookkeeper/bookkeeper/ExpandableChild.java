package com.example.bookkeeper.bookkeeper;

/**
 * Created by Shelley on 7/21/2017.
 *
 * This is a helper class to generate the list of Books.
 */

public class ExpandableChild {
    private String text;
    private boolean button;

    public String getText(){
        return text;
    }
    public boolean getButton(){
        return button;
    }

    public void setText(String a){
        text = a;
    }
    public void setButton(boolean a){
        button = a;
    }
    public ExpandableChild (String a, boolean b){
        text = a;
        button = b;
    }
}