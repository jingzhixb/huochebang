package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class CarModelBean  extends Base{
    /**
     * data : [{"models_id":"1","models":"重卡"},{"models_id":"2","models":"轻卡"},{"models_id":"3","models":"中卡"},{"models_id":"4","models":"牵引车"},{"models_id":"5","models":"载货车"}]
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
         * models_id : 1
         * models : 重卡
         */

        private String models_id;
        private String models;

        public String getModels_id() {
            return models_id;
        }

        public void setModels_id(String models_id) {
            this.models_id = models_id;
        }

        public String getModels() {
            return models;
        }

        public void setModels(String models) {
            this.models = models;
        }
    }
}
