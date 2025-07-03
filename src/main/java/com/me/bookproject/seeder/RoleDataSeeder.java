package com.me.bookproject.seeder;

import com.me.bookproject.constant.Constant;
import com.me.bookproject.entity.RBAC0.Role;
import com.me.bookproject.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleDataSeeder implements CommandLineRunner {
  
  private final RoleService service;
  
  public RoleDataSeeder(RoleService service) {
    this.service = service;
  }
  
  @Override
  public void run(String... args) {
    if (!service.existsByName(Constant.ROLE_ADMIN)) {
      Role role = new Role();
      role.setName(Constant.ROLE_ADMIN);
      role.setDescription("Admin role's description");
      service.create(role);
    }
    if (!service.existsByName(Constant.ROLE_USER)) {
      Role role = new Role();
      role.setName(Constant.ROLE_USER);
      role.setDescription("User role's description");
      service.create(role);
    }
  }
}
