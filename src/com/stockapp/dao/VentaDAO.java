package com.stockapp.dao;

import com.stockapp.db.ConexionDB;



import java.sql.Connection;
import java.sql.PreparedStatement;

public class VentaDAO {

	public void registrarVenta(int productoId, int cantidad) {

	    ProductoDAO productoDAO = new ProductoDAO();
	    int stockActual = productoDAO.obtenerStock(productoId);

	    if (stockActual < cantidad) {
	        System.out.println("Stock insuficiente. Disponible: " + stockActual);
	        return;
	    }

	    String insertVenta = "INSERT INTO ventas (producto_id, cantidad, fecha) VALUES (?, ?, ?)";
	    String updateStock = "UPDATE productos SET stock = stock - ? WHERE id = ?";

	    try (Connection conn = ConexionDB.conectar()) {

	        PreparedStatement ps1 = conn.prepareStatement(insertVenta);
	        ps1.setInt(1, productoId);
	        ps1.setInt(2, cantidad);
	        ps1.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
	        ps1.executeUpdate();

	        PreparedStatement ps2 = conn.prepareStatement(updateStock);
	        ps2.setInt(1, cantidad);
	        ps2.setInt(2, productoId);
	        ps2.executeUpdate();

	        System.out.println("Venta registrada correctamente");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    


}
