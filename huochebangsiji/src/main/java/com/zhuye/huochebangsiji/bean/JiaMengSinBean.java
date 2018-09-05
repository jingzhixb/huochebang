package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class JiaMengSinBean  extends Base{
    /**
     * data : {"jion_id":"加盟商id","face":"头像","name":"名称","encode":"编码"}
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
         * jion_id : 加盟商id
         * face : 头像
         * name : 名称
         * encode : 编码
         */

        private String jion_id;
        private String face;
        private String name;
        private String encode;

        public String getJion_id() {
            return jion_id;
        }

        public void setJion_id(String jion_id) {
            this.jion_id = jion_id;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEncode() {
            return encode;
        }

        public void setEncode(String encode) {
            this.encode = encode;
        }
    }
}
