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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CookService cookService;
    @Autowired
    private AccountService accountService;
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
    public String getAllTest() {
        String res = "";
        res += testAccountFunctions();
        res += testCookFunctions();
        res += testEaterFunctions();
        res += testItemFunctions();
        res += testMenuFunctions();
        res += testOrderFunctions();
        res += testReviewFunctions();
        return res;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String testAccountFunctions(){
        String ans = "";
        // test post and get
        Account cook = new Account("TESTING", "T","E","S","T","T", Boolean.FALSE,
                Boolean.FALSE);
        accountService.postAccount(cook);
        Account getAccount = accountService.getAccountById("TESTING").orElse(null);
        if (getAccount != null){
            ans += "postAccount works\r\n";
            ans += "getAccountById works\r\n";
        }
        else{
            ans += "postAccount Fail\r\n";
            ans += "getAccountById Fail\r\n";
        }

        // test patch and get
        cook.setPassword("LOL");
        accountService.updateAccountById("TESTING", cook);
        getAccount = accountService.getAccountById("TESTING").orElse(null);
        if (getAccount != null && getAccount.getPassword().equals("LOL")){
            ans += "updateAccountById works\r\n";
        }
        else{
            ans += "updateAccountById Fail\r\n";
        }

        // test delete and get
        accountService.deleteAccountById("TESTING");
        getAccount = accountService.getAccountById("TESTING").orElse(null);
        if (getAccount == null){
            ans += "deleteAccountById works\r\n";
        }
        else{
            ans += "deleteAccountById Fail\r\n";
        }
        return ans;
    }

    @RequestMapping(value = "/cook", method = RequestMethod.GET)
    public String testCookFunctions(){
        String ans = "";
        // test post and get
        Cook cook = new Cook("TESTING", "T","E","S","T","T",0,
                "E",null, 5.0 ,Boolean.FALSE,"T", 37.376202, -122.101392);
        cookService.postCook(cook);
        Cook getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook != null){
            ans += "postCook works\r\n";
            ans += "getCookById works\r\n";
        }
        else{
            ans += "postCook Fail\r\n";
            ans += "getCookById Fail\r\n";
        }

        // test patch and get
        cook.setRating(1.0);
        cookService.updateCookById("TESTING", cook);
        getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook != null && getCook.getRating() == 1.0){
            ans += "updateCookById works\r\n";
        }
        else{
            ans += "updateCookById Fail\r\n";
        }

        // test geoSearch and get
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
        }
        else{
            ans += "getCookByPoint Fail\r\n";
        }

        // test delete and get
        cookService.deleteCookById("TESTING");
        getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook == null){
            ans += "deleteCookById works\r\n";
        }
        else{
            ans += "deleteCookById Fail\r\n";
        }
        return ans;
    }

    @RequestMapping(value = "/eater", method = RequestMethod.GET)
    public String testEaterFunctions(){
        String ans = "";
        // test post and get
        Eater cook = new Eater("TESTING", 1.0);
        eaterService.postEater(cook);
        Eater getEater = eaterService.getEaterById("TESTING").orElse(null);
        if (getEater != null){
            ans += "postEater works\r\n";
            ans += "getEaterById works\r\n";
        }
        else{
            ans += "postEater Fail\r\n";
            ans += "getEaterById Fail\r\n";
        }

        // test patch and get
        cook.setRating(5.0);
        eaterService.updateEaterById("TESTING", cook);
        getEater = eaterService.getEaterById("TESTING").orElse(null);
        if (getEater != null && getEater.getRating() == 5.0){
            ans += "updateEaterById works\r\n";
        }
        else{
            ans += "updateEaterById Fail\r\n";
        }

        // test delete and get
        eaterService.deleteEaterById("TESTING");
        getEater = eaterService.getEaterById("TESTING").orElse(null);
        if (getEater == null){
            ans += "deleteEaterById works\r\n";
        }
        else{
            ans += "deleteEaterById Fail\r\n";
        }
        return ans;
    }


    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public String testItemFunctions(){
        String ans = "";
        // test post and get
        Item cook = new Item("TESTING", "TT", 1, 2);
        itemService.postItem(cook);
        Item getItem = itemService.getItemById("TESTING").orElse(null);
        if (getItem != null){
            ans += "postItem works\r\n";
            ans += "getItemById works\r\n";
        }
        else{
            ans += "postItem Fail\r\n";
            ans += "getItemById Fail\r\n";
        }

        // test patch and get
        cook.setDescription("LOL");
        itemService.updateItemById("TESTING", cook);
        getItem = itemService.getItemById("TESTING").orElse(null);
        if (getItem != null && getItem.getDescription().equals("LOL")){
            ans += "updateItemById works\r\n";
        }
        else{
            ans += "updateItemById Fail\r\n";
        }

        // test delete and get
        itemService.deleteItemById("TESTING");
        getItem = itemService.getItemById("TESTING").orElse(null);
        if (getItem == null){
            ans += "deleteItemById works\r\n";
        }
        else{
            ans += "deleteItemById Fail\r\n";
        }
        return ans;
    }


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String testMenuFunctions(){
        String ans = "";
        // test post and get
        Menu cook = new Menu("TESTING", new Date(), Boolean.TRUE,"S",Boolean.TRUE);
        menuService.postMenu(cook);
        Menu getMenu = menuService.getMenuById("TESTING").orElse(null);
        if (getMenu != null){
            ans += "postMenu works\r\n";
            ans += "getMenuById works\r\n";
        }
        else{
            ans += "postMenu Fail\r\n";
            ans += "getMenuById Fail\r\n";
        }

        // test patch and get
        cook.setCuisine("LOL");
        menuService.updateMenuById("TESTING", cook);
        getMenu = menuService.getMenuById("TESTING").orElse(null);
        if (getMenu != null && getMenu.getCuisine().equals("LOL")){
            ans += "updateMenuById works\r\n";
        }
        else{
            ans += "updateMenuById Fail\r\n";
        }

        // test delete and get
        menuService.deleteMenuById("TESTING");
        getMenu = menuService.getMenuById("TESTING").orElse(null);
        if (getMenu == null){
            ans += "deleteMenuById works\r\n";
        }
        else{
            ans += "deleteMenuById Fail\r\n";
        }
        return ans;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String testOrderFunctions(){
        String ans = "";
        // test post and get
        Order cook = new Order("TESTING", "T", "E",new Date(), 5.5, new Date(), "T", "E", new Date());
        orderService.postOrder(cook);
        Order getOrder = orderService.getOrderById("TESTING").orElse(null);
        if (getOrder != null){
            ans += "postOrder works\r\n";
            ans += "getOrderById works\r\n";
        }
        else{
            ans += "postOrder Fail\r\n";
            ans += "getOrderById Fail\r\n";
        }

        // test patch and get
        cook.setPickUpName("LOL");
        orderService.updateOrderById("TESTING", cook);
        getOrder = orderService.getOrderById("TESTING").orElse(null);
        if (getOrder != null && getOrder.getPickUpName().equals("LOL")){
            ans += "updateOrderById works\r\n";
        }
        else{
            ans += "updateOrderById Fail\r\n";
        }

        // test delete and get
        orderService.deleteOrderById("TESTING");
        getOrder = orderService.getOrderById("TESTING").orElse(null);
        if (getOrder == null){
            ans += "deleteOrderById works\r\n";
        }
        else{
            ans += "deleteOrderById Fail\r\n";
        }
        return ans;
    }


    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public String testReviewFunctions(){
        String ans = "";
        // test post and get
        Review cook = new Review("TESTING", "T", "E", "S");
        reviewService.postReview(cook);
        Review getReview = reviewService.getReviewById("TESTING").orElse(null);
        if (getReview != null){
            ans += "postReview works\r\n";
            ans += "getReviewById works\r\n";
        }
        else{
            ans += "postReview Fail\r\n";
            ans += "getReviewById Fail\r\n";
        }

        // test patch and get
        cook.setOrderId("LOL");
        reviewService.updateReviewById("TESTING", cook);
        getReview = reviewService.getReviewById("TESTING").orElse(null);
        if (getReview != null && getReview.getOrderId().equals("LOL")){
            ans += "updateReviewById works\r\n";
        }
        else{
            ans += "updateReviewById Fail\r\n";
        }

        // test delete and get
        reviewService.deleteReviewById("TESTING");
        getReview = reviewService.getReviewById("TESTING").orElse(null);
        if (getReview == null){
            ans += "deleteReviewById works\r\n";
        }
        else{
            ans += "deleteReviewById Fail\r\n";
        }
        return ans;
    }

}
