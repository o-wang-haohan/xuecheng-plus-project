package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.po.CoursePublish;

import java.io.File;

public interface CoursePublishService {
    public CoursePreviewDto getCoursePreviewInfo(Long courseId);

    //提交审核
    public void commitAudit(Long companyId,Long courseId);

    //接口开发
    public void publish(Long companyId,Long courseId);

    //生成课程静态化页面
    public File generateCourseHtml(Long courseId);

    //上传课程静态化页面到MinIo
    public void  uploadCourseHtml(Long courseId,File file);

    public CoursePublish getCoursePublish(Long courseId);
}
