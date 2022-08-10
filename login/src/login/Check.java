package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Check {
	
		private Connection conn() throws Exception {
			
			String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
			Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 데이터베이스 드라이버를 로딩한다
			return DriverManager.getConnection(url,"scott","tiger");
		}
		
		private void connClose(ResultSet rs,Statement stmt,Connection con) 
		{
		try {if(rs!=null)rs.close();} catch (SQLException e) {}
		try	{if(stmt!=null)stmt.close();} catch (SQLException e) {}
		try	{if(con!=null)con.close();} catch (SQLException e) {}
		}
	
		
		int login(String id, String pw) {
			
			String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
			int flag=0;
			ResultSet rs = null;
			Connection con = null;
			PreparedStatement stmt = null;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 데이터베이스 드라이버를 로딩한다
				con = DriverManager.getConnection(url,"scott","tiger"); // 2. 연결하여 커넥션 객체를 생성한다
				stmt = con.prepareStatement("Select * from TBL_USER where id=?");
				stmt.setString(1,id);
				rs=stmt.executeQuery();
				
//			    rs=stmt.executeQuery("select * from tbl_user where id='"+id+"'"); //sql문 실행하기 (select)실행하기;
			    
			    if(rs.next()) {//한 행이 있다면(=, 아이디가 있다면)
			    	String dbPw=rs.getString("pw");
			    	if(pw.equals(dbPw))
			    		flag=3;
			    	else
			    		flag=2;
			    }else
			    	flag=1;
			    
			    while(rs.next()) {
			     System.out.println(rs.getInt("grade")+"   "+rs.getInt(2)+"   "+rs.getInt(3));
			    }
			    
				
			} catch (Exception e) {
				System.out.println();
				e.printStackTrace();
			} finally {
				connClose(rs,stmt,con);
				} 

			return flag;
			}

		 void signUp(String id, String pw, String name) {
			 
			 String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
				int flag=0;

				Connection con = null;
				Statement stmt = null;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 데이터베이스 드라이버를 로딩한다
					con = DriverManager.getConnection(url,"scott","tiger"); // 2. 연결하여 커넥션 객체를 생성한다
					String sql="INSERT INTO TBL_USER (ID, PW, NAME) VALUES ('"+id+"', '"+pw+"',"+"'"+name+"')";
					System.out.println("아이디='"+id+"'");
					System.out.println("패스워드='"+pw+"'");
					System.out.println("이름='"+name+"'");
					System.out.println("계정이 생성되었습니다");

					stmt = con.createStatement();
					
				    stmt.executeUpdate(sql); //sql문 실행하기 (select)실행하기;
	
					
				} catch (Exception e) {
					System.out.println();
					e.printStackTrace();
				} finally {
					connClose(null,stmt,con);
					} 
			 
		 }
		 
		 int checkid(String id, String pw) {
			 
				String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
				int flag=0;
				ResultSet rs = null;
				Connection con = null;
				PreparedStatement stmt = null;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 데이터베이스 드라이버를 로딩한다
					con = DriverManager.getConnection(url,"scott","tiger"); // 2. 연결하여 커넥션 객체를 생성한다
					stmt = con.prepareStatement("Select * from TBL_USER where id=?");
					stmt.setString(1,id);
					rs=stmt.executeQuery();
					
				    if(rs.next()) {//한 행이 있다면(=, 아이디가 있다면)
				    	String dbPw=rs.getString("pw");
				    	if(pw.equals(dbPw))
				    		flag=3; //로그인 성공
				    	else
				    		flag=2; //pw 오류
				    }else
				    	flag=1; //id 오류
				    
				    while(rs.next()) {
				     System.out.println(rs.getInt("grade")+"   "+rs.getInt(2)+"   "+rs.getInt(3));
				    }
				    
					
				} catch (Exception e) {
					System.out.println();
					e.printStackTrace();
				} finally {
					connClose(rs,stmt,con);
					} 

				return flag;
			 
		 }
		 
		 void del (String id) {
			 
			 String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
				int del=0;

				Connection con = null;
				Statement stmt = null;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 데이터베이스 드라이버를 로딩한다
					con = DriverManager.getConnection(url,"scott","tiger"); // 2. 연결하여 커넥션 객체를 생성한다
					String sql="DELETE FROM TBL_USER WHERE id='"+id+"'";
					System.out.println(id+" "+"계정은 삭제되었습니다.");
					stmt = con.createStatement();
					
				    stmt.executeUpdate(sql); //sql문 실행하기 (select)실행하기;
	
					
				} catch (Exception e) {
					System.out.println();
					e.printStackTrace();
				} finally {
					connClose(null,stmt,con);
					} 
			 
		 }

		 int helpid (String name) {
			 
				String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
				int flag=0;
				String dbid;
				ResultSet rs = null;
				Connection con = null;
				PreparedStatement stmt = null;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 데이터베이스 드라이버를 로딩한다
					con = DriverManager.getConnection(url,"scott","tiger"); // 2. 연결하여 커넥션 객체를 생성한다
					stmt = con.prepareStatement("Select * from TBL_USER where name=?");
					stmt.setString(1,name);
					rs=stmt.executeQuery();
					
				    if(rs.next()) {//한 행이 있다면(=, 이름이 있다면)
				    	String dbna=rs.getString("name");
				    	String inid=rs.getString("id");
				    	if(name.equals(dbna)) {
				    		flag=1; //매치되는 이름이 있음
				    		dbid=inid;
				    		System.out.println("'"+name+"'으로 가입된 id는'"+dbid+"'입니다.");
				    	}
				    
				    }else {
				    	flag=2;
			    		System.out.println("'"+name+"'으로 가입된 id는 없습니다.");//매치되는 이름이 없음
				    }
				    
				    while(rs.next()) {
				     System.out.println(rs.getInt("grade")+"   "+rs.getInt(2)+"   "+rs.getInt(3));
				    }
				    
					
				} catch (Exception e) {
					System.out.println();
					e.printStackTrace();
				} finally {
					connClose(rs,stmt,con);
					} 

				return flag;						

		 }
		 

		 int helppw(String id, String name) {
			 	String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
				int flag=0;
				String dbpw;
				ResultSet rs = null;
				ResultSet rs2 = null;
				Connection con = null;
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 데이터베이스 드라이버를 로딩한다
					con = DriverManager.getConnection(url,"scott","tiger"); // 2. 연결하여 커넥션 객체를 생성한다
					stmt = con.prepareStatement("Select * from TBL_USER where id=?");
					stmt.setString(1,id);
					stmt2 = con.prepareStatement("Select * from TBL_USER where name=?");
					stmt2.setString(1,name);
					rs=stmt.executeQuery();
					rs2=stmt2.executeQuery();
					
					  if(rs.next()&&rs2.next()) {//행에 id,이름이 둘다 있으면)
					    	String dbna=rs.getString("name");
					    	String dbid=rs.getString("id");
					    	if(name.equals(dbna)&&id.equals(dbid)) {
					    		flag=1; //매치되는 pw가 있음
					    		dbpw=rs.getString("pw");
					    		System.out.println("'"+name+"','"+"'"+id+"'로 가입된 계정의 pw는'"+dbpw+"'입니다.");
					    	} 
					    	while(rs.next()) {
							     System.out.println(rs.getInt("grade")+"   "+rs.getInt(2)+"   "+rs.getInt(3));
							    }
					  }
					  else {
				    		flag=2;
				    	} 
							} catch (Exception e) {
								System.out.println();
								e.printStackTrace();
							} finally {
								connClose(rs,stmt,con);
								} 
				return flag;

		 
}
		 
		 List<User> list() {
			
			 	String url = "jdbc:oracle:thin:@192.168.0.10:1521:xe";
				ResultSet rs = null;
				Connection con = null;
				PreparedStatement stmt = null;
				List list = new ArrayList();
				
			try {
				con = conn();
				stmt = con.prepareStatement("Select * from TBL_USER");
				rs=stmt.executeQuery();
				
				while (rs.next()) {
					User u = new User();
					u.id = rs.getString(1);
					u.pw = rs.getNString(2);
					list.add(u);
				}
			} catch(Exception ex) {
			    ex.printStackTrace();
			    System.out.println("설마 에러");
			} finally {
			    connClose(rs, stmt, con);
			}
				
				
			return list;
			 
		 }
		 
		 
}


