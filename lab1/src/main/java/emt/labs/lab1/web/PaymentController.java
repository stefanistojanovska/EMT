package emt.labs.lab1.web;


import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import emt.labs.lab1.dto.ChargeRequest;
import emt.labs.lab1.models.Product;
import emt.labs.lab1.models.Transaction;
import emt.labs.lab1.service.PaymentService;
import emt.labs.lab1.service.ProductService;
import emt.labs.lab1.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private PaymentService paymentService;

    private ProductService productService;
    private TransactionService transactionService;

    public PaymentController(PaymentService paymentService, ProductService productService, TransactionService transactionService) {
        this.paymentService = paymentService;
        this.productService=productService;
        this.transactionService=transactionService;
    }

    @RequestMapping("/checkout/{id}")
    public String checkout(@PathVariable("id") Long id, Model model) {

       Product product=productService.getById(id);

        model.addAttribute("name", product.getName());
        model.addAttribute("amount", product.getPrice().intValue()*100);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }

    @PostMapping("/charge")
    public String charge( ChargeRequest chargeRequest, Model model)
            throws StripeException {

        chargeRequest.setDescription("EMT payment");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        if(charge.getStatus().equals("succeeded"))
        {
            //DOVRSI
            Transaction transaction=new Transaction();
            //transaction.setId(transaction.getId());
            transaction.setTransactionId(charge.getId());
            transaction.setStatus(charge.getStatus());
            transaction.setChargeId(charge.getId());
            transaction.setBalanceId(charge.getBalanceTransaction());
            transactionService.addNewTrasnsaction(transaction);
        }
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

}
