package com.TekArchFlights.FlightService.Controller;


import com.TekArchFlights.FlightService.DTO.FlightDTO;
import com.TekArchFlights.FlightService.Services.Interfaces.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long flightId) {
        Optional<FlightDTO> flightDTO = flightService.getFlightById(flightId);
        return flightDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FlightDTO> addFlight(@RequestBody FlightDTO flightDTO) {
        FlightDTO savedFlight = flightService.addFlight(flightDTO);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<FlightDTO> updateFlight(@PathVariable Long flightId, @RequestBody FlightDTO flightDTO) {
        FlightDTO updatedFlight = flightService.updateFlight(flightId, flightDTO);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build();
    }

}
