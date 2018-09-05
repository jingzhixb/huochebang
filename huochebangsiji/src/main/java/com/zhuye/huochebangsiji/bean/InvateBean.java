package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class InvateBean  extends Base{
    /**
     * data : {"url":"http://www.baidu.com"}
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
         * url : http://www.baidu.com
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
