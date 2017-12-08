package fun.dooit.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Eric on 2017/12/8.
 */

public class XToolbar extends Toolbar implements View.OnClickListener {

    private ImageButton mBtnLeft;
    private ImageButton mBtnAction;
    private ImageButton mBtn1, mBtn2, mBtn3;
    private OnToolbarClick mClickListener;

    public XToolbar(Context context) {
        super(context);
    }

    public XToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.XToolbar);
        Drawable btnLeft = typedArray.getDrawable(R.styleable.XToolbar_leftButtonImage);
        int btn1 = typedArray.getInt(R.styleable.XToolbar_buttonImage1, 0);
        int btn2 = typedArray.getInt(R.styleable.XToolbar_buttonImage2, 0);
        int btn3 = typedArray.getInt(R.styleable.XToolbar_buttonImage3, 0);


        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this);
        mBtnLeft = findViewById(R.id.xtoolbar_btn_left);
        mBtn1 = findViewById(R.id.xtoolbar_btn_1);
        mBtn2 = findViewById(R.id.xtoolbar_btn_2);
        mBtn3 = findViewById(R.id.xtoolbar_btn_3);


        mBtnLeft.setVisibility(GONE);
        mBtn1.setVisibility(GONE);
        mBtn2.setVisibility(GONE);
        mBtn3.setVisibility(GONE);

        mBtnLeft.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);


        if (btnLeft != null && mBtnLeft != null) {
            mBtnLeft.setVisibility(VISIBLE);
            mBtnLeft.setBackground(btnLeft);
        }
        if (btn1 > 0 && mBtn1 != null) {
            mBtn1.setVisibility(VISIBLE);
        }
        if (btn2 > 0 && mBtn2 != null) {
            mBtn2.setVisibility(VISIBLE);
        }
        if (btn3 > 0 && mBtn3 != null) {
            mBtn3.setVisibility(VISIBLE);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xtoolbar_btn_left:
                mClickListener.onLeftClick(v);
                break;
            case R.id.xtoolbar_btn_1:
                mClickListener.onBtn1Click(v);
                break;
            case R.id.xtoolbar_btn_2:
                mClickListener.onBtn2Click(v);
                break;
            case R.id.xtoolbar_btn_3:
                mClickListener.onBtn3Click(v);
                break;
        }
    }

    public void setOnClickListener(OnToolbarClick listener) {
        this.mClickListener = listener;
    }

    public interface OnToolbarClick {
        void onLeftClick(View view);

        void onActionClick(View view);

        void onIMClick(View view);

        void onBtn1Click(View view);

        void onBtn2Click(View view);

        void onBtn3Click(View view);
    }

}


