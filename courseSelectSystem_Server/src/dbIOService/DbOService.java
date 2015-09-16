package dbIOService;

import common.Message;

public interface DbOService {
	public Message strategyOutput();
	public Message teacherOutput(String limitType, String limitation);
	public Message deanTeaOutput();
	public Message studentOutput(String limitType,String limitation);
	public Message courseSelectOutput(String limitType,String limitation);
	public Message teachingProOutput(String limitType,String limitation);
	public Message courseOutput(String limitType,String limitation);
	public Message outputCourseDetail(String courseNum);
	public Message outputCourseOfStudents(String courseID);
}
