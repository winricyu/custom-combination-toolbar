package fun.dooit.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 客製TA2通用Toolbar物件
 * Created by Eric on 2017/12/8.
 */

public class XToolbar extends Toolbar implements View.OnClickListener {

    private static final String TAG = "XToolbar";
    private static final String TEXT_GRAVITY_LEFT = "1";
    private static final String TEXT_GRAVITY_CENTER = "2";
    private static final String TEXT_GRAVITY_RIGHT = "3";
    private static final int MAX_BUTTON_COUNT = 3;
    private static final int DEFAULT_TEXT_SIZE_DP = 14;
    private static final int DEFAULT_BUTTON_SIZE_DP = 30;
    private static final int DEFAULT_MARGIN_DP = 8;
    private static final String TAG_BTN_MAIN = "leftButton";
    private static final String TAG_BTN_RIGHT_1 = "rightButton_1";
    private static final String TAG_BTN_RIGHT_2 = "rightButton_2";
    private static final String TAG_BTN_RIGHT_3 = "rightButton_3";
    private static final String TAG_BTN_EXTRA = "rightButton";
    private ImageButton mBtnMain, mBtnExtra;
    private OnToolbarClickListener mClickListener;
    private LinearLayout mPanelRight;
    private TextView mTextTilte;
    private float mButtonSizePx;
    private float mTitleSizePx;
    private DisplayMetrics mMetrics;
    private TypedArray mTypedArray;

    public XToolbar(Context context) {
        super(context);
        Log.d(TAG, "XToolbar() called with: context = [" + context + "]");
    }

