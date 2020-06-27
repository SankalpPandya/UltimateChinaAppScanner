
package com.proudindian.chinamuktbharat.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlternateChinaAppsInfo implements Serializable {
    @SerializedName("root")
    @Expose
    private List<Root> root = null;
    private final static long serialVersionUID = 5883611250854556974L;

    public List<Root> getRoot() {
        return root;
    }

    public void setRoot(List<Root> root) {
        this.root = root;
    }
}
