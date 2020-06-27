package com.proudindian.chinamuktbharat.view.fagments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.proudindian.chinamuktbharat.R;
import com.proudindian.chinamuktbharat.adapter.ExpandableListViewAdapter;
import com.proudindian.chinamuktbharat.model.Alternative;
import com.proudindian.chinamuktbharat.model.Root;
import com.proudindian.chinamuktbharat.util.Utils;
import com.proudindian.chinamuktbharat.view.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlternativesListFragment extends Fragment {

    private ExpandableListView simpleExpandableListView;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> listOfChinaApps;
    private HashMap<String, List<Alternative>> listDataChild;


    public static AlternativesListFragment NewInstance() {
        return new AlternativesListFragment();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main2, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alternative_apps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        simpleExpandableListView = view.findViewById(R.id.expandableListView);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Cool Alternatives ");
        initListeners();
        initObjects();
        initListData();
    }

    private void initListData() {

        List<Root> alternateChinaAppsInfo =
                Utils.getAlternateChinaAppsInfo().getRoot();
        for (int i = 0; i < alternateChinaAppsInfo.size(); i++) {
            listOfChinaApps.add(alternateChinaAppsInfo.get(i).getChinaAppName());
            listDataChild.put(alternateChinaAppsInfo.get(i).getChinaAppName(),
                    alternateChinaAppsInfo.get(i).getAlternatives());
        }
        expandableListViewAdapter.updateData(listOfChinaApps, listDataChild);
        for (int i = 0; i < expandableListViewAdapter.getGroupCount(); i++)
            simpleExpandableListView.expandGroup(i);
    }

    private void initListeners() {

        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (getActivity() != null) {

                    ((MainActivity) getActivity()).downloadThisApp(listDataChild.get(
                            listOfChinaApps.get(groupPosition)).get(
                            childPosition).getPackageName());
                }
                return false;
            }
        });
    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {
        listOfChinaApps = new ArrayList<>();
        listDataChild = new HashMap<>();
        expandableListViewAdapter = new ExpandableListViewAdapter(getActivity(), listOfChinaApps, listDataChild);
        simpleExpandableListView.setAdapter(expandableListViewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.rate_us:
                if (getActivity() != null)
                    ((MainActivity) getActivity()).rateThisApp();
                return true;
            case R.id.share:
                if (getActivity() != null)
                    ((MainActivity) getActivity()).shareAppThisApp();
                return true;
            case R.id.aboutCreator:
                if (getActivity() != null)
                    ((MainActivity) getActivity()).showAboutDev();
                return true;
        }
        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
