package com.teachmeskills.gym_website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Sale_season_tickets")
public class SaleSeasonTickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale_season_tickets", nullable = false)
    private long id;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "date_start ", nullable = false)
    private String dateStart;

    @Column(name = "date_end ", nullable = false)
    private String dateEnd;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private User client;

   }