    public XToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "XToolbar() called with: context = [" + context + "], attrs = [" + attrs + "]");

        //解析XML設定值參數
        mMetrics = getResources().getDisplayMetrics();
        Log.d(TAG, mMetrics.toString());
        mTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.XToolbar);
        Drawable btnMainResId = mTypedArray.getDrawable(R.styleable.XToolbar_mainButton);
        Drawable btnExtraResId = mTypedArray.getDrawable(R.styleable.XToolbar_extraButton);
        Drawable imgResId1 = mTypedArray.getDrawable(R.styleable.XToolbar_rightButton1);
        Drawable imgResId2 = mTypedArray.getDrawable(R.styleable.XToolbar_rightButton2);
        Drawable imgResId3 = mTypedArray.getDrawable(R.styleable.XToolbar_rightButton3);
        String titleText = mTypedArray.getString(R.styleable.XToolbar_titleText);
        mTitleSizePx = mTypedArray.getDimension(R.styleable.XToolbar_titleSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE_DP, mMetrics));
        mButtonSizePx = mTypedArray.getDimension(R.styleable.XToolbar_buttonSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_BUTTON_SIZE_DP, mMetrics));

        String textGravity = mTypedArray.getString(R.styleable.XToolbar_titleGravity);

        //初始化顯示物件
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this);
        mPanelRight = findViewById(R.id.panel_right);
        mBtnMain = findViewById(R.id.xtoolbar_btn_left);
        mBtnExtra = findViewById(R.id.xtoolbar_btn_action);
        mTextTilte = findViewById(R.id.text_title);


        //設定標題文字
        if (textGravity != null) {
            switch (textGravity) {
                case TEXT_GRAVITY_LEFT:
                    mTextTilte.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                    break;
                case TEXT_GRAVITY_CENTER:
                    mTextTilte.setGravity(Gravity.CENTER);
                    break;
                case TEXT_GRAVITY_RIGHT:
                    mTextTilte.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                    break;
                default:
                    mTextTilte.setGravity(Gravity.CENTER);
            }
        } else {
            mTextTilte.setGravity(Gravity.CENTER);

        }
        if (!TextUtils.isEmpty(titleText)) {
            mTextTilte.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleSizePx);
            mTextTilte.setText(titleText);
        }


        //建立左側主要按鈕(選單或返回)
        mBtnMain.setTag(TAG_BTN_MAIN);
        mBtnMain.setVisibility(GONE);
        if (btnMainResId != null && mBtnMain != null) {
            TypedValue tv = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, tv, true);
            mBtnMain.setVisibility(VISIBLE);
            mBtnMain.setImageDrawable(btnMainResId);
            mBtnMain.setBackgroundResource(tv.resourceId);

            int wh = (int) mButtonSizePx;
            int marginPx = (int) (mMetrics.density * DEFAULT_MARGIN_DP);
            ConstraintLayout layout= (ConstraintLayout) mBtnMain.getParent();
            ConstraintLayout.LayoutParams params= new ConstraintLayout.LayoutParams(layout.getLayoutParams());
            params.width=wh;
            params.height=wh;
            mBtnMain.setLayoutParams(params);
            mBtnMain.setOnClickListener(this);
        }


        //建立右側按鈕
        if (imgResId1 != null) {
            buildButton(imgResId1);
        }

        if (imgResId2 != null) {
            buildButton(imgResId2);
        }

        if (imgResId3 != null) {
            buildButton(imgResId3);
        }


        //建立右側額外按鈕
        mBtnExtra.setTag(TAG_BTN_EXTRA);
        mBtnExtra.setVisibility(GONE);
        if (btnExtraResId != null && mBtnExtra != null) {
            mBtnExtra.setVisibility(VISIBLE);
            mBtnExtra.setBackground(btnMainResId);
            mBtnExtra.setOnClickListener(this);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure() called with: widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(TAG, "onLayout() called with: changed = [" + changed + "], l = [" + l + "], t = [" + t + "], r = [" + r + "], b = [" + b + "]");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw() called with: canvas = [" + canvas + "]");
    }


    /**
     * 新增右側按鈕
     *
     * @param imgRes drawable resource id
     */
    private void buildButton(Drawable imgRes) {
        Log.d(TAG, "buildButton() called with: imgRes = [" + imgRes + "]");
        if (mPanelRight == null || imgRes == null) {
            Log.d(TAG, "按鈕圖示resid不正確或按鈕容器尚未建置成功");
            return;
        }
        int childCount = mPanelRight.getChildCount();
        if (childCount >= MAX_BUTTON_COUNT) {
            Log.d(TAG, "按鈕已達上限數量 " + MAX_BUTTON_COUNT);
            return;
        }
        ImageButton imgBtn = new ImageButton(getContext());
        int wh = (int) mButtonSizePx;
        int marginPx = (int) (mMetrics.density * DEFAULT_MARGIN_DP);
        Log.d(TAG, "mButtonSizePx:" + mButtonSizePx);
        Log.d(TAG, "wh:" + wh);
        Log.d(TAG, "marginPx:" + marginPx);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mPanelRight.getLayoutParams());
        params.width = wh;
        params.height = wh;
        params.setMarginEnd(marginPx);


        TypedValue tv = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, tv, true);
        imgBtn.setImageDrawable(imgRes);
        imgBtn.setBackgroundResource(tv.resourceId);
        imgBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);


        String tag = "rightButton_" + (childCount + 1);
        Log.d(TAG, "tag:" + tag);
        imgBtn.setTag(tag);
        imgBtn.setOnClickListener(this);

        imgBtn.setLayoutParams(params);
        mPanelRight.addView(imgBtn);


        return;

    }


    /**
     * 顯示右側按鈕面板
     *
     * @param isVisible
     */
    public void setRightPanelVisible(boolean isVisible) {
        if (mPanelRight == null) {
            return;
        }
        if (isVisible) {
            mPanelRight.setVisibility(VISIBLE);
        } else {
            mPanelRight.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (TextUtils.isEmpty(tag)) {
            return;
        }
        Log.d(TAG, "onClick() called with: tag = [" + tag + "]");

        if (mClickListener == null) {
            Log.e(TAG, "未設定OnToolbarClickListener");
            return;
        }

        switch (tag) {
            case TAG_BTN_MAIN:
                mClickListener.onMainClick(v);
                break;
            case TAG_BTN_RIGHT_1:
                mClickListener.onBtn1Click(v);
                break;
            case TAG_BTN_RIGHT_2:
                mClickListener.onBtn2Click(v);
                break;
            case TAG_BTN_RIGHT_3:
                mClickListener.onBtn3Click(v);
                break;
            case TAG_BTN_EXTRA:
                mClickListener.onExtraClick(v);
                break;
        }
    }


 /*   private void findButtonByTag(String tag) {
        View imgBtn = mPanelRight.findViewWithTag(tag);
        if (imgBtn == null) {
            Log.d(TAG, "指定tag物件不存在");
            return;
        }

    }
*/

    public void setOnClickListener(OnToolbarClickListener listener) {
        this.mClickListener = listener;
    }

    public interface OnToolbarClickListener {
        void onMainClick(View view);

        void onExtraClick(View view);

        void onBtn1Click(View view);

        void onBtn2Click(View view);

        void onBtn3Click(View view);
    }

}


