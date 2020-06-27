package com.proudindian.chinamuktbharat.view;
import com.proudindian.chinamuktbharat.util.Utils;

public class Application  extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.InitRestrictedApps();
    }
}
