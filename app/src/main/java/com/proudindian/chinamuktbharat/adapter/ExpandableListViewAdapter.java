package com.proudindian.chinamuktbharat.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.proudindian.chinamuktbharat.R;
import com.proudindian.chinamuktbharat.model.Alternative;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;

    // group titles
    private List<String> listOfChinaApps;

    // child data in format of header title, child title
    private HashMap<String, List<Alternative>> listOfAlternatives;

    public ExpandableListViewAdapter(Context context, List<String> listDataGroup,
                                     HashMap<String, List<Alternative>> listChildData) {
        this.context = context;
        this.listOfChinaApps = listDataGroup;
        this.listOfAlternatives = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listOfAlternatives.get(this.listOfChinaApps.get(groupPosition))
                .get(childPosititon);
    }

    public void updateData(List<String> chinaApps, HashMap<String, List<Alternative>> listHashMap) {
        listOfChinaApps = null;
        listOfAlternatives = null;
        listOfChinaApps = chinaApps;
        listOfAlternatives = listHashMap;
        notifyDataSetChanged();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Alternative alternative = (Alternative) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_child, null);
        }

        TextView textViewChild = convertView
                .findViewById(R.id.textViewChild);

        textViewChild.setText(alternative.getAppName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listOfAlternatives.get(this.listOfChinaApps.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listOfChinaApps.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listOfChinaApps.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_group, null);
        }

        TextView textViewGroup = convertView
                .findViewById(R.id.textViewGroup);
        textViewGroup.setTypeface(null, Typeface.BOLD);
        textViewGroup.setText(headerTitle);

        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
