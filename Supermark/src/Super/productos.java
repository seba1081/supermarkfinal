package Super;
import java.sql.*;

public class productos {
	int codigo;
	String nombre;
	String descripcion;
	String precio;
	int stock;
	public productos(int codigo,String nombre,String descripcion,String precio,int stock){
		this.codigo=codigo;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.precio=precio;
		this.stock=stock;
	}

	public static void productosMostrar(Statement st) throws SQLException {
		Statement stmt = st;
		String sql;
		sql="select * from pcf";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			int codigo=rs.getInt("codigo");
			String name=rs.getNString("nombre");
			String descripcion=rs.getString("descripcion");
			String precio=rs.getString("precio");
			int nstock=rs.getInt("nstock");
			System.out.println("Codigo: "+codigo+" "+name+" "+descripcion+"\t$"+precio+" cantidad: "+nstock);
			}

	}
	public static void productosMostrar(int cod,Statement st) throws SQLException {
		Statement stmt = st;
		String sql;
		sql="select * from pc where codigo="+cod;
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			int codigo=rs.getInt("codigo");
			String name=rs.getNString("nombre");
			String descripcion=rs.getString("descripcion");
			String precio=rs.getString("precio");
			int nstock=rs.getInt("nstock");
			System.out.println("Codigo: "+codigo+" "+name+" "+descripcion+"\t$"+precio+" cantidad: "+nstock);
			}

	}
	public String mostrar(productos pro) {
		String datos="codigo "+pro.codigo+" "+pro.nombre+" "+pro.descripcion+" "+pro.precio+" "+pro.stock;
		return datos;
	}

	public static void actualizarVenta(Statement stmt) throws SQLException {
		String sql="UPDATE productos INNER JOIN pcf ON productos.codigo= pcf.codigo SET productos.stock = pcf.nstock WHERE pcf.codigo= productos.codigo";
		stmt.execute(sql);
	}

	

}








