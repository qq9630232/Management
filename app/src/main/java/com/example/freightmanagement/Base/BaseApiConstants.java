package com.example.freightmanagement.Base;
import android.os.Environment;


import java.io.File;

import static com.example.freightmanagement.Utils.Network.Host.BASE_URL;

/**
 * @Deprecated 用来存放项目中所有的接口url
 * @author: ztc
 * 2019/8/12
 * @describe:
 */
public class BaseApiConstants {

    /**
     * 图片上传后的BASE_URL
     */
    public static final String IMAGE_BASE_URL = BASE_URL.concat("/cccc/file/download/") ;

    /**
     * 验证码
     * 参数：username，userpass
     */
    public static final String API_SMS_CODE = "/cccc/register/getMessageCode/";
    /**
     * 账号登录
     * 参数：username，userpass
     */
    public static final String API_LOGIN = "/cccc/login";

    /**
     * 账号注册
     * 参数：username，password
     */

    public static final String API_REGISTER = "/cccc/register";

    /**
     * 找回密码
     */
    public static final String API_RETRIEVE_PWD = "/cccc/register/retrieve";
    /**
     * 获取驾驶员信息
     */
    public static final String API_DRIVERINFORMATION = "/cccc/driver/getID/";

    public static final String API_WROKID = "/cccc/examination/getExaminationDriverDatas/";
    /**
     * 获取答题结果记录
     */
    public static final String API_ANSWER_RESULT_LIST = "/cccc/examination/getExaminationDriverDatas/";
    /**
     * 获取驾驶员信息
     */
    public static final String API_PEIXUNJIEGUO = "/cccc/driver/getDirver";

    /**
     * 获取训练题目
     */
    public static final String API_XUNLIANTIMU = "/cccc/examination/getExaminationDatas";


    public static final String API_TRAINING = "/cccc/train/getTrainDatas";
    /**
     * 上传图片
     */
    public static final String API_IMAGE_UPLOAD = "/cccc/file/upload";
    /**
     * 上传多图片
     */
    public static final String API_DUOIMAGE_UPLOAD = "/cccc/file/uploadFiles";

    /**
     * 车主注册
     */
    public static final String API_CAR_OWNER_REGISTER = "/cccc/carOwner/addOwner";

    /**
     * 驾驶员注册
     */
    public static final String API_DRIVER_REGISTER = "/cccc/driver/addDirver";
    /**
     * 修改驾驶员信息
     */
    public static final String API_JIASHIYUANXINXIXIUGAI = "/cccc/driver/info/editDirver";
    /**
     * 企业注册
     */
    public static final String API_COMPANY_REGISTER = "/cccc/enterprise/addEnterprise";

    /**
     * 获取车俩检测项
     */
    public static final String API_JIANCEXIANG = "/cccc/check/getCheckDatas";

    /**
     * 提交车俩检测项
     */
    public static final String API_ADDJIANCEXIANG = "/cccc/check/submit";
    /**
     * 提交车辆维修记录
     */
    public static final String API_ADDWEIXIU = "/cccc/repair/addrepair";
    /**
     * 提交车俩保养记录
     */
    public static final String API_ADDBAOYANG = "/cccc/maintain/addMaintain";

    /**
     * 查看车俩检测列表
     */
    public static final String API_CHEJIANCE = "/cccc/check/getCheckHistorys/";
    /**
     * 查看保养记录
     */
    public static final String API_CHEBAOYANG = "/cccc/maintain/getMaintainList/";
    /**
     * 查看维修记录
     */
    public static final String API_CHEWEIXIU = "/cccc/repair/getrepairList/";
    /**
     * 提交培训
     */
    public static final String API_TIJIAOWENJUAN = "/cccc/examination/submit";
    /**
     * 驾驶员车辆信息
     */
    public static final String API_CLXINXI = "/cccc/driver/getDirver/";
    /**
     * 选择车辆
     */
    public static final String API_XUANZECHELIANG = "/cccc/carOwner/getPassedCarByPage";

