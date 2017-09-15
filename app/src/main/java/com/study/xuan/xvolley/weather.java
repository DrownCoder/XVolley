package com.study.xuan.xvolley;

import java.util.List;

/**
 * Author : xuan.
 * Data : 2017/8/29.
 * Description :input the description of this file.
 */

public class weather {

    /**
     * message : Success !
     * status : 200
     * city : 北京
     * count : 2
     * data : {"shidu":"73%","pm25":33,"pm10":81,"quality":"良","wendu":"23",
     * "ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"12日星期二","sunrise":"05:51","high":"高温
     * 29.0℃","low":"低温 19.0℃","sunset":"18:30","aqi":34,"fx":"东南风","fl":"<3级","type":"晴",
     * "notice":"天气干燥，请适当增加室内湿度"},"forecast":[{"date":"13日星期三","sunrise":"05:52","high":"高温
     * 29.0℃","low":"低温 20.0℃","sunset":"18:28","aqi":59,"fx":"东北风","fl":"<3级","type":"多云",
     * "notice":"悠悠的云里有淡淡的诗"},{"date":"14日星期四","sunrise":"05:53","high":"高温 28.0℃","low":"低温
     * 20.0℃","sunset":"18:26","aqi":85,"fx":"南风","fl":"<3级","type":"阴",
     * "notice":"阴天没有晴天的明朗，却依然明亮"},{"date":"15日星期五","sunrise":"05:54","high":"高温 28.0℃","low":"低温
     * 18.0℃","sunset":"18:25","aqi":66,"fx":"西南风","fl":"<3级","type":"多云",
     * "notice":"今日多云，骑上单车去看看世界吧"},{"date":"16日星期六","sunrise":"05:55","high":"高温 27.0℃","low":"低温
     * 19.0℃","sunset":"18:23","aqi":66,"fx":"西南风","fl":"<3级","type":"阵雨",
     * "notice":"愿雨后清新的空气给您带来好心情！"},{"date":"17日星期日","sunrise":"05:56","high":"高温 27.0℃",
     * "low":"低温 18.0℃","sunset":"18:21","aqi":67,"fx":"西南风","fl":"<3级","type":"多云",
     * "notice":"悠悠的云里有淡淡的诗"}]}
     * udate : 20170913
     */

    private String message;
    private int status;
    private String city;
    private int count;
    private DataBean data;
    private int udate;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getUdate() {
        return udate;
    }

    public void setUdate(int udate) {
        this.udate = udate;
    }

    public static class DataBean {
        /**
         * shidu : 73%
         * pm25 : 33
         * pm10 : 81
         * quality : 良
         * wendu : 23
         * ganmao : 极少数敏感人群应减少户外活动
         * yesterday : {"date":"12日星期二","sunrise":"05:51","high":"高温 29.0℃","low":"低温 19.0℃",
         * "sunset":"18:30","aqi":34,"fx":"东南风","fl":"<3级","type":"晴","notice":"天气干燥，请适当增加室内湿度"}
         * forecast : [{"date":"13日星期三","sunrise":"05:52","high":"高温 29.0℃","low":"低温 20.0℃",
         * "sunset":"18:28","aqi":59,"fx":"东北风","fl":"<3级","type":"多云","notice":"悠悠的云里有淡淡的诗"},
         * {"date":"14日星期四","sunrise":"05:53","high":"高温 28.0℃","low":"低温 20.0℃",
         * "sunset":"18:26","aqi":85,"fx":"南风","fl":"<3级","type":"阴","notice":"阴天没有晴天的明朗，却依然明亮"},
         * {"date":"15日星期五","sunrise":"05:54","high":"高温 28.0℃","low":"低温 18.0℃",
         * "sunset":"18:25","aqi":66,"fx":"西南风","fl":"<3级","type":"多云",
         * "notice":"今日多云，骑上单车去看看世界吧"},{"date":"16日星期六","sunrise":"05:55","high":"高温 27.0℃",
         * "low":"低温 19.0℃","sunset":"18:23","aqi":66,"fx":"西南风","fl":"<3级","type":"阵雨",
         * "notice":"愿雨后清新的空气给您带来好心情！"},{"date":"17日星期日","sunrise":"05:56","high":"高温 27.0℃",
         * "low":"低温 18.0℃","sunset":"18:21","aqi":67,"fx":"西南风","fl":"<3级","type":"多云",
         * "notice":"悠悠的云里有淡淡的诗"}]
         */

        private String shidu;
        private int pm25;
        private int pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getPm10() {
            return pm10;
        }

        public void setPm10(int pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 12日星期二
             * sunrise : 05:51
             * high : 高温 29.0℃
             * low : 低温 19.0℃
             * sunset : 18:30
             * aqi : 34
             * fx : 东南风
             * fl : <3级
             * type : 晴
             * notice : 天气干燥，请适当增加室内湿度
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean {
            /**
             * date : 13日星期三
             * sunrise : 05:52
             * high : 高温 29.0℃
             * low : 低温 20.0℃
             * sunset : 18:28
             * aqi : 59
             * fx : 东北风
             * fl : <3级
             * type : 多云
             * notice : 悠悠的云里有淡淡的诗
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
