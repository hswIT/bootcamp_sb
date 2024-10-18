package com.bootcamp.exercise.demo_bc_forum.model.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private String name;
    private String email;
    private String body;
    private Long postId;

    @JsonIgnore // Exclude postId from JSON serialization
    public Long getPostId() {
        return postId;
    }
}
