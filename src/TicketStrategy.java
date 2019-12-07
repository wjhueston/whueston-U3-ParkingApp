/**
 * Strategy pattern for Parking App. Overridden by CheckIn and CheckOut
 * @author William Hueston
 */
public interface TicketStrategy {
    int randomTime = 0;

    int getRandomTime();
}
