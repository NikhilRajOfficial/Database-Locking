package com.javanik.in.databaselocking.repo;

import com.javanik.in.databaselocking.entity.SeatDetails;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface SeatRepo extends JpaRepository<SeatDetails , Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s WHERE s.id= :seatId")
    SeatDetails findByIdAndLock(Long seatId);
}
