package com.quadecom.QuadrantEcom.CurrencyZone;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="currency_zone")
public class CurrencyZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "currency")
    private Integer currency;

    @Column(name = "status")
    private Integer status;

    @Column(name = "description")
    private String description;

}
