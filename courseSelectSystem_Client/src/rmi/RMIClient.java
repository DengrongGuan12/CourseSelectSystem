package rmi;

import java.net.MalformedURLException;
import java.rmi.*;



import businesslogicservice.ClassTeaBLService;
import businesslogicservice.DeanTeaBLService;
import businesslogicservice.StudentBLService;
import businesslogicservice.TrainingTeaBLService;


public class RMIClient{
	StudentBLService studentBLService;
	DeanTeaBLService deanTeaBLService;
	ClassTeaBLService classTeaBLService;
	



	
	TrainingTeaBLService trainingTeaBLService;
	String ip="127.0.0.1";
	
	public ClassTeaBLService getClassTeaBLService() throws MalformedURLException,RemoteException, NotBoundException{
		try {
			classTeaBLService=(ClassTeaBLService)Naming.lookup("rmi://"+ip+"/classTeaBL");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return classTeaBLService;
	}
	
	public StudentBLService getStudentBLService() throws MalformedURLException,RemoteException, NotBoundException{
		
		try {
			studentBLService= (StudentBLService) Naming.lookup("rmi://"+ip+"/studentBL");  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return studentBLService;
	}

	

	public DeanTeaBLService getDeanTeaBLService() throws MalformedURLException,RemoteException, NotBoundException{
		try {
			deanTeaBLService=(DeanTeaBLService) Naming.lookup("rmi://"+ip+"/deanTeaBL");  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return deanTeaBLService;
	}

	

	

	

	public TrainingTeaBLService getTrainingTeaBLService() throws MalformedURLException,RemoteException, NotBoundException{
		try {
			trainingTeaBLService= (TrainingTeaBLService) Naming.lookup("rmi://"+ip+"/trainingTeaBL");  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return trainingTeaBLService;
	}

	
	public  RMIClient() throws RemoteException {
		super();
		
		
	}
	
//	 public static void main(String[] args){
//		 try {  
//				System.out.println("我在获取对象！");
//				studentBLService= (StudentBLService) Naming.lookup("//127.0.0.1/studentBL");  
//	            System.out.print("对象获取成功！");
//	            String sql = "select name,passwd from Student  where num=?";
//				String[] paras = {"121250036"};
			   
			  
			   
			 /*  String sql1="insert into SelRecord values(?,?,?)";
			   String []paras1={"121250037","002","80"};
			   if(ds.exeUpdate(sql1, paras1)){
				   System.out.println("success");
			   }else {
				System.out.println("fail");
			}*/
				
//	        } catch(Exception e){
//	        	e.printStackTrace();
//	        }
//	 }
	 
	 
}
