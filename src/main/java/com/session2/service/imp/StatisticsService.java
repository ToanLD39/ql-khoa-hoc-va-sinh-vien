package com.session2.service.imp;

import java.util.List;

import com.session2.dao.IStatisticsDAO;
import com.session2.dao.imp.StatisticsDAO;
import com.session2.model.Course;
import com.session2.service.IStatisticsService;

import response.CourseStudentRest;

public class StatisticsService implements IStatisticsService {
    private IStatisticsDAO statisticsDAO = new StatisticsDAO();

    public List<Integer> countCourseAndStudent() {
        return statisticsDAO.countCourseAndStudent();
    }

    public List<CourseStudentRest> getCourseStudentStatistics() {
        return statisticsDAO.getCourseStudentStatistics();
    }

    public List<Course> getTop5CoursesHaveMoreStudent() {
        return statisticsDAO.getTop5CoursesHaveMoreStudent();
    }

    public List<Course> getCourseHaveMore10Students() {
        return statisticsDAO.getCourseHaveMore10Students();
    }

}
