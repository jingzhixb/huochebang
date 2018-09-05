package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class CarListBean extends Base {
    /**
     * data : [{"car_id":"1","license":"123456"}]
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
         * car_id : 1
         * license : 123456
         */

        private String car_id;
        private String license;

        public Boolean getIsselect() {
            return isselect;
        }

        public void setIsselect(Boolean isselect) {
            this.isselect = isselect;
        }

        private Boolean isselect;

        public String getCar_id() {
            return car_id;
        }

        public void setCar_id(String car_id) {
            this.car_id = car_id;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }
    }
}
