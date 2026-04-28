package com.stockapp.dao;

import com.stockapp.db.ConexionDB;
import com.stockapp.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ProductoDAO {

    public void crearProducto(Producto p) {

        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());

            ps.executeUpdate();
            

            System.out.println("Producto insertado correctamente");

        } catch (Exception e) {
            
        	System.out.println("FALLOO");
        	
        	e.printStackTrace();
        }
    }
    
    
    public List<Producto> listarProductos() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public int obtenerStock(int productoId) {

        String sql = "SELECT stock FROM productos WHERE id = ?";
        int stock = 0;

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productoId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                stock = rs.getInt("stock");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stock;
    }

    public void eliminarProducto(int id) {

        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("✔ Producto eliminado correctamente");
            } else {
                System.out.println("❌ No existe un producto con ese ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
}


