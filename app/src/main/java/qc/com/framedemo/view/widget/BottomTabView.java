package qc.com.framedemo.view.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import qc.com.framedemo.R;

/**
 * Implementation of App Widget functionality.
 */
public class BottomTabView extends LinearLayout {

    private ImageView mIcon;
    private TextView mTabText;

    private ColorStateList tabTextColor;
    private int tabTextSize = 15;
    private boolean tabIsSelected;
    private Drawable tabIcon;
    private String tabText;

    public BottomTabView(Context context) {
        super(context);
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomTabView);
        tabTextColor = a.getColorStateList(R.styleable.BottomTabView_tabButtonTextColor);
        tabTextSize = a.getDimensionPixelSize(R.styleable.BottomTabView_tabTextSize, tabTextSize);
        tabIsSelected = a.getBoolean(R.styleable.BottomTabView_tabIsSelected, false);
        tabIcon = a.getDrawable(R.styleable.BottomTabView_tabIcon);
        tabText = a.getString(R.styleable.BottomTabView_tabText);
        a.recycle();
        init();
    }


    /**
     * 初始化控件
     */
    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        addIcon();
        addTabText();

    }

    /**
     * 生成Tab图标
     */
    private void addIcon() {
        mIcon = new ImageView(getContext());
        LayoutParams mParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mParams.topMargin = 10;
        setIcon(tabIcon);
        mIcon.setLayoutParams(mParams);
        addView(mIcon);

    }

    /**
     * 添加Tab文字
     */
    private void addTabText() {
        mTabText = new TextView(getContext());
        LayoutParams mparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setTabText(tabText);
        setTabTextColor(tabTextColor);
        mTabText.setLayoutParams(mparams);
        addView(mTabText);
    }


    /**
     * 设置是否选中
     */
    public void setSelected(boolean isSelected) {
        if (isSelected) {
            mIcon.setSelected(true);
            mTabText.setSelected(true);
        } else {
            mIcon.setSelected(false);
            mTabText.setSelected(false);
        }

    }

    /**
     * 设置图片
     *
     * @param resId 资源Id
     */
    public void setIcon(int resId) {

        mIcon.setImageResource(resId);

    }

    /**
     * 设置图片
     *
     * @param bitmap
     */
    public void setIcon(Bitmap bitmap) {

        if (bitmap != null) {
            mIcon.setImageBitmap(bitmap);
        }
    }

    /**
     * 设置图片
     *
     * @param drawable
     */
    public void setIcon(Drawable drawable) {

        if (drawable != null) {
            mIcon.setImageDrawable(drawable);
        }
    }


    /**
     * 设置文字
     *
     * @param resId 资源Id
     */
    public void setTabText(int resId) {

        setTabText(getResources().getString(resId));
    }


    /**
     * 设置文字
     *
     * @param text 文字
     */
    public void setTabText(String text) {

        mTabText.setText(text);
    }

    /**
     * 设置文字颜色
     *
     * @param colorStateList
     */
    public void setTabTextColor(ColorStateList colorStateList) {

        mTabText.setTextColor(colorStateList);

    }

    /**
     * 设置文字颜色
     *
     * @param resId 资源Id
     */
    public void setTabTextColor(int resId) {

        mTabText.setTextColor(getResources().getColorStateList(resId));

    }

}

