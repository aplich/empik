package me.plich.empik.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface RequestRepository extends CrudRepository<RequestEntity, String> {

    @Modifying
    @Query("update RequestEntity r set r.requestCount = r.requestCount+1 where r.login = ?1")
    void incrementRequestCount(String login);
}
