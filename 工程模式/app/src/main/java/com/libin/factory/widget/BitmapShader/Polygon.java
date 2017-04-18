package com.libin.factory.widget.BitmapShader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by doudou on 2017/4/7.
 */

public class Polygon extends View {
    public Polygon(Context context) {
        this(context,null);
    }

    public Polygon(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Polygon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void init(){

    }
}
