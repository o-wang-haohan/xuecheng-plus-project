package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

public interface TeachplanService {

    public List<TeachplanDto> getTreeNodes(Long courseId);

    public void saveTeachplan(SaveTeachplanDto teachplanDto);
}
