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
    actionService.createIfNotExist(new Action(Constant.ACTION.CREATE, "Create action"));
    actionService.createIfNotExist(new Action(Constant.ACTION.READ, "Read action"));
    actionService.createIfNotExist(new Action(Constant.ACTION.UPDATE, "Update action"));
    actionService.createIfNotExist(new Action(Constant.ACTION.DELETE, "Delete action"));
  }
  
  private void generateResource() {
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.ACCOUNT, "Account resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.CART, "Cart resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.COMMENT, "Comment resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.ATTACHMENT, "Attachment resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.BOOK, "Book resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.ROLE, "Role resource"));
    resourceService.createIfNotExist(new Resource(Constant.RESOURCE.CATEGORY, "Category resource"));
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
    admin.setDescription("Admin role's description");
    admin.getPermissions().addAll(permissionService.list());
    
    Role user = new Role();
    user.setName(Constant.ROLE.USER);
    user.setDescription("User role's description");
    
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
