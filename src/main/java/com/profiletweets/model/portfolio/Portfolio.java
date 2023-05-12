package com.profiletweets.model.portfolio;

import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Jacksonized
@SuperBuilder
@Entity(name = "portfolio")
public class Portfolio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name  = "idportfolio")
    private Long idPortfolio;

    @Column(name  = "image_path")
    private String imagePath;

    private String name;

    private String experience;

    @Column(name  = "twitter_user_name")
    private String twitterUserName;

}
