package com.chen.blog.web.admin;

import com.chen.blog.aspect.SystemLog;
import com.chen.blog.entity.Type;
import com.chen.blog.service.TypeService;
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
 * @create 2020 05 10 16:40
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @SystemLog(serviceName = "blog服务", module = "后台分类管理", action = "分类列表获取")
    @GetMapping("/types")
    public String types(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/back_type";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";
    }

    @SystemLog(serviceName = "blog服务", module = "后台分类管理", action = "新增分类")
    @PostMapping("/types")
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        //若分类名重复，给 name 添加了一个错误
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 !=null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        //name有错直接返回
        if(result.hasErrors()){
            return "admin/type-input";
        }

        Type t = typeService.saveType(type);
        if(t == null){
            //新增失败
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    @SystemLog(serviceName = "blog服务", module = "后台分类管理", action = "修改分类")
    @PostMapping("/types/{id}")
    public String editType(@Valid Type type, BindingResult result,
                           @PathVariable(name = "id") Long id,RedirectAttributes attributes){
        //若分类名重复，给 name 添加了一个错误
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 !=null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        //name有错直接返回
        if(result.hasErrors()){
            return "admin/type-input";
        }

        Type t = typeService.updateType(id,type);
        if(t == null){
            //更新失败
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @SystemLog(serviceName = "blog服务", module = "后台分类管理", action = "删除分类")
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
