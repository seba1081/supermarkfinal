package Super;

import java.util.*;

public class carrito1 {
	public static List<Integer> lista =new ArrayList<Integer>();
	 
	public static void carrito(){
		lista.clear();
	}

	public static void agregar(int codigo) {
		int c=codigo;
		lista.add(c);
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
	public static void mostrar() {
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
System.out.println("tamaño: "+lista.size());	}
	public static int tamaño() {
		return lista.size();
	}
}
