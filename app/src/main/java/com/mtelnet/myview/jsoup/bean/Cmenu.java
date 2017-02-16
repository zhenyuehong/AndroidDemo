package com.mtelnet.myview.jsoup.bean;

/**
 * Created by hongzhenyue on 16/12/29.
 */

public class Cmenu {
    private String title;//菜谱的类型，一周热门 当季最热。。。
    private String url;//链接

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
