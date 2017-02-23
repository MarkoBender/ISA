package com.bender.Repositories;

import com.bender.Beans.Guest;
import com.bender.Beans.Invitation;
import com.bender.Beans.Reservation;
import com.bender.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bender on 1/5/2017.
 */
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    List<Invitation> findByInvited(Guest guest);
    List<Invitation> findByReservation(Reservation reservation);
    Invitation findByInvitedAndReservation(Guest invited, Reservation reservation);
}
