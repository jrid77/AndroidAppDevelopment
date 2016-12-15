package com.example.jarrett.exam1byalexanjs;

/**
 * Created by jarrett on 12/13/16.
 */

public class Gift {

    private String mGift;
    private String mName;
    private boolean mPriority;

    public Gift(String gift, String name) {
        this.mGift = gift;
        this.mName = name;
        this.mPriority = false;
    }

    public String getGift() {
        return mGift;
    }

    public String getName() {
        return mName;
    }
    public void setPriority() {
        mPriority = !mPriority;
    }
    @Override
    public String toString() {
        if(mPriority) {
            return (this.mGift + " for " + this.mName + "\n").toUpperCase();
        }
        return this.mGift + " for " + this.mName + "\n";
    }
}
