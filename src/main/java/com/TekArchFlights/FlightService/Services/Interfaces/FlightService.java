package com.TekArchFlights.FlightService.Services.Interfaces;

import com.TekArchFlights.FlightService.DTO.FlightDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<FlightDTO> getAllFlights();

    Optional<FlightDTO> getFlightById(Long flightId);

    FlightDTO addFlight(FlightDTO flightDTO);

    FlightDTO updateFlight(Long flightId, FlightDTO flightDTO);

    void deleteFlight(Long flightId);
}

