package com.libin.factory.widget.BitmapShader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.libin.factory.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doudou on 2017/4/7.
 */

public class FivePointedStar extends View {
    private int mWidth;
    private int mHeight;
    private BitmapShader bmpShader;
    private Paint mPaint;

    private RectF bmpRect;

    private int startRadius = 350; //五角星的半径
    private int angleCount = 5;    //五角星，5个角 多边形可以更换这里的角的个数
    private int partOfAngle = 360 / angleCount;  //每个顶点的角度
    private int[] angles;
    private int currentAngle = 0;  //开始绘制的角度
    private List<PointF> pointFList = new ArrayList<>();
    public FivePointedStar(Context context) {
        this(context,null);
    }

    public FivePointedStar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FivePointedStar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void init(){
        angles = new int[angleCount];

        for (int i = 0; i < angleCount; i++) {
            angles[i] = currentAngle + partOfAngle * i;

            Logger.e("angles[i]==" +i + "     : " + angles[i] +"    partOfAngle : "+partOfAngle);
            float x = (float) (Math.sin(Math.toRadians(angles[i])) * startRadius);
            float y = (float) (Math.cos(Math.toRadians(angles[i])) * startRadius);
            pointFList.add(new PointF(x, y));
        }
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

        canvas.translate(mWidth/2,mHeight/2);

        Path mPath = new Path();
        mPath.moveTo(pointFList.get(0).x, pointFList.get(0).y);
        for (int i = 2; i < angleCount; i++) {
            if (i % 2 == 0) {// 除以二取余数，余数为0则为偶数,否则奇数
                mPath.lineTo(pointFList.get(i).x, pointFList.get(i).y);
                Logger.e("1  curPoint : " + i );
            }

        }

        if (angleCount % 2 == 0) {  //偶数，moveTo
            mPath.moveTo(pointFList.get(1).x, pointFList.get(1).y);
        } else {                    //奇数，lineTo
            mPath.lineTo(pointFList.get(1).x, pointFList.get(1).y);
        }
        Logger.e("curPoint ");

        for (int i = 3; i < angleCount; i++) {
            if (i % 2 != 0) {
                mPath.lineTo(pointFList.get(i).x, pointFList.get(i).y);
                Logger.e("2  curPoint : " + i );

            }
        }

        canvas.drawPath(mPath, mPaint);
    }
}
