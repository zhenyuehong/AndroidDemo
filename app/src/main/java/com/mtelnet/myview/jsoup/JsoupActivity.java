package com.mtelnet.myview.jsoup;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mtelnet.myview.R;
import com.mtelnet.myview.jsoup.adapter.JsoupPageAdapter;
import com.mtelnet.myview.jsoup.bean.Cmenu;
import com.mtelnet.myview.jsoup.bean.Food;
import com.mtelnet.myview.jsoup.fragment.FoodFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupActivity extends AppCompatActivity {
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private List<Food> mFood=new ArrayList<>();
    private List<Cmenu> mMenu=new ArrayList<Cmenu>();;
    private List<FoodFragment> mFragment=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        getData();


    }

    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.lay_tab);
        mViewPager = (ViewPager) findViewById(R.id.lay_viewpage);
        JsoupPageAdapter adapter = new JsoupPageAdapter(getSupportFragmentManager(),mMenu,mFragment);

        mViewPager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewPager);
    }

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //从一个URL加载一个Document对象。
                    Document doc = Jsoup.connect("http://home.meishichina.com/show-top-type-recipe.html").get();
//                    Document doc = Jsoup.parse(new URL("http://home.meishichina.com/show-top-type-recipe.html"),5000);

//                    菜谱title
                    final Elements title_elements = doc.select("div.nav_wrap2");
//                    mMenu=new ArrayList<Cmenu>();
                    for (int i=0;i<title_elements.size();i++) {
                        Elements title_item = title_elements.get(i).getElementsByTag("li");
                        if (null != title_item) {
                            for (int j=0;j<title_item.size();j++) {
                                String title = title_item.get(j).select("a").text();
                                String url = title_item.get(j).select("a").attr("href");
                                Log.i("title", "菜谱:" + title);
                                Log.i("href", "菜谱url:" + url);
                                FoodFragment mFoodFragment=new FoodFragment();
                                Cmenu menu=new Cmenu();
                                menu.setTitle(title);
                                menu.setUrl(url);
                                mMenu.add(menu);
                                mFragment.add(mFoodFragment);
                            }

                        }
                    }

                    //选择“美食天下”所在节点
                    final Elements elements = doc.select("div.detail");//div 标签下的 class：detail

                    for (Element es_ele : elements) {

                        Element es_item = es_ele.getElementsByTag("h2").first();

//                        Element es_subcontent=elements.get(i).getElementsByTag("p").first();

                        //class：detail下的"h2"里面的内容
                        if (null != es_item) {
                            final String title = es_item.select("a").text();
//                            final String subContent=es_subcontent.getElementsByClass("subcontent").text();
                            final String subContent = es_ele.select("p.subcontent").text();

                            //打印 <a>标签里面的title
                            Log.i("mytag", title + "     --->材料" + subContent);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    tv_web_msg.setText(title + "" + subContent);
                                }
                            });
                        }

//                        elements.get(i).getElementsByTag("")
                    }

                    final Elements pics = doc.select("div.pic");//div 标签下的 class：pic

                    for (Element picture : pics) {
                        String item_url = picture.select("a").attr("href");
                        Log.i("item_url", item_url + "     --->菜谱链接");
                        String pic_url = picture.select("img").attr("data-src");
                        Log.i("pic_url", pic_url + "     --->图片链接");
                    }

                } catch (Exception e) {
                    Log.i("mytag", e.toString());
                }
            }
        }.start();
        initView();

    }


}
