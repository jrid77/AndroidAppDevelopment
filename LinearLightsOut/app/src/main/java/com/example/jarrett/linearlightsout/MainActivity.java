package com.example.jarrett.linearlightsout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mNewGameButton;
    private Button[] mLightsOutButtons;
    private TextView mTextView;
    private LightsOutGame mGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame = new LightsOutGame(this);

        mLightsOutButtons = new Button[7];
        mNewGameButton = (Button)findViewById(R.id.newGameButton);
        mNewGameButton.setOnClickListener(this);

        mTextView = (TextView)findViewById(R.id.textView);

        for (int i = 0; i < 7; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            mLightsOutButtons[i] = (Button) findViewById(id);
            mLightsOutButtons[i].setOnClickListener(this);
            mLightsOutButtons[i].setText("" + mGame.getValueAtIndex(i));
        }

        if (savedInstanceState != null){
            mGame.setAllValues(savedInstanceState.getIntArray("listOfValues"));
            for(int i = 0; i < 7; i++) {
                mLightsOutButtons[i].setText("" + mGame.getValueAtIndex(i));
            }
            mGame.setNumPresses(savedInstanceState.getInt("numPresses"));
            mTextView.setText(savedInstanceState.getString("stringForGameState"));

        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.newGameButton) {
            mGame = new LightsOutGame(this);
            for(int i = 0; i < 7; i++) {
                mLightsOutButtons[i].setEnabled(true);
            }
        } else {
            for (int i = 0; i < 7; i++) {
                if(view.getId() == mLightsOutButtons[i].getId()) {
                    mGame.pressedButtonAtIndex(i);
                }
            }
        }
        this.update();
    }

    private void update() {
        mTextView.setText(mGame.stringForGameState());
        for (int i = 0; i < 7; i++) {
            mLightsOutButtons[i].setText("" + mGame.getValueAtIndex(i));
        }
        if(mGame.checkForWin()) {
            for(int i = 0; i < 7; i++){
                mLightsOutButtons[i].setEnabled(false);
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        int list[] = new int[7];

        for(int i = 0; i < 7; i++) {
            list[i] = mGame.getValueAtIndex(i);
        }
        savedInstanceState.putIntArray("listOfValues", list);
        savedInstanceState.putInt("numPresses", mGame.getNumPresses());
        savedInstanceState.putString("stringForGameState", mGame.stringForGameState());

        super.onSaveInstanceState(savedInstanceState);
    }

}
