package com.zhuye.huochebang.bean;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class PhoneLoginCde extends Base {
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    /**
     * data : {"token":"af9936688678e1cfb1e77b1c974f1ef8","message":0,"audit":"0"}
     * message : 登陆成功
     * code : 200
     */

    private DataBean data;

    /**
     * token : af9936688678e1cfb1e77b1c974f1ef8
         * message : 0
         * audit : 0
         */

    public class DataBean{

        private String token;
        private int message;
        private String audit;
        private String encode;

        public String getEncode() {
            return encode;
        }

        public void setEncode(String encode) {
            this.encode = encode;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }


        public int getMessage() {
            return message;
        }

        public void setMessage(int messages) {
            this.message = messages;
        }

        public String getAudit() {
            return audit;
        }

        public void setAudit(String audit) {
            this.audit = audit;
        }

    }

}
