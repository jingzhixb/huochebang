package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class SystemMessage  extends Base{
    /**
     * data : [{"title":"标题","intro":"消息内容","create_time":"时间"}]
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
         * title : 标题
         * intro : 消息内容
         * create_time : 时间
         */

        private String title;
        private String intro;
        private String create_time;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
