package com.session2.service.imp;

import java.util.List;

import com.session2.common.Constant;
import com.session2.dao.ICourseDAO;
import com.session2.dao.imp.CourseDAO;
import com.session2.model.Course;
import com.session2.service.ICourseService;

public class CourseService implements ICourseService {

    private ICourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourse();
    }

    public Boolean createCourse(Course course) {
        return courseDAO.createCourse(course);
    }

    public Course getCourseById(Integer id) {
        return courseDAO.getCourseById(id);
    }

    public Boolean updateCourse(Integer id, Course course) {
        return courseDAO.updateCourse(id, course);
    }

    public Boolean deleteCourse(Integer id) {
        return courseDAO.deleteEntity(id, Constant.ENTITY_COURSE);
    }

    public List<Course> searchCourseByName(String name) {
        return courseDAO.searchCourseByName(name);
    }

    public List<Course> getCoursesSort(String sortBy, String order) {
        return courseDAO.sortCourses(sortBy, order);
    }
}
