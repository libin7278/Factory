package com.libin.factory.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.libin.factory.R;
import com.orhanobut.logger.Logger;

import static android.graphics.Paint.Style.STROKE;

/**
 * Created by doudou on 2017/3/22.
 * 1、自定义View的属性
 * 2、在View的构造方法中获得我们自定义的属性
 * 3、重写onMesure
 * 4、重写onDraw
 */

public class CustomImageView extends View {
    private static final String TAG = CustomImageView.class.getSimpleName();
    /**
     * 控件的宽
     */
    private int mWidth;
    /**
     * 控件的高
     */
    private int mHeight;
    /**
     * 控件中的图片
     */
    private Bitmap mImage;
    /**
     * 图片的缩放模式
     */
    private int mImageScale;
    private static final int IMAGE_SCALE_FITXY = 0;
    private static final int IMAGE_SCALE_CENTER = 1;
    /**
     * 图片的介绍
     */
    private String mTitle;
    /**
     * 字体的颜色
     */
    private int mTextColor;
    /**
     * 字体的大小
     */
    private int mTextSize;

    private Paint mPaint;
    /**
     * 对文本的约束
     */
    private Rect mTextBound;
    /**
     * 控制整体布局
     */
    private Rect rect;

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);

        int indexCount = typedArray.getIndexCount();

        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomImageView_imageScaleType:
                    mImageScale = typedArray.getInt(attr, 0);
                    break;
                case R.styleable.CustomImageView_titleText:
                    mTitle = typedArray.getString(attr);
                    break;
                case R.styleable.CustomImageView_titleTextColor:
                    mTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomImageView_titleTextSize:
                    mTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
            }

        }

        typedArray.recycle();
        rect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setTextSize(mTextSize);
        //计算描绘字体需要的范围
        mPaint.getTextBounds(mTitle,0,mTitle.length(),mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // TODO: 2017/3/22
        /**
         * UNSPECIFIED 父不没有对子施加任何约束，子可以是任意大小
         * EXACTLY 父决定子的确切大小，子被限定在给定的边界里，忽略本身想要的大小。
         * AT_MOST 子最大可以达到的指定大小
         */
        //设置宽度
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if(specMode == MeasureSpec.EXACTLY){ //match_parent
            Logger.i("EXACTLY");

            mWidth = specSize;
        }else {
            //由图片决定宽
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
            //由字体决定宽
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();

            if(specMode == MeasureSpec.AT_MOST){ //wrap_content
                int desire = Math.max(desireByImg,desireByTitle);
                mWidth = Math.min(desire,specSize);
                Logger.i("AT_MOST");
            }

            Logger.i("getPaddingLeft "+getPaddingLeft() + "    getPaddingRight()"+ getPaddingRight());
        }

        //设置高度
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        if(specMode == MeasureSpec.EXACTLY){  //macth_parent
            mHeight = specSize;
        }else{
            int desire = getPaddingTop()+getPaddingBottom()+mImage.getHeight()+mTextBound.height();

            if(specMode == MeasureSpec.AT_MOST){ //wrap_content
                mHeight = Math.min(desire,specSize);
            }
        }

        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        /**
         * 边框
         */
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(STROKE);  // STROKE 空心 FILL实心
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        // TODO: 2017/3/22
        rect.left = getPaddingLeft();
        rect.right = mWidth - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */
        if(mTextBound.width() > mWidth){
            TextPaint textpaint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitle,textpaint,(float)mWidth-getPaddingLeft()-getPaddingRight(),TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg,getPaddingLeft(),mHeight-getPaddingBottom(),mPaint);
        }else{
            //正常字体 将字体居中
            canvas.drawText(mTitle,mWidth/2 - mTextBound.width()*1.0f/2,mHeight-getPaddingBottom(),mPaint);
        }

        // TODO: 2017/3/22
        //取消使用掉的块
        rect.bottom -= mTextBound.height();

        //居中或填充
        if (mImageScale == IMAGE_SCALE_FITXY)
        {
            canvas.drawBitmap(mImage, null, rect, mPaint);
        } else
        {
            //计算居中的矩形范围
            rect.left = mWidth / 2 - mImage.getWidth() / 2;
            rect.right = mWidth / 2 + mImage.getWidth() / 2;
            rect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
            rect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;

            Logger.e("mWidth : " +mWidth + "    mImage.getWidth() : " +mImage.getWidth());

            canvas.drawBitmap(mImage, null, rect, mPaint);
        }

    }
}

//Android自定义View【实战教程】2⃣️----自定义view那些事