package com.tck.loadmorestudy.bean;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class PageModel {

    public Fragment mFragment;
    @StringRes
    public int titleRes;

    public PageModel(Fragment fragment, @StringRes int titleRes) {
        mFragment = fragment;
        this.titleRes = titleRes;

    }
}
