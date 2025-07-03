package com.me.bookproject.entity.RBAC0;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permission {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  
  @ManyToMany(mappedBy = "permissions")
  private List<Role> roles = new ArrayList<>();
  
  @ManyToOne
  @JoinColumn(name = "resource_id")
  private Resource resource;
  @ManyToOne
  @JoinColumn(name = "action_id")
  private Action action;
  
  public Permission(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
