package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class GoodsDeailBean extends Base {
    /**
     * data : [{"good_id":"货物信息id","start_city":"出发地","end_city":"目的地","good_type":"货物类型","weight":"货物重量","leng":"货车长","models":"货车类型","price":"价格"}]
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
         * good_id : 货物信息id
         * start_city : 出发地
         * end_city : 目的地
         * good_type : 货物类型
         * weight : 货物重量
         * leng : 货车长
         * models : 货车类型
         * price : 价格
         */

        private String good_id;
        private String start_city;
        private String end_city;
        private String good_type;
        private String weight;
        private String leng;
        private String models;
        private String price;

        public String getGood_id() {
            return good_id;
        }

        public void setGood_id(String good_id) {
            this.good_id = good_id;
        }

        public String getStart_city() {
            return start_city;
        }

        public void setStart_city(String start_city) {
            this.start_city = start_city;
        }

        public String getEnd_city() {
            return end_city;
        }

        public void setEnd_city(String end_city) {
            this.end_city = end_city;
        }

        public String getGood_type() {
            return good_type;
        }

        public void setGood_type(String good_type) {
            this.good_type = good_type;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getLeng() {
            return leng;
        }

        public void setLeng(String leng) {
            this.leng = leng;
        }

        public String getModels() {
            return models;
        }

        public void setModels(String models) {
            this.models = models;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
