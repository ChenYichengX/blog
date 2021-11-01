package com.chen.blog.aspect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @ClassName EsLog
 * @Author ChenYicheng
 * @Description EsLog
 * @Date 2021/10/29 9:51
 */
@Document(indexName = "blog_logs")
public class ESLog {

    /** id-ChenYicheng-2021/10/29 11:46 */
    @Id
    private String id;

    /** 服务名-ChenYicheng-2021/10/29 11:46 */
    @Field(type = FieldType.Text)
    private String serverName;

    /** 模块名-ChenYicheng-2021/10/29 11:58 */
    @Field(type = FieldType.Text)
    private String module;
    
    /** 操作-ChenYicheng-2021/10/29 11:58 */
    @Field
    private String action;
    
    /** 用户名-ChenYicheng-2021/10/29 11:58 */
    @Field
    private String userName;
    
    /** ip-ChenYicheng-2021/10/29 11:58 */
    @Field(type = FieldType.Ip)
    private String ip;
    
    /** 客户端浏览器-ChenYicheng-2021/10/29 11:58 */
    @Field
    private String browswer;

    /** 参数-ChenYicheng-2021/10/29 11:59 */
    @Field
    private String data;

    /** 日志生成时间-ChenYicheng-2021/10/29 12:00 */
    @Field(type = FieldType.Date)
    private Date createTime;

    /** 请求状态码-ChenYicheng-2021/10/29 12:00 */
    private String resultCode;

    public ESLog() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowswer() {
        return browswer;
    }

    public void setBrowswer(String browswer) {
        this.browswer = browswer;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ESLog(String id, String serverName, String module, String action, String userName, String ip, String browswer, String data, Date createTime, String resultCode) {
        this.id = id;
        this.serverName = serverName;
        this.module = module;
        this.action = action;
        this.userName = userName;
        this.ip = ip;
        this.browswer = browswer;
        this.data = data;
        this.createTime = createTime;
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "ESLog{" +
                "id='" + id + '\'' +
                ", serverName='" + serverName + '\'' +
                ", module='" + module + '\'' +
                ", action='" + action + '\'' +
                ", userName='" + userName + '\'' +
                ", ip='" + ip + '\'' +
                ", browswer='" + browswer + '\'' +
                ", data='" + data + '\'' +
                ", createTime=" + createTime +
                ", resultCode='" + resultCode + '\'' +
                '}';
    }
}
