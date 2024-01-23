package com.manage.models;
import com.manage.utils.validation.NationalCode;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "the username failed is required")
    private String username;
    @NotBlank(message = "the password failed is required")
    private String password;
//    @NotNull(message = "the firstname failed is required")
    @NotBlank(message = "the firstname failed is required")
    private String firstname;
    @NotBlank(message = "the lastname failed is required")
    private String lastname;
    @Size(min = 10,max = 10)
    @NationalCode(message = "nationalCode must be numeric!")
    private String nationalCode;
    private boolean enable;

//    import java.util.Scanner;
    /*public class Main {
        private static boolean validationNationalCode(String code){
            //check length
            if (code.length() != 10)
                return false;

            long nationalCode = Long.parseLong(code);
            byte[] arrayNationalCode = new byte[10];

            //extract digits from number
            for (int i = 0; i < 10 ; i++) {
                arrayNationalCode[i] = (byte) (nationalCode % 10);
                nationalCode = nationalCode / 10;
            }

            //Checking the control digit
            int sum = 0;
            for (int i = 9; i > 0 ; i--)
                sum += arrayNationalCode[i] * (i+1);
            int temp = sum % 11;
            if (temp < 2)
                return arrayNationalCode[0] == temp;
            else
                return arrayNationalCode[0] == 11 - temp;
        }
        public static void main(String[] args) {
            // snippets.ir

            Scanner scanner = new Scanner(System.in);
            String code = scanner.next();
            if (validationNationalCode(code))
                System.out.println("valid");
            else
                System.out.println("Not valid");
            scanner.close();
        }
    }*/


}
