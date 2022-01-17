package examples;

/** 
    @author         Azer Hojlas and Porsev Aslan

    Date:           2022-01-03

    Description:    Program that sends emails using SMTP and retrieves Emails using IMAP.

    Dependencies:   All the ones listed below

    Compilation:    javac Task4.java

    Execution:      java Task4

    Usage:          The instructions are pretty self-explanatory when you start the program. One thing to pay attention to
                    is that you initially write your username only (as in "hojlas", not "hojlas@kth.se") and later the 
                    recipient of the mail as the entire mail (as in "hojlas@kth.se").

    Issues:         Due to the rules of the kth servers, spamming mails will result in the program not working for a short
                    while. Furthermore, there appear to exist some unwritten rules as what, how and when one can send mails.
                    We have not been able to ascertain what said rules are specifically, therefore we recommend that the
                    client sends one e-mail and then waits two minutes or more before sending the next.
 */

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.Base64;


public class SendMail {
    public static final String SMTP_HOST = "smtp.kth.se";
    public static final int SMTP_PORT = 587;

    public static String send(String email, String studentID, String password) {
        
        StringBuilder sb = new StringBuilder();
        
        Socket socket = null;
        
        //Enter your kth creditentials for testing (pretend to be server email
        //String username = "";
        //String password = "";
    
        try {
          // Establish connection to server
          socket = new Socket(SMTP_HOST, SMTP_PORT);
          PrintWriter pw = new PrintWriter(socket.getOutputStream());
          InputStreamReader isr = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
          BufferedReader br = new BufferedReader(isr);

          write(pw, "EHLO " + SMTP_HOST);
          Thread.sleep(1000);
 
          // We believe this command constitutes the encrypted connection
          write(pw, "STARTTLS");
          Thread.sleep(1000);

          // Iterate through the responses recieved from the server. Used mostly for debugging, can be disabled
          String line = br.readLine();
          while (line != null) {
            // System.out.println(line);
            sb.append(line + "\n");
            if(line.contains("220 2.0.0 Ready to start TLS")) {
              break;
            }
            line = br.readLine();
          }
          
          SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
    
          SSLSocket socketSSL = (SSLSocket)socketFactory.createSocket(socket, socket.getInetAddress().getHostAddress(), socket.getPort(), true);
          pw = new PrintWriter(socketSSL.getOutputStream(), true);
          isr = new InputStreamReader(socketSSL.getInputStream(), StandardCharsets.UTF_8);
          br = new BufferedReader(isr);

          // Initiate communication and login
          write(pw, "EHLO " + SMTP_HOST);
          Thread.sleep(1000);
          write(pw, "AUTH LOGIN");
          Thread.sleep(1000);
          String[] username = email.split("@");
          write(pw, Base64.getEncoder().encodeToString(username[0].getBytes()));
          Thread.sleep(1000);
          write(pw, Base64.getEncoder().encodeToString(password.getBytes()));
          Thread.sleep(1000);
          write(pw, "MAIL FROM:<" + email + ">");
          Thread.sleep(1000);
          write(pw, "RCPT TO:<" + email + ">");
          Thread.sleep(1000);
          write(pw, "DATA");
    
          // Sleep is necessary here, otherwise bugs occur
          Thread.sleep(1000);

          // Send contents of mail and finish with a ".", which is required to close connection
          // Contents are sent both as text and as the subject
          
         
          write(pw, "Subject: Your student ID");
          Thread.sleep(1000);
          write(pw, "Hello student,\n Your student ID is: " + studentID);
          Thread.sleep(1000);
          write(pw, ".");
          Thread.sleep(1000);
          
          // Iterate through the responses recieved from the server. Used mostly for debugging, can be disabled
          String text = br.readLine();
          while (text != null) {
            // System.out.println(text);
            sb.append(text + "\n");
            if(text.contains("250 2.0.0 Ok: queued as")) {
              // System.out.println("\n Email has been sent");
              sb.append("\n Email has been sent" + "\n");
              break;
            }
            text = br.readLine();
          }
          br.close();
          pw.close();

        } catch(Exception exception) {
          System.out.println("An error occured while sending mail");
        }
        
        return sb.toString();
    }
    
    // This method is used to minimize code reusage when writing to servers
    public static void write (PrintWriter pw, String command) throws IOException{
      
      pw.println(command);
      pw.flush();
      // System.out.println("command");
    }
}

