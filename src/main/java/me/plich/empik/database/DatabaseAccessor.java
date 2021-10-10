package me.plich.empik.database;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DatabaseAccessor {

    private final RequestRepository repository;

    public DatabaseAccessor(RequestRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void logQueryForUser(String login) {
        boolean exists = repository.existsById(login);

        if(exists) {
            repository.incrementRequestCount(login);
        } else {
            repository.save(new RequestEntity(login, 1));
        }
    }

}
