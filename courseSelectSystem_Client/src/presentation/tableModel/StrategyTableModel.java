package presentation.tableModel;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import common.*;

public class StrategyTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	Vector colums;
	
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StrategyTableModel(ArrayList<Dean> dean,ArrayList<String> colName){
		// 初始化列
		this.colums = new Vector();

		this.rows = new Vector<Vector>();
		for(int i=0;i<colName.size();i++){
			this.colums.add(colName.get(i));
			
		}
		for(int j=0;j<dean.size();j++){
			Vector temp=new Vector();
			if(dean.get(j).getName()!=null){
				temp.add(dean.get(j).getName());
			}
			if(dean.get(j).getZhunru()!=null){
				temp.add(dean.get(j).getZhunru());
			}
			if(dean.get(j).getZhunchu()!=null){
				temp.add(dean.get(j).getZhunchu());
			}
			if(dean.get(j).getGraduate()!=null){
				temp.add(dean.get(j).getGraduate());
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
