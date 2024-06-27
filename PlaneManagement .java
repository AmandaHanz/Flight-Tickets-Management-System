import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Task 1
public class W2051853_PlaneManagement {
    public static String  option;
    public static Scanner input = new Scanner(System.in);
    public static Ticket[][] ticketInfo = new Ticket[4][14]; //Array to store ticket details

    public static void main(String[] args){
        String[][] booking = new String[4][14]; //Array where booking status stored
        seats(booking);
        System.out.println("\nWelcome to Plane management System!");
        do{
            menu();             //display menu
            optionValidate();    //checking input is valid or not
            switch(option){
                case "1":
                    buy_seat(booking);
                    break;
                case "2":
                    cancel_seat(booking);
                    break;
                case "3":
                    find_first_available(booking);
                    break;
                case "4":
                    show_seating_plan(booking);
                    break;
                case "5":
                    print_tickets_info();
                    break;
                case "6":
                    search_ticket();
                    break;
                case "0":
                    System.out.println("Exiting the program....Thank you!");
                    break;
            }
        }while(!option.equals("0"));

    }

    //Task 2
    public static void menu() {
        System.out.println("\n*********************************");
        System.out.println("*          MENU OPTIONS         *");
        System.out.println("*********************************");
        System.out.println("""
                1. Buy a seat
                2. Cancel a seat
                3. Find first available seat
                4. Show seating plan
                5. Print tickets information and total sales
                6. Search tickets
                0. Quit""");
        System.out.print("Please select an option:");
    }


    //Task 3
    public static void buy_seat(String[][] booking){
        int[] seatNumbers = new int[2];
        seatValidate(seatNumbers); // Method to validate seat selection
        int row_num = seatNumbers[0]; // Extracting row number and seat number from seatNumbers array
        int seat_number = seatNumbers[1];

        if (booking[row_num][seat_number - 1].equals("X")) {   //if seat is booked
            System.out.println("Sorry! Seat is already booked!");
        }
        //if seat available,prompt user for details
        else if(booking[row_num][seat_number-1].equals("O")){   //Task 9;if seat is available
            System.out.println("Enter your name : ");
            String name = input.next();
            System.out.println("Enter your surname : ");
            String surname = input.next();
            System.out.println("Enter your email : ");
            String email = input.next();
            ticketInfo[row_num][seat_number - 1] = new Ticket(row_num, seat_number,
                    ticketPrice(seat_number), new Person(name, surname, email));
            booking[row_num][seat_number - 1] = "X";
            System.out.println("Seat booked successfully!");
            save(ticketInfo,row_num,seat_number-1);

        }
        else {
            booking[row_num][seat_number - 1] = "X"; //mark as a booked seat
            System.out.println("Seat booked successfully!");
        }
    }

    //Task 4
    public static void cancel_seat(String[][] booking){
        int[] seatNumbers = new int[2];
        seatValidate(seatNumbers);
        int row_num = seatNumbers[0];
        int seat_number = seatNumbers[1];

        if (booking[row_num][seat_number - 1].equals("O")) {  //check availability of seat
            System.out.println("Seat is already empty!");
        } else {
            booking[row_num][seat_number - 1] = "O"; //mark the seat as available

            if (ticketInfo[row_num][seat_number - 1] != null) {
                ticketInfo[row_num][seat_number - 1] = null; //remove ticket info
            }

            System.out.println("Seat canceled successfully!");
        }
    }

