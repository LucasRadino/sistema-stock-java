package com.stockapp.main;

import java.util.List;
import java.util.Scanner;

import com.stockapp.dao.ProductoDAO;
import com.stockapp.model.Producto;

import com.stockapp.dao.VentaDAO;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProductoDAO dao = new ProductoDAO();
        VentaDAO ventaDao = new VentaDAO();


        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Registrar venta");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Elegí una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();

                    System.out.print("Stock: ");
                    int stock = sc.nextInt();

                    Producto p = new Producto(0, nombre, precio, stock);
                    dao.crearProducto(p);

                    break;

                case 2:
                    List<Producto> productos = dao.listarProductos();

                    for (Producto pro : productos) {
                        System.out.println(
                            pro.getId() + " - " +
                            pro.getNombre() + " - $" +
                            pro.getPrecio() + " - Stock: " +
                            pro.getStock()
                        );
                    }

                    break;
                    
                case 3:
                    System.out.print("ID del producto: ");
                    int id = sc.nextInt();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();

                    ventaDao.registrarVenta(id, cantidad);
                    break;
                    
                case 4:
                    System.out.print("ID del producto a eliminar: ");
                    int idEliminar = sc.nextInt();

                    dao.eliminarProducto(idEliminar);
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;


                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 5);

        sc.close();
    }
}



