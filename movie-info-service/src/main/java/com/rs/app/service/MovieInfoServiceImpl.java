package com.rs.app.service;

import com.rs.app.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {
    @Autowired
    @Qualifier("defaultRestTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancer;

    @Override
    public Movie getMovie(long id) {
        System.out.println(loadBalancer);
        ServiceInstance serviceInstance = loadBalancer.choose("movie-catalog-service");
        String targetServerURL = String.format("http://%s:%s/", serviceInstance.getHost(), serviceInstance.getPort());
        System.out.println("============= TARGET SERVER URL ======================");
        System.out.println(targetServerURL);
        System.out.println("======================================================");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<Movie> responseEntity = restTemplate.exchange("http://movie-catalog-service/movie/catalog/id/" + id, HttpMethod.GET, requestEntity, Movie.class);
        return responseEntity.getBody();
    }

    @Override
    public Movie getMovie(String name) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Accept", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<Movie> responseEntity = restTemplate.exchange("http://movie-catalog-service/movie/catalog/name/" + name, HttpMethod.GET, requestEntity, Movie.class);
        return responseEntity.getBody();
    }

}
