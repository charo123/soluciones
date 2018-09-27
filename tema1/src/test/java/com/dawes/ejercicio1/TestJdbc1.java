package com.dawes.ejercicio1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TestJdbc1 {

	Jdbc1 j;

	public TestJdbc1() {
		j=new Jdbc1();
		j.conectar();
		j.eliminarDatos();
	}
	
	@Test
	public void t1conectarOK() {
		assertNotNull(j.getCon());
	}
	
	@Test
	public void t2insertarOK() {
		assertEquals(1,j.insertar());
		//assertEquals(1,j.insertar());
		//assertEquals(1,j.insertar());
	}
	
	
	@Test
	public void t3consultarOK() {
		j.consultarTodo();
	}
	@Test
	public void t4insertarParametrizadoOK() {
		assertEquals(1,j.insercionParametrizada("Jairo", 23));
	}
	@Test 
	public void t5consultaParametrizadaOK() {
		assertNotNull(j.consultaParametrizada("ana"));
	}
	
	@Test
	public void t6procedimientoAlmacenadoOK() {
		
		System.out.println("*********");
		
		assertNotNull(j.procedimientoAlmacenado(20));
		ResultSet rs=j.procedimientoAlmacenado(20);
		try {
			while (rs.next()) {
				System.out.println(rs.getString("nombre")+" "+rs.getInt("edad"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("*********");
		
		
	}
}
