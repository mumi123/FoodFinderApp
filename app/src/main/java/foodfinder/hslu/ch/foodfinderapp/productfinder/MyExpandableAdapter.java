package foodfinder.hslu.ch.foodfinderapp.productfinder;

import android.app.Activity;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import foodfinder.hslu.ch.foodfinderapp.R;
import foodfinder.hslu.ch.foodfinderapp.communication.TCPClient;
import foodfinder.hslu.ch.foodfinderapp.entity.Category;
import foodfinder.hslu.ch.foodfinderapp.entity.Product;

public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private List<Category> parents;
    private LayoutInflater inflater;
    private Activity activity;

    public MyExpandableAdapter(Activity activity, List<Category> parents){
        this.activity = activity;
        this.parents = parents;
        inflater = activity.getLayoutInflater();
    }

    public void setInflater(LayoutInflater inflater, Activity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group, null);
        }

        Category category = (Category) getGroup(groupPosition);

        ((CheckedTextView) convertView).setText(category.getName());
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_details, null);
        }

        final Product child = (Product) getChild(groupPosition, childPosition);

        TextView textView = null;
        textView = (TextView) convertView.findViewById(R.id.textView1);
        textView.setText(child.getName());

        convertView.setOnClickListener(new OnClickListener() {

            boolean received = false;
            boolean connected = false;

            @Override
            public void onClick(View v) {
                int resId = -1;
                if(TCPClient.getInstance().getTcpClient() != null) {
                    if(TCPClient.getInstance().getTcpClient().isConnected()){
                        TCPClient.getInstance().send(child); //Sende das Produkt der Datenbrille
                        resId = R.string.info_send;
                    }else {
                        resId = R.string.err_noconnection;
                    }
                }else
                {
                    resId = R.string.err_noconnection;
                }


                if(!connected){
                    Thread thread = new Thread(TCPClient.getInstance());
                    thread.start();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(TCPClient.getInstance().getTcpClient() != null){
                        int counter = 0;

                        while(true){
                            if(TCPClient.getInstance().getTcpClient().isConnected()){
                                connected = true;
                                break;
                            }

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if(counter>10){
                                break; //mehr als 10 versuche! --> Abbrechen
                            }
                            counter++;
                        }
                    }
                }

                if(TCPClient.getInstance().getTcpClient().isConnected()){
                    TCPClient.getInstance().send(child); //Sende das Produkt der Datenbrille
                    try {
                        Thread.sleep(1000); //Eine Sekunde warten, bis die Connection aufgebaut ist.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("Fehler beim Senden!");
                }

                Toast.makeText(activity, resId,
                        Toast.LENGTH_SHORT).show();

                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            if(TCPClient.getInstance().receive()){
                                System.out.println("Ich habe das Produkt gefunden!");
                                ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 1000);
                                toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 2000);
                            }else{
                                System.out.println("Kein Produkt!!!!");
                            }
                            received = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while(!received){
                    //Ladebalken anzeigen
                }
                TCPClient.resetInstance();
                System.out.println("instanz wurde zerstört!");
            }
        });

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parents.get(groupPosition).getProducts().get(childPosition);
    }

    @Override
    public int getGroupCount() {
        return parents.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parents.get(groupPosition).getProducts().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parents.get(groupPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

}
