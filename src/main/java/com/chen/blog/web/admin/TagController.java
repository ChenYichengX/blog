package com.chen.blog.web.admin;

import com.chen.blog.aspect.SystemLog;
import com.chen.blog.entity.Tag;
import com.chen.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author 陈奕成
 * @create 2020 05 11 16:44
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @SystemLog(serviceName = "blog服务", module = "后台标签管理", action = "标签列表分页接口")
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/back_tag";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }

    @GetMapping("/tags/{id}/input")
    public String toEdiTag(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tag-input";
    }

    @SystemLog(serviceName = "blog服务", module = "后台标签管理", action = "新增标签")
    @PostMapping("/tags")
    public String addTag(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        //若标签名重复，给 name 添加了一个错误
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null){
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        //name有错直接返回
        if(result.hasErrors()){
            return "admin/tag-input";
        }

        Tag t = tagService.saveTag(tag);
        if(t == null){
            //新增失败
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    @SystemLog(serviceName = "blog服务", module = "后台标签管理", action = "修改标签")
    @PostMapping("/tags/{id}")
    public String editTag(@Valid Tag tag, BindingResult result,
                           @PathVariable(name = "id") Long id,RedirectAttributes attributes){
        //若分类名重复，给 name 添加了一个错误
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 !=null){
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        //name有错直接返回
        if(result.hasErrors()){
            return "admin/tag-input";
        }

        Tag t = tagService.updateTag(id,tag);
        if(t == null){
            //更新失败
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @SystemLog(serviceName = "blog服务", module = "后台标签管理", action = "删除标签")
    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable("id") Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
