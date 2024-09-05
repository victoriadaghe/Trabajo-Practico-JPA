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

            //cargo factura
            Factura fact1 = new Factura();

            fact1.setNumero(1);
            fact1.setFecha("09/09/2024");

            //Cargo domicilio
           Domicilio dom1 = new Domicilio("San Martin",1224);

            //Cargo cliente y lo vinculo con factura y domicilio
            Cliente cli1 = new Cliente("Carlos" , "Sainz" , 41873946);
            cli1.setDomicilio(dom1);
            dom1.setCliente(cli1);
            fact1.setCliente(cli1);
            
            //Cargo categorias
            Categoria deporte = new Categoria("deporte");
            Categoria moda = new Categoria("moda");
            Categoria maquillaje = new Categoria("maquillaje");

                //Cargo Articulos y los vinculo con sus categorias
            Articulo articulo1 = new Articulo(10 , "Calza" ,20000);
            Articulo articulo2= new Articulo(10,"Labial",8000);

            articulo1.getCategorias().add(moda);
            articulo1.getCategorias().add(deporte);
            moda.getArticulos().add(articulo1);
            deporte.getArticulos().add(articulo1);

            articulo2.getCategorias().add(maquillaje);
            maquillaje.getArticulos().add(articulo2);

            //Vinculos los articulos con su factura y creo detalle factura
            DetalleFactura detfac1 = new DetalleFactura();
            detfac1.setArticulo(articulo1);
            detfac1.setCantidad(1);
            detfac1.setSubtotal(20000);

            articulo1.getDetallesfacturas().add(detfac1);
            fact1.getDetalles().add(detfac1);
            detfac1.setFactura(fac1);

            DetalleFactura detfac2 = new DetalleFactura();
            detfac2.setArticulo(art2);
            detfac2.setCantidad(2);
            detfac2.setSubtotal(16000);

            articulo2.getDetallesfacturas().add(detalle2);
            fact1.getDetalles().add(detfac2);
            detfac2.setFactura(fact1);

            fact1.setTotal(36000);

            em.persist(fact1);


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
