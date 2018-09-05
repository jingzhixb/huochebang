package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class MobileBean  extends Base{
    /**
     * data : {"mobile":"15188397572"}
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
         * mobile : 15188397572
         */

        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
