import java.util.Random;

public class CheckIn extends Driver implements TicketStrategy {
    private Random rand = new Random();

    CheckIn() {
    }
    @Override
    /**
     * Passes a random check in time to the driver
     */
    public int getRandomTime() {

        return rand.nextInt((12 - 7) + 1) + 7;
    }
}
