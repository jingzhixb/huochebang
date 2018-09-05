package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class PeiSongBean extends Base {
    /**
     * data : [{"type_name":"装卸方式","type_arr":[{"chose_id":"1","chose_name":"一装一卸"},{"chose_id":"2","chose_name":"一装两卸"},{"chose_id":"3","chose_name":"一装多卸"},{"chose_id":"4","chose_name":"两装一卸"},{"chose_id":"5","chose_name":"两装两卸"},{"chose_id":"6","chose_name":"两装多卸"}]}]
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
         * type_name : 装卸方式
         * type_arr : [{"chose_id":"1","chose_name":"一装一卸"},{"chose_id":"2","chose_name":"一装两卸"},{"chose_id":"3","chose_name":"一装多卸"},{"chose_id":"4","chose_name":"两装一卸"},{"chose_id":"5","chose_name":"两装两卸"},{"chose_id":"6","chose_name":"两装多卸"}]
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
            public Boolean getIsselect() {
                return isselect;
            }

            public void setIsselect(Boolean isselect) {
                this.isselect = isselect;
            }

            /**
             * chose_id : 1
             * chose_name : 一装一卸
             */

            private Boolean isselect;
            private String chose_id;
            private String chose_name;

            public String getChose_id() {
                return chose_id;
            }

            public void setChose_id(String chose_id) {
                this.chose_id = chose_id;
            }

            public String getChose_name() {
                return chose_name;
            }

            public void setChose_name(String chose_name) {
                this.chose_name = chose_name;
            }
        }
    }
}
