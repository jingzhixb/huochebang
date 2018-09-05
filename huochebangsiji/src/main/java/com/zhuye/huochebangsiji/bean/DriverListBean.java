package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class DriverListBean extends Base{
    /**
     * data : [{"driver_jionid":"1","driver_id":"6","name":"黎明","face":"/Uploads/Picture/2018-01-02/20180102111937.png"}]
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
         * driver_jionid : 1
         * driver_id : 6
         * name : 黎明
         * face : /Uploads/Picture/2018-01-02/20180102111937.png
         */

        private String driver_jionid;
        private String driver_id;
        private String name;
        private String face;

        public String getDriver_jionid() {
            return driver_jionid;
        }

        public void setDriver_jionid(String driver_jionid) {
            this.driver_jionid = driver_jionid;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }
    }
}
