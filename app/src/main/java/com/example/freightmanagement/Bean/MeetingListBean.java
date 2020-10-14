package com.example.freightmanagement.Bean;

import java.util.List;

public class MeetingListBean {

    /**
     * code : 0
     * message : 成功
     * data : [{"id":68,"name":"61","startTime":"2020-09-27 18:08:12","sponsor":"61","isActive":1,"createTime":1601201293000,"updateTime":1601201293000,"endTime":"2020-09-27 18:08:12","driverBos":[],"streamKey":"stream68","startTimeStr":"2020-09-27 18:08:12","endTimeStr":"2020-09-27 18:08:12"},{"id":75,"name":"安全例会","startTime":"2020-09-27 18:10:40","sponsor":"61","isActive":1,"createTime":1601201454000,"updateTime":1601201454000,"endTime":"2020-09-27 18:10:40","driverBos":[],"streamKey":"stream75","startTimeStr":"2020-09-27 18:10:40","endTimeStr":"2020-09-27 18:10:40"}]
     * type : 1
     * token : k69iizcMsR7pOX54wvIUVmumZWOySat8
     */

    private int code;
    private String message;
    private String type;
    private String token;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 68
         * name : 61
         * startTime : 2020-09-27 18:08:12
         * sponsor : 61
         * isActive : 1
         * createTime : 1601201293000
         * updateTime : 1601201293000
         * endTime : 2020-09-27 18:08:12
         * driverBos : []
         * streamKey : stream68
         * startTimeStr : 2020-09-27 18:08:12
         * endTimeStr : 2020-09-27 18:08:12
         */

        private int id;
        private String name;
        private String startTime;
        private String sponsor;
        private int isActive;
        private long createTime;
        private long updateTime;
        private String endTime;
        private String streamKey;
        private String startTimeStr;
        private String endTimeStr;
        private List<?> driverBos;
        private String roomId;
        private Integer state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getSponsor() {
            return sponsor;
        }

        public void setSponsor(String sponsor) {
            this.sponsor = sponsor;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStreamKey() {
            return streamKey;
        }

        public void setStreamKey(String streamKey) {
            this.streamKey = streamKey;
        }

        public String getStartTimeStr() {
            return startTimeStr;
        }

        public void setStartTimeStr(String startTimeStr) {
            this.startTimeStr = startTimeStr;
        }

        public String getEndTimeStr() {
            return endTimeStr;
        }

        public void setEndTimeStr(String endTimeStr) {
            this.endTimeStr = endTimeStr;
        }

        public List<?> getDriverBos() {
            return driverBos;
        }

        public void setDriverBos(List<?> driverBos) {
            this.driverBos = driverBos;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }
    }
}
