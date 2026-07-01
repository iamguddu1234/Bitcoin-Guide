package com.iam.bitcoin;

public class MyAppModel {

    private String image;
    private String appName;

    public MyAppModel(String image, String appName) {
        this.image = image;
        this.appName = appName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
