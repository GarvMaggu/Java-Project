import java.util.Scanner;   //IMPORTING PACKAGES
import java.io.*;

class Hospital
{
    public static void main(String[] args) throws Exception //DECLARING EXCEPTION USING THROWS
    {   Appointments a[]=new Appointments[10];  //ARRAY OBJECT CREATION
        Medicine m[]=new Medicine[10]; //ARRAY OBJECT CREATION
        Medicine o[]=new Medicine[10];  //ARRAY OBJECT CREATION
        AppointmentsFile AppointmentsFile;
        int booking_number=0;
        int order_number=0;
        do
        {
            System.out.println("\n\n\t\tWELCOME TO THE CITY GENERAL HOSPITAL\n");
            System.out.println("\t\t1. Doctor Appointment");
            System.out.println("\t\t2. Medical Store");
            System.out.println("\t\t9. Exit");
            System.out.println("Enter the choice number from above and press the ENTER key:");
            Scanner sc=new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) 
            {
                case 1:
                {                  
                    System.out.println("\t\t\t1. Book a new appointment");
                    System.out.println("\t\t\t2. View all appointments (View your number)");
                    int choice_appointment = Integer.parseInt(sc.nextLine());
                    switch (choice_appointment) //NESTED SWITCH
                    {
                        case 1:
                        {
                            System.out.println("Enter your name: ");
                            String name=sc.nextLine();
                            System.out.println("Enter your gender (M/F/O)");
                            char gender=sc.next().charAt(0);
                            sc.nextLine();
                            System.out.println("Enter your age");
                            int age=Integer.parseInt(sc.nextLine());
                            try                                                     //EXCEPTION HANDLING USING TRY-CATCH
                            {
                                if(age>13&&age<32767)
                                System.out.println("Age vaidated.");
                                else
                                {
                                    sc.close();
                                    throw new InvalidAgeException("Invalid age input. It must be beween 13-120"+age);
                                }
                                
                            }catch(InvalidAgeException z){System.out.println("Exception occured: "+z);}
                            
                                        
                            a[booking_number]=new Appointments(booking_number+1, name, age, gender);
                            a[booking_number].start();  // THREAD START
                            a[booking_number].interrupt(); //THREAD INTERRUPT
                            AppointmentsFile=new AppointmentsFile(booking_number+1, name, age, gender);
                            a[booking_number].display_appointment_info();
                            AppointmentsFile.save_file();
                            booking_number++;
                            break;
                        }
                        case 2:
                        {
                            AppointmentsFile=new AppointmentsFile();
                            AppointmentsFile.show_file();
                            break;
                        }
                        default:
                        {
                            System.out.println("Invalid choice, please enter a valid choice");
                        }
                    }
                break;
                }   
                case 2: 
                {
                    m[0]=new Medicine(101, "Crocin", 20);
                    m[1]=new Medicine(102, "Betadin", 60);
                    m[2]=new Medicine(103, "Amoxicillin", 100);
                    m[3]=new Medicine(104, "Nexium", 150);
                    m[4]=new Medicine(105, "Aspirin", 200);
                    m[5]=new Medicine(106, "Combiflam", 30);
                    m[6]=new Medicine(107, "Azithromycin", 40);
                    m[7]=new Medicine(108, "Meftal Spas", 15);
                    m[8]=new Medicine(109, "Cetirizine", 25);
                    m[9]=new Medicine(110, "Actos", 70);

                    System.out.println("\t\tWELCOME TO CITY GENERAL MEDICAL STORE");
                    System.out.println("\t\t\t1. Check medicine catalogue (Medicine Codes)");
                    System.out.println("\t\t\t2. Place a new order");
                    System.out.println("\t\t\t3. Check order details");

                    int choice_appointment = Integer.parseInt(sc.nextLine());
                    switch (choice_appointment) 
                    {
                        case 1:
                        {
                            for(int i=0;i<10;i++)
                            {
                                m[i].display_medicine_info();
                            }
                            break;
                        }
                        case 2:
                        {
                            for(int r=0;r<10;r++)
                            {
                                m[r].display_medicine_info();
                            }
                            
                            System.out.println("Enter your name: ");
                            String name=sc.nextLine();
                            
                            int code;
                            String m_name;
                            int m_price;
                            int quantity;
                            int j;

                            System.out.println("Enter medicine code");
                            for(;;)
                            {
                                code=Integer.parseInt(sc.nextLine());

                                if(code>=101&&code<=110)
                                {
                                    break;
                                }
                                else
                                {
                                    System.out.println("Inavlid code, enter the correct code: ");
                                }
                            }

                            for(j=0;j<9;j++)
                            {
                                if(code==m[j].medicine_code)
                                {
                                    m_price=m[j].medicine_price;
                                    m_name=m[j].medicine_name;
                                    
                                    System.out.println("Enter quantity");
                                    quantity=Integer.parseInt(sc.nextLine());
                                    o[order_number]=new Medicine();
                                    o[order_number].info(order_number+1, name, quantity, m_price, m_name);
                                    o[order_number].order_print();
                                    o[order_number].info();
                                    order_number++;
                                    break;
                                }
                                
                            }

                            break;
                        }
                        case 3:
                        {
                            int flag=0;        
                            System.out.println("Enter Order ID you want to search");
                            int id=Integer.parseInt(sc.nextLine());
                            for(int l=0;l<10;l++)
                            {
                                if(id==o[l].order_id)
                                {
                                    o[l].info();
                                    flag=1;
                                    break;
                                }
                            }
                            if(flag==0)
                            {
                                System.out.println("Order not found");
                            }
                            break;
                        }
                             
                        default:
                        {
                            System.out.println("Invalid choice, please enter a valid choice");
                        }
                    }
                break;
                }
                case 9:
                {
                    sc.close();
                    System.exit(0);
                } 
            
                default:
                {
                    System.out.println("Invalid choice, please enter a valid choice");
                } 
            }
           

        }while(true);
    }
}

