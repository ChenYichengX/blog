package com.chen.blog.util;

import com.alibaba.fastjson.JSONObject;
import com.chen.blog.aspect.entity.ESLog;
import com.chen.blog.dao.ESLogRepository;
import com.chen.blog.dao.EslogDaoRepository;
import com.chen.blog.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName EsUtil
 * @Author ChenYicheng
 * @Description elasticsearch 工具类
 * @Date 2021/10/29 9:48
 */
@Component
public class LogUtil {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private ESLogRepository esLogRepository;
    @Autowired
    private EslogDaoRepository eslogDaoRepository;

    @Async
    public void insertEsLog(String serviceName, String module, String action, Object proceed, String[] parameterNames, Object[] args, HttpServletRequest request,HttpSession session) {

        try {
//            ESLog esLog = new ESLog();
            EsLog esLog = new EsLog();
            esLog.setServerName(serviceName);
            esLog.setModule(module);
            esLog.setAction(action);
            esLog.setCreateTime(new Date());
            esLog.setBrowswer(getOsAndBrowserInfo(request));
            esLog.setIp(getIpAddr(request));
            esLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));

            Object user =  session.getAttribute("user");
            if (Assert.isBlank(user)) {
                esLog.setUserName("-");
            }else{
                User u = (User) user;
                esLog.setUserName(u.getUserName());
            }

            String data = null;

            try {
                data = this.getJsonData(parameterNames, args);
                esLog.setData(data); // 参数
            } catch (Exception var18) {
                log.error("获取参数失败：{}", var18.getMessage());
            }

            /*String resultCode = "";
            Object httpResponseTempResultCode = getValueByKey(proceed, "resultCode");
            Object status = getValueByKey(proceed, "status");
            Object responseEntityResultCode = null;
            if (Assert.isNotEmpty(status)) {
                responseEntityResultCode = getValueByKey(status, "value");
            }

            if (Assert.isNotEmpty(httpResponseTempResultCode)) {
                resultCode = httpResponseTempResultCode + "";
            } else if (Assert.isNotEmpty(responseEntityResultCode)) {
                resultCode = responseEntityResultCode + "";
            }*/

            esLog.setResultCode(""/*resultCode*/);

            // 调用 esUtil 插入进 elasticsearch
            // esLogRepository.save(esLog);
            eslogDaoRepository.save(esLog);

            log.info("日志记录完成:" + esLog);
        } catch (Exception var19) {
            var19.printStackTrace();
            log.error("日志记录失败：" + var19.getMessage());
        }

    }


    private String getJsonData(String[] parameterNames, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        int i;
        for (i = 0; i < parameterNames.length; ++i) {
            if (!(args[i] instanceof ServletRequest) && !(args[i] instanceof ServletResponse) && !(args[i] instanceof BindingResult)) {
                sb.append("\"").append(parameterNames[i]).append("\"").append(":");
                if(args[i] instanceof Pageable || args[i] instanceof Model || args[i] instanceof RedirectAttributes
                        || args[i] instanceof HttpSession || args[i] instanceof Blog || args[i] instanceof Tag || args[i] instanceof Type || args[i] instanceof Comment){
                    sb.append("{}");
                }else if (args[i] instanceof MultipartFile[]) {
                    MultipartFile[] multipartFiles = (MultipartFile[]) args[i];

                    int indexOf;
                    for (indexOf = 0; indexOf < multipartFiles.length; ++indexOf) {
                        String filesName = multipartFiles[i].getOriginalFilename();
                        sb.append("\"").append(filesName).append(",").append("\"");
                    }

                    indexOf = sb.lastIndexOf(",");
                    if (indexOf >= 0) {
                        sb.deleteCharAt(indexOf);
                    }
                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile multipartFile = (MultipartFile) args[i];
                    String filesName = multipartFile.getOriginalFilename();
                    sb.append("\"").append(filesName).append("\"");
                } else {
                    sb.append(JSONObject.toJSONString(args[i]));
                }
                sb.append(",");
            }
        }
        i = sb.lastIndexOf(",");
        if (i >= 0) {
            sb.deleteCharAt(i);
        }

        sb.append("}");
        return sb.toString();
    }


    /*public static Object getValueByKey(Object obj, String key) {
        Class userCla = obj.getClass();
        Field[] fs = userCla.getDeclaredFields();

        for (int i = 0; i < fs.length; ++i) {
            Field f = fs[i];
            f.setAccessible(true);

            try {
                if (f.getName().endsWith(key)) {
                    return f.get(obj);
                }
            } catch (IllegalArgumentException var7) {
                var7.printStackTrace();
            } catch (IllegalAccessException var8) {
                var8.printStackTrace();
            }
        }
        return null;
    }*/


    /**
     * @Author ChenYicheng
     * @Description 获取用户请求客户端
     * @Date 2021/10/29 11:19
     */
    public static String getOsAndBrowserInfo(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String user = browserDetails.toLowerCase();
        String os = "";
        String browser = "";
        if (browserDetails.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (browserDetails.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (browserDetails.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else if (browserDetails.toLowerCase().indexOf("ipad") >= 0) {
            os = "IPad";
        } else if (browserDetails.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (browserDetails.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else {
            os = "UnKnown, More-Info: " + browserDetails;
        }

        if (user.contains("edge")) {
            browser = browserDetails.substring(browserDetails.indexOf("Edge")).split(" ")[0].replace("/", "-");
        } else {
            String IEVersion;
            if (user.contains("msie")) {
                IEVersion = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
                browser = IEVersion.split(" ")[0].replace("MSIE", "IE") + "-" + IEVersion.split(" ")[1];
            } else if (user.contains("safari") && user.contains("version")) {
                browser = browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0].split("/")[0] + "-" + browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0].split("/")[1];
            } else if (!user.contains("opr") && !user.contains("opera")) {
                if (user.contains("chrome")) {
                    browser = browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0].replace("/", "-");
                } else if (user.indexOf("mozilla/7.0") <= -1 && user.indexOf("netscape6") == -1 && user.indexOf("mozilla/4.7") == -1 && user.indexOf("mozilla/4.78") == -1 && user.indexOf("mozilla/4.08") == -1 && user.indexOf("mozilla/3") == -1 && user.indexOf("mozilla/5.0") == -1) {
                    if (user.contains("firefox")) {
                        browser = browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0].replace("/", "-");
                    } else if (user.contains("rv")) {
                        IEVersion = browserDetails.substring(browserDetails.indexOf("rv")).split(" ")[0].replace("rv:", "-");
                        browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
                    } else {
                        browser = "UnKnown, More-Info: " + browserDetails;
                    }
                } else {
                    browser = "Netscape-?";
                }
            } else if (user.contains("opera")) {
                browser = browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0].split("/")[0] + "-" + browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0].split("/")[1];
            } else if (user.contains("opr")) {
                browser = browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0].replace("/", "-").replace("OPR", "Opera");
            }
        }
        return os + "@@@" + browser;
    }


    /**
     * @Author ChenYicheng
     * @Description 获取请求 ip
     * @Date 2021/10/29 11:20
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-Original-Forwarded-For");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                InetAddress inet = null;

                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }

        if (ip != null && ip.length() > 15 && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }

}
