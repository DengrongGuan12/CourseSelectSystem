/**
 * �����ݿ��������
 * �����ݿ�Ĳ���,����crud
 * ֱ�ӵ��ô洢���̡��󻰡�
 * 1.��������Դ����ʼ-�������-������-����Դ-�û�DNS-���-sql server-
 * -���ƣ�mytest,��������. -��ʽ��ʹ�õ�¼����������֤-spdb1- -���-�����Ƿ����óɹ�
 * 2.�ڳ�������������Դ
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.rmi.*;

import dbservice.DbService;

public class SqlHelper implements DbService {
	// ������Ҫ�Ķ���
	PreparedStatement ps = null;
	ResultSet rs = null;

	Connection ct = null;
	String user = "sa";
	String passwd = "sa";
	String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	String url = "jdbc:odbc:myServer";

	// ���캯������ʼ��ct
	public SqlHelper() {
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// �Ѷ����ݿ����ɾ��дһ������
	public boolean exeUpdate(String sql, String[] paras) {
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			// �Բ�����ֵ
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

	public ResultSet query(String sql, String[] paras) {// ���sql���
		try {
			ps = ct.prepareStatement(sql);
			// �Բ�����ֵ
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

	// �ر���Դ�ķ���
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
		String[] paras = { "00127", "����", "1234567" };
		if (sqlHelper.exeUpdate(sql, paras)) {
			System.out.println("���OK");
		} else {
			System.out.println("���ʧ��");
		}

	}

}
