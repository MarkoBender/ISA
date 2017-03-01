package com.bender.Repositories;

import com.bender.Beans.Guest;
import com.bender.Beans.Reservation;
import com.bender.Beans.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Bender on 1/5/2017.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRestaurant (Restaurant restaurant);
    List<Reservation> findByHostAndDateTimeBefore(Guest host, Date date);
    List<Reservation> findByHostAndDateTimeAfter(Guest host,Date date);
    List<Reservation> findByHost(Guest host);
}
