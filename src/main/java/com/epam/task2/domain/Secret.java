package com.epam.task2.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SECRETS")
@Data
public class Secret {

    @Id
    @Column(name = "SECRET_ID")
    private String uuid;

    @Column(name = "SECRET_STRING")
    private String secretString;

}