class InvalidAgeException extends Exception //CUSTOM EXCEPTION DECLARATION
{    
    private static final long serialVersionUID = 1L;
    InvalidAgeException(String s)
    {
        super(s);
    }
}
interface Printable2    //INTERFACE
{
    void display_appointment_info();
}
class Appointments extends Thread implements Printable2 //THREADING AND IMPLEMENTING
{
    public int patient_booking_id;
    public String patient_name;
    public int patient_age;
    public char patient_gender;

    Appointments()  //INITIALIZING CONSTRUCTOR
    {

    }

    Appointments(int _booking_id, String _name, int _age, char _gender) //PARAMETERIZED CONSTRUCTOR AND CONSTRUCTOR OVERLOADING
    {
        this.patient_booking_id=_booking_id;
        this.patient_name=_name;
        this.patient_age=_age;
        this.patient_gender=_gender;
    }

    public void display_appointment_info()
    {
        System.out.println("Booking ID: "+patient_booking_id);
        System.out.println("Name: "+patient_name);
        System.out.println("Age: "+patient_age);
        System.out.println("Gender: "+patient_gender);
    }

   // public void appointment_print() //METHOD USING ABSTRACT CLASS
      public void run() //THREAD RUN METHOD
    {
        System.out.println("-----APPOINTMENT BOOKED SUCCESSFULLY-----");
    }

}

class AppointmentsFile extends Appointments //INHERITANCE
{
    AppointmentsFile()
    {

    }
    AppointmentsFile(int _booking_id, String _name, int _age, char _gender)
    {
        super(_booking_id, _name, _age, _gender);
    }
    public void save_file() throws Exception    //DECLARING EXCEPTION USING THROWS
    {
        if (patient_booking_id==1)
        {
            FileWriter fw=new FileWriter("appointments.txt");   //FILE HANDLING
            fw.write("Booking ID: "+patient_booking_id+", ");
            fw.write("Name: "+patient_name+", ");
            fw.write("Age: "+patient_age+", ");
            fw.write("Gender: "+patient_gender+"\n");
            fw.close();
        } 
       else
        {
            FileWriter fw=new FileWriter("appointments.txt", true); //FILE HANDLING
            fw.write("Booking ID: "+patient_booking_id+", ");
            fw.write("Name: "+patient_name+", ");
            fw.write("Age: "+patient_age+", ");
            fw.write("Gender: "+patient_gender+"\n");
            fw.close();
        }

    }
    public void show_file() throws Exception //DECLARING EXCEPTION USING THROWS
    {
        FileReader fr=new FileReader("appointments.txt");
        int i;
        while((i=fr.read())!=-1)
        System.out.print((char)i);
        System.out.println(" ");
        fr.close();
    }
}

abstract class Printable //ABSTRACT CLASS
{
    abstract void order_print();
    abstract void display_medicine_info();
    abstract void info();
}

class Medicine extends Printable //INTERFACE IMPLEMENTATION
{
    
        int order_id;
        String customer_name;
        int medicine_quantity;
        int total_bill;
        int medicine_code;
        String medicine_name;
        int medicine_price;
        
        Medicine() //INITIALIZING DEFAULT CONSTRUCTOR
        {
            medicine_code=0;
            medicine_name=null;
            medicine_price=0;
        }
        Medicine(int medicine_code, String medicine_name, int medicine_price) //PARAMETERIZED CONSTRUCTOR
        {
            this.medicine_code=medicine_code;
            this.medicine_name=medicine_name;
            this.medicine_price=medicine_price;
        }

        public void display_medicine_info()
        {
            System.out.println("Code: "+medicine_code+", Medicine Name: "+medicine_name+", Price: "+medicine_price);
        }


        public void info(int order_id, String customer_name, int medicine_quantity, int m_price, String m_name)    //METHOD OVERLOADING
        {
            this.order_id=order_id;
            this.customer_name=customer_name;
            this.medicine_quantity=medicine_quantity;
            this.medicine_price=m_price;
            this.medicine_name=m_name;
            this.total_bill=(medicine_price*medicine_quantity);
        }

        public void info() //METHOD OVERLOADING
        {
            System.out.println("Order ID: "+order_id);
            System.out.println("Name: "+customer_name);
            System.out.println("Medicine Name: "+medicine_name);
            System.out.println("Price: "+medicine_price);
            System.out.println("Quantity: "+medicine_quantity);
            System.out.println("Total Bill: "+total_bill);
        }
        
        public void order_print()   //METHOD USING INTERFACE
        {
            System.out.println("-----ORDER PLACED SUCCESSFULLY-----");
        }
        

}