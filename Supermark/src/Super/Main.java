package Super;
import java.sql.*;
import java.util.Scanner; 
public class Main {
	public static String nombreSesion;
	public static String tipo;
	public static int code;
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
		do {
			System.out.println("   BIENVENIDO A -->>SUPERMARK<<--");
			System.out.println(" POR FAVOR INGRESE UNA OPCION: ");
			System.out.println(" 1 para iniciar sesion");
			System.out.println(" 2 para registrarte");
			System.out.println(" 0 para salir");
			Scanner teclado = new Scanner(System.in);
			op=teclado.nextInt();
			switch (op) {

			  case 1:int codigoU =SesionStart(stmt);
			  if(codigoU!=-1) {
				  System.out.println("Bienvenido "+nombreSesion);
				  String sql="drop table if exists carrito";
				  stmt.execute(sql);
				  sql="create table carrito(codigo int unique,cantidad int)";
				  stmt.execute(sql);
				  sql="create or replace view pc as select p.*, p.stock as nstock, c.cantidad from productos p  left join carrito c on p.codigo =c.codigo";
				  stmt.execute(sql);
				  sql="create or replace view pcf as select codigo, nombre, descripcion, precio, stock-cantidad as nstock, cantidad from pc where cantidad is not null\r\n"
				  		+ "union select codigo, nombre, descripcion, precio, nstock, cantidad from pc where cantidad is null";
				  stmt.execute(sql);
				  carrito.dis=30-carrito.tamaño(stmt);

				  
				  
		  			if (tipo.equals("a")) {//pregunto si es a de administrador
		  			int oa=0;
		  				do {
		  			System.out.println("    ADMINISTRADOR   ");
		  			System.out.println("  Por favor elija una opcion");
		  			System.out.println("	1 para ver los productos");
		  			System.out.println("	2 para agregar un nuevo producto");
		  			System.out.println("	3 para modificar un producto");
		  			System.out.println("	4 para eliminar un producto");
		  			System.out.println("	0 para salir");
					oa=teclado.nextInt();
					switch (oa) {
					case 1:
						productos.productosMostrar(stmt);
						break;
					case 2:
						admin.agregarProducto(stmt);
						break;
					case 3:	
						admin.modificarProducto(stmt);
					break;
					case 4:	
						admin.eliminarProducto(stmt);
					break;
					
					default:
						break;
					}
					
					
		  			}while(oa!=0);
					}
		  			if (tipo.equals("c")) {//pregunto si es c de cliente
				  do {
					  carrito.dis=30-carrito.tamaño(stmt);
					System.out.println("                                           Carrito("+carrito.tamaño(stmt)+")");
		  			System.out.println("  Por favor elija una opcion");
		  			System.out.println("	1 para ver los productos disponibles");
		  			System.out.println("	2 para ver su carrito de compras");
		  			System.out.println("	0 para salir");
					int ou=teclado.nextInt();
					switch (ou) {
					case 1:
						int ou2=0;
						do {
						System.out.println("Lista de productos: ");	
						productos.productosMostrar(stmt);
						System.out.println("                                           Carrito("+carrito.tamaño(stmt)+")");
						carrito.dis=30-carrito.tamaño(stmt);
			  			System.out.println("  Por favor elija una opcion");
			  			System.out.println("	1 para agregar un producto al carro");
			  			System.out.println("	0 para volver");
						ou2=teclado.nextInt();
						if (ou2==1) {
							carrito.dis=30-carrito.tamaño(stmt);
							System.out.println("                                           Carrito("+carrito.tamaño(stmt)+")");
							System.out.println("por favor ingrese el codigo del producto");
							int cod=teclado.nextInt();
							sql="select * from productos where codigo="+cod;
							ResultSet rs=stmt.executeQuery(sql);
							if (rs.next()) {
							carrito.agregar(cod,stmt);
							}else {
								System.out.println("el codigo: "+cod+" no pertenece a ningun producto");
								carrito.esperar(2000);
								System.out.println(" ");
							}
						}
						}while(ou2!=0);
						
						break;
					case 2:
						int ou3=0;
						do {
							carrito.dis=30-carrito.tamaño(stmt);	
						System.out.println("                                           Carrito("+carrito.tamaño(stmt)+")");
						System.out.println("Lista de productos de tu carrito");
						carrito.mostrar(stmt);
						System.out.println(" ");
			  			System.out.println("  Por favor elija una opcion");
			  			System.out.println("	1 PARA CONFIRMAR -->>LA COMPRA<<--");
			  			System.out.println("	2 para quitar un producto del carro");
			  			System.out.println("	0 para volver");
						ou3=teclado.nextInt();
						switch (ou3) {
						case 1:
							if (carrito.tamaño(stmt)!=0) {
							String sqls=ventas.preparaInsert(stmt,code);
							ventas.nuevaVenta(stmt,sqls);
							productos.actualizarVenta(stmt);
							carrito.carrito(stmt);
							}else System.out.println("carrito vacio");
							break;
						case 2:
							carrito.dis=30-carrito.tamaño(stmt);
							System.out.println("                                           Carrito("+carrito.tamaño(stmt)+")");
							System.out.println("por favor ingrese el codigo del producto");
							int cod=teclado.nextInt();
							carrito.quitar(cod,stmt);
							
							break;

						default:
							break;
						}
						
						
						}while(ou3!=0);

						break;
					case 0:
						codigoU=0;
						break;
					default:
						break;
					}

				  }while(codigoU!=0);
				  
			  }}else {
				  System.out.println("USUARIO o CONTRASEÑA INCORRECTA");
				  System.out.println();
			  }

	  			break;
			  case 2: usuario.registrarse(stmt);
			    break;
			  case 3:
				  break;
			  default:
			  }

		}while(op!=0);
		System.out.println("FIN");

		conn.close();
		stmt.close();
	

	} 			////////////////////////fina main//////////////////////////////
	
	
	private static void registrarse() {
		// TODO Auto-generated method stub
		
	}
	
	private static int SesionStart(Statement st) throws SQLException {
		String nu,co,sql;
		Statement stmt=st;
		System.out.println("               --->>SUPERMARK<<----");
		System.out.println("Ingresa el nombre de usuario: ");
		Scanner teclado = new Scanner(System.in);
		nu=teclado.next();
		System.out.println("Ingresa la contraseña:  ");
		co=teclado.next();
		sql="create or replace view usuario as select c.codigo as codigo, c.nombre as nombre, c.apellido as apellido, u.nick as nick, u.tipo as tipo from clientes c inner join usuarios u on c.codigo=u.codigo where u.nick='"+nu+"' and contrasena='"+co+"'";
		stmt.execute(sql);
		sql="select * from usuario";
		//System.out.println(sql);
		ResultSet rs=stmt.executeQuery(sql);
		if (rs.next()) {
		int codigo=rs.getInt("codigo");
		nombreSesion=rs.getString("nombre");
		tipo=rs.getString("tipo");
		code=codigo;
		return codigo; 
		}else return -1; // retorna - cuando no se logra conectar por que el usuario o contraseña incorrecta
		
		//teclado.close(); // me provoca error en el main linea 25
	}				///fin iniciarSesion///////////
	
	
	private static void loguearse(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String u,c;
		Scanner teclado = new Scanner(System.in);
			System.out.println("ingresa el nombre de usuario");
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








