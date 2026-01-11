package response;

import com.session2.model.Student;

public class StudentRest extends Student {
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
