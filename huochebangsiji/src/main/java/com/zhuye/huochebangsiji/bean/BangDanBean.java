package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class BangDanBean extends Base{

    /**
     * data : {"f_unit":"发货单位","s_unit":"收货单位","good_name":"货物名称","car_card":"车号","m_weight":"毛重","p_weight":"皮重","weight":"净重","driver_name":"司磅员","photo":"发货单","landing":"卸货单"}
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
         * f_unit : 发货单位
         * s_unit : 收货单位
         * good_name : 货物名称
         * car_card : 车号
         * m_weight : 毛重
         * p_weight : 皮重
         * weight : 净重
         * driver_name : 司磅员
         * photo : 发货单
         * landing : 卸货单
         */

        private String f_unit;
        private String s_unit;
        private String good_name;
        private String car_card;
        private String m_weight;
        private String p_weight;
        private String weight;
        private String driver_name;
        private String photo;
        private String landing;

        public String getF_unit() {
            return f_unit;
        }

        public void setF_unit(String f_unit) {
            this.f_unit = f_unit;
        }

        public String getS_unit() {
            return s_unit;
        }

        public void setS_unit(String s_unit) {
            this.s_unit = s_unit;
        }

        public String getGood_name() {
            return good_name;
        }

        public void setGood_name(String good_name) {
            this.good_name = good_name;
        }

        public String getCar_card() {
            return car_card;
        }

        public void setCar_card(String car_card) {
            this.car_card = car_card;
        }

        public String getM_weight() {
            return m_weight;
        }

        public void setM_weight(String m_weight) {
            this.m_weight = m_weight;
        }

        public String getP_weight() {
            return p_weight;
        }

        public void setP_weight(String p_weight) {
            this.p_weight = p_weight;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getLanding() {
            return landing;
        }

        public void setLanding(String landing) {
            this.landing = landing;
        }
    }
}
