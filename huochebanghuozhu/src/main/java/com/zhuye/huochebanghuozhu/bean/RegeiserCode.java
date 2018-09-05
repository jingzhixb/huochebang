package com.zhuye.huochebanghuozhu.bean;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class RegeiserCode  extends Base{
    /**
     * data : {"token":"7cfe20ee63537520470c2f7dc3dd428a"}
     * message : 注册成功
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
         * token : 7cfe20ee63537520470c2f7dc3dd428a
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
