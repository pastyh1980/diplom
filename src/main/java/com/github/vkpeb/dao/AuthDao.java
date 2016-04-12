package com.github.vkpeb.dao;

import com.github.vkpeb.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by pasty on 12.04.2016.
 */
public interface AuthDao extends JpaRepository<Auth, Long>{

    @Query("select a from auth a where a.login = :login")
    Auth getAuthByLogin(@Param("login") String login);
}
