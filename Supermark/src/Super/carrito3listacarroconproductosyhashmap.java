package Super;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class carrito3listacarroconproductosyhashmap {
	public static List<Integer> lista =new ArrayList<Integer>();
	public static HashMap<Integer,productos> listaCarro= new HashMap<Integer,productos>();

	 
	public static void carrito(){
		lista.clear();
		listaCarro.clear();
	}

	public static void agregar(int cod, Statement stmt) throws SQLException {
		int c=cod;
		int op;
		Statement stms=stmt;
		Scanner teclado = new Scanner(System.in);
		String sql="select * from productos where codigo ="+c;
		ResultSet rs=stmt.executeQuery(sql);
		rs.next();
		String nombre=rs.getNString("nombre");
		String descripcion=rs.getString("descripcion");
		String precio=rs.getString("precio");
		int stock=rs.getInt("stock");
		do {
		System.out.println("selecció: "+nombre+" "+descripcion+"\tPrecio $"+precio+" Disponible:"+stock);
		System.out.println("Ingrese la cantidad para agregar al carro o '0' para salir");
					op=teclado.nextInt();
			if (op<=stock) {
				lista.add(c);
				listaCarro.putAll(listaCarro);
				System.out.println("se agrego-->> "+op+" "+nombre+" <<-- al carrito");
				op=0;
				esperar(7);
			}else System.out.println("no hay "+op+" disponibles");
		}while(op!=0);
	}
	private static void esperar(int i) {
		int t=i;
        try {
			Thread.sleep(t * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void quitar(int codigo){
		int c=codigo;
	    for (int i = 0; i < lista.size(); i++) {
	    	if (lista.get(i)==c) {
	    		lista.remove(i);
	    		i=lista.size();
			} 
		}
	}
	
	public static void mostrar(Statement st)  throws SQLException {
		Statement stmt=st;
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
		for(int j:listaCarro.keySet()) {
		listaCarro.get(j).productosMostrar(stmt);
		}
System.out.println("tamaño: "+lista.size());	}
	
	public static int tamaño() {
		return lista.size();
	}
}
