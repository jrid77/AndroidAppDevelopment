package com.example.jarrett.exam1byalexanjs;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private EditText mGiftText;
    private EditText mPersonText;
    private Button mButton;
    private TextView mTextView;
    private ArrayList<Gift> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mGiftText = (EditText)findViewById(R.id.editText1);
        mPersonText = (EditText)findViewById(R.id.editText2);
        mButton = (Button)findViewById(R.id.button);
        mTextView = (TextView)findViewById(R.id.shoppingListView);
        mList = new ArrayList<>();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.add(new Gift(mGiftText.getText().toString(), mPersonText.getText().toString()));
                update();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        final ArrayList<Gift> temp = new ArrayList<>();

        for(int i = 0; i < mList.size(); i++) {
            temp.add(mList.get(i));
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        if (id == R.id.action_reset) {
            this.resetList();
            update();
            //Show Snackbar
            View cooridinator = findViewById(R.id.activity_main);
            Snackbar snackbar = Snackbar.make(cooridinator, "Removing", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList = temp;
                    update();
                }
            });
            snackbar.show();
            return true;
        }
        if ( id == R.id.action_priority) {
            showDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Priority");

        builder.setItems(getNames(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mList.get(i).setPriority();
                update();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    public String[] getNames() {
        String names[] = new String[this.mList.size()];
        for(int i = 0; i < this.mList.size(); i++) {
            names[i] = this.mList.get(i).getName();
        }
        return names;
    }

    private void update() {
        String list = "";
        for(int i = 0; i < this.mList.size(); i++) {
            list += this.mList.get(i).toString();
        }
        this.mTextView.setText(list);
    }


    private void resetList() { this.mList.clear(); }
}
