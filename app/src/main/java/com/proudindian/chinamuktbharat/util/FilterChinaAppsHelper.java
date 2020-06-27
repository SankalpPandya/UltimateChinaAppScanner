package com.proudindian.chinamuktbharat.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterChinaAppsHelper {

    private static ArrayList<ApplicationInfo> systemApps = new ArrayList<>();
    static String deviceManufacturer;

    public static class ApplicationInfo {
        private String appname = "";
        public String getAppname() {
            return appname;
        }
        public String getPname() {
            return pname;
        }
        public Drawable getIcon() {
            return icon;
        }
        private String pname = "";
        private Drawable icon;

        public ApplicationInfo(){}
        public ApplicationInfo(String appName,String packageName){
            appname =appName;
            pname =packageName;
        }
        private void prettyPrint() {
            Log.v("FilterChinaAppsHelper", appname + "\t" + pname + "\t");
        }

    }

    public static ArrayList<ApplicationInfo> getInstalledChinaApps(Context context, Map<String, String> restrictedApps) {
        ArrayList<ApplicationInfo> apps = getInstalledApps(context, restrictedApps);
        return apps;
    }

    public static ArrayList<ApplicationInfo> getInstalledChinaSystemApps() {
        return systemApps;
    }

    private static ArrayList<ApplicationInfo> getInstalledApps(Context context, Map<String, String> restrictedApps) {
        ArrayList<ApplicationInfo> res = new ArrayList<>();
        systemApps.clear();
        CheckDeviceMenuFacturer();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            ApplicationInfo newInfo = new ApplicationInfo();
            newInfo.appname = p.applicationInfo.loadLabel(context.getPackageManager()).toString();
            newInfo.pname = p.packageName;
            newInfo.icon = p.applicationInfo.loadIcon(context.getPackageManager());

            if (isSystemPackage(p) && CheckIfAppIsBuiltInChinaApp(p.packageName)) {
                Log.v("FilterChinaAppsHelper", "China APP found...pnam " + p.packageName);
                systemApps.add(newInfo);
            } else {
                if (isThisChinaApp(newInfo.pname, restrictedApps)) {
                    Log.v("FilterChinaAppsHelper", "China APP found... " + newInfo.appname);
                    res.add(newInfo);
                }
            }
        }
        return res;
    }

    private static boolean CheckIfAppIsBuiltInChinaApp(String packagName) {
        if (packagName.contains(".mi.") ||
                packagName.contains(".xiaomi.") ||
                packagName.contains(".mipay.") ||
                packagName.contains(".redmi.") ||
                packagName.contains(".vivo.") ||
                packagName.contains(".oppo.") ||
                packagName.contains(".realme.") ||
                packagName.contains(".oneplus.") ||
                packagName.contains(".lenovo.") ||
                packagName.contains(".gionee.") ||
                packagName.contains(".coolpad.") ||
                packagName.contains(".meizu.") ||
                packagName.contains(".huawei.") ||
                packagName.contains(".honor.") ||
                packagName.contains(".tecno.") ||
                packagName.contains(".realmext.")) {
            return true;
        }
        return false;
    }

    public static boolean CheckDeviceMenuFacturer() {
        deviceManufacturer = android.os.Build.MANUFACTURER;
        if (
                deviceManufacturer.equalsIgnoreCase("Xiaomi") ||
                        deviceManufacturer.equalsIgnoreCase("Realme") ||
                        deviceManufacturer.equalsIgnoreCase("RealmeXT") ||
                        deviceManufacturer.equalsIgnoreCase("Oppo") ||
                        deviceManufacturer.equalsIgnoreCase("Vivo") ||
                        deviceManufacturer.equalsIgnoreCase("Gionee") ||
                        deviceManufacturer.equalsIgnoreCase("lenovo") ||
                        deviceManufacturer.equalsIgnoreCase("huawei") ||
                        deviceManufacturer.equalsIgnoreCase("tecno") ||
                        deviceManufacturer.equalsIgnoreCase("meizu") ||
                        deviceManufacturer.equalsIgnoreCase("honor") ||
                        deviceManufacturer.equalsIgnoreCase("Redmi")
        ) {
            return true;
        }
        return false;
    }

    public static String getDeviceMenufacturerName() {
        return deviceManufacturer;
    }

    private static boolean isSystemPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    private static boolean isThisChinaApp(String appName, Map<String, String> restrictedApps) {
        return restrictedApps.containsKey(appName);
    }

}
