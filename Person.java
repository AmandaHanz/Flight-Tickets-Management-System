//Task 7

public class Person {
    String name ;
    String surname;
    String email;
    public Person(String name,String surname,String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public String getName(){

        return name;
    }
    public void setName(String name){
        this.name = name ;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //method to print person details
    public void printPerson(){
        System.out.println("Name    : "+name);
        System.out.println("Surname : "+surname);
        System.out.println("Email   : "+email);
    }
}