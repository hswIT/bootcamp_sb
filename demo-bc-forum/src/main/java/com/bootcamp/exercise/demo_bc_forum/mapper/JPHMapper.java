package com.bootcamp.exercise.demo_bc_forum.mapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.bootcamp.exercise.demo_bc_forum.entity.CommentEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.PostEntity;
import com.bootcamp.exercise.demo_bc_forum.entity.UserEntity;
import com.bootcamp.exercise.demo_bc_forum.model.Comment;
import com.bootcamp.exercise.demo_bc_forum.model.Post;
import com.bootcamp.exercise.demo_bc_forum.model.User;
import com.bootcamp.exercise.demo_bc_forum.model.dto.AddressDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.CommentDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.CompanyDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.GeoDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.PostDTO;
import com.bootcamp.exercise.demo_bc_forum.model.dto.UserDTO;


@Component
public class JPHMapper {
    // @Autowired
    // private UserRepository userRepository;

    public UserEntity map(User user) {
        return UserEntity.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .addrStreet(user.getAddress().getStreet())
                .addrSuite(user.getAddress().getSuite())
                .addrCity(user.getAddress().getCity())
                .addrZipcode(user.getAddress().getZipcode())
                .addrLat(user.getAddress().getGeo().getLat())
                .addrLng(user.getAddress().getGeo().getLng())
                .comName(user.getCompany().getName())
                .comCatchPhrase(user.getCompany().getCatchPhrase())
                .comBs(user.getCompany().getBs())
                .build();
    }

    public PostEntity map(Post post, UserEntity userEntity) {
        return PostEntity.builder()
                .id((long) post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .user(userEntity)
                .build();
    }


    public CommentEntity map(Comment comment, PostEntity postEntity) {
        return CommentEntity.builder()
                .id((long) comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .post(postEntity)
                .build();
    }

    public UserDTO map(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        // Map Address
        AddressDTO addressDTO = AddressDTO.builder()
                .street(userEntity.getAddrStreet())
                .suite(userEntity.getAddrSuite())
                .city(userEntity.getAddrCity())
                .zipcode(userEntity.getAddrZipcode())
                .geo(GeoDTO.builder()
                        .lat(userEntity.getAddrLat())
                        .lng(userEntity.getAddrLng())
                        .build())
                .build();

        // Map Company
        CompanyDTO companyDTO = CompanyDTO.builder()
                .name(userEntity.getComName())
                .catchPhrase(userEntity.getComCatchPhrase())
                .bs(userEntity.getComBs())
                .build();

        // Map Posts
        List<PostDTO> postDTOs = null;
        if (userEntity.getPosts() != null) {
            AtomicInteger postCounter = new AtomicInteger(1); 
            postDTOs = userEntity.getPosts().stream()
                    .map(postEntity -> map(postEntity, postCounter.getAndIncrement()))
                    .collect(Collectors.toList());
        }

        return UserDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .website(userEntity.getWebsite())
                .address(addressDTO)
                .company(companyDTO)
                .posts(postDTOs)
                .build();
    }

    public PostDTO map(PostEntity postEntity) {
        if (postEntity == null) {
            return null;
        }

        // Map Comments
        List<CommentDTO> commentDTOs = null;
        if (postEntity.getComments() != null) {
            AtomicInteger commentCounter = new AtomicInteger(1);
            commentDTOs = postEntity.getComments().stream()
                    .map(commentEntity -> map(commentEntity, commentCounter.getAndIncrement()))
                    .collect(Collectors.toList());
        }

        return PostDTO.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .body(postEntity.getBody())
                .userId(postEntity.getUser().getId())
                .comments(commentDTOs)
                .build();
    }

    public PostDTO map(PostEntity postEntity, int resetPostId) {
        if (postEntity == null) {
            return null;
        }
    
        List<CommentDTO> commentDTOs = null;
        if (postEntity.getComments() != null) {
            AtomicInteger commentCounter = new AtomicInteger(1);
            commentDTOs = postEntity.getComments().stream()
                    .map(commentEntity -> map(commentEntity, commentCounter.getAndIncrement()))
                    .collect(Collectors.toList());
        }
    
        return PostDTO.builder()
                .id((long) resetPostId) // Assign the new sequential post ID
                .title(postEntity.getTitle())
                .body(postEntity.getBody())
                .comments(commentDTOs)
                .build();
    }

    public CommentDTO map(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }

        return CommentDTO.builder()
                .id(commentEntity.getId())
                .name(commentEntity.getName())
                .email(commentEntity.getEmail())
                .body(commentEntity.getBody())
                .postId(commentEntity.getPost().getId())
                .build();
    }

    public CommentDTO map(CommentEntity commentEntity, int resetId) {
        if (commentEntity == null) {
            return null;
        }
    
        return CommentDTO.builder()
                .id((long) resetId) // New sequential ID for DTO
                .name(commentEntity.getName())
                .email(commentEntity.getEmail())
                .body(commentEntity.getBody())
                .build();
    }

    // public PostEntity map(Post post) {
    // Long userId = (long) post.getUserId();
    // UserEntity userEntity = userRepository.findById(userId)
    //         .orElseThrow(() -> new JPHRestClientException("User not found with id: " + userId));

    // return PostEntity.builder()
    //         .id((long) post.getId())
    //         .title(post.getTitle())
    //         .body(post.getBody())
    //         .user(userEntity)
    //         .build();
    // }
}

