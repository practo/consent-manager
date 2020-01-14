package in.projecteka.consentmanager.link;

import in.projecteka.consentmanager.link.link.model.Error;
import in.projecteka.consentmanager.link.link.model.ErrorCode;
import in.projecteka.consentmanager.link.link.model.ErrorRepresentation;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientError extends Throwable {

    private final HttpStatus httpStatus;
    private final ErrorRepresentation error;

    public ClientError(HttpStatus httpStatus, ErrorRepresentation errorRepresentation) {
        this.httpStatus = httpStatus;
        error = errorRepresentation;
    }

    public static ClientError unableToConnectToProvider(){
        return new ClientError(
                HttpStatus.NOT_FOUND,
                new ErrorRepresentation(new Error(
                        ErrorCode.UNABLE_TO_CONNECT_TO_PROVIDER,
                        "Cannot process the request at the moment, please try later.")));
    }

    public static ClientError userNotFound(){
        return new ClientError(
                HttpStatus.NOT_FOUND,
                new ErrorRepresentation(new Error(
                        ErrorCode.USER_NOT_FOUND,
                        "Cannot find the user")));
    }

    public static ClientError dbOperationFailed(){
        return new ClientError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new ErrorRepresentation(new Error(
                        ErrorCode.DB_OPERATION_FAILED,
                        "Cannot process the request at the moment, please try later.")));
    }

    public static ClientError otpExpired() {
        return new ClientError(
                HttpStatus.UNAUTHORIZED,
                new ErrorRepresentation(new Error(
                        ErrorCode.OTP_EXPIRED,
                        "OTP Expired, please try again")));
    }
}
