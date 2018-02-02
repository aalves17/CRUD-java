package bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
	public static Connection getConnection(){
		Connection conexao = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_ingrid", "root", "root");
		}catch (Exception e) {
			conexao = null;
		}
		return conexao;
	}
}