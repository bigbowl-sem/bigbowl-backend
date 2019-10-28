package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Order;
import edu.cmu.bigbowl.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable("id") String id) {
        return orderService.getOrderById(id).orElse(null);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order deleteOrder(@RequestBody Order order) {
        return orderService.deleteOrder(order).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Order deleteOrderById(@PathVariable("id") String id) {
        return orderService.deleteOrderById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        orderService.deleteAccounts();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrders(order).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Order updateOrderById(@PathVariable("id") String id, @RequestBody Order order) {
        return orderService.updateOrderById(id, order).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order insertOrder(@RequestBody Order order) {
        return orderService.postOrder(order);
    }
}
