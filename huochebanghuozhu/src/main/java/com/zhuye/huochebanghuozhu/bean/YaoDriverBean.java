package com.zhuye.huochebanghuozhu.bean;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class YaoDriverBean  extends Base{
    /**
     * data : {"driver_id":"6","name":"黎明","face":"/Uploads/Picture/2018-01-02/20180102111937.png"}
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
         * driver_id : 6
         * name : 黎明
         * face : /Uploads/Picture/2018-01-02/20180102111937.png
         */

        private String driver_id;
        private String name;
        private String face;

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
