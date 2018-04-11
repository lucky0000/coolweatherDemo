package com.coolweather.android.gson;

public class Now {
    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public More getCond() {
        return cond;
    }

    public void setCond(More cond) {
        this.cond = cond;
    }

    private String tmp;

    private More cond;

    public class More{
        private String txt;

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }
}
