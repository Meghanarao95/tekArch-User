package com.acme.springbootbootstrapproject.services;

import com.acme.springbootbootstrapproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TafUserService {

    private final String BASE_URL = "http://localhost:9200/datastore";

    @Autowired
    private RestTemplate restTemplate;

    // Register a new user
    public Users registerUser(Users user) {
        String url = BASE_URL + "/users";

        // Create the HTTP headers (optional)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);  // Set Content-Type header

        // Create the HTTP entity with user object and headers
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);

        ResponseEntity<Users> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.POST,
                entity,
                Users.class);

        return response.getBody();
    }

    // Get user details by ID
    public Optional<Users> getUserById(Long userId) {
        String url = BASE_URL + "/users/" + userId;

        ResponseEntity<Users> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Users>() {});

        return Optional.ofNullable(response.getBody());
    }

    // Update user details
    public Users updateUser(Long userId, Users updatedUser) {
        String url = BASE_URL + "/users/" + userId;

        // Create the HTTP headers (optional)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);  // Set Content-Type header

        // Create the HTTP entity with user object and headers
        HttpEntity<Users> entity = new HttpEntity<>(updatedUser, headers);

        ResponseEntity<Users> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.PUT,
                entity,
                Users.class);

        return response.getBody();
    }
}
