package com.chen.blog.web;

import com.chen.blog.aspect.SystemLog;
import com.chen.blog.service.BlogService;
import com.chen.blog.service.TagService;
import com.chen.blog.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈奕成
 * @create 2020 05 09 13:25
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 图片存放路径-ChenYicheng-2021/9/14 19:09
     */
    @Value("${upload.picturePath}")
    private String picturePath;

    private static Map imageContentType = new HashMap<>();

    static {
        imageContentType.put("jpg","image/jpeg");
        imageContentType.put("jpeg","image/jpeg");
        imageContentType.put("png","image/png");
        imageContentType.put("tif","image/tiff");
        imageContentType.put("tiff","image/tiff");
        imageContentType.put("ico","image/x-icon");
        imageContentType.put("bmp","image/bmp");
        imageContentType.put("gif","image/gif");
    }

    @SystemLog(serviceName = "blog服务", module = "首页模块", action = "访问首页")
    @GetMapping("/")
    public String index(@PageableDefault(size = 4, sort = {"views"}, direction = Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(4));
        model.addAttribute("tags", tagService.listTagTop(7));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(4));
        return "index";
    }

    @SystemLog(serviceName = "blog服务", module = "首页模块", action = "通过blogId获取该博客信息")
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {

        model.addAttribute("blog", blogService.getAndConvert(id));
        System.out.println("----------");
        return "blog";
    }

    @SystemLog(serviceName = "blog服务", module = "首页模块", action = "搜索博客")
    @PostMapping("/search")
    public String search(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @SystemLog(serviceName = "blog服务", module = "首页模块", action = "访问'关于我'页")
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/footer/newblog")
    public String newBlogList(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newbloglist";
    }

    /**
     * @Author ChenYicheng
     * @Description 读取图片
     * @Date 2021/10/8 15:42
     */
    @GetMapping("/picture")
    public void ioReadPicture(@RequestParam(value = "path") String path, HttpServletResponse response) {


        ServletOutputStream out = null;
        FileInputStream ips = null;
        String fileType = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
        String filesType = (String)imageContentType.get(fileType);
        String parentPath = picturePath.substring(picturePath.indexOf(':') + 1); // 获取存放图片的目录路径
        try{
            File file = new File(parentPath,path);
            if(!file.exists()){ // 文件不存在
                logger.error("图片资源不存在");
                return ;
            }
            ips = new FileInputStream(file);
            response.setContentType(filesType);
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ips != null){
                try {
                    ips.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
