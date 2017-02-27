package com.bender.Repositories;

import com.bender.Beans.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nikola on 27-02-17.
 */
public interface DailyScheduleRepository extends JpaRepository<DailySchedule,Long> {
}
