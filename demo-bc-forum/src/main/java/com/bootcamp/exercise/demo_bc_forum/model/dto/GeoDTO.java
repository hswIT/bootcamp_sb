package com.bootcamp.exercise.demo_bc_forum.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeoDTO {
    private String lat;
    private String lng;
}
