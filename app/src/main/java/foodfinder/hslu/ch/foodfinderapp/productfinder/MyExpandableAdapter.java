package foodfinder.hslu.ch.foodfinderapp.productfinder;

import android.app.Activity;
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
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, child.getName(),
                        Toast.LENGTH_SHORT).show();
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
