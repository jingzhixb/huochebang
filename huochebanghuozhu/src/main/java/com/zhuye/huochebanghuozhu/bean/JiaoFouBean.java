package com.zhuye.huochebanghuozhu.bean;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class JiaoFouBean  extends Base{
    /**
     * data : {"bond":"200","type":1}
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
         * bond : 200
         * type : 1
         */

        private String bond;
        private int type;

        public String getBond() {
            return bond;
        }

        public void setBond(String bond) {
            this.bond = bond;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
