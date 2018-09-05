package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class HomeCarInfoBean  extends Base{
    /**
     * data : [{"car_id":"1","license":"123456","models":"重卡","leng":"4.2","type":"0","driver":[],"driver_count":"0"}]
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
         * models : 重卡
         * leng : 4.2
         * type : 0
         * driver : []
         * driver_count : 0
         */

        private String car_id;
        private String license;
        private String models;
        private String leng;
        private String type;
        private String driver_count;
        private List<Data> driver;

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

        public String getModels() {
            return models;
        }

        public void setModels(String models) {
            this.models = models;
        }

        public String getLeng() {
            return leng;
        }

        public void setLeng(String leng) {
            this.leng = leng;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDriver_count() {
            return driver_count;
        }

        public void setDriver_count(String driver_count) {
            this.driver_count = driver_count;
        }

        public List<Data> getDriver() {
            return driver;
        }

        public void setDriver(List<Data> driver) {
            this.driver = driver;
        }


        public static class Data{
            public String getCar_driverid() {
                return car_driverid;
            }

            public void setCar_driverid(String car_driverid) {
                this.car_driverid = car_driverid;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            private String car_driverid;
            private String face;
            private String name;
            private String mobile;
        }
    }

}
