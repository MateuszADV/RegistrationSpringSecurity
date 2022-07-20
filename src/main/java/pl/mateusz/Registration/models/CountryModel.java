package pl.mateusz.Registration.models;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "country")
public class CountryModel {

    @SequenceGenerator(
            name = "country_sequence",
            sequenceName = "country_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "country_sequence"
    )
    private Long Id;
    private String continent;
    @Column(name = "country_en")
    private String country_en;
    @Column(name = "country_pl")
    private String country_pl;
    @Column(name = "capital_city")
    private String capital_city;
    @Column(name = "alfa_2")
    private String alfa_2;
    @Column(name = "alfa_3")
    private String alfa_3;
    @Column(name = "numeric_code")
    private String numeric_code;
    @Column(name = "iso_code")
    private String iso_code;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "update_at")
    private Timestamp updated_at;
    private Boolean exists;
    private String description;


}
