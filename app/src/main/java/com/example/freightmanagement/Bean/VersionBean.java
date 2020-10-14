package com.example.freightmanagement.Bean;

public class VersionBean {
    /**
     * code : 0
     * message : 成功
     * data : {"id":5,"driverCertificateId":1,"workCertificateId":1,"isActive":1,"createTime":1596784024000,"updateTime":1596784024000,"name":null,"pass":"123456","tel":"18141919315","certificateDriverBo":{"id":1,"name":"孙勇","sex":"1","country":"中国","isActive":1,"createTime":1596608578000,"updateTime":1596872200000,"address":"北京","birthday":"1989-08-10 15:12","receiveDate":null,"classs":null,"startTime":null,"term":null,"fileNumber":"123456","note":"驾驶证信息","picUrl":null},"certificateIDBo":{"id":1,"name":"蜀黍","six":"0","nation":"中国","birthDay":"2020-08-26 18:18","address":"山东","isActive":1,"createTime":1596607560000,"updateTime":1596881924000,"picUrl":null,"picUrl2":null,"file":null,"file2":null,"idno":"3718356965412358521"},"certificateWorkBo":{"id":1,"name":"孙玉勇","sex":"1","birthday":"2020-08-12 15:24","nationality":"中国","address":"北京","grantNo":"566233","classs":null,"isActive":1,"createTime":1596609097000,"updateTime":1596871505000,"picUrl":null,"category":null,"firstTime":null,"validityStartTime":null,"validityEndTime":null,"fileNumber":null},"idcertificateId":1}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public static class DataBean {
        private Integer id;

        private String version;

        private Object createtime;

        private static final long serialVersionUID = 1L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }
    }
}
