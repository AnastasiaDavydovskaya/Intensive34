package ru.aston.davydovskaya_av.task9;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.davydovskaya_av.task9.entities.Bucket;
import ru.aston.davydovskaya_av.task9.entities.Category;
import ru.aston.davydovskaya_av.task9.entities.Product;
import ru.aston.davydovskaya_av.task9.entities.User;
import ru.aston.davydovskaya_av.task9.entities.enums.Role;

import java.awt.*;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class HibernateTest {

    private static SessionFactory sessionFactory;
    private Session session;
    private Bucket bucket;

    @BeforeAll
    public static void init() {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Bucket.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Category.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @BeforeEach
    public void start() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            bucket = Bucket.builder()
                    .user(User.builder()
                            .login("root")
                            .password("root")
                            .role(Role.CUSTOMER_LUX)
                            .meta("{\"info\":\"test\"}")
                            .build())
                    .products(Stream.generate(
                                    () -> Product.builder()
                                            .title("product")
                                            .price(BigDecimal.valueOf(200))
                                            .color(Color.BLUE)
                                            .category(Category.builder()
                                                    .title("category")
                                                    .build())
                                            .build())
                            .limit(5)
                            .collect(Collectors.toList()))
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @AfterEach
    public void stop() {
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void createBucket() {
        session.saveOrUpdate(bucket.getUser());
        for (Product product : bucket.getProducts()) {
            session.saveOrUpdate(product);
        }
        session.saveOrUpdate(bucket);
    }

    @Test
    public void deleteBucket() {
        session.delete(bucket);
    }

}
