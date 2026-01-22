package com.example.TestsAddressbook.appmanager;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.GroupData;
import com.example.TestsAddressbook.model.MySet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

    private SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure() // читает hibernate.cfg.xml
                        .build();
        sessionFactory =  new MetadataSources(registry)
                .addAnnotatedClass(GroupData.class)
                .buildMetadata()
                .buildSessionFactory();
    }


    public MySet<GroupData> groups(){
        System.out.println("Начал работать");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<GroupData> groups = session.createQuery("from GroupData where deprecated = '0000-00-00 00:00:00'").list();
        System.out.println("Все сработало: ");
        System.out.println();
        for (GroupData group : groups) {
            System.out.println(group);
        }

        tx.commit();
        session.close();
        return new MySet<>(groups);
    }

    public MySet<ContactData> contacts(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<ContactData> contacts = session.createQuery("from ContactData where deprecated = null").list();
        System.out.println("Все сработало: ");
        System.out.println();
        for (ContactData contact : contacts) {
            System.out.println(contact);
        }

        tx.commit();
        session.close();
        return new MySet<>(contacts);
    }
}
