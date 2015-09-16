package presentation.tableModel;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import common.Student;

public class StudentTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	Vector colums;
	
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StudentTableModel(ArrayList<Student> students,ArrayList<String> colName){
		// 初始化列
		this.colums = new Vector();

		this.rows = new Vector<Vector>();
		for(int i=0;i<colName.size();i++){
			this.colums.add(colName.get(i));
			
		}
		for(int j=0;j<students.size();j++){
			Vector temp=new Vector();
			if(students.get(j).getNum()!=null){
				temp.add(students.get(j).getNum());
			}
			if(students.get(j).getName()!=null){
				temp.add(students.get(j).getName());
			}
			if(students.get(j).getSex()!=null){
				temp.add(students.get(j).getSex());
			}
			if(students.get(j).getYear()!=null){
				temp.add(students.get(j).getYear());
			}
			if(students.get(j).getAddress()!=null){
				temp.add(students.get(j).getAddress());
			}
			if(students.get(j).getHomeNumber()!=null){
				temp.add(students.get(j).getHomeNumber());
			}
			if(students.get(j).getPhoneNumber()!=null){
				temp.add(students.get(j).getPhoneNumber());
			}
			if(students.get(j).getEmail()!=null){
				temp.add(students.get(j).getEmail());
			}
			if(students.get(j).getDean()!=null){
				temp.add(students.get(j).getDean());
			}
			if(students.get(j).getCredit()!=null){
				temp.add(students.get(j).getCredit());
			}
			if(students.get(j).getCreOfOption()!=null){
				temp.add(students.get(j).getCreOfOption());
			}
			
			
			
			
			
			this.rows.add(temp);
			
		}
		
		
		
		
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
