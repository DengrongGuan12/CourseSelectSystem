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
			System.out.println("��¼�ɹ���");
		}else{
			System.out.println("�û������������");
		}
		try {
			message=classTeaBLService.ChangePasswd("003", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.tea_changePasswd_success)){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ�ܣ�");
		}
		try {
			message=classTeaBLService.inputCourseDetailInfo("1001", "��ϸ��Ϣ");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.tea_inputDetailCInfo_success)){
			System.out.println("����ɹ�!");
		}else {
			System.out.println("����ʧ��!");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
