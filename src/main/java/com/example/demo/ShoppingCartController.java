package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ShoppingCartController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

/*@RequestMapping("/addtoCart/{id}")
public String addItem(@PathVariable("id") long id, Model model){
// model.addAttribute("product", new Product());
model.addAttribute("products", productRepository.findById(id).get());
return "productform";
}*/

    @RequestMapping("/addtoCart/{id}")
    public String processCartItem(@PathVariable("id") long id, @Valid Product product, BindingResult result, Model model){
        if(result.hasErrors()){
            return "list";
        }
        product = productRepository.findById(id).get();
        User user = userService.getUser();
        product.setUser(user);
        Collection<Product> products;
        System.out.println("Cart User: "+ user.getUsername());

        Collection<Order> orders =user.getOrders();
        Order order1;
        if( orders.isEmpty() ) {

            System.out.println("Cart is Empty.");
            order1 = new Order();
            orders.add(order1);
            order1.setUser(user);

            products = new ArrayList<>(); // making an order
            products.add(product);
            product.setOrder(order1);
            System.out.println("Product " + product.getName() + " added to new collection products for user " + product.getUser().getUsername());


//            productRepository.save(product);

            orderRepository.save(order1);

        }else{
           /* products = userService.getUserOrderProducts();
            products.add(product);*/
            order1 = orderRepository.findByOrderStatus(false);
            product.setOrder(order1);
            orderRepository.save(order1);

        }
//            Product product1=productRepository.findById(id).get();
// product1.setOrders(order1);
//            product1.addOrder(order1);
//            productRepository.save(product1);

//            System.out.println(productRepository.findById(id).get());
//            model.addAttribute("product",productRepository.findById(id).get());
//            model.addAttribute("products", productRepository.findAll());
//            model.addAttribute("myuser", userService.getUser());
////model.addAttribute("productnames", userService.getUserOrderProductsNames());
//            System.out.println(userService.getUserOrderProductsNames());
//            System.out.println( userService.getUser());


//        model.addAttribute("product", productRepository.findById(id).get());
////model.addAttribute("productnames", userService.getUserOrderProductsNames());
//        System.out.println(userService.getUserOrderProductsNames());
//        model.addAttribute("user", userService.getUser());
//        System.out.println( userService.getUser());
//        model.addAttribute("products", productRepository.findAll());

        model.addAttribute("products", order1.getProducts());

        return "tempCart";
    }


//    For checkout


    // display total price

}
