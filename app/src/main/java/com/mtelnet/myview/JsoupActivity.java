package com.mtelnet.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        final TextView tv_web_msg= (TextView) findViewById(R.id.tv_web_msg);

        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    //从一个URL加载一个Document对象。
                    Document doc = Jsoup.connect("http://home.meishichina.com/show-top-type-recipe.html").get();
//                    Document doc = Jsoup.parse(new URL("http://home.meishichina.com/show-top-type-recipe.html"),5000);

                    //选择“美食天下”所在节点
                    final Elements elements = doc.select("div.detail");//div 标签下的 class：detail

                    for (int i=0;i<elements.size();i++){

                        Element es_item=elements.get(i).getElementsByTag("h2").first();

                        Element es_subcontent=elements.get(i).getElementsByTag("p").first();
                        //class：detail下的"h2"里面的内容
                        if (null!=es_item){
                            final String title=es_item.select("a").text();
//                            final String title=es_subcontent.;

                            //打印 <a>标签里面的title
//                            Log.i("mytag",title+);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_web_msg.setText(title);
                                }
                            });
                        }

//                        elements.get(i).getElementsByTag("")
                    }


                }catch(Exception e) {
                    Log.i("mytag", e.toString());
                }
            }
        }.start();

    }
}
