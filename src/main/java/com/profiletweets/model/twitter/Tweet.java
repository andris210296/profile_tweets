package com.profiletweets.model.twitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Jacksonized
@SuperBuilder
public class Tweet implements Serializable {

    private Long idTwitter;

    private String text;

    private String imageUrl;
}
