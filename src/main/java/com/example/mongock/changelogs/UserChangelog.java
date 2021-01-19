package com.example.mongock.changelogs;

import com.example.mongock.user.User;
import com.example.mongock.user.UserRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ChangeLog(order = "001")
public class UserChangelog {

    public final static int INITIAL_USERS = 10;

    @ChangeSet(id = "data-initializer-with-repository", order = "001", author = "mongock")
    public void dataInitializer(UserRepository userRepository) {

        java.lang.reflect.Proxy.getInvocationHandler(userRepository);
        List<User> clients = new ArrayList<>();
        int bound = INITIAL_USERS;
        for (int i = 0; i < bound; i++) {
            User user = new User("nome-" + i, "login-" + i);
            clients.add(user);
        }
        userRepository.saveAll(clients);
    }

    @ChangeSet(id = "inclusao-status", order = "001", author = "mongock")
    public void statusUser(MongockTemplate template) throws InterruptedException {

        List<User> all = template.findAll(User.class);

        for (User user : all) {
            user.setStatus("A");
            Thread.sleep(3000);
            template.save(user);
        }

    }

    @ChangeSet(id = "inclusao-status-outros", order = "003", author = "mongock")
    public void statusUserOutro(MongockTemplate template){

        List<User> all = template.findAll(User.class).stream().limit(5).collect(Collectors.toList());

        for (User user : all) {
            user.setStatus("I");
            template.save(user);
        }
    }

    @ChangeSet(id = "inclusao-status-volta", order = "004", author = "mongock")
    public void statusUserVoltaStatus(MongockTemplate template){

        List<User> all = template.findAll(User.class).stream().limit(55).collect(Collectors.toList());

        for (User user : all) {
            user.setStatus("I");
            template.save(user);
        }
    }
}
