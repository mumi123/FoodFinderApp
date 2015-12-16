package foodfinder.hslu.ch.foodfinderapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import foodfinder.hslu.ch.foodfinderapp.communication.TCPClient;
import foodfinder.hslu.ch.foodfinderapp.productfinder.AllProduct;
import foodfinder.hslu.ch.foodfinderapp.settings.Settings;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        PreferenceManager.setDefaultValues(this, R.xml.settings, false); //DefaultValues laden
        Settings settings = new Settings();
        settings.loadPrefs(this);

        System.out.println("Load Prefs1: " + Settings.ipServerAdress);
        System.out.println("Load Prefs2: " + Settings.port);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showAllCat(View view) {
        Intent intent = new Intent(getApplicationContext(), AllProduct.class);
        startActivity(intent);
    }

    public void connectToGlasses(View view){
        Thread thread = new Thread(TCPClient.getInstance());
        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (TCPClient.getInstance().getTcpClient() != null){
            if(TCPClient.getInstance().getTcpClient().isConnected()){
                ImageView conStatus = (ImageView) findViewById(R.id.connectionView);
                conStatus.setImageResource(R.drawable.connected);
            }else{
                ImageView conStatus = (ImageView) findViewById(R.id.connectionView);
                conStatus.setImageResource(R.drawable.notconnected);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //Intent myIntent = new Intent(MainActivity.this, Settings.class);
            //myIntent.putExtra("key", value); //Optional parameters
            //MainActivity.this.startActivity(myIntent);
            Intent i = new Intent(this, Settings.class);
            startActivityForResult(i, RESULT_SETTINGS);
            //Intent intent = new Intent(getApplicationContext(), Settings.class);
            //startActivity(intent);
        }else if(id == R.id.action_about) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title);

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
