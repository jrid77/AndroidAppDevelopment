package com.example.jarrett.jersey;

/**
 * Created by jarrett on 12/14/16.
 */

public class Jersey {

    private String mName;
    private int mNumber;
    private boolean mIsRed;

    public Jersey(String name, int number, boolean isRed) {
        this.mName = name;
        this.mNumber = number;
        this.mIsRed = isRed;
    }

    public Jersey() {
        this.mName = "ANDROID";
        this.mNumber = 17;
        this.mIsRed = true;
    }

    public String getName() {
        return this.mName;
    }

    public int getNumber() {
        return this.mNumber;
    }

    public boolean getIsRed() {
        return this.mIsRed;
    }
}
