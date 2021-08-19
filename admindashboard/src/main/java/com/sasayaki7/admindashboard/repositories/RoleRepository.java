package com.sasayaki7.admindashboard.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sasayaki7.admindashboard.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAll();
    List<Role> findByName(String name);
    
    @Query(value="SELECT COUNT(*) FROM roles JOIN users_roles ON roles.id=users_roles.role_id JOIN users ON users_roles.user_id = users.id WHERE roles.name = 'ROLE_ADMIN'", nativeQuery=true)
    int getAdmin();

}

