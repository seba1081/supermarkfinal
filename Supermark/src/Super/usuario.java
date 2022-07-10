package Super;

import java.sql.*;
import java.util.Scanner;

public class usuario {
	public static void usuario(Statement st) {
		
		
	}
	public static void registrarse(Statement st) throws SQLException {
		Statement stmt=st;
		String r = null;
		boolean edni=false;
		boolean eusuario=false;
		Scanner teclado = new Scanner(System.in);
		int dni=0;
		do {
		System.out.println(" Para registrarse a -->>SUPERMARK<<--");
		System.out.println("ingrese su nombre:");
		String nombre=teclado.next();
		System.out.println("ingrese su apellido:");
		String apellido=teclado.next();
		System.out.println("ingrese su DNI:");
		dni=teclado.nextInt();
		System.out.println("ingrese su direcion:");
		String direccion=teclado.next();
		System.out.println("ingrese su nick-->>(nombre de usuario todo junto sin espacio):");
		String nick=teclado.next();
		System.out.println("ingrese su contraseña:");
		String contrasena=teclado.next();
		System.out.println("Verifique si sus datos son correctos");
		System.out.println("nombre: "+nombre+" apellido: "+apellido+" DNI:"+dni+" direccion: "+direccion+" usuario:"+nick+" contraseña: "+contrasena);
		System.out.println("Ingrese 1 para aceptar");
		System.out.println("Ingrese otro valor para cancelar");
		r = teclado.next();
		if (r.equals("1")) {
		String sql="INSERT INTO `supermark`.`clientes` (`nombre`, `apellido`, `dni`, `domiciolio`) VALUES ('"+nombre+"', '"+apellido+"', '"+dni+"', '"+direccion+"')";
		try {
			stmt.execute(sql);
			edni=false;
			}
			catch (SQLException e) {
				edni=true;
				eusuario=true;
				System.out.println("ERROR AL REGISTRAR");
				System.out.println("	el DNI ya esta registrado...");
				System.out.println("si se olvido la contraseña comuniquese con el ADMINISTRADO al tel +543878570325");
				carrito.esperar(4000);
				System.out.println(" ");
				}
		if (!edni) {
		sql="select codigo from clientes where dni="+dni;	
		ResultSet rs=stmt.executeQuery(sql);
		rs.next();
		int codigo=rs.getInt("codigo");
		sql="INSERT INTO `supermark`.`usuarios` (`codigo`, `nick`, `contrasena`, `tipo`) VALUES ('"+codigo+"', '"+nick+"', '"+contrasena+"', 'c')";
		try {
			stmt.execute(sql);
			eusuario=false;
			}
			catch (SQLException e) {
				eusuario=true;
				}
		
		}
		if (!eusuario) {
			System.out.println("te registraste");
			carrito.esperar(2000);
			System.out.println("ahora inicia sesion");
			carrito.esperar(1500);
			System.out.println(" ");
		}else if(!edni){
			System.out.println("ERROR AL REGISTRAR");
			System.out.println("	El nombre de usuario ya esta en uso");
			carrito.esperar(2000);
		sql="DELETE FROM `supermark`.`clientes` WHERE (`dni` = '"+dni+"')";
		stmt.execute(sql);
			System.out.println("Vuela a intentar con otro usuario");
			carrito.esperar(4000);
			System.out.println(" ");
		}
		
		}else {
			System.out.println("se cancelo el registro");
			carrito.esperar(1000);
		}
		
		
		}while(r.equals(0));
		
	}

}
