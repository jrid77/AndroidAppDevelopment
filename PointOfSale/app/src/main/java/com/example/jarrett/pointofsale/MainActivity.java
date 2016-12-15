package com.example.jarrett.pointofsale;

import android.app.Dialog;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    private Item currentItem;
    private TextView mNameTextView;
    private TextView mQuantityTextView;
    private TextView mDateTextView;
    private ArrayList<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNameTextView = (TextView)findViewById(R.id.name_text);
        mQuantityTextView = (TextView)findViewById(R.id.quantity_text);
        mDateTextView = (TextView)findViewById(R.id.date_text);
        mItems = new ArrayList<Item>();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //currentItem = Item.getDefaultItem();
                //showCurrentItem();
                addItem();
            }
        });
    }

    private void addItem(){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = this.getLayoutInflater().inflate(R.layout.dialog_add, null, false);
                final EditText nameET = (EditText)view.findViewById(R.id.edit_name);
                final EditText quantityET = (EditText)view.findViewById(R.id.edit_quantity);
                final CalendarView calendarView = (CalendarView)view.findViewById(R.id.calendar_view);
                final GregorianCalendar calendar = new GregorianCalendar();
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView calendarView, int year, int month , int dom) {
                        calendar.set(year, month, dom);
                    }
                });

                builder.setView(view);
                builder.setNegativeButton(android.R.string.cancel, null);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("POS", "OK clicked");
                        String name = nameET.getText().toString();
                        int quantity = Integer.parseInt(quantityET.getText().toString());
                        currentItem = new Item(name, quantity, calendar);
                        mItems.add(currentItem);
                        showCurrentItem();
                    }
                });
                builder.create().show();


    }

    private void showCurrentItem() {
        mNameTextView.setText(currentItem.getName());
        mDateTextView.setText(currentItem.getDeliveryDateString());
        mQuantityTextView.setText("" + currentItem.getQuantity());
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
        final Item tempItem = currentItem;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_reset) {
            currentItem = new Item();
            showCurrentItem();
            View cooridinator = findViewById(R.id.cooridinator_layout);
            Snackbar snackbar = Snackbar.make(cooridinator, "Removing", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentItem =  tempItem;
                    showCurrentItem();
                }
            });
            snackbar.show();
            return true;
        }
        if ( id == R.id.action_search ) {
            showSearchDialog();
            return true;
        }
        if ( id == R.id.action_clear_all) {
            showConfirmationDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_item_title);

        builder.setItems(getNames(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentItem = mItems.get(i);
                showCurrentItem();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    private String[] getNames() {
        String[] names = new String[mItems.size()];
        for (int i = 0; i < mItems.size(); i++) {
            names[i] = mItems.get(i).getName();
        }
        return names;
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.clear_all);

        builder.setMessage("Are you sure you want to remove all items?");



        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteAllItems();
                currentItem = new Item();
                showCurrentItem();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    private void deleteAllItems(){
        this.mItems.clear();
    }

}
