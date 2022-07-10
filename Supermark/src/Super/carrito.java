package Super;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class carrito {
	public static List<Integer> lista =new ArrayList<Integer>();
	public static HashMap<Integer,Integer> listaCarro= new HashMap<Integer,Integer>();
	public static int lleno;
	public static int dis;
	 
	public static void carrito(Statement st) throws SQLException{
		Statement stmt=st;
		lista.clear();
		listaCarro.clear();
		String sql="drop table if exists carrito";
		  stmt.execute(sql);
		  sql="create table carrito(codigo int unique,cantidad int)";
		  stmt.execute(sql);
		  
	}

	public static void agregar(int cod, Statement stmt) throws SQLException {
		int c=cod;
		int op;
		Statement stms=stmt;
		Scanner teclado = new Scanner(System.in);
		String sql="select * from pcf where codigo ="+c;
		ResultSet rs=stmt.executeQuery(sql);
		rs.next();
		String nombre=rs.getNString("nombre");
		String descripcion=rs.getString("descripcion");
		String precio=rs.getString("precio");
		int nstock=rs.getInt("nstock");
		do {
		System.out.println("seleccionó: "+nombre+" "+descripcion+"\tPrecio $"+precio+" Disponible:"+nstock);
		System.out.println("Ingrese la cantidad para agregar al carro o '0' para salir");
		
			op=teclado.nextInt();
			carrito.dis=30-carrito.tamaño(stmt);
			
			if (op<=dis) {	
			if (op<=nstock&&op>0) {
				lista.add(c);
				listaCarro.put(c,op);
				try {
					sql="INSERT INTO `supermark`.`carrito`(`codigo`,`cantidad`)VALUES("+c+","+op+")";
					stmt.execute(sql);
					}
					catch (SQLException e) {
						sql="UPDATE `supermark`.`carrito` SET cantidad ="+op+" WHERE codigo ="+c;
						stmt.execute(sql);
					}
				  sql="create or replace view pc as select p.*, p.stock as nstock, c.cantidad from productos p  left join carrito c on p.codigo =c.codigo";
				  stmt.execute(sql);
				  sql="create or replace view pcf as select codigo, nombre, descripcion, precio, stock-cantidad as nstock, cantidad from pc where cantidad is not null\r\n"
				  		+ "union select codigo, nombre, descripcion, precio, nstock, cantidad from pc where cantidad is null";
				  stmt.execute(sql);

				esperar(1000);
				System.out.println("Agregando al Carrito...");
				esperar(1000);
				System.out.println("se agrego-->> "+op+" "+nombre+" <<-- al carrito");
				op=0;
				esperar(1000);
				carrito.dis=30-carrito.tamaño(stmt);
				System.out.println("                                           Carrito("+carrito.tamaño(stmt)+")");
				esperar(1000);
			}else if(op==0) {
				
			} else System.out.println("		no hay "+op+" disponibles");
			esperar(1);
			}else {
				System.out.println("no puede exeder el limite de 30 productos");
			}
			
		}while(op!=0);
	}
	public static void esperar(int i) {
		int t=i;
        try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void quitar(int codigo,Statement st) throws SQLException{
		Statement stmt=st;
		int c=codigo;
		String sql="delete from carrito where codigo ="+c;
		stmt.execute(sql);
	}
	
	public static void mostrar(Statement st) throws SQLException {
		Statement stmt=st;
		int n=listaCarro.size();
		String sql;
		sql="select * from pcf where cantidad is not null ";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			int codigo=rs.getInt("codigo");
			String name=rs.getNString("nombre");
			String descripcion=rs.getString("descripcion");
			String precio=rs.getString("precio");
			String cantidad=rs.getString("cantidad");
			System.out.println("Codigo: "+codigo+" "+name+" "+descripcion+"\t$"+precio+" cantidad: "+cantidad);
			}
		
	}
	
	public static int tamaño(Statement st) throws SQLException {
		Integer t=0;
		Statement stmt=st;
		String sql="select sum(cantidad) as tamaño from carrito";
		ResultSet rs=stmt.executeQuery(sql);
		rs.next();
		t=rs.getInt("tamaño");
		return t;
	}
}
