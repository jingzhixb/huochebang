package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/2/5 0005.
 */

public class NewsNumBean extends Base {
    /**
     * data : {"system_num":"系统消息条数","system":"最新消息信息","news_num":"个人消息条数","news":"最新个人消息记录"}
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
         * system_num : 系统消息条数
         * system : 最新消息信息
         * news_num : 个人消息条数
         * news : 最新个人消息记录
         */

        private String system_num;
        private String system;
        private String news_num;
        private String news;

        public String getSystem_num() {
            return system_num;
        }

        public void setSystem_num(String system_num) {
            this.system_num = system_num;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public String getNews_num() {
            return news_num;
        }

        public void setNews_num(String news_num) {
            this.news_num = news_num;
        }

        public String getNews() {
            return news;
        }

        public void setNews(String news) {
            this.news = news;
        }
    }
}
