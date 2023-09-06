import java.sql.SQLException;
import java.util.Scanner;

public class Start {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		MetodosMysql conn = new MetodosMysql();
		String opcion = "";
		int id, PoderFuego, PoderAgua, PoderViento;
		String nombre, descripcion , nombreTabla;
		
		do {
			System.out.println("**************************************************************");
			System.out.println("*      Bienvenido al programa de acceso con phpmyadmin       *");
			System.out.println("**************************************************************");
			System.out.println(" 0.- Conexión: ");
			System.out.println(" 1.- Crear Tabla: ");
			System.out.println(" 2.- Insertar datos en la tabla: ");
			System.out.println(" 3.- Abrir una tabla:");
			System.out.println(" 4.- Insertar poderes a un SuperGuerrero:");
			System.out.println(" 5.- Mostrar datos de la tabla seleccionada: ");
			System.out.println(" 6.- Eliminar fila seleccionada: ");
			System.out.println(" 7.- Resetear valores de poderes de una tabla: ");
			System.out.println(" 8.- Borrar una tabla:");
			System.out.println(" 9.- Salir: ");

			opcion = sc.next();
			
			if (!opcion.equals("0") && !opcion.equals("1")  && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4") && !opcion.equals("5")
					&& !opcion.equals("6") && !opcion.equals("7") && !opcion.equals("8") && !opcion.equals("9")) 
			{
				System.out.println(" Opción incorrecta, escoja un número del 0 al 9. GRACIAS ");
			}
			if(opcion.equals("0"))
			{
				conn.conectar();
			}
			
			if(opcion.equals("1"))
			{
				System.out.println(" Inserta el nombre de la Tabla que quieres crear ");
				nombreTabla = sc.next(); 
				conn.crearTabla(nombreTabla);
			}
			
			if(opcion.equals("2"))
			{
				
				System.out.println(" Inserta el nombre de la Tabla donde quieres insertar datos ");
				sc.nextLine();
				nombreTabla = sc.nextLine(); 
				System.out.println(" Inserta el id ");
				id = sc.nextInt(); 
				System.out.println(" Inserta nombre ");
				nombre = sc.next(); 
				System.out.println(" Inserta la descripción ");
				sc.nextLine();
				descripcion = sc.nextLine(); 
				System.out.println(" Inserta el Valor de Fuego ");
				PoderFuego = sc.nextInt(); 
				System.out.println(" Inserta el Valor de Agua ");
				PoderAgua = sc.nextInt(); 
				System.out.println(" Inserta el valor de viento ");
				PoderViento = sc.nextInt(); 
				conn.insertarDatos(nombreTabla,id,nombre,descripcion,PoderFuego,PoderAgua,PoderViento);
			}
			
			if(opcion.equals("3"))
			{
				System.out.println(" Inserta el nombre de la tabla que quieres abrir ");
				nombreTabla = sc.next(); 
				conn.abrirTabla(nombreTabla);
			}
			
			if(opcion.equals("4"))
			{
				System.out.println(" Inserta el nombre de la tabla que quieres añadir superPoderes ");
				nombreTabla = sc.next(); 
				System.out.println(" Inserta el id de la tabla que quieres añadir superPoderes ");
				id = sc.nextInt(); 
				System.out.println(" Inserta el nuevo poder de Fuego ");
				PoderFuego= sc.nextInt(); 
				System.out.println("  Inserta el nuevo poder de Agua ");
				PoderAgua= sc.nextInt(); 
				System.out.println("  Inserta el nuevo poder de Viento");
				PoderViento= sc.nextInt(); 
				conn.insertarPoderes(nombreTabla, id, PoderFuego, PoderAgua, PoderViento);
			}
				
			if(opcion.equals("5"))
			{
				System.out.println(" Inserta el nombre de la tabla que quieres consultar ");
				nombreTabla = sc.next();
				
				conn.consDatos(nombreTabla);
				
			}	
			if(opcion.equals("6"))
			{
				System.out.println(" Inserta el nombre de la tabla que quieres consultar ");
				nombreTabla = sc.next();
				System.out.println(" Inserta el id de la fila que quieres eliminar ");
				id = sc.nextInt();
			
				
				conn.eliminarFila(nombreTabla, id);
			}
			
			if(opcion.equals("7"))
			{
				System.out.println(" Inserta el nombre de la tabla que quieres borrar un registro ");
				nombreTabla = sc.next();
				System.out.println(" Inserta el id del SuperGuerrero que quieres restablecer sus poderes a 0 ");
				id = sc.nextInt();
				
				conn.borrarRegistro(nombreTabla,id);
			}
			if(opcion.equals("8"))
			{
				System.out.println(" Inserta el nombre de la tabla que quieres eliminar ");
				nombreTabla = sc.next(); 
				conn.eliminarTabla(nombreTabla);
			}
			
		} while (!opcion.equals("9"));
		System.out.println(" Salió del programa ");
	}
}
