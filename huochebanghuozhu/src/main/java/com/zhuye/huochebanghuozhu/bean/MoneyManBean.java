package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class MoneyManBean  extends Base{
    /**
     * data : {"money":"0","log":[]}
     * message :
     * code : 200
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {
        /**
         * money : 0
         * log : []
         */

        private String money;
        private List<Data> log;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public List<Data> getLog() {
            return log;
        }

        public void setLog(List<Data> log) {
            this.log = log;
        }

        public static class Data{
            private String week;
            private String create_time;
            private String intro;
            private String money;

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
