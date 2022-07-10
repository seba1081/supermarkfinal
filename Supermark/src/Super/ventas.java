package Super;
import java.sql.*;
import java.time.LocalDate;
public class ventas {
public ventas() {}

public static String preparaInsert(Statement st,int code) throws SQLException{
Statement stmt=st;
ResultSet rs;
LocalDate fecha=LocalDate.now();
String sql="select * from carrito";
String sql2="";
int codigo;
int cantidad;
rs=stmt.executeQuery(sql);
sql2="INSERT INTO `supermark`.`ventas` (`fecha`, `cc`, `cp`, `cantidad`) VALUES ";

while(rs.next()) {
	codigo=rs.getInt("codigo");
	cantidad=rs.getInt("cantidad");
	sql2=sql2+"('"+fecha+"', '"+code+"', '"+codigo+"', '"+cantidad+"'),";
	}
	return sql2.substring(0, sql2.length() - 1);
	
}

public static void nuevaVenta(Statement st, String sqls) throws SQLException {
	Statement stmt=st;
	stmt.execute(sqls);
}

}
