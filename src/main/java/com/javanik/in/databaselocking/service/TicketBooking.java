package com.javanik.in.databaselocking.service;


import com.javanik.in.databaselocking.entity.SeatDetails;
import com.javanik.in.databaselocking.repo.SeatRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class TicketBooking {

    Logger log = LoggerFactory.getLogger(TicketBooking.class);
       private final SeatRepo seatRepo;

    public TicketBooking(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }


     @Transactional
     public SeatDetails bookSeatWithOptimistic(Long seatId)
     {
         //getting the existing seat by given id !!!
        SeatDetails seatDetails =  seatRepo.findById(seatId)
                 .orElseThrow(()-> new RuntimeException("Seat not found with id " +seatId));

         log.info("{}Fetched seat with versions{}", Thread.currentThread().getName(), seatDetails.getVersion());
          if(seatDetails.isBooked())
          {
              throw new RuntimeException("Seat already booked ...");
          }

          // Booking seat
           seatDetails.setBooked(true);

          // updating the version or checking
         return   seatRepo.save(seatDetails);

     }

    @Transactional
    public void bookSeatWithPessimistic(Long seatId)
    {
        log.info("{}Fetched seat with versions{}", Thread.currentThread().getName()+ " is attempting to fetch seat");
        //getting the existing seat by given id !!!
        SeatDetails seatDetails =  seatRepo.findByIdAndLock(seatId);


        log.info("{}Fetched seat with versions{}", Thread.currentThread().getName()+ "acquired the lock for seat id " +seatId);
        if(seatDetails.isBooked())
        {
            log.info("{}Fetched seat with versions{}", Thread.currentThread().getName()+ " failed Seat Id " + seatId + " is already booked ");
            throw new RuntimeException("Seat already booked ...");
        }

        // Booking seat
        log.info("{}Fetched seat with versions{}", Thread.currentThread().getName()+ "booking the seat " +seatId);
        seatDetails.setBooked(true);

        // updating the version or checking
           seatRepo.save(seatDetails);
        log.info("{}Fetched seat with versions{}", Thread.currentThread().getName()+ " successfully book the seat with ID " + seatId);

    }

    public SeatDetails save(SeatDetails seatDetails) {

           return seatRepo.save(seatDetails);

    }

    public List<SeatDetails> getAllSeatDetails() {
        return seatRepo.findAll();
    }
}
