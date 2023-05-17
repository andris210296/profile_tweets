package com.profiletweets.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profiletweets.ProfileTweetsTestHelper;
import com.profiletweets.model.portfolio.PortfolioOutput;
import com.profiletweets.service.PortfolioService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PortfolioControllerTest extends ProfileTweetsTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortfolioService portfolioService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createTest() throws Exception {

        when(portfolioService.createPortfolio(returnPortfolio())).thenReturn(returnOptionalPortfolioOutput());

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/portfolio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(generateHashMapPortfolioWithoutId())))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();

        PortfolioOutput portfolio = mapper.readValue(resultString,PortfolioOutput.class);

        Assert.assertEquals(NAME, portfolio.getName());
        Assert.assertEquals(Long.valueOf(1), portfolio.getIdPortfolio());
    }

    @Test
    public void findAllTest() throws Exception {

        when(portfolioService.findAllPortfolios()).thenReturn(Optional.of(List.of(returnPortfolioOutput())));

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/portfolio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();

        List<PortfolioOutput> portfolioOutputList = mapper.readValue(resultString, new TypeReference<List<PortfolioOutput>>(){});

        Assert.assertEquals(NAME, portfolioOutputList.get(0).getName());
    }

    @Test
    public void findPortfolioTest() throws Exception {

        when(portfolioService.findPortfolio(1l)).thenReturn(Optional.of(returnPortfolioOutput()));

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/portfolio/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();

        PortfolioOutput portfolioOutput = mapper.readValue(resultString,PortfolioOutput.class);

        Assert.assertEquals(NAME, portfolioOutput.getName());
    }

    @Test
    public void updateTest() throws Exception {

        when(portfolioService.updatePortfolio(1l, returnPortfolioWithId())).thenReturn(Optional.of(returnPortfolioOutput()));

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/portfolio/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(generateHashMapPortfolioWithId())))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deleteTest() throws Exception {

        when(portfolioService.deletePortfolio(1l)).thenReturn(Optional.of(returnPortfolioOutput()));

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/portfolio/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
