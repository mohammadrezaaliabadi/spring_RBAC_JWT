package com.pureamorous.spring_rbac_jwt;

import com.pureamorous.spring_rbac_jwt.entities.Authority;
import com.pureamorous.spring_rbac_jwt.entities.Book;
import com.pureamorous.spring_rbac_jwt.entities.Role;
import com.pureamorous.spring_rbac_jwt.entities.User;
import com.pureamorous.spring_rbac_jwt.service.AuthorityService;
import com.pureamorous.spring_rbac_jwt.service.BookService;
import com.pureamorous.spring_rbac_jwt.service.RoleService;
import com.pureamorous.spring_rbac_jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class SpringRbacJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRbacJwtApplication.class, args);
    }

    @Bean
        //TODO just for testing, remove it in production
    CommandLineRunner run(BookService bookService, UserService userService,
                          RoleService roleService, AuthorityService authorityService) {
        return args -> {
            if (bookService.isExists("DataBase 101")) return;

            var b1 = new Book("DataBase 101", "alireza testzade");
            var b2 = new Book("Hardware 101", "mohammad springabadi");

            bookService.save(b1);
            bookService.save(b2);

            var select = authorityService.insert(new Authority("select"));
            var create = authorityService.insert(new Authority("insert"));
            var change = authorityService.insert(new Authority("change"));
            var remove = authorityService.insert(new Authority("remove"));

            var adminAuthorities = Set.of(select, create, change, remove);
            var userAuthorities = Set.of(select, create);

            var admin = roleService.insert(new Role("ADMIN", adminAuthorities));
            var user = roleService.insert(new Role("USER", userAuthorities));

            userService.save(new User("ali", "ali", Set.of(admin, user)));
            userService.save(new User("mamad", "mamad", Set.of(user)));


        };
    }
}
