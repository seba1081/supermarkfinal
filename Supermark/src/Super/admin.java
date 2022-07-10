package Super;
import java.sql.*;
import java.util.Scanner;
public class admin {
	public static Scanner teclado = new Scanner(System.in);
	public admin() {	
	}
	public static void agregarProducto(Statement st) throws SQLException{
		Statement stmt=st;
		int r=0;
		do {
		System.out.println("Ingrese el nombre del nuevo producto:");
		String nombre=teclado.next();
		System.out.println("Ingrese la descripcion del nuevo producto:");
		String descripcion=teclado.next();
		System.out.println("Ingrese el precio del nuevo producto:");
		float precio=teclado.nextFloat();
		System.out.println("Ingrese el stock del nuevo producto:");
		int stock=teclado.nextInt();
		System.out.println("confirme el nuevo producto:");
		System.out.println("nombre: "+nombre+" descripcion: "+descripcion+" precio: $"+precio+" Stock:"+stock);
		System.out.println("Ingrese 1 para CONFIRMAR");
		System.out.println("Ingrese 0 para CANCELAR");
		int ok=teclado.nextInt();
		if (ok==1) {
		String sql="INSERT INTO `supermark`.`productos` ( `nombre`, `descripcion`, `precio`, `stock`) VALUES ('"+nombre+"','"+descripcion+"','"+precio+"','"+stock+"')";
		stmt.execute(sql);
		System.out.print("agregando el nuevo producto");
		carrito.esperar(400);
		System.out.print(".");
		carrito.esperar(400);
		System.out.print(".");
		carrito.esperar(400);
		System.out.println(".");
		carrito.esperar(500);
		System.out.println("se agrego correctamente");
		carrito.esperar(1000);
		}else if(ok==0) {
			System.out.println("Se cancela en ingreso");
			carrito.esperar(1000);
		}else {
			System.out.println("ingreso "+ok+" no es valido, se cancela el ingreso del nuevo producto");
			carrito.esperar(1000);
			}
		System.out.println(" ");
		System.out.println("1 para Agregar otro producto");
		System.out.println("0 para volver");
		r=teclado.nextInt();
		}while(r!=0);
	}

