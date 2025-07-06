package com.me.bookproject.seeder;

import com.me.bookproject.constant.Constant;
import com.me.bookproject.entity.RBAC0.Action;
import com.me.bookproject.entity.RBAC0.Permission;
import com.me.bookproject.entity.RBAC0.Resource;
import com.me.bookproject.entity.RBAC0.Role;
import com.me.bookproject.entity.user.Account;
import com.me.bookproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDataSeeder implements CommandLineRunner {
  
  private final RoleService roleService;
  private final PermissionService permissionService;
  private final ActionService actionService;
  private final ResourceService resourceService;
  private final PasswordEncoder passwordEncoder;
  private final AccountService accountService;
  
  @Override
  public void run(String... args) {
    generateAction();
    generateResource();
    generatePermission();
    generateRole();
    createAdminAccount();
  }
  
  private void generateAction() {
    actionService.createIfNotExist(new Action(Constant.ACTION.CREATE, null));
    actionService.createIfNotExist(new Action(Constant.ACTION.READ, null));
    actionService.createIfNotExist(new Action(Constant.ACTION.UPDATE, null));
    actionService.createIfNotExist(new Action(Constant.ACTION.DELETE, null));
  }
  
  private void generateResource() {
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.ACCOUNT, null));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.CART, null));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.COMMENT, null));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.ATTACHMENT, null));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.BOOK, null));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.ROLE, null));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.CATEGORY, null));
  }
  
  private void generatePermission() {
    createPermission(Constant.PERMISSION.CREATE_ACCOUNT, Constant.ACTION.CREATE, Constant.RESOURCE.ACCOUNT);
    createPermission(Constant.PERMISSION.UPDATE_ACCOUNT, Constant.ACTION.UPDATE, Constant.RESOURCE.ACCOUNT);
    createPermission(Constant.PERMISSION.DELETE_ACCOUNT, Constant.ACTION.DELETE, Constant.RESOURCE.ACCOUNT);
    
    createPermission(Constant.PERMISSION.CREATE_CART, Constant.ACTION.CREATE, Constant.RESOURCE.CART);
    createPermission(Constant.PERMISSION.READ_CART, Constant.ACTION.READ, Constant.RESOURCE.CART);
    createPermission(Constant.PERMISSION.UPDATE_CART, Constant.ACTION.UPDATE, Constant.RESOURCE.CART);
    createPermission(Constant.PERMISSION.DELETE_CART, Constant.ACTION.DELETE, Constant.RESOURCE.CART);
    
    createPermission(Constant.PERMISSION.CREATE_COMMENT, Constant.ACTION.CREATE, Constant.RESOURCE.COMMENT);
    createPermission(Constant.PERMISSION.READ_COMMENT, Constant.ACTION.READ, Constant.RESOURCE.COMMENT);
    
    createPermission(Constant.PERMISSION.CREATE_ATTACHMENT, Constant.ACTION.CREATE, Constant.RESOURCE.ATTACHMENT);
    
    createPermission(Constant.PERMISSION.CREATE_BOOK, Constant.ACTION.CREATE, Constant.RESOURCE.BOOK);
    createPermission(Constant.PERMISSION.READ_BOOK, Constant.ACTION.READ, Constant.RESOURCE.BOOK);
    createPermission(Constant.PERMISSION.UPDATE_BOOK, Constant.ACTION.UPDATE, Constant.RESOURCE.BOOK);
    createPermission(Constant.PERMISSION.DELETE_BOOK, Constant.ACTION.DELETE, Constant.RESOURCE.BOOK);
    
    createPermission(Constant.PERMISSION.CREATE_ROLE, Constant.ACTION.CREATE, Constant.RESOURCE.ROLE);
    createPermission(Constant.PERMISSION.READ_ROLE, Constant.ACTION.READ, Constant.RESOURCE.ROLE);
    createPermission(Constant.PERMISSION.UPDATE_ROLE, Constant.ACTION.UPDATE, Constant.RESOURCE.ROLE);
    createPermission(Constant.PERMISSION.DELETE_ROLE, Constant.ACTION.DELETE, Constant.RESOURCE.ROLE);
    
    createPermission(Constant.PERMISSION.CREATE_CATEGORY, Constant.ACTION.CREATE, Constant.RESOURCE.CATEGORY);
    createPermission(Constant.PERMISSION.READ_CATEGORY, Constant.ACTION.READ, Constant.RESOURCE.CATEGORY);
    createPermission(Constant.PERMISSION.UPDATE_CATEGORY, Constant.ACTION.UPDATE, Constant.RESOURCE.CATEGORY);
    createPermission(Constant.PERMISSION.DELETE_CATEGORY, Constant.ACTION.DELETE, Constant.RESOURCE.CATEGORY);
  }
  
  private void createPermission(String name, String actionName, String resourceName) {
    Action action = actionService.findByName(actionName);
    Resource resource = resourceService.findByName(resourceName);
    
    Permission permission = new Permission(name, null);
    permission.setAction(action);
    permission.setResource(resource);
    
    permissionService.createIfNotExist(permission);
  }
  
  private void generateRole() {
    Role admin = new Role();
    admin.setName(Constant.ROLE.ADMIN);
    admin.getPermissions().addAll(permissionService.list());
    
    Role user = new Role();
    user.setName(Constant.ROLE.USER);
    
    roleService.createIfNotExist(admin);
    roleService.createIfNotExist(user);
  }
  
  private void createAdminAccount() {
    Account account = new Account();
    account.setUsername("admin");
    account.setEmail("admin@gmail.com");
    account.setPassword(passwordEncoder.encode("admin"));
    account.getRoles().add(roleService.findByName(Constant.ROLE.ADMIN));
    
    if (accountService.findByUsername("admin") == null) {
      accountService.create(account);
    }
  }
}
