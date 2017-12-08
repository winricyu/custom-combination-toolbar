package fun.dooit.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Eric on 2017/12/8.
 */


public class TouchCounter extends View implements View.OnClickListener {

    private Paint mPaint;
    private Rect mBounds;
    private int mCount;

    public TouchCounter(Context context) {
        super(context);
    }

    public TouchCounter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mBounds = new Rect();
        this.setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#CCCCCC"));
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.parseColor("#FF1333"));
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int dp = 30;
        int px = (int) (displayMetrics.density * dp);
        mPaint.setTextSize(px);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();

        float tx = (getWidth() - textWidth) / 2;
        float ty = (getHeight() + textHeight) / 2;

        canvas.drawText(text, tx, ty, mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
