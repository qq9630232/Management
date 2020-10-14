package com.example.freightmanagement.Bean;


import java.io.Serializable;
import java.util.List;

public class CheliangJcBean implements Serializable{



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

    public static class DataBean implements Serializable{


        private int id;
        private int driverId;
        private String note;
        private String checkDateTime;
        private int type;
        private int isActive;
        private long createTime;
        private long updateTime;
        private int carId;
        private String picUrl;
        private CarBoBean carBo;
        private DriverBoBean driverBo;
        private List<CompleteBosBean> completeBos;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDriverId() {
            return driverId;
        }

        public void setDriverId(int driverId) {
            this.driverId = driverId;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getCheckDateTime() {
            return checkDateTime;
        }

        public void setCheckDateTime(String checkDateTime) {
            this.checkDateTime = checkDateTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public CarBoBean getCarBo() {
            return carBo;
        }

        public void setCarBo(CarBoBean carBo) {
            this.carBo = carBo;
        }

        public DriverBoBean getDriverBo() {
            return driverBo;
        }

        public void setDriverBo(DriverBoBean driverBo) {
            this.driverBo = driverBo;
        }

        public List<CompleteBosBean> getCompleteBos() {
            return completeBos;
        }

        public void setCompleteBos(List<CompleteBosBean> completeBos) {
            this.completeBos = completeBos;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public static class CarBoBean implements Serializable{
            /**
             * id : 89
             * drivingCertificateId : 75
             * tel :
             * transportCertificateId : 75
             * registrationCertificateId : 82
             * isActive : 1
             * createTime : 1599791842000
             * updateTime : 1599791842000
             * adminId : 5
             * enterpriseId : 61
             * isPass : 1
             * carOnwerId : 26
             * certificateDrivingBo : {"id":75,"plateNo":"鲁Q7C312","wchicheType":"小型普通客车","owner":"","address":"","useCharacter":"非营运","model":"福特牌CAF6490A57","engineNo":"","registrationDate":"2018-02-07","issueDate":"2018-02-07","fileNo":"344dfggjks","passengersCount":0,"allWeight":"3400","curbWeight":"","approvedWeight":"5600","outline":"","tractionWeight":"","note":"","chechRecord":"","isActive":1,"updateTime":1599791842000,"createTime":1599791842000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/68f442e0-d8b1-4258-a7f6-7d30db0ca347.jpg","carId":0,"vin":""}
             * certificateIDBo : {"id":222,"name":"李思思","six":"0","nation":"汉族","birthDay":"2020-09-12","address":"北京市朝阳区","isActive":1,"createTime":1599879965000,"updateTime":1599893174000,"picUrl":"http://aicc.ctags.cn/cccc/file/download/23ea5a77-da3e-4da2-8284-de4329c1a342.jpg","picUrl2":"","driverId":0,"carId":0,"carOwnerId":0,"enterpriseId":0,"idno":"1102003003666"}
             * certificateTransportBo : {"id":75,"name":"罗庄运输企业","address":"","plateNo":"临字63355653号","licence":"临字56893号","type":"","weight":"","size":"","scope":"货运","grantDate":"2014-09-11","isActive":1,"createTime":1599791842000,"updateTime":1599791842000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/879cd423-4e29-43e7-b7a1-7e8a2312b6d2.jpg","transportNo":"临字63355653号","validityDate":"2014-09-11","carId":0}
             * certificateRegistrationBo : {"id":82,"owner":"临沂远华运输有限公司/一社会信用代码/1373","grantName":"","grantNo":"鲁Q7C312","carType":"重挂引车","carBrand":"歌曼","carModel":"42535F","carColor":"","carNo":"36","carImportType":"","engineNo":"473","engineType":"","fuelType":"柴油","power":"590","displacement":"45","maker":"北京福田截姆勒汽车有限公司后m","turnType":"","tireDistanceAhead":"","tireDistanceHinder":"","tireCount":"8","tireSpecs":"","springsCount":"","wheelbase":"","axlesCount":"","outline":"3000,4000,2001","inline":"","allWeight":"","curbWeight":"","approvedWeight":"","tractionWeight":"","passengersCount":"1","useNature":"货运","carGetType":"","productionDate":"","issueDate":"2016-09-11","isActive":1,"createTime":1599791842000,"updateTime":1599791842000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/eb9b86f3-5730-4bae-8bd7-10925290b740.jpg","departments":"山东省临沂市公安局交通警察支额,登","registrationDate":"2014-09-11","carId":0}
             * enterpriseBo : {"id":61,"legalPerson":"","signature":"https://aicc.ctags.cn/cccc/file/download/ae4592fd-61fd-4511-9c19-2a3b1026be79.png","sealUrl":"https://aicc.ctags.cn/cccc/file/download/e0142b0e-0f78-4dd4-88a1-0a8cfcc6f717.jpg","isActive":1,"createTime":1599791443000,"updateTime":1599791443000,"certificateBusinessId":61,"certificateOperationId":61,"tel":"","adminId":5,"name":"","certificateIDBo":{"id":173,"name":"陈超","six":"","nation":"","birthDay":"","address":"","isActive":1,"createTime":1599791443000,"updateTime":1599791443000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/9e42767c-d8f8-4172-992f-ecaf1d3f7853.jpg","picUrl2":"https://aicc.ctags.cn/cccc/file/download/0e290f42-c9c0-4ca5-972b-33bc2b2dfbb1.jpg","driverId":0,"carId":0,"carOwnerId":0,"enterpriseId":0,"idno":"371327198407041830"},"certificateOperationBo":{"id":61,"grantNo":"临字371316290072号","validityDate":"2040-10-14","owner":"","address":"","nature":"","scope":"","isActive":1,"updateTime":1599791443000,"createTime":1599791443000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/eb853ca5-e2a2-4ad9-a5ac-3c70c46cfd8f.jpg","enterpriseId":0},"certificateBusinessBo":{"id":61,"picUrl":"https://aicc.ctags.cn/cccc/file/download/410919bd-066c-478b-a262-628cb6235b55.jpg","name":"卓文智科(山东)物联网技术有限公司","capital":"","type":"","establishmentDate":"2020年06月24日","legalPerson":"陈培","term":"","scope":"术开发,技术转让技术咨训、技术服务销售:车载电子产技术三出口。依法短经批准的项目,相关部批准方可品机械设备、工金交电,电子产品,机械设备维修,货物及计算机软硬件的开发,精售维璧,机械设备、电了设备的技开居经营活动","address":"","registrationAuthority":"山东省临沂直兰山区双转路与宏大路交汇普西南凯歌国际文化城东北角阻歌大厦第五层501","isActive":1,"createTime":1599791443000,"updateTime":1599791443000,"enterpriseId":0},"adminBo":{"id":5,"name":"运输企业有限公司","pass":"13853902691","depart":"1","isActive":1,"createTime":1599316126000,"updateTime":1599316126000,"type":3,"tel":"13853902691","oldPass":null,"enterpriseId":0},"idcertificateId":173}
             * idcertificateId : 222
             */

            private int id;
            private int drivingCertificateId;
            private String tel;
            private int transportCertificateId;
            private int registrationCertificateId;
            private int isActive;
            private long createTime;
            private long updateTime;
            private int adminId;
            private int enterpriseId;
            private int isPass;
            private int carOnwerId;
            private CertificateDrivingBoBean certificateDrivingBo;
            private CertificateIDBoBean certificateIDBo;
            private CertificateTransportBoBean certificateTransportBo;
            private CertificateRegistrationBoBean certificateRegistrationBo;
            private EnterpriseBoBean enterpriseBo;
            private int idcertificateId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDrivingCertificateId() {
                return drivingCertificateId;
            }

            public void setDrivingCertificateId(int drivingCertificateId) {
                this.drivingCertificateId = drivingCertificateId;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public int getTransportCertificateId() {
                return transportCertificateId;
            }

            public void setTransportCertificateId(int transportCertificateId) {
                this.transportCertificateId = transportCertificateId;
            }

            public int getRegistrationCertificateId() {
                return registrationCertificateId;
            }

            public void setRegistrationCertificateId(int registrationCertificateId) {
                this.registrationCertificateId = registrationCertificateId;
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

            public int getAdminId() {
                return adminId;
            }

            public void setAdminId(int adminId) {
                this.adminId = adminId;
            }

            public int getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(int enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public int getIsPass() {
                return isPass;
            }

            public void setIsPass(int isPass) {
                this.isPass = isPass;
            }

            public int getCarOnwerId() {
                return carOnwerId;
            }

            public void setCarOnwerId(int carOnwerId) {
                this.carOnwerId = carOnwerId;
            }

            public CertificateDrivingBoBean getCertificateDrivingBo() {
                return certificateDrivingBo;
            }

            public void setCertificateDrivingBo(CertificateDrivingBoBean certificateDrivingBo) {
                this.certificateDrivingBo = certificateDrivingBo;
            }

            public CertificateIDBoBean getCertificateIDBo() {
                return certificateIDBo;
            }

            public void setCertificateIDBo(CertificateIDBoBean certificateIDBo) {
                this.certificateIDBo = certificateIDBo;
            }

            public CertificateTransportBoBean getCertificateTransportBo() {
                return certificateTransportBo;
            }

            public void setCertificateTransportBo(CertificateTransportBoBean certificateTransportBo) {
                this.certificateTransportBo = certificateTransportBo;
            }

            public CertificateRegistrationBoBean getCertificateRegistrationBo() {
                return certificateRegistrationBo;
            }

            public void setCertificateRegistrationBo(CertificateRegistrationBoBean certificateRegistrationBo) {
                this.certificateRegistrationBo = certificateRegistrationBo;
            }

            public EnterpriseBoBean getEnterpriseBo() {
                return enterpriseBo;
            }

            public void setEnterpriseBo(EnterpriseBoBean enterpriseBo) {
                this.enterpriseBo = enterpriseBo;
            }

            public int getIdcertificateId() {
                return idcertificateId;
            }

            public void setIdcertificateId(int idcertificateId) {
                this.idcertificateId = idcertificateId;
            }

            public static class CertificateDrivingBoBean {
                /**
                 * id : 75
                 * plateNo : 鲁Q7C312
                 * wchicheType : 小型普通客车
                 * owner :
                 * address :
                 * useCharacter : 非营运
                 * model : 福特牌CAF6490A57
                 * engineNo :
                 * registrationDate : 2018-02-07
                 * issueDate : 2018-02-07
                 * fileNo : 344dfggjks
                 * passengersCount : 0
                 * allWeight : 3400
                 * curbWeight :
                 * approvedWeight : 5600
                 * outline :
                 * tractionWeight :
                 * note :
                 * chechRecord :
                 * isActive : 1
                 * updateTime : 1599791842000
                 * createTime : 1599791842000
                 * picUrl : https://aicc.ctags.cn/cccc/file/download/68f442e0-d8b1-4258-a7f6-7d30db0ca347.jpg
                 * carId : 0
                 * vin :
                 */

                private int id;
                private String plateNo;
                private String wchicheType;
                private String owner;
                private String address;
                private String useCharacter;
                private String model;
                private String engineNo;
                private String registrationDate;
                private String issueDate;
                private String fileNo;
                private int passengersCount;
                private String allWeight;
                private String curbWeight;
                private String approvedWeight;
                private String outline;
                private String tractionWeight;
                private String note;
                private String chechRecord;
                private int isActive;
                private long updateTime;
                private long createTime;
                private String picUrl;
                private int carId;
                private String vin;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getPlateNo() {
                    return plateNo;
                }

                public void setPlateNo(String plateNo) {
                    this.plateNo = plateNo;
                }

                public String getWchicheType() {
                    return wchicheType;
                }

                public void setWchicheType(String wchicheType) {
                    this.wchicheType = wchicheType;
                }

                public String getOwner() {
                    return owner;
                }

                public void setOwner(String owner) {
                    this.owner = owner;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getUseCharacter() {
                    return useCharacter;
                }

                public void setUseCharacter(String useCharacter) {
                    this.useCharacter = useCharacter;
                }

                public String getModel() {
                    return model;
                }

                public void setModel(String model) {
                    this.model = model;
                }

                public String getEngineNo() {
                    return engineNo;
                }

                public void setEngineNo(String engineNo) {
                    this.engineNo = engineNo;
                }

                public String getRegistrationDate() {
                    return registrationDate;
                }

                public void setRegistrationDate(String registrationDate) {
                    this.registrationDate = registrationDate;
                }

                public String getIssueDate() {
                    return issueDate;
                }

                public void setIssueDate(String issueDate) {
                    this.issueDate = issueDate;
                }

                public String getFileNo() {
                    return fileNo;
                }

                public void setFileNo(String fileNo) {
                    this.fileNo = fileNo;
                }

                public int getPassengersCount() {
                    return passengersCount;
                }

                public void setPassengersCount(int passengersCount) {
                    this.passengersCount = passengersCount;
                }

                public String getAllWeight() {
                    return allWeight;
                }

                public void setAllWeight(String allWeight) {
                    this.allWeight = allWeight;
                }

                public String getCurbWeight() {
                    return curbWeight;
                }

                public void setCurbWeight(String curbWeight) {
                    this.curbWeight = curbWeight;
                }

                public String getApprovedWeight() {
                    return approvedWeight;
                }

                public void setApprovedWeight(String approvedWeight) {
                    this.approvedWeight = approvedWeight;
                }

                public String getOutline() {
                    return outline;
                }

                public void setOutline(String outline) {
                    this.outline = outline;
                }

                public String getTractionWeight() {
                    return tractionWeight;
                }

                public void setTractionWeight(String tractionWeight) {
                    this.tractionWeight = tractionWeight;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public String getChechRecord() {
                    return chechRecord;
                }

                public void setChechRecord(String chechRecord) {
                    this.chechRecord = chechRecord;
                }

                public int getIsActive() {
                    return isActive;
                }

                public void setIsActive(int isActive) {
                    this.isActive = isActive;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public int getCarId() {
                    return carId;
                }

                public void setCarId(int carId) {
                    this.carId = carId;
                }

                public String getVin() {
                    return vin;
                }

                public void setVin(String vin) {
                    this.vin = vin;
                }
            }

            public static class CertificateIDBoBean {
                /**
                 * id : 222
                 * name : 李思思
                 * six : 0
                 * nation : 汉族
                 * birthDay : 2020-09-12
                 * address : 北京市朝阳区
                 * isActive : 1
                 * createTime : 1599879965000
                 * updateTime : 1599893174000
                 * picUrl : http://aicc.ctags.cn/cccc/file/download/23ea5a77-da3e-4da2-8284-de4329c1a342.jpg
                 * picUrl2 :
                 * driverId : 0
                 * carId : 0
                 * carOwnerId : 0
                 * enterpriseId : 0
                 * idno : 1102003003666
                 */

                private int id;
                private String name;
                private String six;
                private String nation;
                private String birthDay;
                private String address;
                private int isActive;
                private long createTime;
                private long updateTime;
                private String picUrl;
                private String picUrl2;
                private int driverId;
                private int carId;
                private int carOwnerId;
                private int enterpriseId;
                private String idno;

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

                public String getSix() {
                    return six;
                }

                public void setSix(String six) {
                    this.six = six;
                }

                public String getNation() {
                    return nation;
                }

                public void setNation(String nation) {
                    this.nation = nation;
                }

                public String getBirthDay() {
                    return birthDay;
                }

                public void setBirthDay(String birthDay) {
                    this.birthDay = birthDay;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
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

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getPicUrl2() {
                    return picUrl2;
                }

                public void setPicUrl2(String picUrl2) {
                    this.picUrl2 = picUrl2;
                }

                public int getDriverId() {
                    return driverId;
                }

                public void setDriverId(int driverId) {
                    this.driverId = driverId;
                }

                public int getCarId() {
                    return carId;
                }

                public void setCarId(int carId) {
                    this.carId = carId;
                }

                public int getCarOwnerId() {
                    return carOwnerId;
                }

                public void setCarOwnerId(int carOwnerId) {
                    this.carOwnerId = carOwnerId;
                }

                public int getEnterpriseId() {
                    return enterpriseId;
                }

                public void setEnterpriseId(int enterpriseId) {
                    this.enterpriseId = enterpriseId;
                }

                public String getIdno() {
                    return idno;
                }

                public void setIdno(String idno) {
                    this.idno = idno;
                }
            }

            public static class CertificateTransportBoBean {
                /**
                 * id : 75
                 * name : 罗庄运输企业
                 * address :
                 * plateNo : 临字63355653号
                 * licence : 临字56893号
                 * type :
                 * weight :
                 * size :
                 * scope : 货运
                 * grantDate : 2014-09-11
                 * isActive : 1
                 * createTime : 1599791842000
                 * updateTime : 1599791842000
                 * picUrl : https://aicc.ctags.cn/cccc/file/download/879cd423-4e29-43e7-b7a1-7e8a2312b6d2.jpg
                 * transportNo : 临字63355653号
                 * validityDate : 2014-09-11
                 * carId : 0
                 */

                private int id;
                private String name;
                private String address;
                private String plateNo;
                private String licence;
                private String type;
                private String weight;
                private String size;
                private String scope;
                private String grantDate;
                private int isActive;
                private long createTime;
                private long updateTime;
                private String picUrl;
                private String transportNo;
                private String validityDate;
                private int carId;

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

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getPlateNo() {
                    return plateNo;
                }

                public void setPlateNo(String plateNo) {
                    this.plateNo = plateNo;
                }

                public String getLicence() {
                    return licence;
                }

                public void setLicence(String licence) {
                    this.licence = licence;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getScope() {
                    return scope;
                }

                public void setScope(String scope) {
                    this.scope = scope;
                }

                public String getGrantDate() {
                    return grantDate;
                }

                public void setGrantDate(String grantDate) {
                    this.grantDate = grantDate;
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

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getTransportNo() {
                    return transportNo;
                }

                public void setTransportNo(String transportNo) {
                    this.transportNo = transportNo;
                }

                public String getValidityDate() {
                    return validityDate;
                }

                public void setValidityDate(String validityDate) {
                    this.validityDate = validityDate;
                }

                public int getCarId() {
                    return carId;
                }

                public void setCarId(int carId) {
                    this.carId = carId;
                }
            }

            public static class CertificateRegistrationBoBean {
                /**
                 * id : 82
                 * owner : 临沂远华运输有限公司/一社会信用代码/1373
                 * grantName :
                 * grantNo : 鲁Q7C312
                 * carType : 重挂引车
                 * carBrand : 歌曼
                 * carModel : 42535F
                 * carColor :
                 * carNo : 36
                 * carImportType :
                 * engineNo : 473
                 * engineType :
                 * fuelType : 柴油
                 * power : 590
                 * displacement : 45
                 * maker : 北京福田截姆勒汽车有限公司后m
                 * turnType :
                 * tireDistanceAhead :
                 * tireDistanceHinder :
                 * tireCount : 8
                 * tireSpecs :
                 * springsCount :
                 * wheelbase :
                 * axlesCount :
                 * outline : 3000,4000,2001
                 * inline :
                 * allWeight :
                 * curbWeight :
                 * approvedWeight :
                 * tractionWeight :
                 * passengersCount : 1
                 * useNature : 货运
                 * carGetType :
                 * productionDate :
                 * issueDate : 2016-09-11
                 * isActive : 1
                 * createTime : 1599791842000
                 * updateTime : 1599791842000
                 * picUrl : https://aicc.ctags.cn/cccc/file/download/eb9b86f3-5730-4bae-8bd7-10925290b740.jpg
                 * departments : 山东省临沂市公安局交通警察支额,登
                 * registrationDate : 2014-09-11
                 * carId : 0
                 */

                private int id;
                private String owner;
                private String grantName;
                private String grantNo;
                private String carType;
                private String carBrand;
                private String carModel;
                private String carColor;
                private String carNo;
                private String carImportType;
                private String engineNo;
                private String engineType;
                private String fuelType;
                private String power;
                private String displacement;
                private String maker;
                private String turnType;
                private String tireDistanceAhead;
                private String tireDistanceHinder;
                private String tireCount;
                private String tireSpecs;
                private String springsCount;
                private String wheelbase;
                private String axlesCount;
                private String outline;
                private String inline;
                private String allWeight;
                private String curbWeight;
                private String approvedWeight;
                private String tractionWeight;
                private String passengersCount;
                private String useNature;
                private String carGetType;
                private String productionDate;
                private String issueDate;
                private int isActive;
                private long createTime;
                private long updateTime;
                private String picUrl;
                private String departments;
                private String registrationDate;
                private int carId;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getOwner() {
                    return owner;
                }

                public void setOwner(String owner) {
                    this.owner = owner;
                }

                public String getGrantName() {
                    return grantName;
                }

                public void setGrantName(String grantName) {
                    this.grantName = grantName;
                }

                public String getGrantNo() {
                    return grantNo;
                }

                public void setGrantNo(String grantNo) {
                    this.grantNo = grantNo;
                }

                public String getCarType() {
                    return carType;
                }

                public void setCarType(String carType) {
                    this.carType = carType;
                }

                public String getCarBrand() {
                    return carBrand;
                }

                public void setCarBrand(String carBrand) {
                    this.carBrand = carBrand;
                }

                public String getCarModel() {
                    return carModel;
                }

                public void setCarModel(String carModel) {
                    this.carModel = carModel;
                }

                public String getCarColor() {
                    return carColor;
                }

                public void setCarColor(String carColor) {
                    this.carColor = carColor;
                }

                public String getCarNo() {
                    return carNo;
                }

                public void setCarNo(String carNo) {
                    this.carNo = carNo;
                }

                public String getCarImportType() {
                    return carImportType;
                }

                public void setCarImportType(String carImportType) {
                    this.carImportType = carImportType;
                }

                public String getEngineNo() {
                    return engineNo;
                }

                public void setEngineNo(String engineNo) {
                    this.engineNo = engineNo;
                }

                public String getEngineType() {
                    return engineType;
                }

                public void setEngineType(String engineType) {
                    this.engineType = engineType;
                }

                public String getFuelType() {
                    return fuelType;
                }

                public void setFuelType(String fuelType) {
                    this.fuelType = fuelType;
                }

                public String getPower() {
                    return power;
                }

                public void setPower(String power) {
                    this.power = power;
                }

                public String getDisplacement() {
                    return displacement;
                }

                public void setDisplacement(String displacement) {
                    this.displacement = displacement;
                }

                public String getMaker() {
                    return maker;
                }

                public void setMaker(String maker) {
                    this.maker = maker;
                }

                public String getTurnType() {
                    return turnType;
                }

                public void setTurnType(String turnType) {
                    this.turnType = turnType;
                }

                public String getTireDistanceAhead() {
                    return tireDistanceAhead;
                }

                public void setTireDistanceAhead(String tireDistanceAhead) {
                    this.tireDistanceAhead = tireDistanceAhead;
                }

                public String getTireDistanceHinder() {
                    return tireDistanceHinder;
                }

                public void setTireDistanceHinder(String tireDistanceHinder) {
                    this.tireDistanceHinder = tireDistanceHinder;
                }

                public String getTireCount() {
                    return tireCount;
                }

                public void setTireCount(String tireCount) {
                    this.tireCount = tireCount;
                }

                public String getTireSpecs() {
                    return tireSpecs;
                }

                public void setTireSpecs(String tireSpecs) {
                    this.tireSpecs = tireSpecs;
                }

                public String getSpringsCount() {
                    return springsCount;
                }

                public void setSpringsCount(String springsCount) {
                    this.springsCount = springsCount;
                }

                public String getWheelbase() {
                    return wheelbase;
                }

                public void setWheelbase(String wheelbase) {
                    this.wheelbase = wheelbase;
                }

                public String getAxlesCount() {
                    return axlesCount;
                }

                public void setAxlesCount(String axlesCount) {
                    this.axlesCount = axlesCount;
                }

                public String getOutline() {
                    return outline;
                }

                public void setOutline(String outline) {
                    this.outline = outline;
                }

                public String getInline() {
                    return inline;
                }

                public void setInline(String inline) {
                    this.inline = inline;
                }

                public String getAllWeight() {
                    return allWeight;
                }

                public void setAllWeight(String allWeight) {
                    this.allWeight = allWeight;
                }

                public String getCurbWeight() {
                    return curbWeight;
                }

                public void setCurbWeight(String curbWeight) {
                    this.curbWeight = curbWeight;
                }

                public String getApprovedWeight() {
                    return approvedWeight;
                }

                public void setApprovedWeight(String approvedWeight) {
                    this.approvedWeight = approvedWeight;
                }

                public String getTractionWeight() {
                    return tractionWeight;
                }

                public void setTractionWeight(String tractionWeight) {
                    this.tractionWeight = tractionWeight;
                }

                public String getPassengersCount() {
                    return passengersCount;
                }

                public void setPassengersCount(String passengersCount) {
                    this.passengersCount = passengersCount;
                }

                public String getUseNature() {
                    return useNature;
                }

                public void setUseNature(String useNature) {
                    this.useNature = useNature;
                }

                public String getCarGetType() {
                    return carGetType;
                }

                public void setCarGetType(String carGetType) {
                    this.carGetType = carGetType;
                }

                public String getProductionDate() {
                    return productionDate;
                }

                public void setProductionDate(String productionDate) {
                    this.productionDate = productionDate;
                }

                public String getIssueDate() {
                    return issueDate;
                }

                public void setIssueDate(String issueDate) {
                    this.issueDate = issueDate;
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

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getDepartments() {
                    return departments;
                }

                public void setDepartments(String departments) {
                    this.departments = departments;
                }

                public String getRegistrationDate() {
                    return registrationDate;
                }

                public void setRegistrationDate(String registrationDate) {
                    this.registrationDate = registrationDate;
                }

                public int getCarId() {
                    return carId;
                }

                public void setCarId(int carId) {
                    this.carId = carId;
                }
            }

            public static class EnterpriseBoBean {
                /**
                 * id : 61
                 * legalPerson :
                 * signature : https://aicc.ctags.cn/cccc/file/download/ae4592fd-61fd-4511-9c19-2a3b1026be79.png
                 * sealUrl : https://aicc.ctags.cn/cccc/file/download/e0142b0e-0f78-4dd4-88a1-0a8cfcc6f717.jpg
                 * isActive : 1
                 * createTime : 1599791443000
                 * updateTime : 1599791443000
                 * certificateBusinessId : 61
                 * certificateOperationId : 61
                 * tel :
                 * adminId : 5
                 * name :
                 * certificateIDBo : {"id":173,"name":"陈超","six":"","nation":"","birthDay":"","address":"","isActive":1,"createTime":1599791443000,"updateTime":1599791443000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/9e42767c-d8f8-4172-992f-ecaf1d3f7853.jpg","picUrl2":"https://aicc.ctags.cn/cccc/file/download/0e290f42-c9c0-4ca5-972b-33bc2b2dfbb1.jpg","driverId":0,"carId":0,"carOwnerId":0,"enterpriseId":0,"idno":"371327198407041830"}
                 * certificateOperationBo : {"id":61,"grantNo":"临字371316290072号","validityDate":"2040-10-14","owner":"","address":"","nature":"","scope":"","isActive":1,"updateTime":1599791443000,"createTime":1599791443000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/eb853ca5-e2a2-4ad9-a5ac-3c70c46cfd8f.jpg","enterpriseId":0}
                 * certificateBusinessBo : {"id":61,"picUrl":"https://aicc.ctags.cn/cccc/file/download/410919bd-066c-478b-a262-628cb6235b55.jpg","name":"卓文智科(山东)物联网技术有限公司","capital":"","type":"","establishmentDate":"2020年06月24日","legalPerson":"陈培","term":"","scope":"术开发,技术转让技术咨训、技术服务销售:车载电子产技术三出口。依法短经批准的项目,相关部批准方可品机械设备、工金交电,电子产品,机械设备维修,货物及计算机软硬件的开发,精售维璧,机械设备、电了设备的技开居经营活动","address":"","registrationAuthority":"山东省临沂直兰山区双转路与宏大路交汇普西南凯歌国际文化城东北角阻歌大厦第五层501","isActive":1,"createTime":1599791443000,"updateTime":1599791443000,"enterpriseId":0}
                 * adminBo : {"id":5,"name":"运输企业有限公司","pass":"13853902691","depart":"1","isActive":1,"createTime":1599316126000,"updateTime":1599316126000,"type":3,"tel":"13853902691","oldPass":null,"enterpriseId":0}
                 * idcertificateId : 173
                 */

                private int id;
                private String legalPerson;
                private String signature;
                private String sealUrl;
                private int isActive;
                private long createTime;
                private long updateTime;
                private int certificateBusinessId;
                private int certificateOperationId;
                private String tel;
                private int adminId;
                private String name;
                private CertificateIDBoBeanX certificateIDBo;
                private CertificateOperationBoBean certificateOperationBo;
                private CertificateBusinessBoBean certificateBusinessBo;
                private AdminBoBean adminBo;
                private int idcertificateId;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getLegalPerson() {
                    return legalPerson;
                }

                public void setLegalPerson(String legalPerson) {
                    this.legalPerson = legalPerson;
                }

                public String getSignature() {
                    return signature;
                }

                public void setSignature(String signature) {
                    this.signature = signature;
                }

                public String getSealUrl() {
                    return sealUrl;
                }

                public void setSealUrl(String sealUrl) {
                    this.sealUrl = sealUrl;
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

                public int getCertificateBusinessId() {
                    return certificateBusinessId;
                }

                public void setCertificateBusinessId(int certificateBusinessId) {
                    this.certificateBusinessId = certificateBusinessId;
                }

                public int getCertificateOperationId() {
                    return certificateOperationId;
                }

                public void setCertificateOperationId(int certificateOperationId) {
                    this.certificateOperationId = certificateOperationId;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public int getAdminId() {
                    return adminId;
                }

                public void setAdminId(int adminId) {
                    this.adminId = adminId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public CertificateIDBoBeanX getCertificateIDBo() {
                    return certificateIDBo;
                }

                public void setCertificateIDBo(CertificateIDBoBeanX certificateIDBo) {
                    this.certificateIDBo = certificateIDBo;
                }

                public CertificateOperationBoBean getCertificateOperationBo() {
                    return certificateOperationBo;
                }

                public void setCertificateOperationBo(CertificateOperationBoBean certificateOperationBo) {
                    this.certificateOperationBo = certificateOperationBo;
                }

                public CertificateBusinessBoBean getCertificateBusinessBo() {
                    return certificateBusinessBo;
                }

                public void setCertificateBusinessBo(CertificateBusinessBoBean certificateBusinessBo) {
                    this.certificateBusinessBo = certificateBusinessBo;
                }

                public AdminBoBean getAdminBo() {
                    return adminBo;
                }

                public void setAdminBo(AdminBoBean adminBo) {
                    this.adminBo = adminBo;
                }

                public int getIdcertificateId() {
                    return idcertificateId;
                }

                public void setIdcertificateId(int idcertificateId) {
                    this.idcertificateId = idcertificateId;
                }

                public static class CertificateIDBoBeanX {
                    /**
                     * id : 173
                     * name : 陈超
                     * six :
                     * nation :
                     * birthDay :
                     * address :
                     * isActive : 1
                     * createTime : 1599791443000
                     * updateTime : 1599791443000
                     * picUrl : https://aicc.ctags.cn/cccc/file/download/9e42767c-d8f8-4172-992f-ecaf1d3f7853.jpg
                     * picUrl2 : https://aicc.ctags.cn/cccc/file/download/0e290f42-c9c0-4ca5-972b-33bc2b2dfbb1.jpg
                     * driverId : 0
                     * carId : 0
                     * carOwnerId : 0
                     * enterpriseId : 0
                     * idno : 371327198407041830
                     */

                    private int id;
                    private String name;
                    private String six;
                    private String nation;
                    private String birthDay;
                    private String address;
                    private int isActive;
                    private long createTime;
                    private long updateTime;
                    private String picUrl;
                    private String picUrl2;
                    private int driverId;
                    private int carId;
                    private int carOwnerId;
                    private int enterpriseId;
                    private String idno;

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

                    public String getSix() {
                        return six;
                    }

                    public void setSix(String six) {
                        this.six = six;
                    }

                    public String getNation() {
                        return nation;
                    }

                    public void setNation(String nation) {
                        this.nation = nation;
                    }

                    public String getBirthDay() {
                        return birthDay;
                    }

                    public void setBirthDay(String birthDay) {
                        this.birthDay = birthDay;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
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

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getPicUrl2() {
                        return picUrl2;
                    }

                    public void setPicUrl2(String picUrl2) {
                        this.picUrl2 = picUrl2;
                    }

                    public int getDriverId() {
                        return driverId;
                    }

                    public void setDriverId(int driverId) {
                        this.driverId = driverId;
                    }

                    public int getCarId() {
                        return carId;
                    }

                    public void setCarId(int carId) {
                        this.carId = carId;
                    }

                    public int getCarOwnerId() {
                        return carOwnerId;
                    }

                    public void setCarOwnerId(int carOwnerId) {
                        this.carOwnerId = carOwnerId;
                    }

                    public int getEnterpriseId() {
                        return enterpriseId;
                    }

                    public void setEnterpriseId(int enterpriseId) {
                        this.enterpriseId = enterpriseId;
                    }

                    public String getIdno() {
                        return idno;
                    }

                    public void setIdno(String idno) {
                        this.idno = idno;
                    }
                }

                public static class CertificateOperationBoBean {
                    /**
                     * id : 61
                     * grantNo : 临字371316290072号
                     * validityDate : 2040-10-14
                     * owner :
                     * address :
                     * nature :
                     * scope :
                     * isActive : 1
                     * updateTime : 1599791443000
                     * createTime : 1599791443000
                     * picUrl : https://aicc.ctags.cn/cccc/file/download/eb853ca5-e2a2-4ad9-a5ac-3c70c46cfd8f.jpg
                     * enterpriseId : 0
                     */

                    private int id;
                    private String grantNo;
                    private String validityDate;
                    private String owner;
                    private String address;
                    private String nature;
                    private String scope;
                    private int isActive;
                    private long updateTime;
                    private long createTime;
                    private String picUrl;
                    private int enterpriseId;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getGrantNo() {
                        return grantNo;
                    }

                    public void setGrantNo(String grantNo) {
                        this.grantNo = grantNo;
                    }

                    public String getValidityDate() {
                        return validityDate;
                    }

                    public void setValidityDate(String validityDate) {
                        this.validityDate = validityDate;
                    }

                    public String getOwner() {
                        return owner;
                    }

                    public void setOwner(String owner) {
                        this.owner = owner;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getNature() {
                        return nature;
                    }

                    public void setNature(String nature) {
                        this.nature = nature;
                    }

                    public String getScope() {
                        return scope;
                    }

                    public void setScope(String scope) {
                        this.scope = scope;
                    }

                    public int getIsActive() {
                        return isActive;
                    }

                    public void setIsActive(int isActive) {
                        this.isActive = isActive;
                    }

                    public long getUpdateTime() {
                        return updateTime;
                    }

                    public void setUpdateTime(long updateTime) {
                        this.updateTime = updateTime;
                    }

                    public long getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(long createTime) {
                        this.createTime = createTime;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public int getEnterpriseId() {
                        return enterpriseId;
                    }

                    public void setEnterpriseId(int enterpriseId) {
                        this.enterpriseId = enterpriseId;
                    }
                }

                public static class CertificateBusinessBoBean {
                    /**
                     * id : 61
                     * picUrl : https://aicc.ctags.cn/cccc/file/download/410919bd-066c-478b-a262-628cb6235b55.jpg
                     * name : 卓文智科(山东)物联网技术有限公司
                     * capital :
                     * type :
                     * establishmentDate : 2020年06月24日
                     * legalPerson : 陈培
                     * term :
                     * scope : 术开发,技术转让技术咨训、技术服务销售:车载电子产技术三出口。依法短经批准的项目,相关部批准方可品机械设备、工金交电,电子产品,机械设备维修,货物及计算机软硬件的开发,精售维璧,机械设备、电了设备的技开居经营活动
                     * address :
                     * registrationAuthority : 山东省临沂直兰山区双转路与宏大路交汇普西南凯歌国际文化城东北角阻歌大厦第五层501
                     * isActive : 1
                     * createTime : 1599791443000
                     * updateTime : 1599791443000
                     * enterpriseId : 0
                     */

                    private int id;
                    private String picUrl;
                    private String name;
                    private String capital;
                    private String type;
                    private String establishmentDate;
                    private String legalPerson;
                    private String term;
                    private String scope;
                    private String address;
                    private String registrationAuthority;
                    private int isActive;
                    private long createTime;
                    private long updateTime;
                    private int enterpriseId;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getCapital() {
                        return capital;
                    }

                    public void setCapital(String capital) {
                        this.capital = capital;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getEstablishmentDate() {
                        return establishmentDate;
                    }

                    public void setEstablishmentDate(String establishmentDate) {
                        this.establishmentDate = establishmentDate;
                    }

                    public String getLegalPerson() {
                        return legalPerson;
                    }

                    public void setLegalPerson(String legalPerson) {
                        this.legalPerson = legalPerson;
                    }

                    public String getTerm() {
                        return term;
                    }

                    public void setTerm(String term) {
                        this.term = term;
                    }

                    public String getScope() {
                        return scope;
                    }

                    public void setScope(String scope) {
                        this.scope = scope;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getRegistrationAuthority() {
                        return registrationAuthority;
                    }

                    public void setRegistrationAuthority(String registrationAuthority) {
                        this.registrationAuthority = registrationAuthority;
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

                    public int getEnterpriseId() {
                        return enterpriseId;
                    }

                    public void setEnterpriseId(int enterpriseId) {
                        this.enterpriseId = enterpriseId;
                    }
                }

                public static class AdminBoBean {
                    /**
                     * id : 5
                     * name : 运输企业有限公司
                     * pass : 13853902691
                     * depart : 1
                     * isActive : 1
                     * createTime : 1599316126000
                     * updateTime : 1599316126000
                     * type : 3
                     * tel : 13853902691
                     * oldPass : null
                     * enterpriseId : 0
                     */

                    private int id;
                    private String name;
                    private String pass;
                    private String depart;
                    private int isActive;
                    private long createTime;
                    private long updateTime;
                    private int type;
                    private String tel;
                    private Object oldPass;
                    private int enterpriseId;

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

                    public String getPass() {
                        return pass;
                    }

                    public void setPass(String pass) {
                        this.pass = pass;
                    }

                    public String getDepart() {
                        return depart;
                    }

                    public void setDepart(String depart) {
                        this.depart = depart;
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

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getTel() {
                        return tel;
                    }

                    public void setTel(String tel) {
                        this.tel = tel;
                    }

                    public Object getOldPass() {
                        return oldPass;
                    }

                    public void setOldPass(Object oldPass) {
                        this.oldPass = oldPass;
                    }

                    public int getEnterpriseId() {
                        return enterpriseId;
                    }

                    public void setEnterpriseId(int enterpriseId) {
                        this.enterpriseId = enterpriseId;
                    }
                }
            }
        }

        public static class DriverBoBean implements Serializable{
            /**
             * id : 40
             * driverCertificateId : 40
             * workCertificateId : 40
             * isActive : 1
             * createTime : 1599378324000
             * updateTime : 1599378324000
             * tel :
             * adminId : 117
             * enterpriseId : 0
             * signUrl :
             * carId : 12
             * certificateDriverBo : {"id":40,"name":"周建强","sex":"","country":"","isActive":1,"createTime":1599378324000,"updateTime":1599461660000,"address":"","birthday":"","receiveDate":"","classs":"C1","startTime":"2020-09-06","term":"","fileNumber":"232521197502108245","note":"","picUrl":"https://aicc.ctags.cn/cccc/file/download/a8e9e441-c637-493d-a584-1f3c8eab4332.jpg","picUrl2":"https://aicc.ctags.cn/cccc/file/download/9619b237-ee9f-4eb0-a066-4f4c1db5e0cb.jpg","driverId":0}
             * certificateIDBo : {"id":68,"name":"周建强","six":"","nation":"","birthDay":"","address":"","isActive":1,"createTime":1599378324000,"updateTime":1599461660000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/20d9a3f9-ed84-433d-9997-9029d1035e40.jpg","picUrl2":"https://aicc.ctags.cn/cccc/file/download/439dba88-4359-489d-a8db-51754b5a9281.jpg","driverId":0,"carId":0,"carOwnerId":0,"enterpriseId":0,"idno":"371311198607283235"}
             * certificateWorkBo : {"id":40,"name":"","sex":"","birthday":"","nationality":"","address":"","grantNo":"371311198697283235","classs":"","isActive":1,"createTime":1599378324000,"updateTime":1599461660000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/5cd06d80-30c2-43ad-aa47-7eaba0d466d1.jpg","category":"","firstTime":"2050-10-14","validityStartTime":"111","validityEndTime":"2050-10-14","fileNumber":"371311198697283235","driverId":0}
             * carBo : {"id":12,"drivingCertificateId":12,"tel":"","transportCertificateId":12,"registrationCertificateId":12,"isActive":1,"createTime":1599326220000,"updateTime":1599326220000,"adminId":40,"enterpriseId":5,"isPass":1,"carOnwerId":0,"certificateDrivingBo":{"id":12,"plateNo":"鲁Q17G(黄-","wchicheType":"重型集装箱半挂车","owner":"临沂某某运输企业","address":"","useCharacter":"非营运","model":"丰田牌ZVV482TJZA","engineNo":"SFSF23242","registrationDate":"2014-04-11","issueDate":"2016-11-09","fileNo":"3333","passengersCount":0,"allWeight":"333","curbWeight":"","approvedWeight":"333","outline":"","tractionWeight":"","note":"","chechRecord":"","isActive":1,"updateTime":1599326220000,"createTime":1599326220000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/f7886d7b-bad9-4eea-bda4-da83e864d11e.jpg","carId":0,"vin":""},"certificateIDBo":null,"certificateTransportBo":{"id":12,"name":"一临沂兴盛运输有限公司-","address":"","plateNo":"临字371316002277号","licence":"","type":"","weight":"","size":"","scope":"货物专用运输(藏保鲜设备)","grantDate":"2020-09-06","isActive":1,"createTime":1599326220000,"updateTime":1599326220000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/9cfeaa77-9c4e-44c0-99c5-97dd7fc86474.jpg","transportNo":"","validityDate":null,"carId":0},"certificateRegistrationBo":{"id":12,"owner":"县通达物流有限公司/组织机构代码证书/5343835","grantName":"","grantNo":"","carType":"型半挂牵引车","carBrand":"解册","carModel":"CA4206PIA2I3EA88","carColor":"","carNo":"解册","carImportType":"","engineNo":"253358","engineType":"","fuelType":"柴油","power":"kw","displacement":"kw","maker":"汽解放青岛汽车有限公司","turnType":"","tireDistanceAhead":"","tireDistanceHinder":"","tireCount":"33","tireSpecs":"","springsCount":"","wheelbase":"","axlesCount":"","outline":"","inline":"","allWeight":"","curbWeight":"","approvedWeight":"","tractionWeight":"","passengersCount":"9","useNature":"货运","carGetType":"","productionDate":"","issueDate":"2020-09-06","isActive":1,"createTime":1599326220000,"updateTime":1599326220000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/8cf444e6-1522-4ef1-8eca-4d2df47b30e1.jpg","departments":"","registrationDate":"","carId":0},"enterpriseBo":{"id":5,"legalPerson":"","signature":"http://aicc.ctags.cn/cccc/file/download/","sealUrl":"https://aicc.ctags.cn/cccc/file/download/f99c981a-738f-4691-b4c2-94cc466f0a71.jpg","isActive":1,"createTime":1599318689000,"updateTime":1599318689000,"certificateBusinessId":5,"certificateOperationId":5,"tel":"","adminId":null,"name":"","certificateIDBo":{"id":26,"name":"郁友亮","six":"","nation":"","birthDay":"","address":"","isActive":1,"createTime":1599318688000,"updateTime":1599318688000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/d91f0fe0-94fa-43ea-b5a7-330088dfb4cc.jpg","picUrl2":"https://aicc.ctags.cn/cccc/file/download/9646a2e5-ecf7-499f-a039-938b9ffafb21.jpg","driverId":0,"carId":0,"carOwnerId":0,"enterpriseId":0,"idno":"37280119770821511X"},"certificateOperationBo":{"id":5,"grantNo":"临字71316290072号","validityDate":"2023","owner":"","address":"","nature":"","scope":"","isActive":1,"updateTime":1599318689000,"createTime":1599318689000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/0f4b3f29-3256-4af1-a1c0-4c9e5e2deac8.jpg","enterpriseId":0},"certificateBusinessBo":{"id":5,"picUrl":"https://aicc.ctags.cn/cccc/file/download/995071cd-1b55-40e3-90f0-11699ed4e0f5.jpg","name":"临沂兴喜运描有限公司","capital":"","type":"","establishmentDate":"2015年06月16日","legalPerson":"郁友亮","term":"","scope":"海+训候,货物圈运服务:销售:汽车,汽车配洋,(依流须普批准的项H,经相关容门批率稻方可开碳经营活动)鲁还切验运偷,售物书面运输(参装题冷藏保鲜,域式)","address":"","registrationAuthority":"山东省借当市经落开发区芝麻给害语办事处看间路116号值信物出近内D3-12号","isActive":1,"createTime":1599318689000,"updateTime":1599318689000,"enterpriseId":0},"adminBo":null,"idcertificateId":26},"idcertificateId":0}
             * sameDriverName : 郁友亮
             * trainDriverDataBos : []
             * idcertificateId : 68
             */

            private int id;
            private int driverCertificateId;
            private int workCertificateId;
            private int isActive;
            private long createTime;
            private long updateTime;
            private String tel;
            private int adminId;
            private int enterpriseId;
            private String signUrl;
            private int carId;
            private CertificateDriverBoBean certificateDriverBo;
            private CertificateIDBoBeanXX certificateIDBo;
            private CertificateWorkBoBean certificateWorkBo;
            private CarBoBeanX carBo;
            private String sameDriverName;
            private int idcertificateId;
            private List<?> trainDriverDataBos;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDriverCertificateId() {
                return driverCertificateId;
            }

            public void setDriverCertificateId(int driverCertificateId) {
                this.driverCertificateId = driverCertificateId;
            }

            public int getWorkCertificateId() {
                return workCertificateId;
            }

            public void setWorkCertificateId(int workCertificateId) {
                this.workCertificateId = workCertificateId;
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

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public int getAdminId() {
                return adminId;
            }

            public void setAdminId(int adminId) {
                this.adminId = adminId;
            }

            public int getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(int enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public String getSignUrl() {
                return signUrl;
            }

            public void setSignUrl(String signUrl) {
                this.signUrl = signUrl;
            }

            public int getCarId() {
                return carId;
            }

            public void setCarId(int carId) {
                this.carId = carId;
            }

            public CertificateDriverBoBean getCertificateDriverBo() {
                return certificateDriverBo;
            }

            public void setCertificateDriverBo(CertificateDriverBoBean certificateDriverBo) {
                this.certificateDriverBo = certificateDriverBo;
            }

            public CertificateIDBoBeanXX getCertificateIDBo() {
                return certificateIDBo;
            }

            public void setCertificateIDBo(CertificateIDBoBeanXX certificateIDBo) {
                this.certificateIDBo = certificateIDBo;
            }

            public CertificateWorkBoBean getCertificateWorkBo() {
                return certificateWorkBo;
            }

            public void setCertificateWorkBo(CertificateWorkBoBean certificateWorkBo) {
                this.certificateWorkBo = certificateWorkBo;
            }

            public CarBoBeanX getCarBo() {
                return carBo;
            }

            public void setCarBo(CarBoBeanX carBo) {
                this.carBo = carBo;
            }

            public String getSameDriverName() {
                return sameDriverName;
            }

            public void setSameDriverName(String sameDriverName) {
                this.sameDriverName = sameDriverName;
            }

            public int getIdcertificateId() {
                return idcertificateId;
            }

            public void setIdcertificateId(int idcertificateId) {
                this.idcertificateId = idcertificateId;
            }

            public List<?> getTrainDriverDataBos() {
                return trainDriverDataBos;
            }

            public void setTrainDriverDataBos(List<?> trainDriverDataBos) {
                this.trainDriverDataBos = trainDriverDataBos;
            }

            public static class CertificateDriverBoBean {
                /**
                 * id : 40
                 * name : 周建强
                 * sex :
                 * country :
                 * isActive : 1
                 * createTime : 1599378324000
                 * updateTime : 1599461660000
                 * address :
                 * birthday :
                 * receiveDate :
                 * classs : C1
                 * startTime : 2020-09-06
                 * term :
                 * fileNumber : 232521197502108245
                 * note :
                 * picUrl : https://aicc.ctags.cn/cccc/file/download/a8e9e441-c637-493d-a584-1f3c8eab4332.jpg
                 * picUrl2 : https://aicc.ctags.cn/cccc/file/download/9619b237-ee9f-4eb0-a066-4f4c1db5e0cb.jpg
                 * driverId : 0
                 */

                private int id;
                private String name;
                private String sex;
                private String country;
                private int isActive;
                private long createTime;
                private long updateTime;
                private String address;
                private String birthday;
                private String receiveDate;
                private String classs;
                private String startTime;
                private String term;
                private String fileNumber;
                private String note;
                private String picUrl;
                private String picUrl2;
                private int driverId;

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

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
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

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public String getReceiveDate() {
                    return receiveDate;
                }

                public void setReceiveDate(String receiveDate) {
                    this.receiveDate = receiveDate;
                }

                public String getClasss() {
                    return classs;
                }

                public void setClasss(String classs) {
                    this.classs = classs;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getTerm() {
                    return term;
                }

                public void setTerm(String term) {
                    this.term = term;
                }

                public String getFileNumber() {
                    return fileNumber;
                }

                public void setFileNumber(String fileNumber) {
                    this.fileNumber = fileNumber;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getPicUrl2() {
                    return picUrl2;
                }

                public void setPicUrl2(String picUrl2) {
                    this.picUrl2 = picUrl2;
                }

                public int getDriverId() {
                    return driverId;
                }

                public void setDriverId(int driverId) {
                    this.driverId = driverId;
                }
            }

            public static class CertificateIDBoBeanXX {
                /**
                 * id : 68
                 * name : 周建强
                 * six :
                 * nation :
                 * birthDay :
                 * address :
                 * isActive : 1
                 * createTime : 1599378324000
                 * updateTime : 1599461660000
                 * picUrl : https://aicc.ctags.cn/cccc/file/download/20d9a3f9-ed84-433d-9997-9029d1035e40.jpg
                 * picUrl2 : https://aicc.ctags.cn/cccc/file/download/439dba88-4359-489d-a8db-51754b5a9281.jpg
                 * driverId : 0
                 * carId : 0
                 * carOwnerId : 0
                 * enterpriseId : 0
                 * idno : 371311198607283235
                 */

                private int id;
                private String name;
                private String six;
                private String nation;
                private String birthDay;
                private String address;
                private int isActive;
                private long createTime;
                private long updateTime;
                private String picUrl;
                private String picUrl2;
                private int driverId;
                private int carId;
                private int carOwnerId;
                private int enterpriseId;
                private String idno;

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

                public String getSix() {
                    return six;
                }

                public void setSix(String six) {
                    this.six = six;
                }

                public String getNation() {
                    return nation;
                }

                public void setNation(String nation) {
                    this.nation = nation;
                }

                public String getBirthDay() {
                    return birthDay;
                }

                public void setBirthDay(String birthDay) {
                    this.birthDay = birthDay;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
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

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getPicUrl2() {
                    return picUrl2;
                }

                public void setPicUrl2(String picUrl2) {
                    this.picUrl2 = picUrl2;
                }

                public int getDriverId() {
                    return driverId;
                }

                public void setDriverId(int driverId) {
                    this.driverId = driverId;
                }

                public int getCarId() {
                    return carId;
                }

                public void setCarId(int carId) {
                    this.carId = carId;
                }

                public int getCarOwnerId() {
                    return carOwnerId;
                }

                public void setCarOwnerId(int carOwnerId) {
                    this.carOwnerId = carOwnerId;
                }

                public int getEnterpriseId() {
                    return enterpriseId;
                }

                public void setEnterpriseId(int enterpriseId) {
                    this.enterpriseId = enterpriseId;
                }

                public String getIdno() {
                    return idno;
                }

                public void setIdno(String idno) {
                    this.idno = idno;
                }
            }

            public static class CertificateWorkBoBean {
                /**
                 * id : 40
                 * name :
                 * sex :
                 * birthday :
                 * nationality :
                 * address :
                 * grantNo : 371311198697283235
                 * classs :
                 * isActive : 1
                 * createTime : 1599378324000
                 * updateTime : 1599461660000
                 * picUrl : https://aicc.ctags.cn/cccc/file/download/5cd06d80-30c2-43ad-aa47-7eaba0d466d1.jpg
                 * category :
                 * firstTime : 2050-10-14
                 * validityStartTime : 111
                 * validityEndTime : 2050-10-14
                 * fileNumber : 371311198697283235
                 * driverId : 0
                 */

                private int id;
                private String name;
                private String sex;
                private String birthday;
                private String nationality;
                private String address;
                private String grantNo;
                private String classs;
                private int isActive;
                private long createTime;
                private long updateTime;
                private String picUrl;
                private String category;
                private String firstTime;
                private String validityStartTime;
                private String validityEndTime;
                private String fileNumber;
                private int driverId;

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

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public String getNationality() {
                    return nationality;
                }

                public void setNationality(String nationality) {
                    this.nationality = nationality;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getGrantNo() {
                    return grantNo;
                }

                public void setGrantNo(String grantNo) {
                    this.grantNo = grantNo;
                }

                public String getClasss() {
                    return classs;
                }

                public void setClasss(String classs) {
                    this.classs = classs;
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

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getFirstTime() {
                    return firstTime;
                }

                public void setFirstTime(String firstTime) {
                    this.firstTime = firstTime;
                }

                public String getValidityStartTime() {
                    return validityStartTime;
                }

                public void setValidityStartTime(String validityStartTime) {
                    this.validityStartTime = validityStartTime;
                }

                public String getValidityEndTime() {
                    return validityEndTime;
                }

                public void setValidityEndTime(String validityEndTime) {
                    this.validityEndTime = validityEndTime;
                }

                public String getFileNumber() {
                    return fileNumber;
                }

                public void setFileNumber(String fileNumber) {
                    this.fileNumber = fileNumber;
                }

                public int getDriverId() {
                    return driverId;
                }

                public void setDriverId(int driverId) {
                    this.driverId = driverId;
                }
            }

            public static class CarBoBeanX {
                /**
                 * id : 12
                 * drivingCertificateId : 12
                 * tel :
                 * transportCertificateId : 12
                 * registrationCertificateId : 12
                 * isActive : 1
                 * createTime : 1599326220000
                 * updateTime : 1599326220000
                 * adminId : 40
                 * enterpriseId : 5
                 * isPass : 1
                 * carOnwerId : 0
                 * certificateDrivingBo : {"id":12,"plateNo":"鲁Q17G(黄-","wchicheType":"重型集装箱半挂车","owner":"临沂某某运输企业","address":"","useCharacter":"非营运","model":"丰田牌ZVV482TJZA","engineNo":"SFSF23242","registrationDate":"2014-04-11","issueDate":"2016-11-09","fileNo":"3333","passengersCount":0,"allWeight":"333","curbWeight":"","approvedWeight":"333","outline":"","tractionWeight":"","note":"","chechRecord":"","isActive":1,"updateTime":1599326220000,"createTime":1599326220000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/f7886d7b-bad9-4eea-bda4-da83e864d11e.jpg","carId":0,"vin":""}
                 * certificateIDBo : null
                 * certificateTransportBo : {"id":12,"name":"一临沂兴盛运输有限公司-","address":"","plateNo":"临字371316002277号","licence":"","type":"","weight":"","size":"","scope":"货物专用运输(藏保鲜设备)","grantDate":"2020-09-06","isActive":1,"createTime":1599326220000,"updateTime":1599326220000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/9cfeaa77-9c4e-44c0-99c5-97dd7fc86474.jpg","transportNo":"","validityDate":null,"carId":0}
                 * certificateRegistrationBo : {"id":12,"owner":"县通达物流有限公司/组织机构代码证书/5343835","grantName":"","grantNo":"","carType":"型半挂牵引车","carBrand":"解册","carModel":"CA4206PIA2I3EA88","carColor":"","carNo":"解册","carImportType":"","engineNo":"253358","engineType":"","fuelType":"柴油","power":"kw","displacement":"kw","maker":"汽解放青岛汽车有限公司","turnType":"","tireDistanceAhead":"","tireDistanceHinder":"","tireCount":"33","tireSpecs":"","springsCount":"","wheelbase":"","axlesCount":"","outline":"","inline":"","allWeight":"","curbWeight":"","approvedWeight":"","tractionWeight":"","passengersCount":"9","useNature":"货运","carGetType":"","productionDate":"","issueDate":"2020-09-06","isActive":1,"createTime":1599326220000,"updateTime":1599326220000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/8cf444e6-1522-4ef1-8eca-4d2df47b30e1.jpg","departments":"","registrationDate":"","carId":0}
                 * enterpriseBo : {"id":5,"legalPerson":"","signature":"http://aicc.ctags.cn/cccc/file/download/","sealUrl":"https://aicc.ctags.cn/cccc/file/download/f99c981a-738f-4691-b4c2-94cc466f0a71.jpg","isActive":1,"createTime":1599318689000,"updateTime":1599318689000,"certificateBusinessId":5,"certificateOperationId":5,"tel":"","adminId":null,"name":"","certificateIDBo":{"id":26,"name":"郁友亮","six":"","nation":"","birthDay":"","address":"","isActive":1,"createTime":1599318688000,"updateTime":1599318688000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/d91f0fe0-94fa-43ea-b5a7-330088dfb4cc.jpg","picUrl2":"https://aicc.ctags.cn/cccc/file/download/9646a2e5-ecf7-499f-a039-938b9ffafb21.jpg","driverId":0,"carId":0,"carOwnerId":0,"enterpriseId":0,"idno":"37280119770821511X"},"certificateOperationBo":{"id":5,"grantNo":"临字71316290072号","validityDate":"2023","owner":"","address":"","nature":"","scope":"","isActive":1,"updateTime":1599318689000,"createTime":1599318689000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/0f4b3f29-3256-4af1-a1c0-4c9e5e2deac8.jpg","enterpriseId":0},"certificateBusinessBo":{"id":5,"picUrl":"https://aicc.ctags.cn/cccc/file/download/995071cd-1b55-40e3-90f0-11699ed4e0f5.jpg","name":"临沂兴喜运描有限公司","capital":"","type":"","establishmentDate":"2015年06月16日","legalPerson":"郁友亮","term":"","scope":"海+训候,货物圈运服务:销售:汽车,汽车配洋,(依流须普批准的项H,经相关容门批率稻方可开碳经营活动)鲁还切验运偷,售物书面运输(参装题冷藏保鲜,域式)","address":"","registrationAuthority":"山东省借当市经落开发区芝麻给害语办事处看间路116号值信物出近内D3-12号","isActive":1,"createTime":1599318689000,"updateTime":1599318689000,"enterpriseId":0},"adminBo":null,"idcertificateId":26}
                 * idcertificateId : 0
                 */

                private int id;
                private int drivingCertificateId;
                private String tel;
                private int transportCertificateId;
                private int registrationCertificateId;
                private int isActive;
                private long createTime;
                private long updateTime;
                private int adminId;
                private int enterpriseId;
                private int isPass;
                private int carOnwerId;
                private CertificateDrivingBoBeanX certificateDrivingBo;
                private Object certificateIDBo;
                private CertificateTransportBoBeanX certificateTransportBo;
                private CertificateRegistrationBoBeanX certificateRegistrationBo;
                private EnterpriseBoBeanX enterpriseBo;
                private int idcertificateId;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getDrivingCertificateId() {
                    return drivingCertificateId;
                }

                public void setDrivingCertificateId(int drivingCertificateId) {
                    this.drivingCertificateId = drivingCertificateId;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public int getTransportCertificateId() {
                    return transportCertificateId;
                }

                public void setTransportCertificateId(int transportCertificateId) {
                    this.transportCertificateId = transportCertificateId;
                }

                public int getRegistrationCertificateId() {
                    return registrationCertificateId;
                }

                public void setRegistrationCertificateId(int registrationCertificateId) {
                    this.registrationCertificateId = registrationCertificateId;
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

                public int getAdminId() {
                    return adminId;
                }

                public void setAdminId(int adminId) {
                    this.adminId = adminId;
                }

                public int getEnterpriseId() {
                    return enterpriseId;
                }

                public void setEnterpriseId(int enterpriseId) {
                    this.enterpriseId = enterpriseId;
                }

                public int getIsPass() {
                    return isPass;
                }

                public void setIsPass(int isPass) {
                    this.isPass = isPass;
                }

                public int getCarOnwerId() {
                    return carOnwerId;
                }

                public void setCarOnwerId(int carOnwerId) {
                    this.carOnwerId = carOnwerId;
                }

                public CertificateDrivingBoBeanX getCertificateDrivingBo() {
                    return certificateDrivingBo;
                }

                public void setCertificateDrivingBo(CertificateDrivingBoBeanX certificateDrivingBo) {
                    this.certificateDrivingBo = certificateDrivingBo;
                }

                public Object getCertificateIDBo() {
                    return certificateIDBo;
                }

                public void setCertificateIDBo(Object certificateIDBo) {
                    this.certificateIDBo = certificateIDBo;
                }

                public CertificateTransportBoBeanX getCertificateTransportBo() {
                    return certificateTransportBo;
                }

                public void setCertificateTransportBo(CertificateTransportBoBeanX certificateTransportBo) {
                    this.certificateTransportBo = certificateTransportBo;
                }

                public CertificateRegistrationBoBeanX getCertificateRegistrationBo() {
                    return certificateRegistrationBo;
                }

                public void setCertificateRegistrationBo(CertificateRegistrationBoBeanX certificateRegistrationBo) {
                    this.certificateRegistrationBo = certificateRegistrationBo;
                }

                public EnterpriseBoBeanX getEnterpriseBo() {
                    return enterpriseBo;
                }

                public void setEnterpriseBo(EnterpriseBoBeanX enterpriseBo) {
                    this.enterpriseBo = enterpriseBo;
                }

                public int getIdcertificateId() {
                    return idcertificateId;
                }

                public void setIdcertificateId(int idcertificateId) {
                    this.idcertificateId = idcertificateId;
                }

                public static class CertificateDrivingBoBeanX {
                    /**
                     * id : 12
                     * plateNo : 鲁Q17G(黄-
                     * wchicheType : 重型集装箱半挂车
                     * owner : 临沂某某运输企业
                     * address :
                     * useCharacter : 非营运
                     * model : 丰田牌ZVV482TJZA
                     * engineNo : SFSF23242
                     * registrationDate : 2014-04-11
                     * issueDate : 2016-11-09
                     * fileNo : 3333
                     * passengersCount : 0
                     * allWeight : 333
                     * curbWeight :
                     * approvedWeight : 333
                     * outline :
                     * tractionWeight :
                     * note :
                     * chechRecord :
                     * isActive : 1
                     * updateTime : 1599326220000
                     * createTime : 1599326220000
                     * picUrl : https://aicc.ctags.cn/cccc/file/download/f7886d7b-bad9-4eea-bda4-da83e864d11e.jpg
                     * carId : 0
                     * vin :
                     */

                    private int id;
                    private String plateNo;
                    private String wchicheType;
                    private String owner;
                    private String address;
                    private String useCharacter;
                    private String model;
                    private String engineNo;
                    private String registrationDate;
                    private String issueDate;
                    private String fileNo;
                    private int passengersCount;
                    private String allWeight;
                    private String curbWeight;
                    private String approvedWeight;
                    private String outline;
                    private String tractionWeight;
                    private String note;
                    private String chechRecord;
                    private int isActive;
                    private long updateTime;
                    private long createTime;
                    private String picUrl;
                    private int carId;
                    private String vin;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getPlateNo() {
                        return plateNo;
                    }

                    public void setPlateNo(String plateNo) {
                        this.plateNo = plateNo;
                    }

                    public String getWchicheType() {
                        return wchicheType;
                    }

                    public void setWchicheType(String wchicheType) {
                        this.wchicheType = wchicheType;
                    }

                    public String getOwner() {
                        return owner;
                    }

                    public void setOwner(String owner) {
                        this.owner = owner;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getUseCharacter() {
                        return useCharacter;
                    }

                    public void setUseCharacter(String useCharacter) {
                        this.useCharacter = useCharacter;
                    }

                    public String getModel() {
                        return model;
                    }

                    public void setModel(String model) {
                        this.model = model;
                    }

                    public String getEngineNo() {
                        return engineNo;
                    }

                    public void setEngineNo(String engineNo) {
                        this.engineNo = engineNo;
                    }

                    public String getRegistrationDate() {
                        return registrationDate;
                    }

                    public void setRegistrationDate(String registrationDate) {
                        this.registrationDate = registrationDate;
                    }

                    public String getIssueDate() {
                        return issueDate;
                    }

                    public void setIssueDate(String issueDate) {
                        this.issueDate = issueDate;
                    }

                    public String getFileNo() {
                        return fileNo;
                    }

                    public void setFileNo(String fileNo) {
                        this.fileNo = fileNo;
                    }

                    public int getPassengersCount() {
                        return passengersCount;
                    }

                    public void setPassengersCount(int passengersCount) {
                        this.passengersCount = passengersCount;
                    }

                    public String getAllWeight() {
                        return allWeight;
                    }

                    public void setAllWeight(String allWeight) {
                        this.allWeight = allWeight;
                    }

                    public String getCurbWeight() {
                        return curbWeight;
                    }

                    public void setCurbWeight(String curbWeight) {
                        this.curbWeight = curbWeight;
                    }

                    public String getApprovedWeight() {
                        return approvedWeight;
                    }

                    public void setApprovedWeight(String approvedWeight) {
                        this.approvedWeight = approvedWeight;
                    }

                    public String getOutline() {
                        return outline;
                    }

                    public void setOutline(String outline) {
                        this.outline = outline;
                    }

                    public String getTractionWeight() {
                        return tractionWeight;
                    }

                    public void setTractionWeight(String tractionWeight) {
                        this.tractionWeight = tractionWeight;
                    }

                    public String getNote() {
                        return note;
                    }

                    public void setNote(String note) {
                        this.note = note;
                    }

                    public String getChechRecord() {
                        return chechRecord;
                    }

                    public void setChechRecord(String chechRecord) {
                        this.chechRecord = chechRecord;
                    }

                    public int getIsActive() {
                        return isActive;
                    }

                    public void setIsActive(int isActive) {
                        this.isActive = isActive;
                    }

                    public long getUpdateTime() {
                        return updateTime;
                    }

                    public void setUpdateTime(long updateTime) {
                        this.updateTime = updateTime;
                    }

                    public long getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(long createTime) {
                        this.createTime = createTime;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public int getCarId() {
                        return carId;
                    }

                    public void setCarId(int carId) {
                        this.carId = carId;
                    }

                    public String getVin() {
                        return vin;
                    }

                    public void setVin(String vin) {
                        this.vin = vin;
                    }
                }

                public static class CertificateTransportBoBeanX {
                    /**
                     * id : 12
                     * name : 一临沂兴盛运输有限公司-
                     * address :
                     * plateNo : 临字371316002277号
                     * licence :
                     * type :
                     * weight :
                     * size :
                     * scope : 货物专用运输(藏保鲜设备)
                     * grantDate : 2020-09-06
                     * isActive : 1
                     * createTime : 1599326220000
                     * updateTime : 1599326220000
                     * picUrl : https://aicc.ctags.cn/cccc/file/download/9cfeaa77-9c4e-44c0-99c5-97dd7fc86474.jpg
                     * transportNo :
                     * validityDate : null
                     * carId : 0
                     */

                    private int id;
                    private String name;
                    private String address;
                    private String plateNo;
                    private String licence;
                    private String type;
                    private String weight;
                    private String size;
                    private String scope;
                    private String grantDate;
                    private int isActive;
                    private long createTime;
                    private long updateTime;
                    private String picUrl;
                    private String transportNo;
                    private Object validityDate;
                    private int carId;

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

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getPlateNo() {
                        return plateNo;
                    }

                    public void setPlateNo(String plateNo) {
                        this.plateNo = plateNo;
                    }

                    public String getLicence() {
                        return licence;
                    }

                    public void setLicence(String licence) {
                        this.licence = licence;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getWeight() {
                        return weight;
                    }

                    public void setWeight(String weight) {
                        this.weight = weight;
                    }

                    public String getSize() {
                        return size;
                    }

                    public void setSize(String size) {
                        this.size = size;
                    }

                    public String getScope() {
                        return scope;
                    }

                    public void setScope(String scope) {
                        this.scope = scope;
                    }

                    public String getGrantDate() {
                        return grantDate;
                    }

                    public void setGrantDate(String grantDate) {
                        this.grantDate = grantDate;
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

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getTransportNo() {
                        return transportNo;
                    }

                    public void setTransportNo(String transportNo) {
                        this.transportNo = transportNo;
                    }

                    public Object getValidityDate() {
                        return validityDate;
                    }

                    public void setValidityDate(Object validityDate) {
                        this.validityDate = validityDate;
                    }

                    public int getCarId() {
                        return carId;
                    }

                    public void setCarId(int carId) {
                        this.carId = carId;
                    }
                }

                public static class CertificateRegistrationBoBeanX {
                    /**
                     * id : 12
                     * owner : 县通达物流有限公司/组织机构代码证书/5343835
                     * grantName :
                     * grantNo :
                     * carType : 型半挂牵引车
                     * carBrand : 解册
                     * carModel : CA4206PIA2I3EA88
                     * carColor :
                     * carNo : 解册
                     * carImportType :
                     * engineNo : 253358
                     * engineType :
                     * fuelType : 柴油
                     * power : kw
                     * displacement : kw
                     * maker : 汽解放青岛汽车有限公司
                     * turnType :
                     * tireDistanceAhead :
                     * tireDistanceHinder :
                     * tireCount : 33
                     * tireSpecs :
                     * springsCount :
                     * wheelbase :
                     * axlesCount :
                     * outline :
                     * inline :
                     * allWeight :
                     * curbWeight :
                     * approvedWeight :
                     * tractionWeight :
                     * passengersCount : 9
                     * useNature : 货运
                     * carGetType :
                     * productionDate :
                     * issueDate : 2020-09-06
                     * isActive : 1
                     * createTime : 1599326220000
                     * updateTime : 1599326220000
                     * picUrl : https://aicc.ctags.cn/cccc/file/download/8cf444e6-1522-4ef1-8eca-4d2df47b30e1.jpg
                     * departments :
                     * registrationDate :
                     * carId : 0
                     */

                    private int id;
                    private String owner;
                    private String grantName;
                    private String grantNo;
                    private String carType;
                    private String carBrand;
                    private String carModel;
                    private String carColor;
                    private String carNo;
                    private String carImportType;
                    private String engineNo;
                    private String engineType;
                    private String fuelType;
                    private String power;
                    private String displacement;
                    private String maker;
                    private String turnType;
                    private String tireDistanceAhead;
                    private String tireDistanceHinder;
                    private String tireCount;
                    private String tireSpecs;
                    private String springsCount;
                    private String wheelbase;
                    private String axlesCount;
                    private String outline;
                    private String inline;
                    private String allWeight;
                    private String curbWeight;
                    private String approvedWeight;
                    private String tractionWeight;
                    private String passengersCount;
                    private String useNature;
                    private String carGetType;
                    private String productionDate;
                    private String issueDate;
                    private int isActive;
                    private long createTime;
                    private long updateTime;
                    private String picUrl;
                    private String departments;
                    private String registrationDate;
                    private int carId;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getOwner() {
                        return owner;
                    }

                    public void setOwner(String owner) {
                        this.owner = owner;
                    }

                    public String getGrantName() {
                        return grantName;
                    }

                    public void setGrantName(String grantName) {
                        this.grantName = grantName;
                    }

                    public String getGrantNo() {
                        return grantNo;
                    }

                    public void setGrantNo(String grantNo) {
                        this.grantNo = grantNo;
                    }

                    public String getCarType() {
                        return carType;
                    }

                    public void setCarType(String carType) {
                        this.carType = carType;
                    }

                    public String getCarBrand() {
                        return carBrand;
                    }

                    public void setCarBrand(String carBrand) {
                        this.carBrand = carBrand;
                    }

                    public String getCarModel() {
                        return carModel;
                    }

                    public void setCarModel(String carModel) {
                        this.carModel = carModel;
                    }

                    public String getCarColor() {
                        return carColor;
                    }

                    public void setCarColor(String carColor) {
                        this.carColor = carColor;
                    }

                    public String getCarNo() {
                        return carNo;
                    }

                    public void setCarNo(String carNo) {
                        this.carNo = carNo;
                    }

                    public String getCarImportType() {
                        return carImportType;
                    }

                    public void setCarImportType(String carImportType) {
                        this.carImportType = carImportType;
                    }

                    public String getEngineNo() {
                        return engineNo;
                    }

                    public void setEngineNo(String engineNo) {
                        this.engineNo = engineNo;
                    }

                    public String getEngineType() {
                        return engineType;
                    }

                    public void setEngineType(String engineType) {
                        this.engineType = engineType;
                    }

                    public String getFuelType() {
                        return fuelType;
                    }

                    public void setFuelType(String fuelType) {
                        this.fuelType = fuelType;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }

                    public String getDisplacement() {
                        return displacement;
                    }

                    public void setDisplacement(String displacement) {
                        this.displacement = displacement;
                    }

                    public String getMaker() {
                        return maker;
                    }

                    public void setMaker(String maker) {
                        this.maker = maker;
                    }

                    public String getTurnType() {
                        return turnType;
                    }

                    public void setTurnType(String turnType) {
                        this.turnType = turnType;
                    }

                    public String getTireDistanceAhead() {
                        return tireDistanceAhead;
                    }

                    public void setTireDistanceAhead(String tireDistanceAhead) {
                        this.tireDistanceAhead = tireDistanceAhead;
                    }

                    public String getTireDistanceHinder() {
                        return tireDistanceHinder;
                    }

                    public void setTireDistanceHinder(String tireDistanceHinder) {
                        this.tireDistanceHinder = tireDistanceHinder;
                    }

                    public String getTireCount() {
                        return tireCount;
                    }

                    public void setTireCount(String tireCount) {
                        this.tireCount = tireCount;
                    }

                    public String getTireSpecs() {
                        return tireSpecs;
                    }

                    public void setTireSpecs(String tireSpecs) {
                        this.tireSpecs = tireSpecs;
                    }

                    public String getSpringsCount() {
                        return springsCount;
                    }

                    public void setSpringsCount(String springsCount) {
                        this.springsCount = springsCount;
                    }

                    public String getWheelbase() {
                        return wheelbase;
                    }

                    public void setWheelbase(String wheelbase) {
                        this.wheelbase = wheelbase;
                    }

                    public String getAxlesCount() {
                        return axlesCount;
                    }

                    public void setAxlesCount(String axlesCount) {
                        this.axlesCount = axlesCount;
                    }

                    public String getOutline() {
                        return outline;
                    }

                    public void setOutline(String outline) {
                        this.outline = outline;
                    }

                    public String getInline() {
                        return inline;
                    }

                    public void setInline(String inline) {
                        this.inline = inline;
                    }

                    public String getAllWeight() {
                        return allWeight;
                    }

                    public void setAllWeight(String allWeight) {
                        this.allWeight = allWeight;
                    }

                    public String getCurbWeight() {
                        return curbWeight;
                    }

                    public void setCurbWeight(String curbWeight) {
                        this.curbWeight = curbWeight;
                    }

                    public String getApprovedWeight() {
                        return approvedWeight;
                    }

                    public void setApprovedWeight(String approvedWeight) {
                        this.approvedWeight = approvedWeight;
                    }

                    public String getTractionWeight() {
                        return tractionWeight;
                    }

                    public void setTractionWeight(String tractionWeight) {
                        this.tractionWeight = tractionWeight;
                    }

                    public String getPassengersCount() {
                        return passengersCount;
                    }

                    public void setPassengersCount(String passengersCount) {
                        this.passengersCount = passengersCount;
                    }

                    public String getUseNature() {
                        return useNature;
                    }

                    public void setUseNature(String useNature) {
                        this.useNature = useNature;
                    }

                    public String getCarGetType() {
                        return carGetType;
                    }

                    public void setCarGetType(String carGetType) {
                        this.carGetType = carGetType;
                    }

                    public String getProductionDate() {
                        return productionDate;
                    }

                    public void setProductionDate(String productionDate) {
                        this.productionDate = productionDate;
                    }

                    public String getIssueDate() {
                        return issueDate;
                    }

                    public void setIssueDate(String issueDate) {
                        this.issueDate = issueDate;
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

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getDepartments() {
                        return departments;
                    }

                    public void setDepartments(String departments) {
                        this.departments = departments;
                    }

                    public String getRegistrationDate() {
                        return registrationDate;
                    }

                    public void setRegistrationDate(String registrationDate) {
                        this.registrationDate = registrationDate;
                    }

                    public int getCarId() {
                        return carId;
                    }

                    public void setCarId(int carId) {
                        this.carId = carId;
                    }
                }

                public static class EnterpriseBoBeanX {
                    /**
                     * id : 5
                     * legalPerson :
                     * signature : http://aicc.ctags.cn/cccc/file/download/
                     * sealUrl : https://aicc.ctags.cn/cccc/file/download/f99c981a-738f-4691-b4c2-94cc466f0a71.jpg
                     * isActive : 1
                     * createTime : 1599318689000
                     * updateTime : 1599318689000
                     * certificateBusinessId : 5
                     * certificateOperationId : 5
                     * tel :
                     * adminId : null
                     * name :
                     * certificateIDBo : {"id":26,"name":"郁友亮","six":"","nation":"","birthDay":"","address":"","isActive":1,"createTime":1599318688000,"updateTime":1599318688000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/d91f0fe0-94fa-43ea-b5a7-330088dfb4cc.jpg","picUrl2":"https://aicc.ctags.cn/cccc/file/download/9646a2e5-ecf7-499f-a039-938b9ffafb21.jpg","driverId":0,"carId":0,"carOwnerId":0,"enterpriseId":0,"idno":"37280119770821511X"}
                     * certificateOperationBo : {"id":5,"grantNo":"临字71316290072号","validityDate":"2023","owner":"","address":"","nature":"","scope":"","isActive":1,"updateTime":1599318689000,"createTime":1599318689000,"picUrl":"https://aicc.ctags.cn/cccc/file/download/0f4b3f29-3256-4af1-a1c0-4c9e5e2deac8.jpg","enterpriseId":0}
                     * certificateBusinessBo : {"id":5,"picUrl":"https://aicc.ctags.cn/cccc/file/download/995071cd-1b55-40e3-90f0-11699ed4e0f5.jpg","name":"临沂兴喜运描有限公司","capital":"","type":"","establishmentDate":"2015年06月16日","legalPerson":"郁友亮","term":"","scope":"海+训候,货物圈运服务:销售:汽车,汽车配洋,(依流须普批准的项H,经相关容门批率稻方可开碳经营活动)鲁还切验运偷,售物书面运输(参装题冷藏保鲜,域式)","address":"","registrationAuthority":"山东省借当市经落开发区芝麻给害语办事处看间路116号值信物出近内D3-12号","isActive":1,"createTime":1599318689000,"updateTime":1599318689000,"enterpriseId":0}
                     * adminBo : null
                     * idcertificateId : 26
                     */

                    private int id;
                    private String legalPerson;
                    private String signature;
                    private String sealUrl;
                    private int isActive;
                    private long createTime;
                    private long updateTime;
                    private int certificateBusinessId;
                    private int certificateOperationId;
                    private String tel;
                    private Object adminId;
                    private String name;
                    private CertificateIDBoBeanXXX certificateIDBo;
                    private CertificateOperationBoBeanX certificateOperationBo;
                    private CertificateBusinessBoBeanX certificateBusinessBo;
                    private Object adminBo;
                    private int idcertificateId;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getLegalPerson() {
                        return legalPerson;
                    }

                    public void setLegalPerson(String legalPerson) {
                        this.legalPerson = legalPerson;
                    }

                    public String getSignature() {
                        return signature;
                    }

                    public void setSignature(String signature) {
                        this.signature = signature;
                    }

                    public String getSealUrl() {
                        return sealUrl;
                    }

                    public void setSealUrl(String sealUrl) {
                        this.sealUrl = sealUrl;
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

                    public int getCertificateBusinessId() {
                        return certificateBusinessId;
                    }

                    public void setCertificateBusinessId(int certificateBusinessId) {
                        this.certificateBusinessId = certificateBusinessId;
                    }

                    public int getCertificateOperationId() {
                        return certificateOperationId;
                    }

                    public void setCertificateOperationId(int certificateOperationId) {
                        this.certificateOperationId = certificateOperationId;
                    }

                    public String getTel() {
                        return tel;
                    }

                    public void setTel(String tel) {
                        this.tel = tel;
                    }

                    public Object getAdminId() {
                        return adminId;
                    }

                    public void setAdminId(Object adminId) {
                        this.adminId = adminId;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public CertificateIDBoBeanXXX getCertificateIDBo() {
                        return certificateIDBo;
                    }

                    public void setCertificateIDBo(CertificateIDBoBeanXXX certificateIDBo) {
                        this.certificateIDBo = certificateIDBo;
                    }

                    public CertificateOperationBoBeanX getCertificateOperationBo() {
                        return certificateOperationBo;
                    }

                    public void setCertificateOperationBo(CertificateOperationBoBeanX certificateOperationBo) {
                        this.certificateOperationBo = certificateOperationBo;
                    }

                    public CertificateBusinessBoBeanX getCertificateBusinessBo() {
                        return certificateBusinessBo;
                    }

                    public void setCertificateBusinessBo(CertificateBusinessBoBeanX certificateBusinessBo) {
                        this.certificateBusinessBo = certificateBusinessBo;
                    }

                    public Object getAdminBo() {
                        return adminBo;
                    }

                    public void setAdminBo(Object adminBo) {
                        this.adminBo = adminBo;
                    }

                    public int getIdcertificateId() {
                        return idcertificateId;
                    }

                    public void setIdcertificateId(int idcertificateId) {
                        this.idcertificateId = idcertificateId;
                    }

                    public static class CertificateIDBoBeanXXX {
                        /**
                         * id : 26
                         * name : 郁友亮
                         * six :
                         * nation :
                         * birthDay :
                         * address :
                         * isActive : 1
                         * createTime : 1599318688000
                         * updateTime : 1599318688000
                         * picUrl : https://aicc.ctags.cn/cccc/file/download/d91f0fe0-94fa-43ea-b5a7-330088dfb4cc.jpg
                         * picUrl2 : https://aicc.ctags.cn/cccc/file/download/9646a2e5-ecf7-499f-a039-938b9ffafb21.jpg
                         * driverId : 0
                         * carId : 0
                         * carOwnerId : 0
                         * enterpriseId : 0
                         * idno : 37280119770821511X
                         */

                        private int id;
                        private String name;
                        private String six;
                        private String nation;
                        private String birthDay;
                        private String address;
                        private int isActive;
                        private long createTime;
                        private long updateTime;
                        private String picUrl;
                        private String picUrl2;
                        private int driverId;
                        private int carId;
                        private int carOwnerId;
                        private int enterpriseId;
                        private String idno;

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

                        public String getSix() {
                            return six;
                        }

                        public void setSix(String six) {
                            this.six = six;
                        }

                        public String getNation() {
                            return nation;
                        }

                        public void setNation(String nation) {
                            this.nation = nation;
                        }

                        public String getBirthDay() {
                            return birthDay;
                        }

                        public void setBirthDay(String birthDay) {
                            this.birthDay = birthDay;
                        }

                        public String getAddress() {
                            return address;
                        }

                        public void setAddress(String address) {
                            this.address = address;
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

                        public String getPicUrl() {
                            return picUrl;
                        }

                        public void setPicUrl(String picUrl) {
                            this.picUrl = picUrl;
                        }

                        public String getPicUrl2() {
                            return picUrl2;
                        }

                        public void setPicUrl2(String picUrl2) {
                            this.picUrl2 = picUrl2;
                        }

                        public int getDriverId() {
                            return driverId;
                        }

                        public void setDriverId(int driverId) {
                            this.driverId = driverId;
                        }

                        public int getCarId() {
                            return carId;
                        }

                        public void setCarId(int carId) {
                            this.carId = carId;
                        }

                        public int getCarOwnerId() {
                            return carOwnerId;
                        }

                        public void setCarOwnerId(int carOwnerId) {
                            this.carOwnerId = carOwnerId;
                        }

                        public int getEnterpriseId() {
                            return enterpriseId;
                        }

                        public void setEnterpriseId(int enterpriseId) {
                            this.enterpriseId = enterpriseId;
                        }

                        public String getIdno() {
                            return idno;
                        }

                        public void setIdno(String idno) {
                            this.idno = idno;
                        }
                    }

                    public static class CertificateOperationBoBeanX {
                        /**
                         * id : 5
                         * grantNo : 临字71316290072号
                         * validityDate : 2023
                         * owner :
                         * address :
                         * nature :
                         * scope :
                         * isActive : 1
                         * updateTime : 1599318689000
                         * createTime : 1599318689000
                         * picUrl : https://aicc.ctags.cn/cccc/file/download/0f4b3f29-3256-4af1-a1c0-4c9e5e2deac8.jpg
                         * enterpriseId : 0
                         */

                        private int id;
                        private String grantNo;
                        private String validityDate;
                        private String owner;
                        private String address;
                        private String nature;
                        private String scope;
                        private int isActive;
                        private long updateTime;
                        private long createTime;
                        private String picUrl;
                        private int enterpriseId;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getGrantNo() {
                            return grantNo;
                        }

                        public void setGrantNo(String grantNo) {
                            this.grantNo = grantNo;
                        }

                        public String getValidityDate() {
                            return validityDate;
                        }

                        public void setValidityDate(String validityDate) {
                            this.validityDate = validityDate;
                        }

                        public String getOwner() {
                            return owner;
                        }

                        public void setOwner(String owner) {
                            this.owner = owner;
                        }

                        public String getAddress() {
                            return address;
                        }

                        public void setAddress(String address) {
                            this.address = address;
                        }

                        public String getNature() {
                            return nature;
                        }

                        public void setNature(String nature) {
                            this.nature = nature;
                        }

                        public String getScope() {
                            return scope;
                        }

                        public void setScope(String scope) {
                            this.scope = scope;
                        }

                        public int getIsActive() {
                            return isActive;
                        }

                        public void setIsActive(int isActive) {
                            this.isActive = isActive;
                        }

                        public long getUpdateTime() {
                            return updateTime;
                        }

                        public void setUpdateTime(long updateTime) {
                            this.updateTime = updateTime;
                        }

                        public long getCreateTime() {
                            return createTime;
                        }

                        public void setCreateTime(long createTime) {
                            this.createTime = createTime;
                        }

                        public String getPicUrl() {
                            return picUrl;
                        }

                        public void setPicUrl(String picUrl) {
                            this.picUrl = picUrl;
                        }

                        public int getEnterpriseId() {
                            return enterpriseId;
                        }

                        public void setEnterpriseId(int enterpriseId) {
                            this.enterpriseId = enterpriseId;
                        }
                    }

                    public static class CertificateBusinessBoBeanX {
                        /**
                         * id : 5
                         * picUrl : https://aicc.ctags.cn/cccc/file/download/995071cd-1b55-40e3-90f0-11699ed4e0f5.jpg
                         * name : 临沂兴喜运描有限公司
                         * capital :
                         * type :
                         * establishmentDate : 2015年06月16日
                         * legalPerson : 郁友亮
                         * term :
                         * scope : 海+训候,货物圈运服务:销售:汽车,汽车配洋,(依流须普批准的项H,经相关容门批率稻方可开碳经营活动)鲁还切验运偷,售物书面运输(参装题冷藏保鲜,域式)
                         * address :
                         * registrationAuthority : 山东省借当市经落开发区芝麻给害语办事处看间路116号值信物出近内D3-12号
                         * isActive : 1
                         * createTime : 1599318689000
                         * updateTime : 1599318689000
                         * enterpriseId : 0
                         */

                        private int id;
                        private String picUrl;
                        private String name;
                        private String capital;
                        private String type;
                        private String establishmentDate;
                        private String legalPerson;
                        private String term;
                        private String scope;
                        private String address;
                        private String registrationAuthority;
                        private int isActive;
                        private long createTime;
                        private long updateTime;
                        private int enterpriseId;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getPicUrl() {
                            return picUrl;
                        }

                        public void setPicUrl(String picUrl) {
                            this.picUrl = picUrl;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getCapital() {
                            return capital;
                        }

                        public void setCapital(String capital) {
                            this.capital = capital;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public String getEstablishmentDate() {
                            return establishmentDate;
                        }

                        public void setEstablishmentDate(String establishmentDate) {
                            this.establishmentDate = establishmentDate;
                        }

                        public String getLegalPerson() {
                            return legalPerson;
                        }

                        public void setLegalPerson(String legalPerson) {
                            this.legalPerson = legalPerson;
                        }

                        public String getTerm() {
                            return term;
                        }

                        public void setTerm(String term) {
                            this.term = term;
                        }

                        public String getScope() {
                            return scope;
                        }

                        public void setScope(String scope) {
                            this.scope = scope;
                        }

                        public String getAddress() {
                            return address;
                        }

                        public void setAddress(String address) {
                            this.address = address;
                        }

                        public String getRegistrationAuthority() {
                            return registrationAuthority;
                        }

                        public void setRegistrationAuthority(String registrationAuthority) {
                            this.registrationAuthority = registrationAuthority;
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

                        public int getEnterpriseId() {
                            return enterpriseId;
                        }

                        public void setEnterpriseId(int enterpriseId) {
                            this.enterpriseId = enterpriseId;
                        }
                    }
                }
            }
        }

        public static class CompleteBosBean implements Serializable {
            /**
             * id : 5
             * chechDataId : 117
             * state : 1
             * reslut : 1111
             * isActive : 1
             * createTime : 1599532601000
             * updateTime : 1599532601000
             * checkHistoryId : 5
             * checkData : {"id":117,"item":"证件","isActive":1,"createTime":1598946365000,"updateTime":1598946365000}
             */

            private int id;
            private int chechDataId;
            private int state;
            private String reslut;
            private int isActive;
            private long createTime;
            private long updateTime;
            private int checkHistoryId;
            private CheckDataBean checkData;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getChechDataId() {
                return chechDataId;
            }

            public void setChechDataId(int chechDataId) {
                this.chechDataId = chechDataId;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getReslut() {
                return reslut;
            }

            public void setReslut(String reslut) {
                this.reslut = reslut;
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

            public int getCheckHistoryId() {
                return checkHistoryId;
            }

            public void setCheckHistoryId(int checkHistoryId) {
                this.checkHistoryId = checkHistoryId;
            }

            public CheckDataBean getCheckData() {
                return checkData;
            }

            public void setCheckData(CheckDataBean checkData) {
                this.checkData = checkData;
            }

            public static class CheckDataBean implements Serializable {
                /**
                 * id : 117
                 * item : 证件
                 * isActive : 1
                 * createTime : 1598946365000
                 * updateTime : 1598946365000
                 */

                private int id;
                private String item;
                private int isActive;
                private long createTime;
                private long updateTime;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getItem() {
                    return item;
                }

                public void setItem(String item) {
                    this.item = item;
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
            }
        }
    }
}
