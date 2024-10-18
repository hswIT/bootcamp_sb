package com.bootcamp.exercise.demo_bc_forum.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
// JPA library/ Framework
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    // Address Fields
    @Column(name = "address_street")
    private String addrStreet;

    @Column(name = "address_suite")
    private String addrSuite;

    @Column(name = "address_city")
    private String addrCity;

    @Column(name = "address_zipcode")
    private String addrZipcode;

    @Column(name = "address_lat")
    private String addrLat;

    @Column(name = "address_lng")
    private String addrLng;

    // Company Fields
    @Column(name = "company_name")
    private String comName;

    @Column(name = "company_catch_phrase")
    private String comCatchPhrase;

    @Column(name = "company_bs")
    private String comBs;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default // Ensures the list is initialized as an empty list by default
    private List<PostEntity> posts = new ArrayList<>();
}

