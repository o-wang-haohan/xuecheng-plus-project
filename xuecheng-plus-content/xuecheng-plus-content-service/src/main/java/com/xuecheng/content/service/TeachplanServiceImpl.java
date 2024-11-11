package com.xuecheng.content.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: whh
 * @Description: TODO
 * @Date: 2024/11/5 下午6:56
 */
@Service
public class TeachplanServiceImpl implements TeachplanService{

    @Autowired
    TeachplanMapper teachplanMapper;

    @Override
    public List<TeachplanDto> getTreeNodes(Long courseId) {
        List<TeachplanDto> list =  teachplanMapper.selectTreeNodes(courseId);
        return list;
    }

    private int getTeachplanCount(long courseId,long parentId){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        //有课程id和父类id可以确定这个类别有多少个
        queryWrapper.eq(Teachplan::getCourseId,courseId).eq(Teachplan::getParentid,parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count+1;
    }

    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {
        Long id = teachplanDto.getId();
        if(id == null){
            //表示是新增操作
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(teachplanDto,teachplan);
            //需要添加排序字段
            //实现思路，统计排序字段的个数，加1即可
            Long courseId = teachplan.getCourseId();
            Long parentId = teachplan.getParentid();
            Integer order = getTeachplanCount(courseId,parentId);
            teachplan.setOrderby(order);

            int insert = teachplanMapper.insert(teachplan);
            if(insert<=0){
                XueChengPlusException.cast("新增课程计划信息失败");
            }
        }else{
            //id存在是修改操作
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }
    }
}
