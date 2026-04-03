package com.example.BookMyShow.payment.repository;


import com.example.BookMyShow.payment.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<PaymentTransaction, String> {
}

