package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.*;
import edu.cmu.bigbowl.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CheckoutItemService checkoutItemService;
    @Autowired
    private CookService cookService;
    @Autowired
    private EaterService eaterService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ReviewService reviewService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public String getAllTest() throws IOException {
        String res = "";
        res += "[Account Service]\r\n";
        res += testAccountFunctions();
        res += "\r\n[Cook Service]\r\n";
        res += testCookFunctions();
        res += "\r\n[Cart Service]\r\n";
        res += testCartFunctions();
        res += "\n[Eater Service]\r\n";
        res += testEaterFunctions();
        res += "\n[Item Service]\r\n";
        res += testItemFunctions();
        res += "\n[CheckoutItem Service]\r\n";
        res += testCheckoutItemFunctions();
        res += "\n[Menu Service]\r\n";
        res += testMenuFunctions();
        res += "\n[Order Service]\r\n";
        res += testOrderFunctions();
        res += "\n[Review Service]\r\n";
        res += testReviewFunctions();
        return res;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String testAccountFunctions(){
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;

        // test post and get
        testNum += 2;
        Account account = new Account("TESTING", "T","E","S","T","T", Boolean.FALSE,
                Boolean.FALSE);
        accountService.postAccount(account);
        Account getAccount = accountService.getAccountById("TESTING").orElse(null);
        if (getAccount != null){
            ans += "postAccount works\r\n";
            ans += "getAccountById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postAccount Fail\r\n";
            ans += "getAccountById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        account.setPassword("LOL");
        accountService.updateAccountById("TESTING", account);
        getAccount = accountService.getAccountById("TESTING").orElse(null);
        if (getAccount != null && getAccount.getPassword().equals("LOL")){
            ans += "updateAccountById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateAccountById Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        accountService.deleteAccountById("TESTING");
        getAccount = accountService.getAccountById("TESTING").orElse(null);
        if (getAccount == null){
            ans += "deleteAccountById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteAccountById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String testCartFunctions(){
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;

        // test post and get
        testNum += 2;
        Cart cart = new Cart("TESTING", null, 1.2);
        cartService.postCart(cart);
        Cart getCart = cartService.getCartById("TESTING").orElse(null);
        if (getCart != null){
            ans += "postCart works\r\n";
            ans += "getCartById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postCart Fail\r\n";
            ans += "getCartById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        cart.setTotalPrice(1.1);
        cartService.updateCartById("TESTING", cart);
        getCart = cartService.getCartById("TESTING").orElse(null);
        if (getCart != null && getCart.getTotalPrice() == 1.1){
            ans += "updateCartById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateCartById Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        cartService.deleteCartById("TESTING");
        getCart = cartService.getCartById("TESTING").orElse(null);
        if (getCart == null){
            ans += "deleteCartById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteCartById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    @RequestMapping(value = "/cook", method = RequestMethod.GET)
    public String testCookFunctions() throws IOException {
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;
        // test post and get
        testNum += 2;
        Cook cook = new Cook("TESTING", "T","E","S","T","T",0,
                "E",null, 5.0 ,Boolean.FALSE,"T", 37.376202, -122.101392, "T", "E", null);
        cookService.postCook(cook);
        Cook getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook != null){
            ans += "postCook works\r\n";
            ans += "getCookById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postCook Fail\r\n";
            ans += "getCookById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        cook.setRating(1.0);
        cookService.updateCookById("TESTING", cook);
        getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook != null && getCook.getRating() == 1.0){
            ans += "updateCookById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateCookById Fail\r\n";
        }

        // test geoSearch and get
        testNum += 1;
        cookService.updateCookById("TESTING", cook);
        getCook = cookService.getCookById("TESTING").orElse(null);
        Point point = new Point(cook.getLng(), cook.getLat());
        Distance distance = new Distance(10, Metrics.MILES);
        List<Cook> getCooks = cookService.getCookByPoint(point, distance);
        Boolean flag = Boolean.FALSE;
        for (Cook c: getCooks)
        {
            if (c.getCookId().equals("TESTING")){
                flag = Boolean.TRUE;
            }
        }
        if (flag == Boolean.TRUE) {
            ans += "getCookByPoint works\r\n";
            passNum += 1;
        }
        else{
            ans += "getCookByPoint Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        cookService.deleteCookById("TESTING");
        getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook == null){
            ans += "deleteCookById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteCookById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    @RequestMapping(value = "/eater", method = RequestMethod.GET)
    public String testEaterFunctions(){
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;

        // test post and get
        testNum += 2;
        Eater eater = new Eater("TESTING", 1.0, null);
        eaterService.postEater(eater);
        Eater getEater = eaterService.getEaterById("TESTING").orElse(null);
        if (getEater != null){
            ans += "postEater works\r\n";
            ans += "getEaterById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postEater Fail\r\n";
            ans += "getEaterById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        eater.setRating(5.0);
        eaterService.updateEaterById("TESTING", eater);
        getEater = eaterService.getEaterById("TESTING").orElse(null);
        if (getEater != null && getEater.getRating() == 5.0){
            ans += "updateEaterById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateEaterById Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        eaterService.deleteEaterById("TESTING");
        getEater = eaterService.getEaterById("TESTING").orElse(null);
        if (getEater == null){
            ans += "deleteEaterById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteEaterById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public String testItemFunctions(){
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;

        // test post and get
        testNum += 2;
        Item item = new Item("TESTING", "EE","TT", 1, 2.0, "T", "E", null);
        itemService.postItem(item);
        Item getItem = itemService.getItemById("TESTING").orElse(null);
        if (getItem != null){
            ans += "postItem works\r\n";
            ans += "getItemById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postItem Fail\r\n";
            ans += "getItemById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        item.setDescription("LOL");
        itemService.updateItemById("TESTING", item);
        getItem = itemService.getItemById("TESTING").orElse(null);
        if (getItem != null && getItem.getDescription().equals("LOL")){
            ans += "updateItemById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateItemById Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        itemService.deleteItemById("TESTING");
        getItem = itemService.getItemById("TESTING").orElse(null);
        if (getItem == null){
            ans += "deleteItemById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteItemById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    @RequestMapping(value = "/checkoutItem", method = RequestMethod.GET)
    public String testCheckoutItemFunctions(){
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;

        // test post and get
        testNum += 2;
        CheckoutItem checkoutItem = new CheckoutItem("TESTING", 0.0, 1, "aa");
        checkoutItemService.postCheckoutItem(checkoutItem);
        CheckoutItem getCheckoutItem = checkoutItemService.getCheckoutItemById("TESTING").orElse(null);
        if (getCheckoutItem != null){
            ans += "postCheckoutItem works\r\n";
            ans += "getCheckoutItemById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postCheckoutItem Fail\r\n";
            ans += "getCheckoutItemById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        checkoutItem.setItemId("LOL");
        checkoutItemService.updateCheckoutItemById("TESTING", checkoutItem);
        getCheckoutItem = checkoutItemService.getCheckoutItemById("TESTING").orElse(null);
        if (getCheckoutItem != null && getCheckoutItem.getItemId().equals("LOL")){
            ans += "updateCheckoutItemById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateCheckoutItemById Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        checkoutItemService.deleteCheckoutItemById("TESTING");
        getCheckoutItem = checkoutItemService.getCheckoutItemById("TESTING").orElse(null);
        if (getCheckoutItem == null){
            ans += "deleteCheckoutItemById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteCheckoutItemById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String testMenuFunctions(){
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;

        // test post and get
        testNum += 2;
        Menu menu = new Menu("TESTING", new Date(), Boolean.TRUE,"S",Boolean.TRUE, null);
        menuService.postMenu(menu);
        Menu getMenu = menuService.getMenuById("TESTING").orElse(null);
        if (getMenu != null){
            ans += "postMenu works\r\n";
            ans += "getMenuById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postMenu Fail\r\n";
            ans += "getMenuById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        menu.setCuisine("LOL");
        menuService.updateMenuById("TESTING", menu);
        getMenu = menuService.getMenuById("TESTING").orElse(null);
        if (getMenu != null && getMenu.getCuisine().equals("LOL")){
            ans += "updateMenuById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateMenuById Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        menuService.deleteMenuById("TESTING");
        getMenu = menuService.getMenuById("TESTING").orElse(null);
        if (getMenu == null){
            ans += "deleteMenuById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteMenuById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String testOrderFunctions(){
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;

        // test post and get
        testNum += 2;
        Order order = new Order("TESTING", "T", "E",new Date(), 5.5, new Date(), "T", "E", new Date());
        orderService.postOrder(order);
        Order getOrder = orderService.getOrderById("TESTING").orElse(null);
        if (getOrder != null){
            ans += "postOrder works\r\n";
            ans += "getOrderById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postOrder Fail\r\n";
            ans += "getOrderById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        order.setPickUpName("LOL");
        orderService.updateOrderById("TESTING", order);
        getOrder = orderService.getOrderById("TESTING").orElse(null);
        if (getOrder != null && getOrder.getPickUpName().equals("LOL")){
            ans += "updateOrderById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateOrderById Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        orderService.deleteOrderById("TESTING");
        getOrder = orderService.getOrderById("TESTING").orElse(null);
        if (getOrder == null){
            ans += "deleteOrderById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteOrderById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public String testReviewFunctions(){
        String ans = "";
        Integer testNum = 0;
        Integer passNum = 0;

        // test post and get
        testNum += 2;
        Review review = new Review("TESTING", "T", "E", "S", "T", 0.0);
        reviewService.postReview(review);
        Review getReview = reviewService.getReviewById("TESTING").orElse(null);
        if (getReview != null){
            ans += "postReview works\r\n";
            ans += "getReviewById works\r\n";
            passNum += 2;
        }
        else{
            ans += "postReview Fail\r\n";
            ans += "getReviewById Fail\r\n";
        }

        // test patch and get
        testNum += 1;
        review.setOrderId("LOL");
        reviewService.updateReviewById("TESTING", review);
        getReview = reviewService.getReviewById("TESTING").orElse(null);
        if (getReview != null && getReview.getOrderId().equals("LOL")){
            ans += "updateReviewById works\r\n";
            passNum += 1;
        }
        else{
            ans += "updateReviewById Fail\r\n";
        }

        // test delete and get
        testNum += 1;
        reviewService.deleteReviewById("TESTING");
        getReview = reviewService.getReviewById("TESTING").orElse(null);
        if (getReview == null){
            ans += "deleteReviewById works\r\n";
            passNum += 1;
        }
        else{
            ans += "deleteReviewById Fail\r\n";
        }
        return ans + "Test: " + testNum + " Pass: " + passNum + " Pass Ratio: " + passNum + "/" + testNum + " = " + (float)passNum/testNum*100 + "%\r\n";
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll()
    {
        accountService.deleteAccounts();
        cartService.deleteCarts();
        checkoutItemService.deleteCheckoutItems();
        cookService.deleteCooks();
        eaterService.deleteEaters();
        itemService.deleteitems();
        menuService.deleteMenus();
        orderService.deleteOrders();
        reviewService.deleteReviews();
    }

    // POST
    @RequestMapping(method = RequestMethod.POST)
    public void postFakeAll() throws IOException {
        accountService.postFakeAccount();
        cookService.postFakeCook();
        itemService.postFakeItem();
        reviewService.postFakeReview();
    }
}
