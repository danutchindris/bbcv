package ro.leje.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danut Chindris
 * @since September 11, 2015
 */
public class ValidationResponse {

    private List<ErrorMessage> errorMessageList;

    public ValidationResponse() {
        errorMessageList = new ArrayList<>();
    }

    public ValidationResponse(final List<ErrorMessage> errorMessages) {
        this.errorMessageList = errorMessages;
    }

    public List<ErrorMessage> getErrorMessageList() {
        return this.errorMessageList;
    }

    public void addErrorMessage(String fieldName, String message) {
        ErrorMessage errorMessage = new ErrorMessage(fieldName, message);
        this.errorMessageList.add(errorMessage);
    }
}
