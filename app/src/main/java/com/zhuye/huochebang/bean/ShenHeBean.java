package com.zhuye.huochebang.bean;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class ShenHeBean extends Base{
    /**
     * data : {"id":"6","uid":"12","name":"故意","card1":"/Uploads/Picture/2018-01-04/video-170947651.jpg","card2":"/Uploads/Picture/2018-01-04/video-63785265.jpg","face":"/Uploads/Picture/2018-01-04/video-31240884.jpg","license":"/Uploads/Picture/2018-01-04/video-183672975.jpg","driving_book":"","cord":"","encode":"","type":"0","create_time":"1515037322","request":"                \r\n       测试"}
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
         * id : 6
         * uid : 12
         * name : 故意
         * card1 : /Uploads/Picture/2018-01-04/video-170947651.jpg
         * card2 : /Uploads/Picture/2018-01-04/video-63785265.jpg
         * face : /Uploads/Picture/2018-01-04/video-31240884.jpg
         * license : /Uploads/Picture/2018-01-04/video-183672975.jpg
         * driving_book :
         * cord :
         * encode :
         * type : 0
         * create_time : 1515037322
         * request :
         测试
         */

        private String id;
        private String uid;
        private String name;
        private String card1;
        private String card2;
        private String face;
        private String license;
        private String driving_book;
        private String cord;
        private String encode;
        private String type;
        private String create_time;
        private String request;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCard1() {
            return card1;
        }

        public void setCard1(String card1) {
            this.card1 = card1;
        }

        public String getCard2() {
            return card2;
        }

        public void setCard2(String card2) {
            this.card2 = card2;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getDriving_book() {
            return driving_book;
        }

        public void setDriving_book(String driving_book) {
            this.driving_book = driving_book;
        }

        public String getCord() {
            return cord;
        }

        public void setCord(String cord) {
            this.cord = cord;
        }

        public String getEncode() {
            return encode;
        }

        public void setEncode(String encode) {
            this.encode = encode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getRequest() {
            return request;
        }

        public void setRequest(String request) {
            this.request = request;
        }
    }
}
