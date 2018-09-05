package com.zhuye.huochebangsiji.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.zhuye.huochebangsiji.R;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class MyToolBar extends Toolbar {

    private Context mContent;

    public MyToolBar(Context context) {
        this(context,null);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContent = context;
        initView();
    }

    private void initView() {
        View content = View.inflate(mContent, R.layout.toolbar,null);
        addView(content);
    }

}
