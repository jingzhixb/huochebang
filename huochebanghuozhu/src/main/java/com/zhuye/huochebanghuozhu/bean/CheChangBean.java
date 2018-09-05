package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class CheChangBean  extends Base{
    /**
     * data : [{"commander_id":"1","leng":"4.2"},{"commander_id":"2","leng":"4.8"},{"commander_id":"3","leng":"6.2"},{"commander_id":"4","leng":"6.8"},{"commander_id":"5","leng":"7.5"},{"commander_id":"6","leng":"1.0"}]
     * message :
     * code : 200
     */


    private List<DataBean> data;



    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commander_id : 1
         * leng : 4.2
         */

        private int commander_id;
        private String leng;

        public int getCommander_id() {
            return commander_id;
        }

        public void setCommander_id(int commander_id) {
            this.commander_id = commander_id;
        }

        public String getLeng() {
            return leng;
        }

        public void setLeng(String leng) {
            this.leng = leng;
        }
    }
}
