package com.bender.Repositories;

import com.bender.Beans.Guest;
import com.bender.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bender on 12/25/2016.
 */
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
