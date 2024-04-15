package com.example.Ethnicity.entity;

import jakarta.persistence.*;
import lombok.*;



@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ethnicity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(unique = true)
    private String Name;
}
