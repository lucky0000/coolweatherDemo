package com.coolweather.android.gson;

public class Suggestion {

    private Comfort comf;

    public Comfort getComf() {
        return comf;
    }

    public void setComf(Comfort comf) {
        this.comf = comf;
    }

    public CarWash getCw() {
        return cw;
    }

    public void setCw(CarWash cw) {
        this.cw = cw;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    private CarWash cw;

    private Sport sport;

    public class Comfort {
        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        private String txt;
    }

    public class CarWash {
        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        private String txt;
    }

    public class Sport {
        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        private String txt;
    }
}
