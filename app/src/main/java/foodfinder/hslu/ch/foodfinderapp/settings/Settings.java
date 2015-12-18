package foodfinder.hslu.ch.foodfinderapp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


import foodfinder.hslu.ch.foodfinderapp.R;

public class Settings extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static String ipServerAdress;
    public static int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        ipServerAdress = sharedPreferences.getString("prefIPAddress", "192.168.1.1");
        port = Integer.parseInt(sharedPreferences.getString("prefPort", "8080"));
    }

    public void loadPrefs(Context context){
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        ipServerAdress = SP.getString("prefIPAddress", "192.168.1.1");
        
        try{
            port = Integer.parseInt(SP.getString("prefPort", "8080"));
        }catch (NumberFormatException ex){
            System.out.println("Keine Gültige Zahl");
        }
    }
}
