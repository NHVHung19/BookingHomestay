package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String utilityName;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_TypeUtility", nullable=false)
    private TypeUtility typeUtility;

}
