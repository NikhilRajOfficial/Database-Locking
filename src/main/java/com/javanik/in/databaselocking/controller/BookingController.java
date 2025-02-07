package com.javanik.in.databaselocking.controller;



import com.javanik.in.databaselocking.entity.SeatDetails;
import com.javanik.in.databaselocking.repo.SeatRepo;
import com.javanik.in.databaselocking.service.ByOptimisticBookingService;
import com.javanik.in.databaselocking.service.ByPessimisticBookingService;
import com.javanik.in.databaselocking.service.TicketBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private TicketBooking ticketBooking;

    private final ByOptimisticBookingService byOptimisticBookingService;

    public BookingController(ByOptimisticBookingService byOptimisticBookingService, ByPessimisticBookingService byPessimisticBookingService) {
        this.byOptimisticBookingService = byOptimisticBookingService;
        this.byPessimisticBookingService = byPessimisticBookingService;
    }

    private final ByPessimisticBookingService byPessimisticBookingService;


    @GetMapping("/optimistic/{seatId}")
        public String getSeatByOptimistic(@PathVariable Long seatId) throws InterruptedException
         {
             byOptimisticBookingService.testOptimisticLocking(seatId);
             return "Optimistic locking started ";
         }

         @GetMapping("/pessimistic/{seatId)")
         public String getSeatByPessimistic(@PathVariable Long seatIid)  throws InterruptedException
         {
             byPessimisticBookingService.testPessimisticLocking(seatIid);
             return " pessimistic locking started";
         }



      @PostMapping("/setSetDetails")
      public SeatDetails setSeatDetails(@RequestBody SeatDetails seatDetails)
      {
          return ticketBooking.save(seatDetails);
      }


    @GetMapping("/allMovies")
    public ResponseEntity<List<SeatDetails>> allMovies() {
        List<SeatDetails> movies = ticketBooking.getAllSeatDetails();
        return ResponseEntity.ok(movies);
    }




}
