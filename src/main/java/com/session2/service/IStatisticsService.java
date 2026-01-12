package com.session2.service;

import java.util.List;

import com.session2.model.Course;

import response.CourseStudentRest;

public interface IStatisticsService {
    List<Integer> countCourseAndStudent();

    List<CourseStudentRest> getCourseStudentStatistics();

    List<Course> getTop5CoursesHaveMoreStudent();

    List<Course> getCourseHaveMore10Students();
}
