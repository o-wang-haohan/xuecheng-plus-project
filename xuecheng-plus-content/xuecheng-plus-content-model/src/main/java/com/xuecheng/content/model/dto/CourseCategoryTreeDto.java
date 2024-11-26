package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: whh
 * @Description: TODO
 * @Date: 2024/10/15 下午2:05
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory implements Serializable{
    List<CourseCategoryTreeDto> childrenTreeNodes;
}
