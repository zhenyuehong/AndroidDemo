package com.mtelnet.myview.jsoup.bean;

/**
 * Created by hongzhenyue on 16/12/29.
 */

public class Food {
    private String title,//title
            caipu,//caipu
            name,//菜名
            material,//材料
            pic_url,//图片链接
            cp_url;//菜谱链接

    public String getCaipu() {
        return caipu;
    }

    public void setCaipu(String caipu) {
        this.caipu = caipu;
    }

    public String getCp_url() {
        return cp_url;
    }

    public void setCp_url(String cp_url) {
        this.cp_url = cp_url;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
