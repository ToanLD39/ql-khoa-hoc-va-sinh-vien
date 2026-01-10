package com.session2.dao;

import java.util.List;

import com.session2.model.Course;

public interface ICourseDAO extends IGenericDAO {
    List<Course> getAllCourse();

    Course getCourseById(Integer id);

    Boolean createCourse(Course course);

    Boolean updateCourse(Integer id, Course course);

    List<Course> searchCourseByName(String name);

    List<Course> sortCourses(String sortBy, String order);
}
