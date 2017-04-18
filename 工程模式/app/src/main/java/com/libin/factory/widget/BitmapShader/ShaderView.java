package com.libin.factory.widget.BitmapShader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.libin.factory.R;
import com.orhanobut.logger.Logger;

/**
 * Created by doudou on 2017/4/7.
 */

public class ShaderView extends View {
    private int mWidth;
    private int mHeight;
    private BitmapShader bmpShader;
    private Paint mPaint;

    private RectF bmpRect;

    public ShaderView(Context context) {
        this(context, null);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Logger.e("onSizeChanged  :  "+System.currentTimeMillis());

        mWidth = w; //屏幕宽
        mHeight = h; //屏幕高
        //Logger.e("w: " + w + " h:" + h + " oldw: " + oldw + " oldh : " + oldh);
        bmpRect = new RectF(0, 0, mWidth, mHeight);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dlw);

        //Shader.TileMode里有三种模式：CLAMP（拉伸）、MIRROR（镜像）、REPETA（重复）
        bmpShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);

        mPaint = new Paint((Paint.ANTI_ALIAS_FLAG));
        mPaint.setShader(bmpShader); //设置BitmapShader之后相当于绘制了底层的图片背景
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Logger.e("onLayout  :  "+System.currentTimeMillis());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Logger.e("onMeasure  :  "+System.currentTimeMillis());
        Logger.e("widthMeasureSpec  :  "+widthMeasureSpec +"heightMeasureSpec"+heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Logger.e("onDraw  :  "+System.currentTimeMillis());

        //矩形
        canvas.drawRect(bmpRect, mPaint);

    }
}
