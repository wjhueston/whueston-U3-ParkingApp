import java.io.*;
import java.util.*;

/**
 * The main class for my Parking Garage App. Includes instantiation for ArrayLists, input from file, and pay logic.
 * @author William Hueston
 */
public class Driver {


    /**
     * @param args
     * @throws IOException If file is not found, or main method receives input from file that it does not expect
     */
    public static void main(String[] args) throws IOException {
        Startup run = Startup.getInstance();
        File file = new File("src\\garageCount.txt");
        Scanner sc = new Scanner(file);

        String total;
        String key;
        String value;
        ArrayList <String> keyList = new ArrayList();
        ArrayList<String> valueList = new ArrayList();

        CheckIn moxxi = new CheckIn();
        while (sc.hasNextLine()) {

            total = sc.nextLine();

            key = total.substring(0, total.indexOf(" "));
            value = total.substring(total.indexOf(" ") + 1);
            keyList.add(key);
            valueList.add(value);
        }


        int checkOutTime;
        int checkInTime=scopePasserStayDuration(moxxi);
        int stayDuration;
        int parkPrice;
        int carCounter = 100;
        int smartCash = 0;
        int lostCash=0;
        int checkOutCount=0;
        int lostTicCount=0;
        int specialEventCash=0;
        int specialEventCount=0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Scooter's Parking Garage and Hair Salon Incorporated");
        int nav;

        PrintWriter writer = new PrintWriter(file);
        /**
         * Instantiation of the printwriter for garageCount.txt, where car ID and visit price pairs are stored in conjunction with Arraylists above
         * @author William Hueston
         */
        int  keyCount = keyList.size();
        int keyIter = 0;
        while (keyIter != keyCount) {

            writer.println(keyList.get(keyIter) +" " + valueList.get(keyIter));
            keyIter++;
        }
        writer.println("   ");
        /**
         * Main loop of the program. Contains logic for Checking in, Checking out, and tallies totals for each guest and profit for the day.
         * @author William Hueston
         */
        do{
        System.out.println();
        System.out.println("=========================");
        System.out.println();
        System.out.println("1 - Check-In");
        System.out.println();
        System.out.println("2 - Check-Out");
        System.out.println();
        System.out.println("3 - Close Garage");
        System.out.println();
        System.out.print("=>");
        nav = keyboard.nextInt();
        if(nav != 1 && nav!=2 && nav!=3) System.out.println("Invalid");
        else if(nav==1) {
            System.out.println("Check-In");
            System.out.println();
            System.out.println("=========================");
            System.out.println();
            System.out.println("1 - Ticket");
            System.out.println();
            System.out.println("2 - Special Event");
            System.out.println();
            System.out.print("=>");
            carCounter++;
            int ticketType = keyboard.nextInt();
            if (ticketType != 1 && ticketType != 2) System.out.println("Invalid");
            else if (ticketType == 1) {
               System.out.println("Your ticket number is " + carCounter);
               scopePasserStayDuration(moxxi);
            }
            else {
                System.out.println("Special Event Pricing");
                System.out.println("Car ID: " + carCounter + "\n" + "Special Event\n$20.00");
                specialEventCount++;
                specialEventCash += 20;
                writer.println(carCounter + " 20");
                keyList.add(Integer.toString(carCounter));
                valueList.add("20");
            }

        }

        else if(nav==2) {
            System.out.println("Check-Out");
            System.out.println();
            System.out.println("=========================");
            System.out.println();
            System.out.println("1 - Ticket");
            System.out.println();
            System.out.println("2 - Lost Ticket");
            System.out.println();
            System.out.println("3 - Special Event");
            System.out.println();
            System.out.print("=>");
            int checkOutNav;
            checkOutNav=keyboard.nextInt();
            if (checkOutNav != 1 && checkOutNav != 2 && checkOutNav!=3) System.out.println("Invalid");
            if (checkOutNav == 1) {
                CheckOut ellie = new CheckOut();
                checkOutTime = ellie.getRandomTime();
                stayDuration = (checkOutTime + 12) - checkInTime;
                if (stayDuration <= 3) parkPrice = 5;
                else parkPrice = (stayDuration - 3) + 5;
                System.out.println("Check-Out - Ticket");
                System.out.println();
                System.out.println("=========================");
                System.out.print("Ticket Number =>");
                int facadeCounter = keyboard.nextInt();
                if(facadeCounter < 100 && facadeCounter!=carCounter) System.out.println("No such ticket");
                if (checkInTime != 12) {
                    System.out.println("=========================");
                    System.out.println();
                    System.out.println("Car ID: " + facadeCounter + "\n" + stayDuration + " hours parked  " + checkInTime + "am - " + checkOutTime + "pm");

                } else
                    System.out.println("Car ID: " + facadeCounter + "\n" + stayDuration + " hours parked  " + checkInTime + "pm - " + checkOutTime + "pm");
                System.out.println("$" + parkPrice + ".00");
                checkOutCount++;
                smartCash += parkPrice;

                writer.println(carCounter + " " + parkPrice);

            } else if (checkOutNav == 2) {
                System.out.println("Car ID: " + carCounter + "\n" + "Lost Ticket\n$25.00");
                lostTicCount++;
                lostCash += 25;
                writer.println(carCounter + " 25");
                keyList.add(Integer.toString(carCounter));
                valueList.add("25");
            }
            else if(checkOutNav == 3){
                System.out.println("Check-Out - Special Event");
                System.out.println();
                System.out.println("=========================");
                System.out.println();
                System.out.println("Ticket Number =>");
                int facadeCounter = keyboard.nextInt();
                if(facadeCounter < 100 && facadeCounter!=carCounter) System.out.println("No such ticket");
                else System.out.println("Ticket Paid at Entrance.");
            }
        }
        }




        while(nav!=3);

        System.out.println("Activity Today: ");
        System.out.println("$"+ smartCash +".00 was collected from "+ checkOutCount + " Check Ins");
        System.out.println("$"+ lostCash +".00 was collected from "+ lostTicCount + " Lost Tickets");
        System.out.println("$"+ specialEventCash +".00 was collected from "+ specialEventCount + " Special Events");
        int valueSummer = 0;
        int sumFromPast = 0;
        while(valueSummer != valueList.size()){
            if(valueList.get(valueSummer).contains("  ")) valueList.remove(valueSummer);
            sumFromPast+=Integer.parseInt(valueList.get(valueSummer));
            valueSummer++;
        }
        int sum = sumFromPast + smartCash + lostCash + specialEventCash;
        System.out.println("Total Collected Across All Days of Operation: $" + sum);
        writer.close();

    }

    /**
     * Passes the check in time generated in the check in logic scope outside said scope
     * @param moxxi instance variable for check in
     * @return check in int
     */
    private static int scopePasserStayDuration(CheckIn moxxi){
        return moxxi.getRandomTime();

    }

    
}