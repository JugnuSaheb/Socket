import java.net.*;
import java.io.*;
import java.util.*;
public class Client1V1
{
    public static void main(String[] args) throws Exception
    {
        new Thread( () -> runServer() ).start();
        runClient1();
    }
    
    public static void runServer()
    {
        try
        {
            boolean connected1,connected2;
            int users=0;
            System.out.println("Server is started");
            
            ServerSocket ss1 = new ServerSocket(1111);
            System.out.println("Server is waiting for the client1");
            Socket s1 = ss1.accept();users++;connected1=true;
            System.out.println("Client1 Connected");
            
            ServerSocket ss2 = new ServerSocket(1112);
            System.out.println("Server is waiting for the client2");
            Socket s2 = ss2.accept();users++;connected2=true;
            System.out.println("Client2 Connected");
            
            OutputStreamWriter os1,os2;
            PrintWriter out1,out2;
            BufferedReader br1,br2;
            String fromC1="",fromC2="";
            
            os2 = new OutputStreamWriter(s1.getOutputStream());
            out2 = new PrintWriter(os2);
            out2.println("Type a message to C2");
            os2.flush();
            System.out.println("Users: "+users);
            while(true && connected1==true)// && connected2==true)
            {
                br1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                fromC1 = br1.readLine();
              //System.out.println("Client1: "+fromC1);
                
                os1 = new OutputStreamWriter(s2.getOutputStream());
                out1 = new PrintWriter(os1);
                out1.println(fromC1);
                os1.flush();
                
                br2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
                fromC2 = br2.readLine();
              //System.out.println("Client2: "+fromC2);
                
                os2 = new OutputStreamWriter(s1.getOutputStream());
                out2 = new PrintWriter(os2);
                out2.println(fromC2);
                os2.flush();
            }
        }catch(Exception e){}
    }
    
    public static void runClient1() throws Exception
    {
        try{
            String ip = "localhost";
            int port = 1111;
            Socket s1 = new Socket(ip, port);
            
            System.out.println("Now Connect to C2");
            
            BufferedReader br;
            String fromC2;
            
            Scanner sc = new Scanner(System.in);
            String toC2="";
            
            OutputStreamWriter os;
            PrintWriter out;
            
            br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            fromC2 = br.readLine();
            System.out.println(fromC2);
            while(!toC2.equals("exit"))
            {  
                toC2 =  sc.nextLine();
                
                os = new OutputStreamWriter(s1.getOutputStream());
                out = new PrintWriter(os);
                out.println(toC2);
                os.flush();
                
                br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                fromC2 = br.readLine();
                System.out.println("C2: "+fromC2);
            }
        }catch(Exception e){}
    }
} 
//server
/*import java.net.*;
import java.io.*;
import java.util.*;
public class ServerV1
{
   public static void main(String[] args) throws Exception
   {
      new Thread( () -> 
      runServer() ).start();
   }
  
   public static void runServer()
   {
      try
      {
          boolean connected1,connected2;
          System.out.println("Server is started");
          
          ServerSocket ss1 = new ServerSocket(1111);
          System.out.println("Server is waiting for the client1");
          Socket s1 = ss1.accept();connected1=true;
          System.out.println("Client1 Connected");
          
          ServerSocket ss2 = new ServerSocket(1112);
          System.out.println("Server is waiting for the client2");
          Socket s2 = ss2.accept();connected2=true;
          System.out.println("Client2 Connected");
          
          OutputStreamWriter os1,os2;
          PrintWriter out1,out2;
          BufferedReader br1,br2;
          String fromC1="",fromC2="";
          
          os2 = new OutputStreamWriter(s1.getOutputStream());
          out2 = new PrintWriter(os2);
          out2.println("Type a message to C2");
          os2.flush();
          while(true && connected1==true && connected2==true)
          {
              if(fromC2==null || !fromC2.contains("1") || fromC2.equals(""))
              {
                  br1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                  fromC1 = br1.readLine();
                  System.out.println("Client1: "+fromC1);
                  
                  os1 = new OutputStreamWriter(s2.getOutputStream());
                  out1 = new PrintWriter(os1);
                  out1.println(fromC1);
                  os1.flush();
                  
                  if(fromC1.contains("0")) continue;
                 
                  br2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
                  fromC2 = br2.readLine();
                  System.out.println("Client2: "+fromC2);
                  
                  os2 = new OutputStreamWriter(s1.getOutputStream());
                  out2 = new PrintWriter(os2);
                  out2.println(fromC2);
                  os2.flush();
              }else {
                  br2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
                  fromC2 = br2.readLine();
                  System.out.println("Client2: "+fromC2);
                  
                  os2 = new OutputStreamWriter(s1.getOutputStream());
                  out2 = new PrintWriter(os2);
                  out2.println(fromC2);
                  os2.flush();
              }
          }
      }catch(Exception e){}
   }
}  */




//client1
/*
import java.net.*;
import java.io.*;
import java.util.*;
public class Client1V1
{
   public static void main(String[] args) throws Exception
   {
      runClient1();
   }
   
   public static void runClient1() throws Exception
   {
      try{
          String ip = "localhost";
          int port = 1111;
          Socket s1 = new Socket(ip, port);
          
          System.out.println("Now Connect to C2");
          
          BufferedReader br;
          String fromC2;
          
          Scanner sc = new Scanner(System.in);
          String toC2="";
          
          OutputStreamWriter os;
          PrintWriter out;
          
          br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
          fromC2 = br.readLine();
          System.out.println(fromC2);
          while(!toC2.equals("exit"))
          {  
              if(fromC2==null || !fromC2.contains("1")  || fromC2.equals(""))
              {
                  toC2 =  sc.nextLine();
              
                  os = new OutputStreamWriter(s1.getOutputStream());
                  out = new PrintWriter(os);
                  out.println(toC2);
                  os.flush();
                  
                  if(toC2.contains("0")) continue;
                  
                  br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                  fromC2 = br.readLine();
                  System.out.println("C2: "+fromC2);
              }else {
                  br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                  fromC2 = br.readLine();
                  System.out.println("C2: "+fromC2);
              }
          }
      }catch(Exception e){}
   }
} 
 */
