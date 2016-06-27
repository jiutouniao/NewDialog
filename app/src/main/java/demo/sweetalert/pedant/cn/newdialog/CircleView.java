package demo.sweetalert.pedant.cn.newdialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Date: 2016/6/22 17:05
 * User: shaobing
 */
public class CircleView extends View{

    private int mWidth;
    private int mHeight;
    private int mNumber;
    private Paint mPaint;
    private float mRotateNumber = 0;
    private float mRotateNumber2 = 0;
    private float mRotateNumber3 = 0;
    private float[] mRadius ={0.462f,0.42f,0.320f,0.293f,0.224f};   //圆弧的半径
    private float[] mRotateNumbers={0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
    private int[] mColors ={0xFFD669,0xFFD669,0xFFB900,0xFFB900,0xFFB900};
    private float[] mStrokeWidths ={6.0f,5.0f,4.0f,3.0f,2.0f};
    private int[] mAlphas ={255,255,102,128,51};
    private float[] mRadians ={60,45,80,145,145};
    private float[] mStartradians ={-80f,-80f,45f,-180f,-170f};
    private float mDensity;
    private List <Paint> mPaints;
    private List<RectF> mRectFs;
    private RectF mOval1;
    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private Handler mHandler = new Handler();
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = right - left;
        mHeight= bottom - top;
        mHandler.post(new Runnable()
        {
            @Override
            public void run() {

                mNumber ++;
                mRotateNumber =mRotateNumber+ 0.01f*((mNumber-1)*(mNumber-30)*(mNumber-80)*(mNumber-160)*(mNumber-190)/3000000.0f+10.5f);
                mRotateNumber2 =mRotateNumber2+ 0.01f*((mNumber-15)*(mNumber-55)*(mNumber-120)*(mNumber-175)*(mNumber-200)/3000000.0f+10.5f);
                mRotateNumber3 =mRotateNumber3+ 0.01f*((mNumber-10)*(mNumber-50)*(mNumber-70)*(mNumber-120)*(mNumber-180)/3000000.0f+10.5f);
                mRotateNumbers[0] = mRotateNumber;
                mRotateNumbers[1] = mRotateNumber2;
                mRotateNumbers[2] = mRotateNumber;
                mRotateNumbers[3] = mRotateNumber3;
                mRotateNumbers[4] = mRotateNumber;
                if(mNumber>200)
                {
                    mNumber = 0;
                }
                invalidate();
                mHandler.postDelayed(this,100);
            }
        });
    }

    private void init()
    {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mDensity = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        mPaints = new ArrayList<>();
        mPaints.clear();
        mRectFs = new ArrayList<>();
        mRectFs.clear();
        for(int i = 0; i< mRadius.length; i++)
        {
            RectF  oval1=new RectF();
            oval1.left=mWidth*(0.5f- mRadius[i]);
            oval1.top=mHeight*(0.5f- mRadius[i]);
            oval1.right=mWidth*(0.5f+ mRadius[i]);
            oval1.bottom=mHeight*(0.5f+ mRadius[i]);
            mRectFs.add(oval1);
            Paint paint = new Paint();
            paint.setAntiAlias(true);                       //设置画笔为无锯齿
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(mColors[i]);
            paint.setStrokeWidth(mStrokeWidths[i]* mDensity);
            paint.setAlpha(mAlphas[i]);
            mPaints.add(paint);
        }
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        init();
        mOval1 =new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);                       //设置画笔为无锯齿
        mPaint.setStrokeWidth((float)50);              //线宽
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.save();
        for(int i = 0; i< mRadius.length; i++)
        {
            canvas.rotate(mRotateNumbers[i], this.getWidth()/2,this.getHeight()/2);
            canvas.drawArc(mRectFs.get(i), mStartradians[i], mRadians[i], false, mPaints.get(i));    //绘制圆弧
        }
        canvas.restore();

    }
}
