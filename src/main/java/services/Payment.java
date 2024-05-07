package Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;

public class Payment {
    public static void main(String[] args) {
// Set your secret key here
        Stripe.apiKey = "sk_test_51PCkTGIxYIBwh0lasQ3Ur1y6P2tIgqzxTLGNachA9riqTfKGFwFE1yRfT8Mw3QyGFFoKUEqnyMyW2TcTL4pqz7M900fg0Bl1my";
        try {
// Retrieve your account information
            Account account = Account.retrieve();
            System.out.println("Account ID: " + account.getId());
// Print other account information as needed
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
