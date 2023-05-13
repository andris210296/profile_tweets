package com.profiletweets.service.impl;

import com.profiletweets.model.PortfolioUtils;
import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.model.portfolio.PortfolioOutput;
import com.profiletweets.model.twitter.Tweet;
import com.profiletweets.repository.PortfolioRepository;
import com.profiletweets.service.PortfolioService;
import com.profiletweets.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private TwitterService twitterService;

    @Override
    public ResponseEntity createPortfolio(Portfolio portfolio) {
        Portfolio portfolioSaved = portfolioRepository.save(portfolio);
        return ResponseEntity.ok().body(callTweetsFromPortfolio(portfolioSaved));
    }

    @Override
    public ResponseEntity addImageInPortfolio(long id, MultipartFile imageFile) {

        return portfolioRepository.findById(id)
                .map(portfolio ->{

                    String folder = "/photos/";
                    byte[] bytes = new byte[0];
                    Path path = null;
                    try {
                        bytes = imageFile.getBytes();
                        path = Paths.get(folder + imageFile.getOriginalFilename());
                        Files.write(path, bytes);

                    } catch (IOException e) {
                        return ResponseEntity.internalServerError().build();
                    }

                    portfolio.setImagePath(path.toString());
                    portfolioRepository.save(portfolio);

                    return ResponseEntity.ok()
                        .body(callTweetsFromPortfolio(portfolio));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity findAllPortfolios() {
        return ResponseEntity.ok().body(
                portfolioRepository.findAll().stream()
                        .map(portfolio -> callTweetsFromPortfolio(portfolio))
                        .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity findPortfolio(long id) {
        return portfolioRepository.findById(id)
                .map(portfolio -> ResponseEntity.ok()
                        .body(callTweetsFromPortfolio(portfolio)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity updatePortfolio(long id, Portfolio portfolio) {
        return portfolioRepository.findById(id)
                .map(record -> {
                    record.setImagePath(portfolio.getImagePath());
                    record.setName(portfolio.getName());
                    record.setExperience(portfolio.getExperience());
                    record.setTwitterUserName(portfolio.getTwitterUserName());
                    Portfolio updated = portfolioRepository.save(record);
                    return ResponseEntity.ok().body(callTweetsFromPortfolio(updated));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deletePortfolio(long id) {
        return portfolioRepository.findById(id)
                .map(record -> {
                    portfolioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    private PortfolioOutput callTweetsFromPortfolio(Portfolio portfolio){
        Optional<List<Tweet>> tweetsList = twitterService.getTweetsByUserName(portfolio.getTwitterUserName());
        return PortfolioUtils.portfolioToPortfolioOutput(portfolio, tweetsList.orElse(Arrays.asList(Tweet.builder().build())));
    }
}
