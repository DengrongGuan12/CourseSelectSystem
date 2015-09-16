package test;


import common.Message;
import common.MessageType;
import common.Student;

import businesslogicservice.StudentBLService;
import rmi.RMIClient;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RMIClient rmiClient=new RMIClient();
			StudentBLService studentBLService=rmiClient.getStudentBLService();
			Message message=new Message();
			message=studentBLService.checkUserLog("121250036", "gdr12");
			if(message.getMesType().equals(MessageType.stu_log_success)){
				Student s=(Student)message.getU();
				System.out.println("welcome "+s.getName());
				
			}else {
				System.out.println("Fail!");
				
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		

	}

}
