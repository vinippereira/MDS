package com.ufscar.alunos.mqc.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.ufscar.alunos.mqc.R;
import com.parse.*;

public class Inicial extends AppCompatActivity implements
        TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private TabHost mTabHost;
    private ViewPager mViewPager;
    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, Inicial.TabInfo>();
    private PagerAdapter mPagerAdapter;

    // Informação da Tab
    private class TabInfo {
        private String tag;
        private Class<?> clss;
        private Bundle args;
        private Fragment fragment;

        TabInfo(String tag, Class<?> clazz, Bundle args) {
            this.tag = tag;
            this.clss = clazz;
            this.args = args;
        }
    }

    // Um simples factory que retorna View para o TabHost
    class TabFactory implements TabContentFactory {

        private final Context mContext;

        public TabFactory(Context context) {
            mContext = context;
        }

        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        // Infla o layout
        setContentView(R.layout.activity_inicial);

        // Inicializa o TabHost
        this.initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            // Define a Tab de acordo com o estado salvo
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }

       // this.getActionBar().hide();

        // Inicializa o ViewPager
        this.intialiseViewPager();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "MZILWm0EqFyy1lOauqRy9gHB1a4j5kJZ6pW1Z6U5", "hzVeLBtrkieewXP3r1WfvFMlh1xK33LAdH0SNV7b");

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("TESTE", "RODOU");
//        testObject.saveInBackground();
        
    }

    protected void onSaveInstanceState(Bundle outState) {
        // salva a Tab selecionada
        outState.putString("tab", mTabHost.getCurrentTabTag());
        super.onSaveInstanceState(outState);
    }

    private void intialiseViewPager() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, MeusCursosActivity.class.getName()));
        fragments.add(Fragment.instantiate(this, HorarioCursos.class.getName()));
        fragments.add(Fragment.instantiate(this, LocalActivity.class.getName()));

        this.mPagerAdapter = new com.ufscar.alunos.mqc.Menu.ViewPagerAdapter(
                super.getSupportFragmentManager(), fragments);
        this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOnPageChangeListener(this);
    }

    private void initialiseTabHost(Bundle args) {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        TabInfo tabInfo = null;


        Inicial.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab1").setIndicator("Meus Cursos"),
                (tabInfo = new TabInfo("Tab1", MeusCursosActivity.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);

        Inicial.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab3").setIndicator("Horário"),
                (tabInfo = new TabInfo("Tab3", HorarioCursos.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        mTabHost.setOnTabChangedListener(this);

        Inicial.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab3").setIndicator("Local"),
                (tabInfo = new TabInfo("Tab3", LocalActivity.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        mTabHost.setOnTabChangedListener(this);

        //obs: dentro do esquema de abas do curso eh que terá a aba de menu ....

    }

    private static void AddTab(Inicial activity, TabHost tabHost,
                               TabHost.TabSpec tabSpec, TabInfo tabInfo) {
        // Attach uma Tab view factory para o spec
        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    public void onTabChanged(String tag) {
        // Avisa para o mViewPager qual a Tab que está ativa
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        this.mTabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}