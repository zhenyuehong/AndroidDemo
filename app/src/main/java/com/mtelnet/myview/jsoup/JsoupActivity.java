package com.mtelnet.myview.jsoup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtelnet.myview.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupActivity extends AppCompatActivity {
    private TextView tv_web_msg;
    private LinearLayout layout_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

        layout_title = (LinearLayout) findViewById(R.id.lay_title);
        tv_web_msg = (TextView) findViewById(R.id.tv_web_msg);

//        addTitle();

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
                    for (Element title_ele : title_elements) {
                        Elements title_item = title_ele.getElementsByTag("li");
                        if (null != title_item) {
                            for (Element title_element:title_item){
                                String title = title_element.select("a").text();
                                Log.i("title",  "菜谱:"+title );
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
                                    tv_web_msg.setText(title + "" + subContent);
                                }
                            });
                        }

//                        elements.get(i).getElementsByTag("")
                    }

                    final Elements pics = doc.select("div.pic");//div 标签下的 class：pic

                    for (Element picture : pics) {
                        String item_url = picture.select("a").attr("href");
                        Log.i("item_url", item_url + "     --->菜谱链接");
                        String pic_url=picture.select("img").attr("data-src");
                        Log.i("pic_url", pic_url + "     --->图片链接");
                    }

                } catch (Exception e) {
                    Log.i("mytag", e.toString());
                }
            }
        }.start();

    }
}
