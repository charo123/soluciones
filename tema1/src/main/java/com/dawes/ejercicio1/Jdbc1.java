package com.dawes.ejercicio1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc1 {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	private CallableStatement cs;
	
	public void conectar() {
		try  {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/curso","root","temporal");
			st=con.createStatement();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar "+e.getMessage());
		} 
	}

	public Connection getCon() {
		return con;
	}

	public int insertar() {
		try {
			return st.executeUpdate("insert into Titular (nombre,edad) values ('ana',1)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public void consultarTodo() {
		try {
			rs=st.executeQuery("select * from Titular");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				System.out.println(rs.getString("nombre")+" "+rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int insercionParametrizada(String nombre, int edad) {
		try {
			ps=con.prepareStatement("insert into Titular(nombre,edad) values (?,?)");
			ps.setString(1, nombre);
			ps.setInt(2, edad);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public ResultSet consultaParametrizada(String nombre) {
		try {
			ps=con.prepareStatement("select * from Titular where nombre=?");
			ps.setString(1, nombre);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			return ps.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		 
		
	}
	public ResultSet procedimientoAlmacenado(int dato) {
		try {
			cs=con.prepareCall("{CALL pa1(?)}");
			cs.setInt(1, dato);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return cs.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void transacciones() {
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insercionParametrizada("juana",18);
		insercionParametrizada("pedro",38);
		
	}
	public int eliminarDatos() {
		try {
			return st.executeUpdate("delete from Titular");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
