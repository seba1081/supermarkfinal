package Super;
import java.sql.*;
import java.util.Scanner; 
public class Main2 {

	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/supermark";
	static final String USER="root";
	static final String PASS="1234";
	public static void main(String args[]) throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		int op;
		//System.out.println("CONECTANDO A LA BASE DE DATOS...");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		//System.out.println("CREANDO EL STATEMMENT....");		
		stmt=conn.createStatement();
		System.out.println("   BIENVENIDO A -->>SUPERMARK<<--");
		do {
			System.out.println(" POR FAVOR INGRESE UNA OPCION: ");
			System.out.println(" 1 para iniciar sesion");
			System.out.println(" 2 para registrarte");
			System.out.println(" 3 para salir");
			Scanner teclado = new Scanner(System.in);
			op=teclado.nextInt();
			switch (op) {

			  case 1:iniciarSesion(stmt);
	  			System.out.println("por favor eliga una opcion");
	  			System.out.println("1 para ver los productos disponibles");
	  			System.out.println("2 para ver su carrito de compras");
	  			System.out.println("3 para salir");
	  			break;
			  case 2:registrarse();
			    break;
			  case 3:
				  break;
			  default:
				  System.out.println("por favor ingrese una opcion ");
			  }

		}while(op!=3);
		System.out.println("FIN");

		//productos.productos(stmt);
		conn.close();
		stmt.close();
	

	} 			////////////////////////fina main//////////////////////////////
	
	private static void registrarse() {
		// TODO Auto-generated method stub
		
	}
	private static void iniciarSesion(Statement st) throws SQLException {
		String nu,co,sql;
		Statement stmt=st;
		System.out.println("               --->>SUPERMARK<<----");
		System.out.println("Ingresa el nombre de ususario: ");
		Scanner teclado = new Scanner(System.in);
		nu=teclado.next();
		System.out.println("Ingresa la contraseña:  ");
		co=teclado.next();
		sql="select c.nombre as nom, c.apellido as ape from clientes c inner join usuarios u on c.codigo=u.codigo where u.nick='"+nu+"' and contrasena='"+co+"'";
		//System.out.println(sql);
		ResultSet rs=stmt.executeQuery(sql);
		if (rs.next()) {
		String name=rs.getNString("nom");
		String apellido=rs.getString("ape");
		System.out.println("bienvenido: "+name+" "+apellido);
		
		
		}else System.out.println("ERROR en usuario o contraseña ");
		//teclado.close(); // me provoca error en el main linea 25
	}				///fin iniciarSesion///////////
	
	
	private static void loguearse(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String u,c;
		Scanner teclado = new Scanner(System.in);
			System.out.println("ingresa el nombre de ususario");
			u=teclado.next();
			System.out.println("ingresa la contraseña");
			c=teclado.next();
			Statement stmt = st;
			String sql;
			sql="select * from usuario";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String name=rs.getNString("nombre");
				String descripcion=rs.getString("descripcion");
				System.out.println("nombre: "+name+" descripcion: "+descripcion);
				}	
	}

}








