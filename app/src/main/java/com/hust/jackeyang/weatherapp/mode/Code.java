package com.hust.jackeyang.weatherapp.mode;

/**
 * 天气代码表中数据模型
 * Created by jackeyang on 2016/6/21.
 */
public class Code {
    private String code;
    private String txt;
    private String txt_en;
    private String icon;

    public Code() {
    }

    public Code(String code, String txt, String txt_en, String icon) {
        this.code = code;
        this.txt = txt;
        this.txt_en = txt_en;
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt_en() {
        return txt_en;
    }

    public void setTxt_en(String txt_en) {
        this.txt_en = txt_en;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
