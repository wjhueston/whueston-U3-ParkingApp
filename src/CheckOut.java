import java.util.Random;

/**
 * CheckOut logic, passed to main method in Check Out logic scope
 * @author William Hueston
 */
public class CheckOut extends Driver implements TicketStrategy {
    private Random rand = new Random();

    CheckOut() {
    }
    @Override
    /**
     * Passes a random check out time to driver
     */
    public int getRandomTime() {

        return rand.nextInt((11 - 1) + 1) + 1;
    }
}