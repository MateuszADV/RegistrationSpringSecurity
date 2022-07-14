package pl.mateusz.Registration.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mateusz.Registration.appuser.AppUser;
import pl.mateusz.Registration.appuser.AppUserRole;
import pl.mateusz.Registration.appuser.AppUserService;
import pl.mateusz.Registration.registration.token.ConfirmationToken;
import pl.mateusz.Registration.registration.token.ConfirmationTokenService;


import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final EmailValidator emailValidator;
  private final AppUserService appUserService;
  private final ConfirmationTokenService confirmationTokenService;

  public String register(RegistrationRequest request){
    Boolean isValidEmail = emailValidator.test(request.getEmail());

    if (!isValidEmail){
      throw new IllegalStateException("email not valid");
    }

    return appUserService.signUpUser(
            new AppUser(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword(),
                    AppUserRole.USER
            )
    );
  }
  @Transactional
  public String confirmToken(String token) {
    ConfirmationToken confirmationToken = confirmationTokenService
            .getToken(token)
            .orElseThrow(() ->
                    new IllegalStateException("token not found"));

    if (confirmationToken.getConfirmedAt() != null) {
      throw new IllegalStateException("email already confirmed");
    }

    LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    if (expiredAt.isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("token expired");
    }

    confirmationTokenService.setConfirmedAt(token);
    appUserService.enableAppUser(
            confirmationToken.getAppUser().getEmail());
    return "confirmed";
  }
}
