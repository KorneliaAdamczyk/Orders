import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    String clientId;
    long requestId;
    String name;
    int quantity;
    double price;
}
