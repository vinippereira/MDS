package com.ufscar.alunos.mqc.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.ufscar.alunos.mqc.R;

public class InicialCursos extends AppCompatActivity implements
        TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private TabHost mTabHost;
    private ViewPager mViewPager;
    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, InicialCursos.TabInfo>();
    private PagerAdapter mPagerAdapter;
    private Toolbar toolbar;

    private String name_course;

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

    public String getName_course() {
        return name_course;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();

        //getSupportActionBar().setTitle(null);


        // Infla o layout
        setContentView(R.layout.activity_inicial_cursos);

        toolbar = (Toolbar) findViewById(R.id.toolbarCursos);
        toolbar.setTitle(getIntent().getStringExtra("course"));
        setSupportActionBar(toolbar);

        name_course = getIntent().getStringExtra("course");


        // Inicializa o TabHost
        this.initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            // Define a Tab de acordo com o estado salvo
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }

        //atualiza as cores da fonte das abas das tabelas
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
        }

        // this.getActionBar().hide();

        // Inicializa o ViewPager
        this.intialiseViewPager();

        // Enable Local Datastore.

    }

    protected void onSaveInstanceState(Bundle outState) {
        // salva a Tab selecionada
        outState.putString("tab", mTabHost.getCurrentTabTag());
        super.onSaveInstanceState(outState);
    }

    private void intialiseViewPager() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Disciplinas.class.getName()));
        fragments.add(Fragment.instantiate(this, HorarioProvas.class.getName()));
        fragments.add(Fragment.instantiate(this, HorarioTrabalhos.class.getName()));

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

        InicialCursos.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab1").setIndicator("Minhas Disciplinas"),
                (tabInfo = new TabInfo("Tab1", Disciplinas.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);

        InicialCursos.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab2").setIndicator("Horário de provas"),
                (tabInfo = new TabInfo("Tab2", HorarioProvas.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        //mTabHost.setOnTabChangedListener(this);

        InicialCursos.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab3").setIndicator("Horário de trabalhos"),
                (tabInfo = new TabInfo("Tab3", HorarioTrabalhos.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        mTabHost.setOnTabChangedListener(this);

        //obs: dentro do esquema de abas do curso eh que terá a aba de menu ....

    }

    private static void AddTab(InicialCursos activity, TabHost tabHost,
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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent intent = new Intent(getApplication(), Inicial.class);
        startActivity(intent);
    }


}