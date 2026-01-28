package com.example.TestsAddressbook.connections;

import com.example.TestsAddressbook.model.ContactData;
import com.example.TestsAddressbook.model.GroupData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeMethod
    protected void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure() // читает hibernate.cfg.xml
                        .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(GroupData.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException("Ошибка при создании SessionFactory", e);
        }
    }

    @AfterClass
    protected void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testHbConnection() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // создаём видео
        Video video = new Video("Formula 1", 1);

        // создаём превью и связываем обе стороны
        Preview preview = new Preview("http://form.kz/image");
        preview.setVideo(video);
        video.setPreview(preview);

        // сохраняем только video — Hibernate сам сохранит preview
        session.save(video);

        session.getTransaction().commit();
        session.close();
    }

}
