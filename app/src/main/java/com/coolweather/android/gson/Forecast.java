package com.coolweather.android.gson;

public class Forecast {
//    public Temperature getTmp() {
//        return tmp;
//    }
//
//    public void setTmp(Temperature tmp) {
//        this.tmp = tmp;
//    }
//
//    public More getCond() {
//        return cond;
//    }
//
//    public void setCond(More cond) {
//        this.cond = cond;
//    }
//
//    private Temperature tmp;
//    private More cond;
    public class Tmp{
        private String max;

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        private String min;

    }
    public class Cond{
        public String getTxt_d() {
            return txt_d;
        }

        public void setTxt_d(String txt_d) {
            this.txt_d = txt_d;
        }

        private String txt_d;

    }


    private String date;

    private Cond cond;

    private Tmp tmp;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }

    public Cond getCond() {
        return this.cond;
    }

    public void setTmp(Tmp tmp) {
        this.tmp = tmp;
    }

    public Tmp getTmp() {
        return this.tmp;
    }

}
