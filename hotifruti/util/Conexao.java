package hotifruti.model;

public class Conexao {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/hotifruti";
	private static final String USER = "postgres";
	private static final String PASS = "12345678";

	public static java.sql.Connection getConnection() throws ClassNotFoundException, java.sql.SQLException {
		Class.forName(DRIVER);
		return java.sql.DriverManager.getConnection(URL, USER, PASS);
	}
	

}
