package dbIOService;

import common.Message;

public interface DbIService {
	public boolean strategyInput(Message message);
	public boolean teacherInput(Message message);
	public boolean deanTeaInput(Message message);
	public boolean studentInput(Message message);
	public boolean courseSelectInput(Message message);
	public boolean courseInput(Message message);
	public boolean teachingProInput(Message message);
	public void selRecordInput(Message message);
}
