package com.xuecheng.content.service;

import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: whh
 * @Description: TODO
 * @Date: 2024/10/15 下午3:03
 */
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService{

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
        //将list转换为map，并且排除根节点
        Map<String, CourseCategoryTreeDto> mapTemp =  courseCategoryTreeDtos.stream()
                .filter(item -> !id.equals(item.getId()))
                .collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        //最终返回的list
        List<CourseCategoryTreeDto> categoryTreeDtos = new ArrayList<>();
        courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).forEach(item -> {
            //加入一级节点
            if(item.getParentid().equals(id)){
                categoryTreeDtos.add(item);
            }
            //对于其他节点加入到一级节点中

            CourseCategoryTreeDto parentCategoryDto = mapTemp.get(item.getParentid());
            if(parentCategoryDto!=null){
                if(parentCategoryDto.getChildrenTreeNodes()==null){
                    parentCategoryDto.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                parentCategoryDto.getChildrenTreeNodes().add(item);
            }
        });
        return categoryTreeDtos;
    }
}
