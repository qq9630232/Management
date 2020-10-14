package com.example.freightmanagement.Bean;

public class TuiliuKeyBean {

    /**
     * code : 0
     * message : 成功
     * data : {"chatRoomId":"1","streamKey":"stream383"}
     * type : 3
     * token : 3ez1IVYnzKhj1cUoRAlGu0145kkhQdkc
     */

    private int code;
    private String message;
    private DataBean data;
    private String type;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * chatRoomId : 1
         * streamKey : stream383
         */

        private String chatRoomId;
        private String streamKey;

        public String getChatRoomId() {
            return chatRoomId;
        }

        public void setChatRoomId(String chatRoomId) {
            this.chatRoomId = chatRoomId;
        }

        public String getStreamKey() {
            return streamKey;
        }

        public void setStreamKey(String streamKey) {
            this.streamKey = streamKey;
        }
    }
}
