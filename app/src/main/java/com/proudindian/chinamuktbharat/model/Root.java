
package com.proudindian.chinamuktbharat.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root implements Serializable
{

    @SerializedName("ChinaAppName")
    @Expose
    private String chinaAppName;
    @SerializedName("alternatives")
    @Expose
    private List<Alternative> alternatives = null;
    private final static long serialVersionUID = 3979945244548688978L;

    public String getChinaAppName() {
        return chinaAppName;
    }

    public void setChinaAppName(String chinaAppName) {
        this.chinaAppName = chinaAppName;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

}
