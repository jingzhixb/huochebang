package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class LiuYanBean  extends Base{
    /**
     * data : [{"create_time":"时间","name":"姓名","face":"头像","intro":"消息内容"}]
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
         * create_time : 时间
         * name : 姓名
         * face : 头像
         * intro : 消息内容
         */

        private String create_time;
        private String name;
        private String face;
        private String intro;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }
}
