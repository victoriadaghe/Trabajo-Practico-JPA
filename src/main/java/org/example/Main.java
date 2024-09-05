package org.example;


import entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();


        try {
            em.getTransaction().begin();

            Factura factura1 = new Factura();

            factura1.setNumero(12);
            factura1.setFecha("10/06/2020");

           Domicilio domicilio1 = new Domicilio("San Martin",1224);
            Cliente cliente1 = new Cliente("Ramiro" , "Diz" , 153344);
            cliente1.setDomicilio(domicilio1);
            domicilio1.setCliente(cliente1);

            factura1.setCliente(cliente1);

            Categoria electronica = new Categoria("electronica");
            Categoria cocina = new Categoria("cocina");
            Categoria Jardin = new Categoria("Jardin");

            Articulo art1 = new Articulo(200 , "Freidora de Aire" ,10000);
            Articulo art2= new Articulo(20,"Corta Cesped",50000);

            art1.getCategorias().add(electronica);
            art1.getCategorias().add(cocina);
            electronica.getArticulos().add(art1);
            cocina.getArticulos().add(art1);

            art2.getCategorias().add(Jardin);
            Jardin.getArticulos().add(art2);

            DetalleFactura detalle1 = new DetalleFactura();
            detalle1.setArticulo(art1);
            detalle1.setCantidad(2);
            detalle1.setSubtotal(20000);

            art1.getDetallesfacturas().add(detalle1);
            factura1.getDetalles().add(detalle1);
            detalle1.setFactura(factura1);

            DetalleFactura detalle2 = new DetalleFactura();
            detalle2.setArticulo(art2);
            detalle2.setCantidad(1);
            detalle2.setSubtotal(50000);

            art2.getDetallesfacturas().add(detalle2);
            factura1.getDetalles().add(detalle2);
            detalle2.setFactura(factura1);

            factura1.setTotal(70000);

            em.persist(factura1);


            em.flush();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        em.close();
        emf.close();
    }
}