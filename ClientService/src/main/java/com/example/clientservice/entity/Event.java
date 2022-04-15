package com.example.clientservice.entity;

import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Speaker;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Attachment attachment;

    private String name;

    @ManyToOne
    private Speaker speaker;

    @Column(columnDefinition = "text")
    private String description;

    private Timestamp startTime;

    private boolean byPlace = true;
}

