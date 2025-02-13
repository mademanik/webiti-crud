package com.webiti.crud.repository;

import com.webiti.crud.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Query("select e from #{#entityName} e where e.user.id = ?1")
    List<Order> findByUserId(int userId);
}
