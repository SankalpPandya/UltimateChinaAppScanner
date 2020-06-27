
package com.proudindian.chinamuktbharat.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alternative implements Serializable
{

    @SerializedName("AppName")
    @Expose
    private String appName;
    @SerializedName("PackageName")
    @Expose
    private String packageName;
    private final static long serialVersionUID = -6045418502643399881L;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
