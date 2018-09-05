package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class RechargeBean extends Base{
    /**
     * data : [{"shopname":"星星豪华店","address":"郑州市大学科技园东区","mobile":"13460300870"}]
     * message :
     * code : 200
     */

   ;
    private List<DataBean> data;



    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shopname : 星星豪华店
         * address : 郑州市大学科技园东区
         * mobile : 13460300870
         */

        private String shopname;
        private String address;
        private String mobile;

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
