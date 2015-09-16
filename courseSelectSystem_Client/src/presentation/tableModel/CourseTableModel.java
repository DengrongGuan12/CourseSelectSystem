package presentation.tableModel;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import common.*;

public class CourseTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	Vector colums;
	
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CourseTableModel(ArrayList<Course> courses,ArrayList<String> colName){
		// 初始化列
		this.colums = new Vector();

		this.rows = new Vector<Vector>();
		for(int i=0;i<colName.size();i++){
			this.colums.add(colName.get(i));
			
		}
		for(int j=0;j<courses.size();j++){
			Vector temp=new Vector();
			if(courses.get(j).getNum()!=null){
				temp.add(courses.get(j).getNum());
			}
			if(courses.get(j).getName()!=null){
				temp.add(courses.get(j).getName());
			}
			if(courses.get(j).getPlace()!=null){
				temp.add(courses.get(j).getPlace());
			}
			if(courses.get(j).getTime()!=null){
				temp.add(courses.get(j).getTime());
			}
			if(courses.get(j).getTeaName()!=null){
				temp.add(courses.get(j).getTeaName());
			}
			if(courses.get(j).getCredit()!=null){
				temp.add(courses.get(j).getCredit());
			}
			if(courses.get(j).getDean()!=null){
				temp.add(courses.get(j).getDean());
			}
			if(courses.get(j).getLimit()!=null){
				temp.add(courses.get(j).getLimit());
			}
			if(courses.get(j).getProperty()!=null){
				temp.add(courses.get(j).getProperty());
			}
			if(courses.get(j).getScore()!=null){
				temp.add(courses.get(j).getScore());
				
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
