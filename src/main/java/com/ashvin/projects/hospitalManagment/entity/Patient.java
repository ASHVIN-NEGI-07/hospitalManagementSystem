package com.ashvin.projects.hospitalManagment.entity;

import com.ashvin.projects.hospitalManagment.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(
        name = "patient",
        uniqueConstraints = {
         //       @UniqueConstraint(name = "unique_patient_email",columnNames = {"email"}),  (for 1 column you can use [ @Column unique = true ] instead)
                @UniqueConstraint(name = "unique_patient_name_birthdate",columnNames = {"name","birthDate"})
        },
        indexes = {
                @Index(name = "idx_patient_birth_date",columnList = "birthDate")
        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // name = "patient_name"  --> to change the column name in the database table
    @Column(nullable = false,length = 40)   // nullable = false means name is now a required field ( name can not be NULL )
    private String name;

    //@ToString.Exclude                  //   birthDate field wil be excluded from the toString method
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType  bloodGroup;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")  // owning side of the relationship
    private Insurance insurance;

    @OneToMany(mappedBy = "patient",cascade = {CascadeType.REMOVE},orphanRemoval = true,fetch = FetchType.EAGER)
   // @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();
}
