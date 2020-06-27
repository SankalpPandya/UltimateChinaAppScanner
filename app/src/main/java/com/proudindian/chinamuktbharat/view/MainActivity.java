package com.proudindian.chinamuktbharat.view;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.proudindian.chinamuktbharat.R;
import com.proudindian.chinamuktbharat.util.FilterChinaAppsHelper;
import com.proudindian.chinamuktbharat.util.Utils;
import com.proudindian.chinamuktbharat.view.fagments.AboutPageFragment;
import com.proudindian.chinamuktbharat.view.fagments.AlternativesListFragment;
import com.proudindian.chinamuktbharat.view.fagments.AppsListFragment;
import com.proudindian.chinamuktbharat.view.fagments.ScanFragment;
import com.proudindian.chinamuktbharat.view.fagments.ScanResultFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IFragmentTransactionListner
        , FragmentManager.OnBackStackChangedListener {


    AppUpdateManager mAppUpdateManager;
    public IFragmentTransactionListner iFragmentTransactionListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        iFragmentTransactionListner = this;
        Utils.setAlternateChinaAppsInfo();
        showScanFragment();
        checkAppUpdate();
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    private void showScanFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out)
                .replace(R.id.base_frame_container,
                        ScanFragment.NewInstance(),
                        ScanFragment.class.getName())
                .commitAllowingStateLoss();
    }

    private void showAppListFragment(ArrayList<FilterChinaAppsHelper.ApplicationInfo> apps, boolean isSystemApp, boolean isForDeviceStaus) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out)
                .replace(R.id.base_frame_container,
                        AppsListFragment.NewInstance(apps, isSystemApp, isForDeviceStaus),
                        AppsListFragment.class.getName())
                .addToBackStack(AppsListFragment.class.getName())
                .commitAllowingStateLoss();
    }

    private void showScanResultFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out)
                .add(R.id.base_frame_container,
                        ScanResultFragment.NewInstance(),
                        ScanResultFragment.class.getName())
                .addToBackStack(ScanResultFragment.class.getName())
                .commitAllowingStateLoss();
    }


    private void showAlternativeAppsFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out)
                .replace(R.id.base_frame_container,
                        AlternativesListFragment.NewInstance(),
                        AlternativesListFragment.class.getName())
                .addToBackStack(AlternativesListFragment.class.getName())
                .commitAllowingStateLoss();
    }

    private void showAboutPage() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out)
                .add(R.id.base_frame_container,
                        AboutPageFragment.NewInstance(),
                        AboutPageFragment.class.getName())
                .addToBackStack(AboutPageFragment.class.getName())
                .commitAllowingStateLoss();
    }

    private void checkAppUpdate() {
        try {
            this.mAppUpdateManager = AppUpdateManagerFactory.create(this);
            this.mAppUpdateManager.registerListener(this.installStateUpdatedListener);
            this.mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
                public void onSuccess(AppUpdateInfo appUpdateInfo) {
                    if (appUpdateInfo.updateAvailability() == 2 && appUpdateInfo.isUpdateTypeAllowed(0)) {
                        try {
                            mAppUpdateManager.startUpdateFlowForResult
                                    (appUpdateInfo, 0, MainActivity.this, 201);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                    } else if (appUpdateInfo.installStatus() == 11) {
                        popupSnackbarForCompleteUpdate();
                    } else {
                        Log.e("MainActivity", "checkForAppUpdateAvailability: something else");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        public void onStateUpdate(InstallState installState) {
            try {
                if (installState.installStatus() == InstallStatus.INSTALLED) {
                    popupSnackbarForCompleteUpdate();
                } else if (installState.installStatus() != InstallStatus.INSTALLED) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("InstallStateUpdatedListener: state: ");
                    sb.append(installState.installStatus());
                    Log.i("MainActivity", sb.toString());
                } else if (mAppUpdateManager != null) {
                    mAppUpdateManager.unregisterListener(installStateUpdatedListener);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void popupSnackbarForCompleteUpdate() {
        try {
            Snackbar make = Snackbar.make(findViewById(R.id.base_frame_container), (CharSequence)
                    "An update has just been downloaded.", 3000);
            make.setAction((CharSequence) "RESTART", (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    if (MainActivity.this.mAppUpdateManager != null) {
                        MainActivity.this.mAppUpdateManager.completeUpdate();
                    }
                }
            });
            make.setActionTextColor(getResources().getColor(R.color.colorPrimaryDark));
            make.show();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void displayScanResult(ArrayList<FilterChinaAppsHelper.ApplicationInfo>
                                          apps, ArrayList<FilterChinaAppsHelper.ApplicationInfo> systemApp,
                                  boolean isChinaManufacturer, String menuName) {
        Log.v("MainAcivity", "displayScanResult " + apps.size() +
                "systemApp " + systemApp.size() + "isChinaManufacturer "
                + isChinaManufacturer + "menuName " + menuName);

        Utils.setInstalledApps(apps);
        Utils.setSystemApps(systemApp);
        Utils.setIsMenuFacturerChina(isChinaManufacturer);
        Utils.setMenufacturerName(menuName);
        showScanResultFragment();
    }

    @Override

    public void loadAppsList(ArrayList<FilterChinaAppsHelper.ApplicationInfo> apps, boolean isSystemApp, boolean isForDeviceStatus) {
        Log.v("MainAcivity", "loadAppsList ");
        showAppListFragment(apps, isSystemApp, isForDeviceStatus);
    }

    @Override
    public void loadAlternativeAppsList() {
        Log.v("MainAcivity", "loadAlternativeAppsList ");
        showAlternativeAppsFragment();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
            ab.setTitle("App Exit");
            ab.setMessage("Are you sure want to exit?");
            ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    finish();
                }
            });
            ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ab.show();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void shareAppThisApp() {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
            String sb = "\nHey, I am using Ultimate China app Scanner to find all Chinese apps including system apps and find few alternative apps as a replacement. Lets come together by using this app get rid of chinese apps\n\n" +
                    "https://play.google.com/store/apps/details?id=" +
                    getPackageName() +
                    "\n\n";
            intent.putExtra("android.intent.extra.TEXT", sb);
            startActivity(Intent.createChooser(intent, "choose one"));
        } catch (Exception ex) {
        }
    }

    public void rateThisApp() {
        StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(getPackageName());
        String str = "android.intent.action.VIEW";
        Intent intent = new Intent(str, Uri.parse(sb.toString()));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            String sb2 = "http://play.google.com/store/apps/details?id=" +
                    getPackageName();
            startActivity(new Intent(str, Uri.parse(sb2)));
        }
    }


    public void downloadThisApp(String packageName) {
        Log.v("MainAcivity", "downloadThisApp " + packageName);

        String str = "android.intent.action.VIEW";
        String sb = "market://details?id=" +
                packageName;
        Intent intent = new Intent(str, Uri.parse(sb));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            String sb2 = "http://play.google.com/store/apps/details?id=" +
                    packageName;
            startActivity(new Intent(str, Uri.parse(sb2)));
        }
    }

    public void showAboutDev() {
        Log.v("MainAcivity", "showAboutDev ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment topFragment = fragmentManager.findFragmentByTag(AboutPageFragment.class.getName());
        if (topFragment == null) {
            showAboutPage();
        }
    }

    @Override
    public void onBackStackChanged() {
        Log.v("MainAcivity", "onBackStackChanged " + getSupportFragmentManager().getBackStackEntryCount());
    }
}
