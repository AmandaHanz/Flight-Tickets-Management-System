//Task 8

public class Ticket {
    String row;
    int seat;
    int price;
    Person person;
    public Ticket(int row, int seat, int price, Person person){
        this.row = String.valueOf(row);
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    public String getRow(){

        return row;
    }
    public void setRow(String row){

        this.row = row ;
    }

    public int getSeat(){

        return seat;
    }
    public void setSeat(int seat){

        this.seat = seat ;
    }

    public int getPrice(){

        return price;
    }
    public void setPrice(int price){

        this.price = price ;
    }
    public void printTicket(){
        person.printPerson();
        System.out.println("Price   : "+price+" $");
    }

    // Method to print all tickets
    public void printAllTickets(){
        String fullName = person.name +" "+ person.surname;
        String seatName = (char)('A' + Integer.parseInt(row)) + (seat < 10 ? "0" : "") + seat;// Combine row letter with seat number
        System.out.print("|\t" + seatName);
        int fullNameLength = fullName.length();
        for(int space = 20;space>=fullNameLength;space--){
            fullName = fullName + " ";
        }
        System.out.print("\t\t"+fullName + "\t\t$" + price+"  \t|");
    }
}