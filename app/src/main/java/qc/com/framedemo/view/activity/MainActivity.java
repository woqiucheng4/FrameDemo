package qc.com.framedemo.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import qc.com.framedemo.R;
import qc.com.framedemo.view.BaseActivity;
import qc.com.framedemo.view.fragment.FirstFragment;
import qc.com.framedemo.view.fragment.FourthFragment;
import qc.com.framedemo.view.fragment.SecondFragment;
import qc.com.framedemo.view.fragment.ThirdFragment;
import qc.com.framedemo.view.widget.BottomTabView;


public class MainActivity extends BaseActivity {
    /**
     * 底部四个按钮
     */
    @BindView(R.id.bottom_tab_first)
    public BottomTabView mBottomTabFirst;
    @BindView(R.id.bottom_tab_second)
    public BottomTabView mBottomTabSecond;
    @BindView(R.id.bottom_tab_third)
    public BottomTabView mBottomTabThird;
    @BindView(R.id.bottom_tab_fourth)
    public BottomTabView mBottomTabFourth;
    @BindView(R.id.main_drawer)
    public DrawerLayout mDrawerLayout;
    @BindView(R.id.nv_main_navigation)
    public NavigationView navigationView;
    @BindView(R.id.viewpager_fragment)
    public ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initCustomView() {
        initFragmentList();
        initViewPager();
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFragmentList() {
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        FourthFragment fourthFragment = new FourthFragment();
        mFragments.add(firstFragment);
        mFragments.add(secondFragment);
        mFragments.add(thirdFragment);
        mFragments.add(fourthFragment);
    }

    private void initViewPager() {
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                resetBottomTabBtn();
                changeBottomTabBtn(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        changeBottomTabBtn(0);
    }

    private void resetBottomTabBtn() {
        mBottomTabFirst.setSelected(false);
        mBottomTabSecond.setSelected(false);
        mBottomTabThird.setSelected(false);
        mBottomTabFourth.setSelected(false);
    }


    @OnClick({R.id.bottom_tab_first, R.id.bottom_tab_second, R.id.bottom_tab_third, R.id.bottom_tab_fourth})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_tab_first:
                resetBottomTabBtn();
                changeBottomTabBtn(0);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.bottom_tab_second:
                resetBottomTabBtn();
                changeBottomTabBtn(1);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.bottom_tab_third:
                resetBottomTabBtn();
                changeBottomTabBtn(2);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.bottom_tab_fourth:
                resetBottomTabBtn();
                changeBottomTabBtn(3);
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    private void changeBottomTabBtn(int position) {
        switch (position) {
            case 0:
                mBottomTabFirst.setSelected(true);
                setTitleText("First");
                break;
            case 1:
                mBottomTabSecond.setSelected(true);
                setTitleText("Second");
                break;
            case 2:
                mBottomTabThird.setSelected(true);
                setTitleText("Third");
                break;
            case 3:
                mBottomTabFourth.setSelected(true);
                setTitleText("Fourth");
                break;
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setCheckable(true);
                menuItem.setChecked(false);
                mDrawerLayout.closeDrawers();
                menuItem.setCheckable(false);
                return true;
            }
        });
    }

}