	public static void modificarProducto(Statement st) throws SQLException {
		Statement stmt=st;
		int r=0;
		int m=0;
		int a=0;
		String codigo=null;
		String nnombre = null;
		String ndescripcion = null;
		do {
			System.out.println("Ingrese el codigo producto a modificar:");
			codigo=teclado.next();
			String sql="select * from productos where codigo ="+codigo;
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) {
			String nombre=rs.getNString("nombre");
			String descripcion=rs.getString("descripcion");
			float precio=rs.getFloat("precio");
			int stock=rs.getInt("stock");
			System.out.println("codigo= "+codigo);
			System.out.println(" Nombre: "+nombre+"  Descripcion: "+descripcion+"  Precio: "+precio+"  Stock: "+stock);
			System.out.println(" ");
			System.out.println("INGRESE 1 PARA MODIFICAR EL NOMBRE");
			System.out.println("INGRESE 2 PARA MODIFICAR LA DESCRIPCION");
			System.out.println("INGRESE 3 PARA MODIFICAR EL PRECIO");
			System.out.println("INGRESE 4 PARA MODIFICAR EL STOCK");
			System.out.println("INGRESE OTRO VALOR PARA SALIR");
			m=teclado.nextInt();
			carrito.esperar(500);
			switch (m) {
			case 1:
				System.out.println(" Por favor ingrese el nombre modificado: ");
				nombre=teclado.next();
				System.out.println("ingreso :  "+nombre);
				modificaNombre(stmt,codigo,nombre);
				carrito.esperar(500);
				System.out.println("Se modifico correctamente");
				carrito.esperar(500);
				break;
			case 2:
				System.out.println(" Por favor ingrese la descripcion: ");
				descripcion=teclado.next();
				modificaDescripcion(stmt,codigo,descripcion);
				break;
			case 3:
				System.out.println(" Por favor ingrese el nuevo precio: ");
				precio=teclado.nextFloat();
				modificarPrecio(stmt,codigo,precio);
				break;
			case 4:
				System.out.println(" Por favor ingrese la nueva cantidad de Stock: ");
				stock=teclado.nextInt();
				modificarStock(stmt,codigo,stock);
				break;
			default:
				break;
			}
				
		}else {
			System.out.println("	NO EXISTE PRODUCTO CON CODIGO: "+codigo);
			System.out.println("Ingrese 1 para agregar un porducto con codigo "+codigo);
			System.out.println("ingrese otro valor para omitir");
			a=teclado.nextInt();
			if (a==1) {
				System.out.println("Ingrese el nombre del nuevo producto:");
				String nombre=teclado.next();
				System.out.println("Ingrese la descripcion del nuevo producto:");
				String descripcion=teclado.next();
				System.out.println("Ingrese el precio del nuevo producto:");
				float precio=teclado.nextFloat();
				System.out.println("Ingrese el stock del nuevo producto:");
				int stock=teclado.nextInt();
				System.out.println("confirme el nuevo producto con codigo: "+codigo);
				System.out.println("nombre: "+nombre+" descripcion: "+descripcion+" precio: $"+precio+" Stock:"+stock);
				System.out.println("Ingrese 1 para CONFIRMAR");
				System.out.println("Ingrese 0 para CANCELAR");
				int ok=teclado.nextInt();
				if (ok==1) {
				sql="INSERT INTO `supermark`.`productos` (`codigo`, `nombre`, `descripcion`, `precio`, `stock`) VALUES ('"+codigo+"','"+nombre+"','"+descripcion+"','"+precio+"','"+stock+"')";
				stmt.execute(sql);
				System.out.print("agregando el nuevo producto");
				carrito.esperar(400);
				System.out.print(".");
				carrito.esperar(400);
				System.out.print(".");
				carrito.esperar(400);
				System.out.println(".");
				carrito.esperar(500);
				System.out.println("se agrego correctamente");
				carrito.esperar(1000);
				}else if(ok==0) {
					System.out.println("Se cancela en ingreso");
					carrito.esperar(1000);
				}else {
					System.out.println("ingreso "+ok+" no es valido, se cancela el ingreso del nuevo producto");
					carrito.esperar(1000);
					}
			}
			
		}
		} while (r!=0);
	}

		private static void modificarStock(Statement st, String codigo, int stock) throws SQLException {
			Statement stmt=st;
			String sql="UPDATE `supermark`.`productos` SET `stock` = '"+stock+"' WHERE (`codigo` = '"+codigo+"')";
			stmt.execute(sql);
		}
		private static void modificarPrecio(Statement st, String codigo, float precio) throws SQLException {
			String sql="UPDATE `supermark`.`productos` SET `precio` = '"+precio+"' WHERE (`codigo` = '"+codigo+"')";
			Statement stmt=st;
			stmt.execute(sql);
		
	}
		private static void modificaDescripcion(Statement st, String codigo, String descripcion) throws SQLException {
			String sql="UPDATE `supermark`.`productos` SET `descripcion` = '"+descripcion+"' WHERE (`codigo` = '"+codigo+"')";
			Statement stmt=st;
			stmt.execute(sql);		
	}
		private static void modificaNombre(Statement st, String codigo, String nombre) throws SQLException {
			String sql="UPDATE `supermark`.`productos` SET `nombre` = '"+nombre+"' WHERE (`codigo` = '"+codigo+"')";
			Statement stmt=st;
			stmt.execute(sql);
		}
		public static void eliminarProducto(Statement st) throws SQLException {
			Statement stmt=st;
			System.out.println("por favor ingrese el codigo del producto a eliminar: ");
			int codigo=teclado.nextInt();
			System.out.print("     ");
			productos.productosMostrar(codigo, stmt);
			System.out.println("Ingrese 1 para eliminar");
			System.out.println("Ingrese otro valor para cancelar");
			int o=teclado.nextInt();
			if (o==1) {
				String sql="DELETE FROM `supermark`.`productos` WHERE (`codigo` = '"+codigo+"')";
				stmt.execute(sql);
				carrito.esperar(500);
				System.out.print("eliminado");
				carrito.esperar(500);
				System.out.print(".");
				carrito.esperar(500);
				System.out.print(".");
				carrito.esperar(500);
				System.out.println(".");
				carrito.esperar(500);
				System.out.println("Se elimino correctamente");
				carrito.esperar(500);
				
			}else System.out.println("se cancelo la eliminacion del producto");
			
	}

	public static void reportes(Statement st) {
		
	}
	
}
