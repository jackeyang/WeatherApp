package com.hust.jackeyang.weatherapp.mode;

/**
 * Created by jackeyang on 2016/6/20.
 */
//实况天气
public class Now {
    public String tmp;
    public String fl;
    public Wind wind;
    public Cond cond;
    public String pcpn;
    public String hum;
    public String pres;
    public String vis;


    public Now() {
    }

    public Now(String tmp, String fl, Wind wind, Cond cond, String pcpn, String hum, String pres,
               String vis) {
        this.tmp = tmp;
        this.fl = fl;
        this.wind = wind;
        this.cond = cond;
        this.pcpn = pcpn;
        this.hum = hum;
        this.pres = pres;
        this.vis = vis;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }



    public Cond getCond() {
        return cond;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public static class Cond {
        public String code;
        public String txt;

        public Cond(String code, String txt) {
            this.code = code;
            this.txt = txt;
        }
    }

    public static class Wind {
        public String deg;
        public String dir;
        public String sc;
        public String spd;

        public Wind(String deg, String dir, String sc, String spd) {
            this.deg = deg;
            this.dir = dir;
            this.sc = sc;
            this.spd = spd;
        }
    }
}
