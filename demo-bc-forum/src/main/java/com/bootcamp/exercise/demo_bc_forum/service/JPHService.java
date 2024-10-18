package com.bootcamp.exercise.demo_bc_forum.service;

import java.util.List;
import java.util.Map;
import com.bootcamp.exercise.demo_bc_forum.entity.CommentEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.PostEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.UserEntity;
import com.bootcamp.exercise.demo_bc_forum.model.Comment;
import com.bootcamp.exercise.demo_bc_forum.model.Post;
import com.bootcamp.exercise.demo_bc_forum.model.User;
import com.bootcamp.exercise.demo_bc_forum.model.dto.CommentDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.PostDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.UserDTO;

public interface JPHService {
    List<User> getUsers();

    List<Post> getPosts();

    List<Comment> getComments();

    //List<AggregatedDataDTO> getAggregatedData();

    List<UserEntity> saveUsers();

    List<PostEntity> savePosts();

    List<CommentEntity> saveComments();

    List<PostDTO> getAllPosts();

    List<CommentDTO> getAllComments();

    List<UserDTO> saveAll();

    List<UserDTO> getAll();

    Map<String, Object> getCommentByUser(Long id);
}
