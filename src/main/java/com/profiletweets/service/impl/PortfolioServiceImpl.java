package com.profiletweets.service.impl;

import com.profiletweets.model.PortfolioUtils;
import com.profiletweets.model.portfolio.Portfolio;
import com.profiletweets.model.portfolio.PortfolioOutput;
import com.profiletweets.model.twitter.Tweet;
import com.profiletweets.repository.PortfolioRepository;
import com.profiletweets.service.PortfolioService;
import com.profiletweets.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${dir.images:photos}")
    private String dirImages;

    @Override
    public Optional<PortfolioOutput> createPortfolio(Portfolio portfolio) {
        Portfolio portfolioSaved = portfolioRepository.save(portfolio);
        return Optional.of(callTweetsFromPortfolio(portfolioSaved));
    }

    @Override
    public Optional<PortfolioOutput> addImageInPortfolio(long id, MultipartFile imageFile) {

        return portfolioRepository.findById(id)
                .map(portfolio ->{

                    String uploadDirectory = System.getProperty("user.dir") + "\\src\\main\\webapp\\"+ dirImages +"\\";

                    byte[] bytes = new byte[0];
                    Path path = null;
                    try {
                        bytes = imageFile.getBytes();
                        path = Paths.get(uploadDirectory + imageFile.getOriginalFilename());
                        Files.write(path, bytes);

                    } catch (IOException e) {
                        System.err.println("In catch IOException: "+e.getClass());
                        return Optional.of(new PortfolioOutput());
                    }

                    portfolio.setImagePath(PortfolioUtils.pathCompleteToSmall(path.toString()));
                    portfolioRepository.save(portfolio);

                    return Optional.of(callTweetsFromPortfolio(portfolio));
                }).orElse(Optional.empty());
    }

    @Override
    public Optional<List<PortfolioOutput>> findAllPortfolios() {
        return Optional.of(portfolioRepository.findAll().stream()
                .map(portfolio -> {
                    return callTweetsFromPortfolio(portfolio);
                })
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<PortfolioOutput> findPortfolio(long id) {
        return portfolioRepository.findById(id)
                .map(portfolio -> Optional.of(callTweetsFromPortfolio(portfolio)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<PortfolioOutput> updatePortfolio(long id, Portfolio portfolio) {
        return portfolioRepository.findById(id)
                .map(record -> {
                    record.setImagePath(portfolio.getImagePath());
                    record.setName(portfolio.getName());
                    record.setExperience(portfolio.getExperience());
                    record.setTwitterUserName(portfolio.getTwitterUserName());
                    Portfolio updated = portfolioRepository.save(record);
                    return Optional.of(callTweetsFromPortfolio(updated));
                }).orElse(Optional.empty());
    }

    @Override
    public Optional<PortfolioOutput> deletePortfolio(long id) {
        return portfolioRepository.findById(id)
                .map(record -> {
                    portfolioRepository.deleteById(id);
                    return Optional.of(new PortfolioOutput());
                }).orElse(Optional.empty());
    }

    private PortfolioOutput callTweetsFromPortfolio(Portfolio portfolio){
        Optional<List<Tweet>> tweetsList = twitterService.getTweetsByUserName(portfolio.getTwitterUserName());
        return PortfolioUtils.portfolioToPortfolioOutput(portfolio, tweetsList.orElse(Arrays.asList(Tweet.builder().build())));
    }

}
