/**
 * The Factory pattern implementation for Parking App. Decides whether to return a CheckOut or a CheckIn
 * @author William Hueston
 */
public class TicketFactory {
    private TicketStrategy getRandomTime(){
        if(getRandomTime().equals(new CheckOut())){
            return new CheckOut();
        }
        else return new CheckIn();
    }
}
