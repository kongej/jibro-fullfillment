//package com.jibro.fulfill.listener;
//
//import javax.persistence.PostPersist;
//import javax.persistence.PostUpdate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.jibro.fulfill.entity.Ongoing;
//import com.jibro.fulfill.entity.Order;
//import com.jibro.fulfill.repository.OrderRepository;
//
//@Component
//public class OngoingEntityListener {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @PostPersist
//    @PostUpdate
//    public void updateOrderInvc(Ongoing ongoing) {
//        Order order = ongoing.getOrder();
//        if (order != null) {
//            order.setInvc(ongoing.getInvc());
//            orderRepository.save(order);
//        }
//    }
//}
