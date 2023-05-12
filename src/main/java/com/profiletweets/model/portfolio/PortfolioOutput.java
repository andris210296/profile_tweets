package com.profiletweets.model.portfolio;

import com.profiletweets.model.twitter.Tweet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Jacksonized
@SuperBuilder
public class PortfolioOutput extends Portfolio implements Serializable {

    private List<Tweet> tweets;

}
