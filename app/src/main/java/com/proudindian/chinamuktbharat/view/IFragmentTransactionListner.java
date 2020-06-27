package com.proudindian.chinamuktbharat.view;

import com.proudindian.chinamuktbharat.util.FilterChinaAppsHelper;

import java.util.ArrayList;

public interface IFragmentTransactionListner {
    void displayScanResult(ArrayList<FilterChinaAppsHelper.ApplicationInfo> apps
            , ArrayList<FilterChinaAppsHelper.ApplicationInfo> systemApp, boolean isChinaManufacturer, String menuName);

    void loadAppsList(ArrayList<FilterChinaAppsHelper.ApplicationInfo> apps, boolean isSystemApp, boolean isForDeviceStatus);

    void loadAlternativeAppsList();
}
