package com.szsszwl.materail_demo;

import com.github.paolorotolo.appintro.AppIntroFragment;
import com.szsszwl.frameworkproj.intro_page.BaseIntroActivity;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends BaseIntroActivity {
    @Override
    public List<AppIntroFragment> setPages() {
        List<AppIntroFragment> pages = new ArrayList<>();
        pages.add(AppIntroFragment.newInstance("第一页","这是第一页",
                R.mipmap.ic_launcher,getResources().getColor(R.color.theme_color_primary)));
        return pages;
    }
}
