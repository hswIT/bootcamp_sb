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
public class Address {
  private String street;
  private String suite;
  private String city;
  private String zipcode;
  private Geo geo;
}
