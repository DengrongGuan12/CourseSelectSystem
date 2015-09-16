package dbIOService;

import common.*;

public interface DbUService {
	public boolean strategyUpdate(Message message);
	public boolean courseUpdate(Message message);
	public boolean passwordUpdate(Message message);
}
