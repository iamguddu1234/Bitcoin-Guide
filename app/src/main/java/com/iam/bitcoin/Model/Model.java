package com.iam.bitcoin.Model;

import android.content.Intent;

public class Model {
    private int title;
    private int subtitle1;
    private int subtitle2;

    public Model(int title, int subtitle1, int subtitle2) {
        this.title = title;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
    }

    public Model(int title, int subtitle1) {
        this.title = title;
        this.subtitle1 = subtitle1;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getSubtitle1() {
        return subtitle1;
    }

    public void setSubtitle1(int subtitle1) {
        this.subtitle1 = subtitle1;
    }

    public int getSubtitle2() {
        return subtitle2;
    }

    public void setSubtitle2(int subtitle2) {
        this.subtitle2 = subtitle2;
    }
}
