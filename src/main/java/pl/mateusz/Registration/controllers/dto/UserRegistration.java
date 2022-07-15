package pl.mateusz.Registration.controllers.dto;

import lombok.*;

import javax.mail.Message;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistration {

    private String firstName;
    private String lastName;
    private String password;
    @NonNull
    private String email;
}
