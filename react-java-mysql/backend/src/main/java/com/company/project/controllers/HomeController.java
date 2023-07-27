package com.company.project.controllers;

import com.company.project.entity.Greeting;
import com.company.project.entity.Hello;
import com.company.project.repository.GreetingRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.mysema.commons.lang.Assert.assertThat;

@RestController
public class HomeController {

    @Autowired
    private GreetingRepository repository;
    @PersistenceContext
    EntityManager em; // 1

    @GetMapping("/")
    public Greeting showHome(String name, Model model) {

        Hello hello = new Hello();
        em.persist(hello);

//        JPAQueryFactory query = new JPAQueryFactory(em); // 2
//        QHello qHello;
//
//        Hello result = query
//                .selectFrom(qHello)
//                .fetchOne();
//
//        assertThat(result).isEqualTo(hello);
//        assertThat(result.getId()).isEqualTo(hello.getId());

        return repository.findById(1).orElse(new Greeting("Not Found 😕"));
    }

}
