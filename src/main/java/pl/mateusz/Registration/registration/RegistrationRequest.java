package pl.mateusz.Registration.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

  private String firstName;
  private String lastName;
  private String password;
  private String email;
}
