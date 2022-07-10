package Super;
import java.sql.*; 
public class conexion {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/supermark";
	static final String USER="root";
	static final String PASS="1234";
	public void conexion(String args[]) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		System.out.println("CONECTANDO A LA BASE DE DATOS...");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("CREANDO EL STATEMMENT....");
		stmt=conn.createStatement();
	}


}








