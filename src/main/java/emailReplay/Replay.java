package emailReplay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Replay {

	public static void main(String[] args) throws Exception{
		
		outlookEmailRead();

	}
	
	public static void outlookEmailRead() throws Exception{
			///String Phone
			final String userName = "pattanayak.purna@outlook.com";
			final String password = "xxxx";
			final String host = "smtp.live.com";
			// properties
			Properties props = System.getProperties();

			Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
			}
			});
			session.setDebug(true);

			 // Store object
			Store store = session.getStore("imaps");
			store.connect(host, userName, password);
			 BufferedReader reader = new BufferedReader(new InputStreamReader(
			            System.in));
			 // Get folder
			Folder folder = store.getFolder("inbox");
			folder.open(Folder.READ_ONLY);
			Message messages[] = folder.getMessages();
			String PhN = "UTI MF is now available on WhatsApp";
			System.out.println(PhN);
			System.out.println(messages.length);
			L: for(int i = messages.length;i>=1;i--) {
					if(i>=messages.length-10) {
					System.out.println(messages[i-1].getContent().toString());
						if(messages[i-1].getSubject().toString().contains(PhN)) {
							Message message = messages[i-1];
							//
							 String from = InternetAddress.toString(message.getFrom());
				               if (from != null) {
				                  System.out.println("From: " + from);
				               }
				               String replyTo = InternetAddress.toString(message
					         .getReplyTo());
				               if (replyTo != null) {
				                  System.out.println("Reply-to: " + replyTo);
				               }
				               String to = InternetAddress.toString(message
					         .getRecipients(Message.RecipientType.TO));
				               if (to != null) {
				                  System.out.println("To: " + to);
				               }

				               String subject = message.getSubject();
				               if (subject != null) {
				                  System.out.println("Subject: " + subject);
				               }
				               Date sent = message.getSentDate();
				               if (sent != null) {
				                  System.out.println("Sent: " + sent);
				               }

				               System.out.print("Do you want to reply [y/n] : ");
				               String ans = reader.readLine();
				               if ("Y".equals(ans) || "y".equals(ans)) {

				                  Message replyMessage = new MimeMessage(session);
				                  replyMessage = (MimeMessage) message.reply(false);
				                  replyMessage.setFrom(new InternetAddress(to));
				                  replyMessage.setText("Thanks");
				                  replyMessage.setReplyTo(message.getReplyTo());

				                  // Send the message by authenticating the SMTP server
				                  // Create a Transport instance and call the sendMessage
				                  Transport t = session.getTransport("smtp");
				                  try {
					   	     //connect to the smpt server using transport instance
						     //change the user and password accordingly	
					             t.connect("abc", "****");
					             t.sendMessage(replyMessage,
				                        replyMessage.getAllRecipients());
				                  } finally {
				                     t.close();
				                  }
				                  System.out.println("message replied successfully ....");

				                  // close the store and folder objects
				                  folder.close(false);
				                  store.close();
							//
							
							}
				               break L;
					}
			}
			

			
		}
	}

	
	public void readEmail() {
		 Properties properties = new Properties();
	      properties.put("mail.store.protocol", "pop3");
	      properties.put("mail.pop3s.host", "pop.gmail.com");
	      properties.put("mail.pop3s.port", "995");
	      properties.put("mail.pop3.starttls.enable", "true");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", "relay.jangosmtp.net");
	      properties.put("mail.smtp.port", "25");
	      Session session = Session.getDefaultInstance(properties);

	      // session.setDebug(true);
	      try 
	      {
	         // Get a Store object and connect to the current host
	         Store store = session.getStore("pop3s");
	         store.connect("pop.gmail.com", "xyz@gmail.com",
	            "*****");//change the user and password accordingly

	         Folder folder = store.getFolder("inbox");
	         if (!folder.exists()) {
	            System.out.println("inbox not found");
	               System.exit(0);
	         }
	         folder.open(Folder.READ_ONLY);

	         BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));

	         Message[] messages = folder.getMessages();
	         if (messages.length != 0) {

	            for (int i = 0, n = messages.length; i < n; i++) {
	               Message message = messages[i];
	             //  Date date = message.getSentDate();
	               // Get all the information from the message
	               String from = InternetAddress.toString(message.getFrom());
	               if (from != null) {
	                  System.out.println("From: " + from);
	               }
	               String replyTo = InternetAddress.toString(message
		         .getReplyTo());
	               if (replyTo != null) {
	                  System.out.println("Reply-to: " + replyTo);
	               }
	               String to = InternetAddress.toString(message
		         .getRecipients(Message.RecipientType.TO));
	               if (to != null) {
	                  System.out.println("To: " + to);
	               }

	               String subject = message.getSubject();
	               if (subject != null) {
	                  System.out.println("Subject: " + subject);
	               }
	               Date sent = message.getSentDate();
	               if (sent != null) {
	                  System.out.println("Sent: " + sent);
	               }

	               System.out.print("Do you want to reply [y/n] : ");
	               String ans = reader.readLine();
	               if ("Y".equals(ans) || "y".equals(ans)) {

	                  Message replyMessage = new MimeMessage(session);
	                  replyMessage = (MimeMessage) message.reply(false);
	                  replyMessage.setFrom(new InternetAddress(to));
	                  replyMessage.setText("Thanks");
	                  replyMessage.setReplyTo(message.getReplyTo());

	                  // Send the message by authenticating the SMTP server
	                  // Create a Transport instance and call the sendMessage
	                  Transport t = session.getTransport("smtp");
	                  try {
		   	     //connect to the smpt server using transport instance
			     //change the user and password accordingly	
		             t.connect("abc", "****");
		             t.sendMessage(replyMessage,
	                        replyMessage.getAllRecipients());
	                  } finally {
	                     t.close();
	                  }
	                  System.out.println("message replied successfully ....");

	                  // close the store and folder objects
	                  folder.close(false);
	                  store.close();

	               } else if ("n".equals(ans)) {
	                  break;
	               }
	            }//end of for loop

	         } else {
	            System.out.println("There is no msg....");
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
}
