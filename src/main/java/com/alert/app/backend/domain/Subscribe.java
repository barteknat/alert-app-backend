package com.alert.app.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "SUBSCRIBES")
public class Subscribe {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", unique = true)
    private User user;
    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;
}
