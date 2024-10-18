package com.bootcamp.exercise.demo_bc_forum.model.dto;

import java.util.List;
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
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private Long userId;
    private List<CommentDTO> comments;

    @JsonIgnore // Exclude userId from JSON serialization
    public Long getUserId() {
        return userId;
    }
}
