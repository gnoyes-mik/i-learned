package me.gnoyes.springexample.order.service;

import me.gnoyes.springexample.order.model.Order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
