package response;

import com.session2.model.Course;

public class CourseRest extends Course {
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
