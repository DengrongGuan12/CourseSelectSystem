package businesslogicservice_driver;

import java.rmi.RemoteException;

import common.Message;
import common.MessageType;

import businesslogicservice.ClassTeaBLService;

public class ClassTeaBL_Driver {
	public void drive(ClassTeaBLService classTeaBLService){
		Message message=new Message();
		try {
			message=classTeaBLService.ChangePasswd("003", "12345");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.tea_log_success)){
			System.out.println("登录成功！");
		}else{
			System.out.println("用户名或密码错误！");
		}
		try {
			message=classTeaBLService.ChangePasswd("003", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.tea_changePasswd_success)){
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}
		try {
			message=classTeaBLService.inputCourseDetailInfo("1001", "详细信息");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.tea_inputDetailCInfo_success)){
			System.out.println("输入成功!");
		}else {
			System.out.println("输入失败!");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
