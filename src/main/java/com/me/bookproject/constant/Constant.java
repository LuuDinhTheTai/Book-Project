package com.me.bookproject.constant;

public class Constant {
  
  public static class ROLE {
    
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
  }
  
  public static class ACTION {
    public static final String CREATE = "CREATE";
    public static final String READ = "READ";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
  }
  
  public static class RESOURCE {
    public static final String ACCOUNT = "ACCOUNT";
    public static final String CART = "CART";
    public static final String COMMENT = "COMMENT";
    public static final String ATTACHMENT = "ATTACHMENT";
    public static final String BOOK = "BOOK";
    public static final String ROLE = "ROLE";
    public static final String CATEGORY = "CATEGORY";
  }
  
  public static class PERMISSION {
    public static final String CREATE_ACCOUNT = "CREATE_ACCOUNT";
    public static final String UPDATE_ACCOUNT = "UPDATE_ACCOUNT";
    public static final String DELETE_ACCOUNT = "DELETE_ACCOUNT";
    
    public static final String CREATE_CART = "CREATE_CART";
    public static final String READ_CART = "READ_CART";
    public static final String UPDATE_CART = "UPDATE_CART";
    public static final String DELETE_CART = "DELETE_CART";
    
    public static final String CREATE_COMMENT = "CREATE_COMMENT";
    public static final String READ_COMMENT = "READ_COMMENT";
    
    public static final String CREATE_ATTACHMENT = "CREATE_ATTACHMENT";
    
    public static final String CREATE_BOOK = "CREATE_BOOK";
    public static final String READ_BOOK = "READ_BOOK";
    public static final String UPDATE_BOOK = "UPDATE_BOOK";
    public static final String DELETE_BOOK = "DELETE_BOOK";
    
    public static final String CREATE_ROLE = "CREATE_ROLE";
    public static final String READ_ROLE = "READ_ROLE";
    public static final String UPDATE_ROLE = "UPDATE_ROLE";
    public static final String DELETE_ROLE = "DELETE_ROLE";
    
    public static final String CREATE_CATEGORY = "CREATE_CATEGORY";
    public static final String READ_CATEGORY = "READ_CATEGORY";
    public static final String UPDATE_CATEGORY = "UPDATE_CATEGORY";
    public static final String DELETE_CATEGORY = "DELETE_CATEGORY";
  }
}
