package com.bender.Repositories;

import com.bender.Beans.BuyingOrder;
import com.bender.Beans.Offer;
import com.bender.Beans.Restaurant;
import com.bender.Beans.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nikola on 14-02-17.
 */
public interface OfferRepository extends JpaRepository<Offer,Long> {

    List<Offer> findBySalesman (Salesman salesman);
    List<Offer> findByMyorder (BuyingOrder order);
}
