package com.example.market.repository;

import com.example.market.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email and m.pw = :pw")
    Member findMember(@Param("email") String email, @Param("pw") String pw);

    @Query("select m from Member m where m.email = :email")
    Member findByEmail(@Param("email") String email);

}
