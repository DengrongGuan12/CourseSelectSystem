package rmi;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import businesslogic.ClassTeaBL;
import businesslogic.DeanTeaBL;
import businesslogic.StudentBL;
import businesslogic.TrainingTeaBL;

@SuppressWarnings("serial")
public class RMIServer implements Serializable {
	public static void main(String args[]) throws MalformedURLException,
			RemoteException {

		try {
			LocateRegistry.createRegistry(1099);
			ClassTeaBL classTeaBL = new ClassTeaBL();
			DeanTeaBL deanTeaBL = new DeanTeaBL();
			StudentBL studentBL = new StudentBL();
			TrainingTeaBL trainingTeaBL = new TrainingTeaBL();
			Naming.bind("classTeaBL", classTeaBL);
			Naming.bind("deanTeaBL", deanTeaBL);
			Naming.bind("studentBL", studentBL);
			Naming.bind("trainingTeaBL", trainingTeaBL);
			System.out.println("Server is ready.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Server failed.");
		}
	}
}