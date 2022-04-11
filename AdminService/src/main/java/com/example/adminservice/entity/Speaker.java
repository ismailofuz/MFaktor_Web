package com.example.adminservice.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Attachment attachment;

    private String firstName;
    private String lastName;
    private String middleName;
    private String companyName;
    private String position;
    private String bio;

    private String phoneNumber;
}

