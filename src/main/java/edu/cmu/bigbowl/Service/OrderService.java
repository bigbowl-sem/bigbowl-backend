package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.OrderDao;
import edu.cmu.bigbowl.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    // Create
    public Order postOrder(Order order) {
        return orderDao.save(order);
    }

    // Read
    public Collection<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderDao.findById(id);
    }

    // Update
    public Optional<Order> updateOrders(Order order) {
        if (order.getOrderId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateOrderById(order.getOrderId(), order);
        }
        else{
            return null;
        }
    }

    public Optional<Order> updateOrderById(String id, Order order) {
        Optional<Order> optOrder = orderDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        optOrder.ifPresent(theOrder -> orderDao.save(order));
        return optOrder;
    }

    // Delete
    public Optional<Order> deleteOrder(Order order) {
        Optional<Order> optOrder = orderDao.findById(order.getOrderId());
        optOrder.ifPresent(theOrder -> orderDao.delete(theOrder));
        return optOrder;
    }

    public Optional<Order> deleteOrderById(String id) {
        Optional<Order> optOrder = orderDao.findById(id);
        optOrder.ifPresent(theOrder -> orderDao.delete(theOrder));
        return optOrder;
    }

    public void deleteAccounts() {
        orderDao.deleteAll();
    }
}