    /**
     * 解除合同
     */
    public static final String API_EDIT_CONTRACT = "/cccc/manager/contract/editContract";
    /**
     * 提交合同
     */
    public static final String API_ADD_CONTRACT = "/cccc/letter/addContract";
    /**
     * 提交承诺书
     */
    public static final String API_ADD_COMMITMENT = "/cccc/letter/addCommitment";
    /**
     * 提交责任书
     */
    public static final String API_ADD_RESPONSIBILITY = "/cccc/letter/addResponsibility";
    /**
     * 驾驶员列表
     */
    public static final String API_DRIVER_LIST = "/cccc/manager/driver/getDriverList";
    /**
     * 车辆列表
     */
    public static final String API_CAR_LIST = "/cccc/manager/car/getCarList";


    /**
     * 删除或者更改车辆
     */
    public static final String API_CAR_DEL = "/cccc/carOwner/deleteCar";
    /**
     * 新增车辆
     */
    public static final String API_CAR_ADD = "/cccc/carOwner/addOwner";

    /**
     * 获取合同状态
     */
    public static final String API_CONTRACT_GET = "/cccc/letter/getContract";
    /**
     * 获取合同
     */
    public static final String API_CONTRACT_GET_BY_ID = "/cccc/letter/getContractById/";
    /**
     * 获取合同by driverId
     */
    public static final String API_CONTRACT_GET_BY_DRIVER_ID = "/cccc/letter/getContractByDriverId/";
    /**
     * 解除合同
     */
    public static final String API_RESLOVE_CONTRACT = "/cccc/letter/getResloveConstract/";
    /**
     * 获取企业信息
     */
    public static final String API_QIYEXINXI_GET = "/cccc/enterprise/getEenterprise";
    /**
     * 获取车主信息
     */
    public static final String API_CHEZHU_GET = "/cccc/carOwner/getOwner";
    /**
     * 获取车主信息
     */
    public static final String API_TRAIN_GET = "/cccc/train/complete/";
    /**
     * 增加车辆
     */
    public static final String API_ADD_CAR = "/cccc/carOwner/addCar";
    /**
     * 修改密码
     */
    public static final String API_CHANGE_PASS = "/cccc/manager/admin/editAdmin";
    /**
     * 修改密码
     */
    public static final String API_QIYEXINXIXIUGAI = "/cccc/manager/enterprise/info/editEnterprise";
    /**
     * 獲取車輛详情
     */
    public static final String API_CHELIANGXIANGQING = "/cccc/manager/car/getCar/";
    /**
     * 獲取車輛详情
     */
    public static final String API_JIASHIYUAN = "/cccc/driver/getDirver/";
    /**
     * 合同结果列表
     */
    public static final String API_HETONG_LIST = "/cccc/letter/getDriverLetters";
    /**
     * 聘用合同模板
     */
    public static final String API_HETONG_CONTRACT_HTML = "/cccc/letter/contract.html";
    /**
     * 聘用合同模板
     */
    public static final String API_HETONG_RESPONSIBILITY_HTML = "/cccc/letter/responsibility.html";
    /**
     * 聘用合同模板
     */
    public static final String API_HETONG_COMMITMENT_HTML = "/cccc/letter/commitment.html";

    /**
     * 获取推送url
     */
    public static final String API_PUSH_URL = "/cccc/rtmp/RTMPPublishURL/";
    /**
     * 获取拉流URL
     */
    public static final String API_PLAY_URL = "/cccc/rtmp/RTMPPlayURL";
	
	 /**
      * * 获取推流Key
     */
    public static final String API_PLAY_KEY = "/cccc/meeting/createMeeting";

    /**
     * 结束会议
     */
    public static final String API_END_MEETING = "/cccc/meeting/endMeeting";


    /**
     * 获取拉流URL
     */
    public static final String API_GET_MEETING = "/cccc/meeting/getAllMeetings";
    /**
     * 搜索车辆
     */
    public static final String API_SEARCH_CAR = "/cccc/carOwner/searchCar/";
    /**
     * 搜索驾驶员
     */
    public static final String API_SEARCH_DRIVER = "/cccc/driver/searchDriver/";
    /**
     * 获取服务器端app版本号
     */
    public static final String WEBVERSION = "/cccc/version/getVersion";
    /**
     * App下载地址
     */
    public static final String APKHOST = "http://192.168.1.73:3005/app/Freightmanagement.apk";
    /**
     * Apk路径
     */
    public static final String SDFILE = Environment.getExternalStoragePublicDirectory("") + File.separator + "Freightmanagement/gt/";
    public static final String APK_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String APK_PATH_ABSOULT = SDFILE+ "apk" + File.separator;

}
