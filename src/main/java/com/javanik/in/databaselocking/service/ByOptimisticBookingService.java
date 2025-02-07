package com.javanik.in.databaselocking.service;


import com.javanik.in.databaselocking.entity.SeatDetails;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ByOptimisticBookingService {

      Logger log = LoggerFactory.getLogger(ByOptimisticBookingService.class);
       private final TicketBooking ticketBooking;

    public ByOptimisticBookingService(TicketBooking ticketBooking) {
        this.ticketBooking = ticketBooking;
    }

       public void testOptimisticLocking(Long seatId) throws InterruptedException {
            Thread th1= new Thread(() ->
            {
                try {
                    log.info(Thread.currentThread().getName()+ "is now attempting to book ticket " );
                    SeatDetails seatDetails = ticketBooking.bookSeatWithOptimistic(seatId);
                    log.info(Thread.currentThread().getName()+ "is now attempting to book ticket " +seatDetails.getVersion());
                }
                catch (Exception e)
                {
                    log.info(Thread.currentThread().getName()+ "Failed !!!" +e);
                }
            });

           Thread th2= new Thread(() ->
           {
               try {
                   log.info(Thread.currentThread().getName()+ "is now attempting to book ticket " );
                   SeatDetails seatDetails = ticketBooking.bookSeatWithOptimistic(seatId);
                   log.info(Thread.currentThread().getName()+ "is now attempting to book ticket " +seatDetails.getVersion());
               }
               catch (Exception e)
               {
                   log.info(Thread.currentThread().getName()+ "Failed !!!" +e);
               }
           });

           th1.start();
          th2.start();
          th1.join();
          th2.join();

       }

}
