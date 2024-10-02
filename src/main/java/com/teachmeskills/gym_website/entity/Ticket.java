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
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket", nullable = false)
    private long id;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "number_of_visits", nullable = false)
    private int numberVisits;

    @Column(name = "number_of_days", nullable = false)
    private int numberDays;

    @Column(name = "number_of_months", nullable = false)
    private int numberMonth;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    private List<SaleSeasonTickets> saleSeasonTicketsList = new ArrayList<>();


}
