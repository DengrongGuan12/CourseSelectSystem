/**
 * 对数据库操作的类
 * 对数据库的操作,就是crud
 * 直接调用存储过程【后话】
 * 1.配置数据源：开始-控制面板-管理工具-数据源-用户DNS-添加-sql server-
 * -名称：mytest,服务器：. -方式：使用登录名和密码认证-spdb1- -完成-测试是否配置成功
 * 2.在程序中连接数据源
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.rmi.*;

import dbservice.DbService;

public class SqlHelper implements DbService {
	// 定义需要的对象
	PreparedStatement ps = null;
	ResultSet rs = null;

	Connection ct = null;
	String user = "sa";
	String passwd = "sa";
	String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	String url = "jdbc:odbc:myServer";

	// 构造函数，初始化ct
	public SqlHelper() {
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 把对数据库的增删改写一个函数
	public boolean exeUpdate(String sql, String[] paras) {
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			// 对参数赋值
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			b = false;
			e.printStackTrace();

		}
		return b;
	}

	public void trim(String sql) {
		try {
			ps = ct.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ResultSet query(String sql, String[] paras) {// 拆分sql语句
		try {
			ps = ct.prepareStatement(sql);
			// 对参数赋值
			for (int i = 0; i < paras.length; i++) {
				paras[i].trim();
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
		return rs;

	}

	public void deleteTable(String tableName) {
		try {
			String sql = "drop table " + tableName;
			ps = ct.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}

	}

	public boolean createTempRecordTable() {
		boolean b = true;
		try {
			String sql = "create table TempRecord("
					+ "grade char(1),sNum varchar(100),cNum varchar(100),creOfOption varchar(2))";
			ps = ct.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			b = false;
		}

		return b;

	}

	public boolean judgeTempExist() {
		boolean b = true;
		try {
			String sql = "select * from TempRecord ";
			ps = ct.prepareStatement(sql);
			ps.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
			b = false;

		}
		return b;
	}

	// 关闭资源的方法
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (ct != null) {
				ct.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws RemoteException {
		SqlHelper sqlHelper = new SqlHelper();
		String sql = "insert into Teacher values(?,?,?)";
		String[] paras = { "00127", "绍荣", "1234567" };
		if (sqlHelper.exeUpdate(sql, paras)) {
			System.out.println("添加OK");
		} else {
			System.out.println("添加失败");
		}

	}

}
