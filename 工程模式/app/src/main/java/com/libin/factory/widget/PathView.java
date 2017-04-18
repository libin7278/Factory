package com.libin.factory.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.libin.factory.R;

/**
 * Created by doudou on 2017/3/24.
 */

public class PathView extends View {
    private int mWidth;
    private int mHeight;
    private BitmapShader bmpShader;
    private Paint mPaint;

    private RectF bmpRect;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        path.moveTo(200, 200);
        path.lineTo(75, 75);
        path.lineTo(200, 75);
        path.lineTo(75,200);

        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
    }
}
