package com.example.jarrett.jersey;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private Jersey mCurrentJersey;
    private TextView mNameView;
    private ImageView mJerseyView;
    private TextView mNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCurrentJersey = new Jersey();

        mNameView = (TextView)findViewById(R.id.name);
        mNumberView = (TextView)findViewById(R.id.number);
        mJerseyView = (ImageView)findViewById(R.id.jerseyView);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeJersey();
            }
        });

        update();
    }

    private void changeJersey() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = this.getLayoutInflater().inflate(R.layout.dialog_add, null, false);
        final EditText nameET = (EditText)view.findViewById(R.id.edit_name);
        final EditText numberET = (EditText)view.findViewById(R.id.edit_number);
        final Switch redOrBlue = (Switch)view.findViewById(R.id.redorblue);

        builder.setView(view);
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int number = 0;
                if(!numberET.getText().toString().equals("")) {
                    number = Integer.parseInt(numberET.getText().toString());
                }
                mCurrentJersey = new Jersey(nameET.getText().toString(), number, redOrBlue.isChecked());
                update();
            }
        });
        builder.create().show();
    }

    private void update() {
        mNameView.setText(mCurrentJersey.getName());
        mNumberView.setText("" + mCurrentJersey.getNumber());
        if(mCurrentJersey.getIsRed()) {
            mJerseyView.setImageResource(R.drawable.red_jersey);
        } else {
            mJerseyView.setImageResource(R.drawable.blue_jersey);
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
