package com.example.mongock.changelogs;

import com.example.mongock.user.User;
import com.example.mongock.user.UserRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ChangeLog(order = "001")
public class UserChangelog {

    public final static int INITIAL_USERS = 10;

    @ChangeSet(id = "data-initializer-with-repository", order = "001", author = "mongock")
    public void dataInitializer(UserRepository userRepository) {

        java.lang.reflect.Proxy.getInvocationHandler(userRepository);
        List<User> clients = IntStream.range(0, INITIAL_USERS)
                .mapToObj(i -> new User("nome-" + i, "login-" + i))
                .collect(Collectors.toList());
        List<User> result = userRepository.saveAll(clients);
    }

    @ChangeSet(order = "002", author = "mongock", id = "teste")
    public void userUpdate(MongockTemplate template){
        List<User> users = template.findAll(User.class, "user");

        users.stream()
                .map(user -> user.setPassword(""))
                .forEach(user -> template.save(user, "user"));

    }

}
