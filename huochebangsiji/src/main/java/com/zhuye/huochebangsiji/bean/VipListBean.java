package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class VipListBean extends Base {
    /**
     * data : [{"vip_id":"vip的id","month":"月份","money":"金额"},{"vip_id":"7","month":"2","money":"10.00"},{"vip_id":"8","month":"3","money":"20.00"},{"vip_id":"9","month":"6","money":"50.00"}]
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
         * vip_id : vip的id
         * month : 月份
         * money : 金额
         */

        private Boolean isSelected;

        public Boolean getSelected() {
            return isSelected;
        }

        public void setSelected(Boolean selected) {
            isSelected = selected;
        }

        private String vip_id;
        private String month;
        private String money;

        public String getVip_id() {
            return vip_id;
        }

        public void setVip_id(String vip_id) {
            this.vip_id = vip_id;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
