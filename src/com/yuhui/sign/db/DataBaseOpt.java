package com.yuhui.sign.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class DataBaseOpt {
	private static final String URL = "jdbc:mysql://139.196.104.98:3306/signatrue?autoReconnect=true";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String user = "root";
	private static final String pwd = "zxc,.888";
	
	private Connection conn = null;
	
	public String tableName = "";
	
	private static DataBaseOpt baseOpt = new DataBaseOpt();
	public static DataBaseOpt getInstance(){
		return baseOpt ;
	}
	
	private DataBaseOpt(){
		if(conn == null){
			connectDataBase();
		}
	}
	public void connectDataBase(){
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, user, pwd);
			
//			createUserTalbe();
//			createDownloadTalbe();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	private void tryConnect(){
		try {
			
			if(null == conn){
				connectDataBase();
			}
			
			if(null != conn && conn.isClosed()){
				connectDataBase();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String query(String sql){
		
		tryConnect();
		
		String result = "";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			result = createJson(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JsonArray queryJson(String sql){
		
		tryConnect();
		
		JsonArray array = new JsonArray();
				
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			array = createJsonArray(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
	}
	
	
	
	public void insertLoanInfo(String name,String phone,String idcard,String data,long time){
		
		tryConnect();
		
		String sql = "insert into `signatrue`.`loaninfo` (`name`, `phone`, `idcard`, `data`,`time`) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, idcard);
			ps.setString(4, data);
			ps.setLong(5, time);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void insertDown(String filename,String path,String data,String phone,String idcard,long time){
		
		tryConnect();
				
		String sql = String.format("insert into download ( filename , path , data , phone , idcard,time) values (?,?,?,?,?,?)",tableName);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, filename);
			ps.setString(2, path);
			ps.setString(3, data);
			ps.setString(4, phone);
			ps.setString(5, idcard);
			ps.setLong(6, time);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateToken(String token,String account){
		
		tryConnect();
		
		String sql = "UPDATE user set token = '"+token+"'"+" where account = '"+account+"'";
		try {
			Statement st = conn.prepareStatement(sql);
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateAccess(int count,String ip,String phone){
		
		tryConnect();
		
		String sql = "UPDATE access set count = '%s',ip='%s',phone='%s' where ip = '%s' or phone = '%s'";
		sql = String.format(sql, count,ip,phone,ip,phone);
		
		try {
			Statement st = conn.prepareStatement(sql);
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String createJson(ResultSet rs){
		String json = "";
		try {
			
			ResultSetMetaData data = rs.getMetaData();
			String col[] = new String[data.getColumnCount()];
			
			for (int i = 1; i <= data.getColumnCount(); i++) {
				col[i-1] = data.getColumnName(i);
			}
			JsonArray ja = new JsonArray();
			while(rs.next()){
				JsonObject jo = new JsonObject();
				for (int i = 1; i <= col.length; i++) {
					Object obj = rs.getObject(i);
					if(obj instanceof Integer){
						jo.addProperty(col[i-1], (int)obj);
					}else if(obj instanceof Long){
						jo.addProperty(col[i-1], (long)obj);
					}else{
						jo.addProperty(col[i-1], rs.getString(i));
					}
				}
				ja.add(jo);
			}
			json = ja.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//rs.getFetchSize()
		return json;
	}
	
	
	private JsonArray createJsonArray(ResultSet rs){
		
		JsonArray ja = new JsonArray();

		try {
	
			ResultSetMetaData data = rs.getMetaData();
			String col[] = new String[data.getColumnCount()];
			
			for (int i = 1; i <= data.getColumnCount(); i++) {
				col[i-1] = data.getColumnName(i);
			}
			while(rs.next()){
				JsonObject jo = new JsonObject();
				for (int i = 1; i <= col.length; i++) {
					Object obj = rs.getObject(i);
					if(obj instanceof Integer){
						jo.addProperty(col[i-1], (int)obj);
					}else if(obj instanceof Long){
						jo.addProperty(col[i-1], (long)obj);
					}else{
						jo.addProperty(col[i-1], rs.getString(i));
					}
				}
				ja.add(jo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//rs.getFetchSize()
		return ja;
	}
	

		

	
	public void createTalbe(String name){
		
		String sql = "create table IF NOT EXISTS %s("
				+ "id INT NOT NULL AUTO_INCREMENT,"
				+ "source VARCHAR(50),data text,"
				+ "time BIGINT,"
				+ "p_type INT,"
				+ "phone text,"
				+ "PRIMARY KEY (id))";
		sql = String.format(sql, name);
		try {
			Statement st = conn.prepareStatement(sql);
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
