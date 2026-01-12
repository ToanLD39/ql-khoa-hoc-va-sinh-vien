package com.session2.dao;

import java.util.List;

import com.session2.model.Course;

import response.CourseStudentRest;

public interface IStatisticsDAO {
    List<Integer> countCourseAndStudent();

    List<CourseStudentRest> getCourseStudentStatistics();

    List<Course> getTop5CoursesHaveMoreStudent();

    List<Course> getCourseHaveMore10Students();

}
