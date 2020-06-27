package com.proudindian.chinamuktbharat.view.fagments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.proudindian.chinamuktbharat.R;
import com.proudindian.chinamuktbharat.adapter.ChinaAppsAdapter;
import com.proudindian.chinamuktbharat.adapter.IAdapterInterface;
import com.proudindian.chinamuktbharat.util.FilterChinaAppsHelper;
import com.proudindian.chinamuktbharat.util.Utils;
import com.proudindian.chinamuktbharat.view.MainActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;

public class AppsListFragment extends Fragment implements IAdapterInterface {

    int UNINSTALL_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ChinaAppsAdapter adapter;
    private static ArrayList<FilterChinaAppsHelper.ApplicationInfo> apps;
    private static boolean isNoChinaApps;
    private static boolean IsSystemApp;
    private int requestDeletInddex;
    private ConstraintLayout noChinaAppFoundScreen;
    private ConstraintLayout deviceLayout;
    private static boolean isForDeviceStatus;
    private LottieAnimationView deiceAnimation;
    private AppCompatTextView deviceStatusMessage;
    private AppCompatTextView deviceName;
    private LottieAnimationView alertAnim;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static Fragment NewInstance(ArrayList<FilterChinaAppsHelper.ApplicationInfo> appList,
                                       boolean isSystemApp, boolean isForDeviceState) {
        apps = appList;
        IsSystemApp = isSystemApp;
        isForDeviceStatus = isForDeviceState;
        if (apps.size() == 0) {
            isNoChinaApps = true;
        }
        return new AppsListFragment();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main2, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        loadAppropriateScreen(isNoChinaApps, IsSystemApp, isForDeviceStatus);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.applist_layout, container, false);
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        noChinaAppFoundScreen = view.findViewById(R.id.stateDisplay);
        deviceLayout = view.findViewById(R.id.deviceStateDisplay);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        deiceAnimation = view.findViewById(R.id.deviceAnim);
        LottieAnimationView shareButton = view.findViewById(R.id.share_anim);
        alertAnim = view.findViewById(R.id.alert_anim);
        deviceStatusMessage = view.findViewById(R.id.deviceStatusMessage);
        deviceName = view.findViewById(R.id.deviceName);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null)
                    ((MainActivity) getActivity()).shareAppThisApp();
            }
        });
    }

    private void loadAppropriateScreen(boolean isNoChinaApps, boolean IsSystemApp, boolean IsforDeviceSatus) {

        if (IsforDeviceSatus) {
            ShowDeviceStatus();
        } else if (isNoChinaApps && !IsSystemApp) {
            ShowNoChinaApps();
        } else if (IsSystemApp && isNoChinaApps) {
            ShowNoChinaApps();
        } else {
            ArrayList<FilterChinaAppsHelper.ApplicationInfo> listOfApps = new ArrayList<>();
            recyclerView.setLayoutManager(mLayoutManager);
            adapter = new ChinaAppsAdapter(
                    listOfApps, IsSystemApp);
            int resId = R.anim.layout_animation_from_bottom;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
            adapter.setReference(this);
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);

            if (IsSystemApp) {
                alertAnim.setAnimation(R.raw.alert_systemapps);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("System Apps");
            } else {
                alertAnim.setAnimation(R.raw.alert_apps);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("User Installed Apps");

            }
            alertAnim.setVisibility(View.VISIBLE);
            adapter.UpdateData(apps);
            runLayoutAnimation(recyclerView);
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

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);
        recyclerView.setLayoutAnimation(controller);
        if (recyclerView.getAdapter() != null)
            recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void ScanDeviceForChinaApps() {
        ArrayList<FilterChinaAppsHelper.ApplicationInfo> apps = Utils.getChinaInstalledApps();
        Log.v("data ", "apps " + apps.size());
        if (apps.size() == 0) {
            ShowNoChinaApps();
        }
        adapter.UpdateData(apps);
    }

    private void ShowNoChinaApps() {
        Log.v("MainActivity", "Good no more china apps ");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable
                (new ColorDrawable(Color.parseColor("#00e676")));
        noChinaAppFoundScreen.setVisibility(View.VISIBLE);
        alertAnim.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    private void ShowDeviceStatus() {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Your Device Manufacturer");

        deviceName.setText(Utils.getMenufacturerName());
        if (Utils.isIsMenuFacturerChina()) {
            deiceAnimation.setAnimation(R.raw.device_black);
            deviceStatusMessage.setText(R.string.device_manufacturer_is_china);
        } else {
            deiceAnimation.setAnimation(R.raw.device_green);
            deviceStatusMessage.setText(R.string.device_manufacturer_is_not_china);
        }
        alertAnim.setVisibility(View.GONE);
        noChinaAppFoundScreen.setVisibility(View.GONE);
        deviceLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void createRequestForAppDelete(String packageName, int index) {
        requestDeletInddex = index;
        Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        if (getActivity() != null && getActivity().getPackageManager() != null && intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, UNINSTALL_REQUEST_CODE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("TAG", "onActivityResult: user accepted the (un)install");
                removeItemFromList(requestDeletInddex);
                requestDeletInddex = -5;
                ScanDeviceForChinaApps();
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("TAG", "onActivityResult: user canceled the (un)install");
                requestDeletInddex = -5;
            } else if (resultCode == RESULT_FIRST_USER) {
                Log.d("TAG", "onActivityResult: failed to (un)install");
                requestDeletInddex = -5;
            }
        }
    }

    private void removeItemFromList(int index) {
        if (index >= 0 && index < Utils.getChinaInstalledApps().size()) {
            Utils.getChinaInstalledApps().remove(index);
        }
    }

    @Override
    public void deleteChinaApp(String packageName, int index) {
        createRequestForAppDelete(packageName, index);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        if (adapter != null) {
            adapter.setReference(null);
        }
        IsSystemApp = false;
        isForDeviceStatus = false;
        isNoChinaApps = false;
        super.onDestroyView();
    }
}
