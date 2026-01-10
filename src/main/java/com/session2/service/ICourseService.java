package com.session2.service;

import java.util.List;

import com.session2.model.Course;

public interface ICourseService {
    List<Course> getAllCourses();

    Boolean createCourse(Course course);

    Course getCourseById(Integer id);

    Boolean updateCourse(Integer id, Course course);

    Boolean deleteCourse(Integer id);

    List<Course> searchCourseByName(String name);

    List<Course> getCoursesSort(String sortBy, String order);
}
