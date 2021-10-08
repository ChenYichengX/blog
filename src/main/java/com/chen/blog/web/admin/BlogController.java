package com.chen.blog.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.chen.blog.entity.Blog;
import com.chen.blog.entity.Picture;
import com.chen.blog.entity.User;
import com.chen.blog.service.BlogService;
import com.chen.blog.service.PictureService;
import com.chen.blog.service.TagService;
import com.chen.blog.service.TypeService;
import com.chen.blog.vo.BlogQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author 陈奕成
 * @create 2020 05 10 15:10
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PictureService pictureService;

    /**
     * 图片存放路径-ChenYicheng-2021/9/14 19:09
     */
    @Value("${upload.picturePath}")
    private String picturePath;

    /**
     * serverUrl
     */
    @Value("${upload.serverUrl}")
    private String serverUrl;

    @GetMapping("/blogs")
    public String blog(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Direction.DESC) Pageable pageable,
                       BlogQuery blog, Model model, HttpServletRequest request) {
        // 获取当前用户
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return null;
        }
        User u = (User) user;
        Long id = u.getId();
        model.addAttribute("page", blogService.listBlog(pageable, blog, id));
        model.addAttribute("types", typeService.listType());
        return "admin/back_blog";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model, HttpServletRequest request) {
        // 获取当前用户
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return null;
        }
        User u = (User) user;
        Long id = u.getId();
        model.addAttribute("page", blogService.listBlog(pageable, blog, id));
        return "admin/back_blog :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        return "admin/blog-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return "admin/blog-input";
    }

    @PostMapping("/blogs")
    public String saveBlog(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }


    @ResponseBody
    @PostMapping("/uploadPicture")
    public JSONObject uploadPicture(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        File realFile = null;
        String s = null;
        try {
            if (file.isEmpty()) {
                logger.info("图片为空，请先上传文件!");
            } else {
                request.setCharacterEncoding("utf-8");
                response.setHeader("Content-Type", "text/html");
                String parentPath = picturePath.substring(picturePath.indexOf(':') + 1); // 获取存放图片的目录路径
                File parentDir = new File(parentPath);
                if (!parentDir.exists()) { // 目录不存在先创建目录
                    parentDir.mkdirs();
                }
                // 新的文件名
//                realFile = new File(parentPath, System.currentTimeMillis() + file.getOriginalFilename());
                String originalFilename = file.getOriginalFilename();
                String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                s = System.currentTimeMillis() + "." + substring;
                realFile = new File(parentPath, s);
                file.transferTo(realFile); // 将图片存起来

                // 将图片信息存入数据库
                Picture picture = new Picture();
                picture.setRealPath(realFile.getAbsolutePath()); // 文件的绝对路径
                picture.setPictureName(originalFilename); // 原始文件名
                pictureService.savePicture(picture);
            }
        } catch (Exception e) {
            logger.error("图片上传失败");
            jsonObject.put("success", 0);
            e.printStackTrace();
            return jsonObject;
        }
        logger.info("图片上传成功,存放路径为" + realFile.getAbsolutePath());
        jsonObject.put("success", 1);
        jsonObject.put("message","上传成功");
        jsonObject.put("url",serverUrl + "/picture?path=" + s);
        return jsonObject;
    }
}
