package com.libin.factory.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by doudou on 2017/3/24.
 */

public class DemoView extends View {
    Paint paint = new Paint();
    Path path = new Path();
    float phase;
    PathEffect[] effects = new PathEffect[7];
    int[] colors;

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        //创建,并初始化Path
        path = new Path();
        path.moveTo(100, 100);
        path.rLineTo(90,0);
        path.rLineTo(-80,50);
        path.rLineTo(35,-80);
        path.rLineTo(45,80);
        path.close();

//        path.setLastPoint(0,0);
//        path.close();
        //初始化七个颜色
        colors = new int[] {
                Color.BLACK,Color.BLUE,Color.CYAN,
                Color.GREEN,Color.MAGENTA,Color.RED,Color.YELLOW,Color.BLACK
        };

//        paint.setTextSize(50);
//        int[] colors = new int[]{Color.RED, Color.GREEN, Color.BLUE};
//        Shader shader = new RadialGradient(100, 100, 80, colors, null, TileMode.REPEAT);
//        paint.setShader(shader);
//        paint.setTypeface(Typeface.DEFAULT_BOLD);
//        paint.setUnderlineText(true);

//        // 设置光源的方向
//        float[] direction = new float[]{100, 100, 100};
//
//        //设置环境光亮度
//        float light = 20;
//
//        // 选择要应用的反射等级
//        float specular = 60;
//        // 向mask应用一定级别的模糊
//        float blur = 30f;
//
//        EmbossMaskFilter emboss = new EmbossMaskFilter(direction, light, specular, blur);
//
//        BlurMaskFilter maskFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID);
//        // 应用mask
//        paint.setMaskFilter(emboss);

        //path在后面进行讲解

        //        path.cubicTo(200, 200, 300, 200, 400, 400);
        // 连接路径到点、




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //将背景填充成白色
        canvas.drawColor(Color.WHITE);
        //-------下面开始初始化7中路径的效果
        //使用路径效果
        effects[0] = null;
        //使用CornerPathEffect路径效果
        effects[1] = new CornerPathEffect(10);
        //初始化DiscretePathEffect
        effects[2] = new DiscretePathEffect(3.0f,5.0f);
        //初始化DashPathEffect
        effects[3] = new DashPathEffect(new float[]{20,10,5,10},phase);
        //初始化PathDashPathEffect
        Path p = new Path();
        p.addRect(0, 0, 8, 8, Path.Direction.CCW);
        effects[4] = new PathDashPathEffect(p,12,phase,PathDashPathEffect.Style.ROTATE);
        //初始化PathDashPathEffect
        effects[5] = new ComposePathEffect(effects[2],effects[4]);
        effects[6] = new SumPathEffect(effects[4],effects[3]);
        //将画布移到8,8处开始绘制
        canvas.translate(8, 8);
        //依次使用7中不同路径效果,7种不同的颜色来绘制路径
        for(int i = 0; i < effects.length; i++)
        {
            paint.setPathEffect(effects[i]);
            paint.setColor(colors[i]);
            canvas.drawPath(path, paint);
            canvas.translate(0, 60);
        }
        //改变phase值,形成动画效果
        phase += 1;
        invalidate();


//        path = new Path();
//        path.moveTo(100, 100);
//        path.rLineTo(200, 200);
//        RectF oval = new RectF(100, 100, 300, 200);
//        path.addRect(oval, Path.Direction.CW);

//        Path path1 = new Path();
//        path1.addCircle(120, 120, 100, Path.Direction.CW);
//        Path path2 = new Path();
//        path2.addCircle(200, 200, 100, Path.Direction.CW);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            path1.op(path2, Path.Op.DIFFERENCE);
//        }

//        path.addCircle(300, 300, 100, Path.Direction.CW);
//        path.addCircle(400, 300, 100, Path.Direction.CW);
//
//        canvas.drawColor(Color.WHITE);
//
//        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        //canvas.drawPath(path, paint);


//        paint.setTextSize(60);
//        canvas.drawTextOnPath("12345",path,0,0,paint);
        //canvas.drawPath(path,paint);

        //canvas.drawText("自定义View", 200, 200, paint);
    }
}
