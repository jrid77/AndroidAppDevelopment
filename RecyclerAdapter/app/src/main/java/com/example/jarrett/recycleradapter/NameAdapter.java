package com.example.jarrett.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {

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

    /**
     * Created by jarrett on 12/12/16.
     */

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView positionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.name_view);
            positionTextView = (TextView)itemView.findViewById(R.id.position_view);
        }

        // Click listeners will go here too
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.row_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTextView.setText(mNames.get(position));
        holder.positionTextView.setText("I'm number " + position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public void addName() {
        mNames.add(getRandomName());
        notifyDataSetChanged(); // resets the view
    }

    public void removeName(int index) {
        mNames.remove(index);
        notifyDataSetChanged();
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
