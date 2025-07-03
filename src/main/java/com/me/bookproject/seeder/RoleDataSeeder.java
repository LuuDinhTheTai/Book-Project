package com.me.bookproject.seeder;

import com.me.bookproject.constant.Constant;
import com.me.bookproject.controller.AccountController;
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
    actionService.createIfNotExist(new Action(Constant.ACTION_CREATE, "Create action"));
    actionService.createIfNotExist(new Action(Constant.ACTION_READ, "Read action"));
    actionService.createIfNotExist(new Action(Constant.ACTION_UPDATE, "Update action"));
    actionService.createIfNotExist(new Action(Constant.ACTION_DELETE, "Delete action"));
  }
  
  private void generateResource() {
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE_ACCOUNT, "Account resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE_CART, "Cart resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE_COMMENT, "Comment resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE_ATTACHMENT, "Attachment resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE_BOOK, "Book resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE_ROLE, "Role resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE_CATEGORY, "Category resource"));
  }
  
  private void generatePermission() {
    createPermission(Constant.PERMISSION_CREATE_ACCOUNT, Constant.ACTION_CREATE, Constant.RESOURCE_ACCOUNT);
    createPermission(Constant.PERMISSION_UPDATE_ACCOUNT, Constant.ACTION_UPDATE, Constant.RESOURCE_ACCOUNT);
    createPermission(Constant.PERMISSION_DELETE_ACCOUNT, Constant.ACTION_DELETE, Constant.RESOURCE_ACCOUNT);
    
    createPermission(Constant.PERMISSION_CREATE_CART, Constant.ACTION_CREATE, Constant.RESOURCE_CART);
    createPermission(Constant.PERMISSION_READ_CART, Constant.ACTION_READ, Constant.RESOURCE_CART);
    createPermission(Constant.PERMISSION_UPDATE_CART, Constant.ACTION_UPDATE, Constant.RESOURCE_CART);
    createPermission(Constant.PERMISSION_DELETE_CART, Constant.ACTION_DELETE, Constant.RESOURCE_CART);
    
    createPermission(Constant.PERMISSION_CREATE_COMMENT, Constant.ACTION_CREATE, Constant.RESOURCE_COMMENT);
    createPermission(Constant.PERMISSION_READ_COMMENT, Constant.ACTION_READ, Constant.RESOURCE_COMMENT);
    
    createPermission(Constant.PERMISSION_CREATE_ATTACHMENT, Constant.ACTION_CREATE, Constant.RESOURCE_ATTACHMENT);
    
    createPermission(Constant.PERMISSION_CREATE_BOOK, Constant.ACTION_CREATE, Constant.RESOURCE_BOOK);
    createPermission(Constant.PERMISSION_READ_BOOK, Constant.ACTION_READ, Constant.RESOURCE_BOOK);
    createPermission(Constant.PERMISSION_UPDATE_BOOK, Constant.ACTION_UPDATE, Constant.RESOURCE_BOOK);
    createPermission(Constant.PERMISSION_DELETE_BOOK, Constant.ACTION_DELETE, Constant.RESOURCE_BOOK);
    
    createPermission(Constant.PERMISSION_CREATE_ROLE, Constant.ACTION_CREATE, Constant.RESOURCE_ROLE);
    createPermission(Constant.PERMISSION_READ_ROLE, Constant.ACTION_READ, Constant.RESOURCE_ROLE);
    createPermission(Constant.PERMISSION_UPDATE_ROLE, Constant.ACTION_UPDATE, Constant.RESOURCE_ROLE);
    createPermission(Constant.PERMISSION_DELETE_ROLE, Constant.ACTION_DELETE, Constant.RESOURCE_ROLE);
    
    createPermission(Constant.PERMISSION_CREATE_CATEGORY, Constant.ACTION_CREATE, Constant.RESOURCE_CATEGORY);
    createPermission(Constant.PERMISSION_READ_CATEGORY, Constant.ACTION_READ, Constant.RESOURCE_CATEGORY);
    createPermission(Constant.PERMISSION_UPDATE_CATEGORY, Constant.ACTION_UPDATE, Constant.RESOURCE_CATEGORY);
    createPermission(Constant.PERMISSION_DELETE_CATEGORY, Constant.ACTION_DELETE, Constant.RESOURCE_CATEGORY);
  }
  
  private void createPermission(String name, String actionName, String resourceName) {
    Action action = actionService.findByName(actionName);
    Resource resource = resourceService.findByName(resourceName);
    String description = name.replace('_', ' ').toLowerCase() + " permission";
    
    Permission permission = new Permission(name, capitalize(description));
    permission.setAction(action);
    permission.setResource(resource);
    
    permissionService.createIfNotExist(permission);
  }
  
  private String capitalize(String str) {
    return Character.toUpperCase(str.charAt(0)) + str.substring(1);
  }
  
  private void generateRole() {
    Role admin = new Role();
    admin.setName(Constant.ROLE_ADMIN);
    admin.setDescription("Admin role's description");
    admin.getPermissions().addAll(permissionService.list());
    
    Role user = new Role();
    user.setName(Constant.ROLE_USER);
    user.setDescription("User role's description");
    
    roleService.createIfNotExist(admin);
    roleService.createIfNotExist(user);
  }
  
  private void createAdminAccount() {
    Account account = new Account();
    account.setUsername("admin");
    account.setEmail("admin@gmail.com");
    account.setPassword(passwordEncoder.encode("admin"));
    account.getRoles().add(roleService.findByName(Constant.ROLE_ADMIN));
    
    if (accountService.findByUsername("admin") == null) {
      accountService.create(account);
    }
  }
}
