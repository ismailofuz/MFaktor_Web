package com.example.adminservice.entity;
//package com.example.clientservice.entity;

import com.example.adminservice.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Event event;

    //@ManyToOne
    //private User user;


    // @ManyToOne
    //private Visitor visitor;


    @Enumerated(EnumType.STRING)
    private Status status = Status.EMPTY;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;






}
