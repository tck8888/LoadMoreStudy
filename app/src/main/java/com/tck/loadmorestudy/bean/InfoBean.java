package com.tck.loadmorestudy.bean;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

public class InfoBean {

    @DrawableRes
    public int iconId;

    @StringRes
    public int title;

    public InfoBean(@DrawableRes int iconId, @StringRes int title) {
        this.iconId = iconId;
        this.title = title;
    }
}
