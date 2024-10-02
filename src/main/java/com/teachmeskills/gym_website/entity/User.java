package com.teachmeskills.gym_website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usrs")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "date_of_birth")
    private String dateBirth;

    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Review> clientReviewList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Review> employeeReviewList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<SaleSeasonTickets> saleSeasonTicketsList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Schedule> scheduleEmployeeList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Schedule> scheduleClientList = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", clientReviewList=" + clientReviewList.size() +
                ", employeeReviewList=" + employeeReviewList.size() +
                ", saleSeasonTicketsList=" + saleSeasonTicketsList.size() +
                ", scheduleEmployeeList=" + scheduleEmployeeList.size() +
                ", scheduleClientList=" + scheduleClientList.size() +
                '}';
    }
}
