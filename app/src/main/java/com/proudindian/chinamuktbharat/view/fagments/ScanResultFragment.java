package com.proudindian.chinamuktbharat.view.fagments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.proudindian.chinamuktbharat.R;
import com.proudindian.chinamuktbharat.util.FilterChinaAppsHelper;
import com.proudindian.chinamuktbharat.util.Utils;
import com.proudindian.chinamuktbharat.view.MainActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class ScanResultFragment extends Fragment implements OnChartValueSelectedListener {

    PieChart chart;
    LottieAnimationView suggetions;
    int[] MY_COLORS = new int[3];
    protected final String[] parties = new String[]{
            "Installed", "In-Built", "Manufacturer"
    };

    public static Fragment NewInstance() {
        return new ScanResultFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.scan_result_layout, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main2, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart = view.findViewById(R.id.chart1);
        suggetions = view.findViewById(R.id.alternates);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        suggetions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).iFragmentTransactionListner.loadAlternativeAppsList();
            }
        });
        initCharts();
    }

    private void initCharts() {

        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);
        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(0);
        chart.setDrawRoundedSlices(true);
        chart.setHoleRadius(40f);
        chart.setTransparentCircleRadius(61f);
        chart.setDrawCenterText(true);
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.setUsePercentValues(false);
        chart.setOnChartValueSelectedListener(this);
        chart.animateY(1400, Easing.EaseInOutQuad);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(14f);
        refreshChart();
    }

    public void refreshChart() {
        SetColorCodingBasedOnScanStatus(Utils.getChinaInstalledApps().size() == 0,
                Utils.getChinaSystemAppsApp().size() == 0, Utils.isIsMenuFacturerChina());
        setData(Utils.getChinaInstalledApps(), Utils.getChinaSystemAppsApp(), Utils.getMenufacturerName(),
                Utils.isIsMenuFacturerChina());
    }

    private void setData(ArrayList<FilterChinaAppsHelper.ApplicationInfo> apps,
                         ArrayList<FilterChinaAppsHelper.ApplicationInfo> systemApps, String deviceMenu, boolean isChinaMenu) {

        ArrayList<PieEntry> entries = new ArrayList<>();
        PieEntry pieEntryInstalledApps = new PieEntry(0.35f, "Installed Apps", apps);
        entries.add(pieEntryInstalledApps);
        PieEntry pieEntrySystemApps = new PieEntry(0.34f, "System Apps", systemApps);
        entries.add(pieEntrySystemApps);
        PieEntry menufactuer = new PieEntry(0.33f, "Manufacturer", systemApps);
        entries.add(menufactuer);
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(7f);
        dataSet.setSelectionShift(5f);
        dataSet.setDrawValues(false);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : MY_COLORS) colors.add(c);
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);
        chart.highlightValues(null);
        chart.invalidate();
    }

    public void SetColorCodingBasedOnScanStatus(boolean isNoInstalledAppFound, boolean isNoSystemAppsFound
            , boolean isNoChinaDevice) {
        MY_COLORS = null;
        MY_COLORS = new int[3];
        if (isNoInstalledAppFound) {
            MY_COLORS[0] = Color.rgb(0, 230, 118);
        } else {
            MY_COLORS[0] = Color.rgb(227, 30, 36);
        }
        if (isNoSystemAppsFound) {
            MY_COLORS[1] = Color.rgb(0, 230, 118);
        } else {
            MY_COLORS[1] = Color.rgb(232, 114, 74);
        }
        if (!isNoChinaDevice) {
            MY_COLORS[2] = Color.rgb(0, 230, 118);
        } else {
            MY_COLORS[2] = Color.rgb(77, 89, 79);
        }
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.v("MainActivity", "apps " + e.getY());

        if (e.getY() == 0.33f) {
            setToolBarForMenu(Utils.isIsMenuFacturerChina());
            ((MainActivity) getActivity()).iFragmentTransactionListner.loadAppsList(Utils.getSystemApps(),
                    false, true);
        } else if (e.getY() == 0.35f) {
            ((MainActivity) getActivity()).iFragmentTransactionListner.loadAppsList(Utils.getChinaInstalledApps(),
                    false, false);
            setTouchColorForItemSelection(Utils.getChinaInstalledApps().size() == 0);
        } else if (e.getY() == 0.34f) {
            ((MainActivity) getActivity()).iFragmentTransactionListner.loadAppsList(Utils.getSystemApps(),
                    true, false);
            setToolBarColorForSystemAppClick(Utils.getSystemApps().size() == 0);
        }
    }


    private void setTouchColorForItemSelection(boolean isTrue) {
        if (isTrue) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                    (new ColorDrawable(Color.parseColor("#00e676")));
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                    (new ColorDrawable(Color.parseColor("#e31e24")));
        }
    }

    private void setToolBarColorForSystemAppClick(boolean isTrue) {
        if (isTrue) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                    (new ColorDrawable(Color.parseColor("#00e676")));
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                    (new ColorDrawable(Color.parseColor("#e8724a")));
        }
    }

    private void setToolBarForMenu(boolean isTrue) {
        if (!isTrue) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                    (new ColorDrawable(Color.parseColor("#00e676")));
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                    (new ColorDrawable(Color.parseColor("#4d594f")));
        }
    }

    @Override
    public void onNothingSelected() {
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

}
