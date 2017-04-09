package com.libin.factory.widget.BitmapShader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.libin.factory.R;
import com.orhanobut.logger.Logger;

/**
 * Created by doudou on 2017/4/7.
 */

public class Shader extends View {
    private int mWidth;
    private int mHeight;
    private Shader bmpShader;
    private Paint mPaint;

    private RectF bmpRect;

    public Shader(Context context) {
        this(context,null);
    }

    public Shader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Shader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void init(){

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight =h;
        Logger.e("w: "+w+"h:"+h+"oldw: "+oldw+"oldh : "+oldh);
        bmpRect = new RectF(0,0,mWidth,mHeight);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dlw);

        bmpShader = new BitmapShader(bitmap, android.graphics.Shader.TileMode.CLAMP)
    }
}
