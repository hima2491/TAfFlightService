package com.TekArchFlights.FlightService.Services;

import com.TekArchFlights.FlightService.DTO.FlightDTO;
import com.TekArchFlights.FlightService.Services.Interfaces.FlightService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final RestTemplate restTemplate;

    @Value("${datastore.service.url}")
    private String datastoreServiceUrl;  // URL of the TafDatastoreService

    public FlightServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        ResponseEntity<List> responseEntity = restTemplate.exchange(
                datastoreServiceUrl,
                org.springframework.http.HttpMethod.GET,
                null,
                List.class
        );
        return responseEntity.getBody();
    }

    @Override
    public Optional<FlightDTO> getFlightById(Long flightId) {
        String url = datastoreServiceUrl + "/" + flightId;
        FlightDTO flightDTO = restTemplate.getForObject(url, FlightDTO.class);
        return Optional.ofNullable(flightDTO);
    }

    @Override
    public FlightDTO addFlight(FlightDTO flightDTO) {
        ResponseEntity<FlightDTO> responseEntity = restTemplate.postForEntity(
                datastoreServiceUrl,
                flightDTO,
                FlightDTO.class
        );
        return responseEntity.getBody();
    }

    @Override
    public FlightDTO updateFlight(Long flightId, FlightDTO flightDTO) {
        String url = datastoreServiceUrl + "/" + flightId;
        restTemplate.put(url, flightDTO);
        return flightDTO;  // Returning updated DTO after performing PUT
    }

    @Override
    public void deleteFlight(Long flightId) {
        String url = datastoreServiceUrl + "/" + flightId;
        restTemplate.delete(url);
    }
}
