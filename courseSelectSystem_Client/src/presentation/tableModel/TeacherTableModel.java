package presentation.tableModel;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import common.*;

public class TeacherTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	Vector colums;
	
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TeacherTableModel(ArrayList<Teacher> teachers,ArrayList<String> colName){
		// 初始化列
		this.colums = new Vector();

		this.rows = new Vector<Vector>();
		for(int i=0;i<colName.size();i++){
			this.colums.add(colName.get(i));
			
		}
		for(int j=0;j<teachers.size();j++){
			Vector temp=new Vector();
			if(teachers.get(j).getNum()!=null){
				temp.add(teachers.get(j).getNum());
			}
			if(teachers.get(j).getName()!=null){
				temp.add(teachers.get(j).getName());
			}
			if(teachers.get(j).getSex()!=null){
				temp.add(teachers.get(j).getSex());
			}
			if(teachers.get(j).getDean()!=null){
				temp.add(teachers.get(j).getDean());
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
