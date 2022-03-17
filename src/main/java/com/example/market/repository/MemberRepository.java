package com.example.market.repository;

import com.example.market.model.Grade;
import com.example.market.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository{

    @PersistenceContext //스프링이 엔티티매니저 만들어서 주입
    private EntityManager entityManager;

    public void save(Member member) {
        member.setGrade(Grade.USER);
        entityManager.persist(member);
    }

    public Member findMember(Long id){
        return entityManager.find(Member.class, id);
    }


    public List<Member> memberList(){
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public List<Member> findByName(String name){
        return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }



}