    //Task 5
    public static void find_first_available(String[][] seatPlan) {
        String firstAvailableSeat = "";
        boolean seatFound = false;

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 14; column++) {
                if (seatPlan[row][column].equals("O")) { //check if seat available
                    char rowLetter = (char) ('A' + (char)row);
                    int seatNumber = column + 1;
                    firstAvailableSeat = String.valueOf(rowLetter) + seatNumber;
                    seatFound = true;
                    break;
                }
            }
            if (seatFound) {
                break;
            }
        }

        if (!firstAvailableSeat.isEmpty()) {
            System.out.println("The first available seat is: " + firstAvailableSeat);
        } else {
            System.out.println("No available seats found.");
        }
    }

    //Task 10
    public static void print_tickets_info(){
        int total = 0;
        int ticketPrice;
        System.out.println("\n\tSeat\tName\t\t\t\t\t\tPrice \t");
        System.out.println();
        for(int i = 0 ; i<4 ; i++){
            for(int j = 0 ; j<14;j++){
                if(ticketInfo[i][j] != null){
                    ticketInfo[i][j].printAllTickets();
                    ticketPrice = ticketInfo[i][j].price;
                    total = total +ticketPrice;
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println("Total amount : $"+total+"\n");
    }

    //Task 11
    public static void search_ticket(){
        int[] seatNumbers = new int[2];
        seatValidate(seatNumbers);
        int rowNo = seatNumbers[0];
        int seatNo = seatNumbers[1];
        if(ticketInfo[rowNo][seatNo-1] != null){          //check if ticket exists
            ticketInfo[rowNo][seatNo-1].printTicket();
        }else{
            System.out.println("This seat is available.");
        }
    }

    //Task 6
    public static void show_seating_plan(String[][] booking){
        System.out.println("\n**************     SEATING PLAN    **************");
        System.out.print("    ");
        //print seat numbers
        for(int count = 1;count<10;count++) {
            System.out.print(count+"  ");
        }
        for(int count = 10;count<15;count++) {
            System.out.print(count+" ");
        }
        System.out.println();

        //print seating plan
        for (int row = 0; row < 4; row++) {
            char rowLetter = (char) ('A' + row);
            System.out.print(rowLetter + "-  ");
            for (int column = 0; column < 14; column++) {
                System.out.print(booking[row][column] + "  ");
            }
            System.out.println();
        }
    }

    public static void seatValidate(int[] seatNumbers) {
        int row_num;
        int seat_number;

        while (true) {
            System.out.print("Enter row letter (A - D) : ");
            char row_letter = input.next().toUpperCase().charAt(0);

            if (row_letter < 'A' || row_letter > 'D') {   //validate row letter
                System.out.println("Invalid Row!\t(A | B | C | D)");
                continue;
            }

            row_num = row_letter - 'A';

            System.out.print("Enter seat number (1 - 14): ");
            seat_number = input.nextInt();

            if (seat_number < 1 || seat_number > 14) {  //validate seat no.
                System.out.println("Invalid seat!\t(1 - 14)");
                continue;
            }

            if ((row_num == 1 || row_num == 2) && seat_number >= 13) {
                System.out.println("Invalid seat!\t(1 - 12)");
                continue;
            }

            break;
        }

        seatNumbers[0] = row_num;
        seatNumbers[1] = seat_number;
    }

    public static void seats(String[][] booking){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 14; col++) {
                booking[row][col] = "O"; // initialize all seats as available
                if ((row == 1 || row == 2) && (col == 12 || col == 13)) {
                    booking[row][col] = "";
                }

            }
        }
    }

    public static int ticketPrice(int seatNum){
        int price;
        if(0<=seatNum && seatNum<=5){
            price = 200;
        } else if (seatNum>5 && seatNum<10) {
            price = 150;
        }else{
            price = 180;
        }
        return price;
    }

    //Task 12 : save ticket info to a file
    public static void save(Ticket[][] tickets,int rowNum,int SeatNum){
        char rowLetter = (char) ('A' + rowNum);
        String fileName = rowLetter + String.valueOf(SeatNum + 1);
        try {
            FileWriter File = new FileWriter(fileName+".txt");
            String inFileTxt = "Seat : " +fileName
                    +"\nName : "+tickets[rowNum][SeatNum].person.name
                    +"\nSurname : "+tickets[rowNum][SeatNum].person.surname
                    +"\nEmail : "+tickets[rowNum][SeatNum].person.email
                    +"\nPrice : "+tickets[rowNum][SeatNum].price;
            File.write(inFileTxt);
            File.close();
            System.out.println("Ticket saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred!");
        }
    }

        //Method to validate user input for selecting options
        public static void optionValidate() {
            option = input.next();
            String[] options = {"0","1","2","3","4","5","6"};
            while (true) {
                for (String num : options) {
                    if (num.equals(option)) {
                        return;
                    }
                }
                System.out.print("Invalid option. Please enter a valid option (0-6): ");
                option = input.next();
            }
        }

}