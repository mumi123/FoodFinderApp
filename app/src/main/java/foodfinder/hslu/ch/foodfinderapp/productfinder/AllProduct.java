package foodfinder.hslu.ch.foodfinderapp.productfinder;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import foodfinder.hslu.ch.foodfinderapp.R;
import foodfinder.hslu.ch.foodfinderapp.database.DatabaseHandler;

public class AllProduct extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productselection);

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);
        MyExpandableAdapter adapter = new MyExpandableAdapter(this, dbHandler.getAllCatetory());

        listView.setAdapter(adapter);
    }

}
