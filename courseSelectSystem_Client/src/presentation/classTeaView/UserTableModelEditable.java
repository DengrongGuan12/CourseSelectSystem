package presentation.classTeaView;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


import common.Student;
import common.User;
//未使用！！！
public class UserTableModelEditable extends AbstractTableModel{


	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	Vector colums;
	
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserTableModelEditable(ArrayList<User> users,ArrayList<String> colName){
		// 初始化列
		this.colums = new Vector();

		this.rows = new Vector<Vector>();
		for(int i=0;i<colName.size();i++){
			this.colums.add(colName.get(i));
			
		}
		
		for(int j=0;j<users.size();j++){
			Student s=(Student)users.get(j);
			Vector temp=new Vector();
			temp.add(s.getNum());
			temp.add(s.getName());
			temp.add(s.getDean());
			temp.add(s.getScore());
			this.rows.add(temp);
			
		}
	
		
		
		
	}
	
	public boolean isCellEditable(int row,int column){
		return true;
		
	}
	

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colums.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Vector)rows.get(arg0)).get(arg1);
	}


	@Override
	//让表有列名
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return this.colums.get(arg0).toString();
	}




	

}
