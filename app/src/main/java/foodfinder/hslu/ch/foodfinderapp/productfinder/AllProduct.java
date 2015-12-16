package foodfinder.hslu.ch.foodfinderapp.productfinder;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import foodfinder.hslu.ch.foodfinderapp.R;
import foodfinder.hslu.ch.foodfinderapp.database.DatabaseHandler;
import foodfinder.hslu.ch.foodfinderapp.entity.Category;
import foodfinder.hslu.ch.foodfinderapp.entity.Product;

public class AllProduct extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productselection);

        System.out.println("Started");

        DatabaseHandler dbHandler = new DatabaseHandler(this);

        /*
        for(Category cat : dbHandler.getAllCatetory()){
            System.out.println("Kategorie: "+cat.getName());

            for(Product prd : cat.getProducts()){
                System.out.println("Produkt: "+prd.getName());
            }
        }
        */

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);
        MyExpandableAdapter adapter = new MyExpandableAdapter(this, dbHandler.getAllCatetory());
        listView.setAdapter(adapter);
    }

}
