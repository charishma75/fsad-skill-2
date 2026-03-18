package com.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Product;
import com.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // 🔹 1. INSERT
        Product p1 = new Product("Laptop", "Dell i5", 50000, 10);
        Product p2 = new Product("Phone", "Samsung", 20000, 15);

        session.save(p1);
        session.save(p2);

        // 🔹 2. RETRIEVE
        Product product = session.get(Product.class, 1);
        if (product != null) {
            System.out.println("Product: " + product.getName());
        }

        // 🔹 3. UPDATE
        if (product != null) {
            product.setPrice(55000);
            session.update(product);
        }

        // 🔹 4. DELETE
        Product deleteProduct = session.get(Product.class, 2);
        if (deleteProduct != null) {
            session.delete(deleteProduct);
        }

        tx.commit();
        session.close();

        System.out.println("CRUD Operations Completed!");
    }
}