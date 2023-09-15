package com.examportal.examPortal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@SpringBootApplication
public class ExamPortalApplication {

//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Value("${spring.mail.username}")
//    private String from;

    public static void main(String[] args) {
        SpringApplication.run(ExamPortalApplication.class, args);
        System.out.println("===========================================");
        System.out.println("===========================================");
        System.out.println("======Exam Portal Start");
        System.out.println("===========================================");
        System.out.println("===========================================");

//		 EmailSender emailSender = new EmailSender();
//		String to = "tusar.sahoo@thrymr.net";
//		String from ="sahootusar009@gmail.com";
//		String subject = "Sending email using gmail";
//		String text ="This is a Example email send using gmail and using java program whit out using less secure app";
//		emailSender.sendEmail(to,from,subject,text);
//		boolean value = emailSender.sendEmail(to,from,subject,text);
//		if(value){
//			System.out.println("Email sent successfully to "+to);
//		}
//		else{
//			System.out.println("There is a problem to sent the email");
//		}
//	}
        // }

//    public SimpleMailMessage sendMail(MailDto mailDto) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(mailDto.getTo());
//        message.setSubject(mailDto.getSubject());
//        message.setText(mailDto.getText());
//        javaMailSender.send(message);
//        return message;
//    }

//    @Bean
//    public JavaMailSender javaMailSender() {
//        return new JavaMailSenderImpl();
//    }
//}
    }
    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
    }
