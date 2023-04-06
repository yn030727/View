package com.example.httpurlconnection;

public class HappyJoke {
    private int code;
    private String msg;
    private Data data;
    private Au author;

    public Au getAuthor() {
        return author;
    }

    public void setAuthor(Au author) {
        author = author;
    }

    public Data getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class Data{
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    public class Au{
        private String name;
        private String desc;

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
