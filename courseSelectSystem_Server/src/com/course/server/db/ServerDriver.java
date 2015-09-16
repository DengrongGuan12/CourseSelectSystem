package com.course.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerDriver {

	/**
	 * @param args
	 */
	
	public boolean insert(String num,String name,String passwd){
		boolean b=false;
		SqlHelper sqlHelper=new SqlHelper();
		String sql="insert into Teacher values(?,?,?)";
		String [] paras={num,name,passwd};
		if(sqlHelper.exeUpdate(sql, paras)){
			b=true;
		}else {
			b=false;
		}
		sqlHelper.close();
		return b;
	}
	
	public boolean delete(String num){
		boolean b=false;
		SqlHelper sqlHelper=new SqlHelper();
		try {
			if(!find(num).next()){
				b=false;
				
			}else {
				String sql="delete from Teacher where num=?";
				String []paras={num};
				sqlHelper.exeUpdate(sql, paras);
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqlHelper.close();
		return b;
	}
	
	public boolean update(String num,String passwd){
		boolean b=false;
		SqlHelper sqlHelper=new SqlHelper();
		try {
			if(!find(num).next()){
				b=false;
			}else {
				String sql="update Teacher set passwd=? where num=?";
				String []paras={passwd,num};
				sqlHelper.exeUpdate(sql, paras);
				b=true;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqlHelper.close();
		return b;
	}
	
	public ResultSet find(String num){
		ResultSet rs;
		SqlHelper sqlHelper=new SqlHelper();
		String sql="select name,passwd from Teacher where num=?";
		String[]paras={num};
		rs=sqlHelper.query(sql, paras);
		
 		return rs;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerDriver serverDriver=new ServerDriver();
		if(serverDriver.update("00124","1234")){
			System.out.println("T");
		}else {
			System.out.println("F");
		}
		
		
		

	}

}
