import models.Payment;
import models.Product;
import models.Order;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void simpleCheckRequest() throws IOException {
        Payment payment = new Payment();

        Optional <Order> receipt = payment.purchase(new Product(), new User());

        assertNotNull(receipt);
    }

    @Test
    void requestSuccess() throws IOException {
        Payment payment = new Payment();

        int userBalance = 10;

        Optional<Order> receipt = payment.purchase(new Product("테스트상품", 10), new User(userBalance));

        assertEquals("상품명: 테스트상품, 금액: 10원", receipt.get().toString());
    }

    @Test
    void requestFailed() throws IOException {
        Payment payment = new Payment();

        int userBalance = 10;
        int productPrice = 100;

        Optional<Order> receipt = payment.purchase(new Product("테스트상품", productPrice), new User(userBalance));

        assertEquals(true, receipt.isEmpty());
    }

    @Test
    void modifyUserMoney() throws IOException {
        Payment payment = new Payment();

        User user = new User("test", 100);
        Product product = new Product("테스트", 10);

        payment.receivePayment(user, new Order(product, user.id()));

        assertEquals(90, user.balance());
    }
}
