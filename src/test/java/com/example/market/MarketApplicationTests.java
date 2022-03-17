package com.example.market;

import com.example.market.model.Grade;
import com.example.market.model.Item;
import com.example.market.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootTest
class MarketApplicationTests {

    @Test
    void contextLoads() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("chan");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                Member member = new Member();
                member.setName("김찬회");
                member.setEmail("asd@admin.ac.kr");
                member.setPw("qwe123");
                member.setTel("01071298965");
                member.setGrade(Grade.ADMIN);
                entityManager.persist(member);
                entityManager.flush();

                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            } finally {
                entityManager.close();
            }
            factory.close();
    }

}
