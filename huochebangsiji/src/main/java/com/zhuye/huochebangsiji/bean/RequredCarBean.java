package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12 0012.
 */

public class RequredCarBean  extends Base{
    /**
     * data : [{"type_name":"用车类型","type_arr":[{"chose_name":"整车","chose_id":1},{"chose_name":"拼车","chose_id":2}]},{"type_name":"车长","type_arr":[{"chose_name":"4.2","chose_id":3},{"chose_name":"4.8","chose_id":4},{"chose_name":"6.2","chose_id":5},{"chose_name":"6.8","chose_id":6},{"chose_name":"7.5","chose_id":7},{"chose_name":"1.0","chose_id":8}]},{"type_name":"车型","type_arr":[{"chose_name":"重卡","chose_id":9},{"chose_name":"轻卡","chose_id":10},{"chose_name":"中卡","chose_id":11},{"chose_name":"牵引车","chose_id":12},{"chose_name":"载货车","chose_id":13}]}]
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
         * type_name : 用车类型
         * type_arr : [{"chose_name":"整车","chose_id":1},{"chose_name":"拼车","chose_id":2}]
         */

        private String type_name;
        private List<TypeArrBean> type_arr;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public List<TypeArrBean> getType_arr() {
            return type_arr;
        }

        public void setType_arr(List<TypeArrBean> type_arr) {
            this.type_arr = type_arr;
        }

        public static class TypeArrBean {
            /**
             * chose_name : 整车
             * chose_id : 1
             */

            private String chose_name;
            private int chose_id;

            public String getChose_name() {
                return chose_name;
            }

            public void setChose_name(String chose_name) {
                this.chose_name = chose_name;
            }

            public int getChose_id() {
                return chose_id;
            }

            public void setChose_id(int chose_id) {
                this.chose_id = chose_id;
            }
        }
    }
}
