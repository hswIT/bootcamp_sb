package com.bootcamp.exercise.demo_bc_forum.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.exercise.demo_bc_forum.entity.CommentEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.PostEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.UserEntity;
import com.bootcamp.exercise.demo_bc_forum.exception.JPHRestClientException;
import com.bootcamp.exercise.demo_bc_forum.exception.UserNotFoundException;
import com.bootcamp.exercise.demo_bc_forum.mapper.JPHMapper;
import com.bootcamp.exercise.demo_bc_forum.model.Comment;
import com.bootcamp.exercise.demo_bc_forum.model.Post;
import com.bootcamp.exercise.demo_bc_forum.model.User;
import com.bootcamp.exercise.demo_bc_forum.model.dto.CommentDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.PostDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.UserDTO;
import com.bootcamp.exercise.demo_bc_forum.repository.CommentRepository;
import com.bootcamp.exercise.demo_bc_forum.repository.PostRepository;
import com.bootcamp.exercise.demo_bc_forum.repository.UserRepository;
import com.bootcamp.exercise.demo_bc_forum.service.JPHService;
import com.bootcamp.exercise.demo_bc_forum.util.Scheme;
import com.bootcamp.exercise.demo_bc_forum.util.Url;
import jakarta.transaction.Transactional;

@Service
public class JPHServiceImpl implements JPHService {
    @Autowired
    @Qualifier(value = "JPHRestTemplate") 
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JPHMapper jphMapper;

    @Value("${api.jph.domain}")
    private String jphDomain;

    @Value("${api.jph.endpoints.users}")
    private String usersEndpoint;

    @Value("${api.jph.endpoints.posts}")
    private String postsEndpoint;

    @Value("${api.jph.endpoints.comments}")
    private String commentsEndpoint;

    @Override
    public List<User> getUsers() {
        String url = Url.builder()
                .scheme(Scheme.HTTPS)
                .domain(this.jphDomain)
                .endpoint(this.usersEndpoint)
                .build()
                .toUriString();

        User[] users;
        try {
            users = this.restTemplate.getForObject(url, User[].class);
        } catch (RestClientException e) {
            throw new JPHRestClientException("Error fetching users from JSONPlaceholder.");
        }

        return users != null ? List.of(users) : List.of();
    }

    @Override
    public List<Post> getPosts() {
        String url = Url.builder()
                .scheme(Scheme.HTTPS)
                .domain(this.jphDomain)
                .endpoint(this.postsEndpoint)
                .build()
                .toUriString();

        Post[] posts;
        try {
            posts = this.restTemplate.getForObject(url, Post[].class);
        } catch (RestClientException e) {
            throw new JPHRestClientException("Error fetching posts from JSONPlaceholder.");
        }

        return posts != null ? List.of(posts) : List.of();
    }

    @Override
    public List<Comment> getComments() {
        String url = Url.builder()
                .scheme(Scheme.HTTPS)
                .domain(this.jphDomain)
                .endpoint(this.commentsEndpoint)
                .build()
                .toUriString();
        System.out.println("Fetching Comments from URL: " + url);

        Comment[] comments;
        try {
            comments = this.restTemplate.getForObject(url, Comment[].class);
        } catch (RestClientException e) {
            throw new JPHRestClientException("Error fetching comments from JSONPlaceholder.");
        }

        return comments != null ? List.of(comments) : List.of();
    }

    @Override
    public List<UserEntity> saveUsers() {
        List<User> users = this.getUsers();
        return this.saveUsers(users);
    }

    @Override
    public List<PostEntity> savePosts() {
        List<Post> posts = this.getPosts();
        return this.savePosts(posts);
    }

    @Override
    public List<CommentEntity> saveComments() {
        List<Comment> comments = this.getComments();
        return this.saveComments(comments);
    }


    private List<UserEntity> saveUsers(List<User> users) {
        List<UserEntity> userEntities = users.stream()
                .map(this.jphMapper::map) // .map(e->jphMapper.map(e))
                .collect(Collectors.toList());
        return userRepository.saveAll(userEntities);
    }

    private List<PostEntity> savePosts(List<Post> posts) {
        List<PostEntity> postEntities = posts.stream()
                .map(post -> {
                    if (post.getUserId() == 0) {
                        throw new JPHRestClientException("Post entity missing userId.");
                    }
                    Long userId = Long.valueOf(post.getUserId());
                    UserEntity userEntity = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
                    return this.jphMapper.map(post, userEntity);
                })
                .collect(Collectors.toList());
        return postRepository.saveAll(postEntities);
    }

    private List<CommentEntity> saveComments(List<Comment> comments) {
        List<CommentEntity> commentEntities = comments.stream()
                // .map(comment -> {
                //     if (comment.getPostId() == 0) {
                //         throw new JPHRestClientException("Comment entity missing postId.");
                //     }
                //     Long postId = Long.valueOf(comment.getPostId());
                //     PostEntity postEntity = postRepository.findById(postId)
                //         .orElseThrow(() -> new JPHRestClientException("Post not found with id: " + postId));
                //     return this.jphMapper.map(comment, postEntity);
                // })
                // .collect(Collectors.toList());
                    .map(comment -> {
                    try {
                        if (comment.getPostId() == 0) {
                            throw new JPHRestClientException("Comment entity missing postId.");
                        }
                        Long postId = Long.valueOf(comment.getPostId());
                        PostEntity postEntity = postRepository.findById(postId)
                            .orElseThrow(() -> new JPHRestClientException("Post not found with id: " + postId));
                        return this.jphMapper.map(comment, postEntity);
                    } catch (Exception e) {
                        // Log the exception
                        System.err.println("Error processing comment with id " + comment.getId() + ": " + e.getMessage());
                        return null;
                    }
                    })
                    .filter(Objects::nonNull) // Exclude nulls resulting from exceptions
                    .collect(Collectors.toList());
        return commentRepository.saveAll(commentEntities);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postEntities.stream()
                .map(jphMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<CommentEntity> commentEntities = commentRepository.findAll();
        return commentEntities.stream()
                .map(jphMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserDTO> saveAll() {
        // Save Users
        List<UserEntity> savedUsers = this.saveUsers();

        // Save Posts
        List<PostEntity> savedPosts = this.savePosts();

        // Save Comments
        List<CommentEntity> savedComments = this.saveComments();

        // Map saved UserEntities to UserDTOs
        List<UserDTO> userDTOs = savedUsers.stream()
                .map(jphMapper::map)
                .collect(Collectors.toList());

        return userDTOs;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOs = userEntities.stream()
                .map(jphMapper::map)
                .collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    public Map<String, Object> getCommentByUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("user not found"));

        List<Map<String, Object>> comments = new ArrayList<>();
        if (userEntity.getPosts() != null) {
            for (PostEntity post : userEntity.getPosts()) {
                if (post.getComments() != null) {
                    for (CommentEntity commentEntity : post.getComments()) {
                        if (commentEntity != null) {
                            Map<String, Object> commentMap = new HashMap<>();
                            commentMap.put("name", commentEntity.getName());
                            commentMap.put("email", commentEntity.getEmail());
                            commentMap.put("body", commentEntity.getBody());
                            comments.add(commentMap);
                        }
                    }
                }
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", userEntity.getId());
        response.put("username", userEntity.getUsername());
        response.put("comments", comments);
    
        return response;
    }
}
