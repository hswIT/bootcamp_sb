package com.bootcamp.exercise.demo_bc_forum.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.exercise.demo_bc_forum.entity.CommentEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.PostEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.UserEntity;
import com.bootcamp.exercise.demo_bc_forum.model.User;
import com.bootcamp.exercise.demo_bc_forum.model.dto.CommentDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.PostDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.UserDTO;

public interface JPHOperation {
  @GetMapping("/jph/users")
  List<User> getUsers();

  @PostMapping("/jph/users")
  List<UserEntity> createUsers();

  @PostMapping ("/jph/saveusers")
  List<UserEntity> saveUsers();

  @PostMapping("/jph/saveposts")
  List<PostEntity> savePosts();

  @GetMapping("/jph/posts")
  List<PostDTO> getAllPosts();

  @PostMapping("/jph/savecomments")
  List<CommentEntity> saveComments();

  @GetMapping("/jph/comments")
  List<CommentDTO> getAllComments();

  @PostMapping("/jph/saveall")
  List<UserDTO> saveAll();

  @GetMapping("/jph/getall")
  List<UserDTO> getAll();

  @GetMapping("/jph/user/comments/")
  Map<String, Object> getCommentByUser(@RequestParam Long id);

}
