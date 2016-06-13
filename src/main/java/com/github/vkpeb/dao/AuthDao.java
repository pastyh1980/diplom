package com.github.vkpeb.dao;

import com.github.vkpeb.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pasty on 12.04.2016.
 */
public interface AuthDao extends JpaRepository<Auth, Long>{

    @Query("select a from Auth a where a.login = :login")
    Auth getAuthByLogin(@Param("login") String login);

    @Query("select a from Auth a where a.enabled = 1")
    List<Auth> getAllActiveAuth();

    @Query("select s.family || ' ' || s.name from Student s where s.auth=:auth")
    String getStudNameByAuth(@Param("auth") Auth auth);

    @Query("select p.family || ' ' || p.name from Parent p where p.auth=:auth")
    String getParentNameByAuth(@Param("auth") Auth auth);

    @Query("select s.family || ' ' || s.name from Boss s where s.auth=:auth")
    String getBossNameByAuth(@Param("auth") Auth auth);
}
