package com.traffic.api.DAO;


import com.traffic.api.models.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Integer>{
    Role findByRole(String role);
}
