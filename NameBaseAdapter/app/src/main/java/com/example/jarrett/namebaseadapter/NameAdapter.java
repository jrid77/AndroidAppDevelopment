package com.example.jarrett.namebaseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jarrett on 12/12/16.
 */

public class NameAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mNames;
    private Random mRandom;

    public NameAdapter(Context mContext) {
        this.mContext = mContext;
        mNames = new ArrayList<>();
        mRandom = new Random();
        for (int i = 0; i < 5; i++) {
            mNames.add(getRandomName());
        }
    }

    @Override
    public int getCount() {
        return mNames.size();
    }

    @Override
    public Object getItem(int i) {
        return mNames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void addName() {
        mNames.add(getRandomName());
        notifyDataSetChanged(); // resets the view
    }

    public void removeName(int index) {
        mNames.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            // I have to make it myself
            view = LayoutInflater.from(this.mContext).inflate(R.layout.row_view, parent, false);
        } else {
            // I can reuse it
            view = convertView;
        }
        // Customize the view
        TextView nameTextView = (TextView)view.findViewById(R.id.name_view);
        nameTextView.setText(mNames.get(position));

        TextView positionTextView = (TextView)view.findViewById(R.id.position_view);
        positionTextView.setText("I'm number " + position);
        return view;
    }

    private String getRandomName() {
        String[] names = new String[]{
                "Hannah", "Emily", "Sarah", "Madison", "Brianna",
                "Kaylee", "Kaitlyn", "Hailey", "Alexis", "Elizabeth",
                "Michael", "Jacob", "Matthew", "Nicholas", "Christopher",
                "Joseph", "Zachary", "Joshua", "Andrew", "William"
        };
        return names[mRandom.nextInt(names.length)];
    }
}
