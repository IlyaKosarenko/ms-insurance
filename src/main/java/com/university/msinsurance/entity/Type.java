package com.university.msinsurance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_seq_gen")
    @SequenceGenerator(name = "type_seq_gen", sequenceName = "type_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
