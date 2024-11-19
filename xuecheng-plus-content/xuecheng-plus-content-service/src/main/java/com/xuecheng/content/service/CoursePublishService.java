package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CoursePreviewDto;

public interface CoursePublishService {
    public CoursePreviewDto getCoursePreviewInfo(Long courseId);

    //提交审核
    public void commitAudit(Long companyId,Long courseId);

    //接口开发
    public void publish(Long companyId,Long courseId);
}
