package com.chen.blog.leetcode5;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CourseStatistService
 * @Author ChenYicheng
 * @Description
 * @Date 2022/3/2 15:08
 */
public class CourseStatistService {

    /**
     {
     "Tooken": "USDFGGH345345",
     "Cs": [{
                "CName": "高等数学"
            },
            {
                "CName": "大学物理"
            }
        ]
     }
     */
    /*public JSONObject getStudentByCourseName(JSONObject jsonObject){
        JSONArray cs = jsonObject.getJSONArray("Cs");
        List<String> cNames = new ArrayList<>();
        for (int i = 0; i < cs.size(); i++) {
            Student o = (Student) cs.get(i);
            cNames.add(o.getCName());
        }
        *//*
            sql 大概逻辑：
            SELECT
                t2.xm SName
            FROM
                t_student_score t1
                LEFT JOIN t_student t2 ON t1.xh = t2.xh
                LEFT JOIN t_course t3 ON t1.course_id = t3.course_id
            WHERE
                t3.course_name IN ('1'...)
         *//*
        List<Student> studentList = courseDao.getStudentByCourseName(cNames);
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("Result","SUCCESS");
        jsonResult.put("Ss",JSONObject.toJSONString(studentList));
        return jsonResult;
    }*/
}



