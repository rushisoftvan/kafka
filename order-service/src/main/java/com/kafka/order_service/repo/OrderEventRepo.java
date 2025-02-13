package com.kafka.order_service.repo;

import com.kafka.order_service.entities.OutboxCreatOrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderEventRepo extends JpaRepository<OutboxCreatOrderEventEntity, Long> {


    @Query("""
          select o from OutboxCreatOrderEventEntity o where o.eventProcessed = false
    
        """
    )
    public List<OutboxCreatOrderEventEntity> fetchOrderEven();


}
