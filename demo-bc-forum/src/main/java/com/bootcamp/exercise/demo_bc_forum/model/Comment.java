package com.bootcamp.exercise.demo_bc_forum.model;

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
public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}

