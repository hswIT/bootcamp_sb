package com.bootcamp.exercise.demo_bc_forum.controller.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.exercise.demo_bc_forum.controller.JPHOperation;
import com.bootcamp.exercise.demo_bc_forum.entity.CommentEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.PostEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.UserEntity;
import com.bootcamp.exercise.demo_bc_forum.model.User;
import com.bootcamp.exercise.demo_bc_forum.model.dto.CommentDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.PostDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.UserDTO;
import com.bootcamp.exercise.demo_bc_forum.service.JPHService;

@RestController
public class JPHController implements JPHOperation{
  @Autowired // optional, even there is no such bean, it still execute
  private JPHService jphService; // interface

  @Override
  public List<User> getUsers() {
    return this.jphService.getUsers();
  }

  @Override
  public List<UserEntity> createUsers() {
    return this.jphService.saveUsers();
  }

  @Override
  public List<UserEntity> saveUsers() {
    return this.jphService.saveUsers();
  }

  @Override
  public List<PostEntity> savePosts(){
    return this.jphService.savePosts();
  }

  @Override
  public List<PostDTO> getAllPosts() {
    return jphService.getAllPosts();
  }

  @Override
  public List<CommentEntity> saveComments(){
    return this.jphService.saveComments();
  }

  @Override
  public List<CommentDTO> getAllComments() {
    return jphService.getAllComments();
  }

  @Override
  public List<UserDTO> saveAll() {
    return this.jphService.saveAll();
  }

  @Override
  public List<UserDTO> getAll() {
    return this.jphService.getAll();
  }

  @Override
  public Map<String, Object> getCommentByUser(Long id){
    return this.jphService.getCommentByUser(id);
  }
}
