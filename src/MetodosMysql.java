import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MetodosMysql {// creamos los atributos

	private static Connection conexion;
	private static final String controlador = "com.mysql.jdbc.Driver";// creamos un atributo statico final que no
																		// cambiara su valor, le asignamos la cadena.
	private static final String url = "jdbc:mysql://localhost:3306/superespecies";// indicamos la url, direccion, puerto
																					// y la base de datos
	private static final String usuario = "root";// ruta
	private static final String clave = "";// clave la dejamos vacia
	private PreparedStatement PS;
	String nombre, descripcion, nombreTabla;
	int id, PoderFuego, PoderAgua, PoderViento;
	Statement st = null;
	ResultSet rs = null;

	public Connection conectar()// creamos un metodo para conectarnos, cambiamos el metodo que nos devuelva
								// Connection

	{
		conexion = null;// el objeto connection lo declaramos como null, este objeto seria la conexion
						// entre eclipse y la base de datos

		try {
			Class.forName(controlador);// aqui lo cambiamos por el atributo que hemos creado
			conexion = DriverManager.getConnection(url, usuario, clave);// el driverManager pide tres parametros, la 1
																		// pide una cadena de conexion, se sigue un
																		// orden
			// 1º nos pide la api que utilizamos q es "jdbc"-el nom de gestor de base de
			// datos que es mysql, el servidor de conexion, la ip y el ejemplo que hemos
			// creado
			// el 2ºparametro es el ussuario y el 3º la contraseña

			System.out.println("Conexión ok ");// si nos hemos conectado bien con la base de datos

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar ");// indicamos un error si no carga
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la conexión ");// en caso que no se pueda conectar
			e.printStackTrace();
		}

		return conexion;// retornamos la variable conexion.
	}

	// Desconectar
	public void desconectar() {
		conexion = null;
		System.out.println("Se ha desconectado correctamente");
	}

	public void crearTabla(String nombreTabla) throws SQLException {

		this.nombreTabla = nombreTabla;
		conectar();
		eliminarTabla(nombreTabla); // eliminar si existe
		Statement st = null;
		try {

			st = conexion.createStatement();//le decimos que st haga la conexion a la base de datos y cree una consulta
			String sql = "CREATE TABLE " + nombreTabla
					+ " ( id INT PRIMARY KEY NOT NULL , nombre TEXT NOT NULL, descripción TEXT NOT NULL, PoderAgua INT NOT NULL, PoderFuego INT NOT NULL, PoderViento INT NOT NULL)";
			st.executeUpdate(sql);
			System.out.println("Creada tabla correctamente");

		} catch (SQLException e) {
			System.out.println("No se ha podido crear la tabla ");
		} finally {
			if (st != null) {
				st.close();
			}
		}

	}

	public void eliminarTabla(String nombreTabla) throws SQLException {

		try {
			st = conexion.createStatement();
			String sql = "DROP TABLE  " + nombreTabla + "";
			st.executeUpdate(sql);
			System.out.println("Tabla eliminada correctamente");
		} catch (Exception e) {
			System.out.println("No existe el nombre de esta tabla, por favor introduzca otro nombre. ");
		} finally {

			if (st != null) {
				st.close();
			}
		}
	}
	public void eliminarFila(String nombreTabla, int id) throws SQLException {

		try {
			st = conexion.createStatement();
			String sql = "DELETE FROM " + nombreTabla + " WHERE "+id+"";
			st.executeUpdate(sql);
			System.out.println("Fila eliminada correctamente");
		} catch (Exception e) {
			System.out.println("No existe esta fila , por favor introduzca otro is.  ");
		} finally {
			if (st != null) {
				st.close();
			}
		}
	}

	public void borrarRegistro(String nombreTabla, int id) throws SQLException {

		String sql = "UPDATE " + nombreTabla + "  SET  PoderFuego = '0', PoderAgua = '0', PoderViento='0'  WHERE id ";

		try {

			conectar();
			st.executeUpdate(sql);
			System.out.println("Registro de datos eliminado correctamente");

		} catch (Exception e) {
			System.out.println("Error al modificar ");
		} finally {

			if (st != null) {
				st.close();
			}
		}

	}

	
	public void consDatos(String nombreTabla) throws SQLException {

		try {

			conectar();
			st = conexion.createStatement();
			rs = st.executeQuery("select * from " + nombreTabla + " ");

			while (rs.next()) {
				System.out.println("******************************************");
				System.out.println("**Leyendo datos de la tabla seleccionada**");
				System.out.println("******************************************");
				System.out.println("ID: " + rs.getInt("Id") + " " + " Nombre: " + rs.getString(2) + " "
						+ " Descripción: " + rs.getString(3) + " " + " PoderFuego: " + rs.getInt(4) + " PoderAgua: "
						+ rs.getInt(5) + " PoderViento: " + rs.getInt(6));
			}

			rs.close();
			st.close();
			conexion.close();
		}

		catch (Exception e) {

			System.out.println("Error al leer datos ");

		}

	}

	public void abrirTabla(String nombreTabla) throws SQLException {

		try {
			conectar();
			st = conexion.createStatement();
			String sql = "SELECT * FROM " + nombreTabla + "; ";
			rs = st.executeQuery(sql);
			System.out.println("Tabla abierta");
		
		} catch (Exception e) {

			System.out.println("Error al leer datos ");

		}

	}

	
	public void insertarPoderes(String nombreTabla, int id, int PoderFuego, int PoderAgua, int PoderViento) throws SQLException {
		

			String sql = "UPDATE " + nombreTabla + "  SET  PoderFuego = "+PoderFuego+" , PoderAgua= "+PoderAgua+" , PoderViento= "+PoderViento+" WHERE " + id + " ";

			try {

				conectar();
				st.executeUpdate(sql);
				System.out.println("Poderes añadidos correctamente");

			} catch (Exception e) {
				System.out.println("Error al modificar ");
			} finally {

				if (st != null) {
					st.close();
				}
			}

		}
	

	public void insertarDatos(String nombreTabla, int id, String nombre, String descripcion, int PoderFuego,
			int PoderAgua, int PoderViento) throws SQLException {

		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.PoderFuego = PoderFuego;
		this.PoderAgua = PoderAgua;
		this.PoderViento = PoderViento;

		String sql = "INSERT INTO " + nombreTabla + " ( id, nombre, descripción, PoderFuego, PoderAgua, PoderViento)"
				+ " VALUES (" + id + " ,' " + nombre + " ',' " + descripcion + " ',' " + PoderAgua + " ',' "
				+ PoderFuego + " ',' " + PoderViento + "');";
		try {

			{
				conectar();
				st = conexion.createStatement();
				st.executeUpdate(sql);
				st.close();
				conexion.close();
				System.out.println("Linea insertada correctamente ");
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido insertar la linea ");
		}
	}
}
