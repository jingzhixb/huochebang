package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class PhoneLoginCde  extends Base{
    /**
     * data : {"token":"af9936688678e1cfb1e77b1c974f1ef8","message":0,"audit":"0"}
     * message : 登陆成功
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
         * token : af9936688678e1cfb1e77b1c974f1ef8
         * message : 0
         * audit : 0
         */

        private String token;
        private int message;
        private String audit;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getMessage() {
            return message;
        }

        public void setMessage(int message) {
            this.message = message;
        }

        public String getAudit() {
            return audit;
        }

        public void setAudit(String audit) {
            this.audit = audit;
        }
    }
}
