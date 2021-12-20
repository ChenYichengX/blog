package com.chen.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName EsLog
 * @Author ChenYicheng
 * @Description eslog实体类
 * @Date 2021/12/20 11:10
 */
@Entity
@Table(name = "t_es_log")
public class EsLog {

    @Id
    @GeneratedValue
    private String id;

    /** 服务名-ChenYicheng-2021/10/29 11:46 */
    private String serverName;

    /** 模块名-ChenYicheng-2021/10/29 11:58 */
    private String module;

    /** 操作-ChenYicheng-2021/10/29 11:58 */
    private String action;

    /** 用户名-ChenYicheng-2021/10/29 11:58 */
    private String userName;

    /** ip-ChenYicheng-2021/10/29 11:58 */
    private String ip;

    /** 客户端浏览器-ChenYicheng-2021/10/29 11:58 */
    private String browswer;

    /** 参数-ChenYicheng-2021/10/29 11:59 */
    private String data;

    /** 日志生成时间-ChenYicheng-2021/10/29 12:00 */
    private Date createTime;

    /** 请求状态码-ChenYicheng-2021/10/29 12:00 */
    private String resultCode;

    public EsLog() {
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

    public EsLog(String id, String serverName, String module, String action, String userName, String ip, String browswer, String data, Date createTime, String resultCode) {
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
        return "EsLog{" +
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
