package presentation.tableModel;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import common.TeachingPro;

public class TeachingProTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	Vector columns;
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TeachingProTableModel (ArrayList<TeachingPro> teachingPro,ArrayList<String> columnName) {
		this.columns = new Vector();
		this.rows = new Vector<Vector>();
		
		for (int i = 0; i < columnName.size(); i++) {
			this.columns.add(columnName.get(i));
		}
		for (int j = 0; j < teachingPro.size(); j++) {
			Vector temp = new Vector();
			if (teachingPro.get(j).getCourseName() != null) {
				temp.add(teachingPro.get(j).getCourseName());
			} 
			if (teachingPro.get(j).getCourseCredit() != null) {
				temp.add(teachingPro.get(j).getCourseCredit());
			} 
			if (teachingPro.get(j).getCourseDean() != null) {
				temp.add(teachingPro.get(j).getCourseDean());
			}
			
			this.rows.add(temp);
		}
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columns.size();
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
	// 让表有列名
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return this.columns.get(arg0).toString();
	}

}
