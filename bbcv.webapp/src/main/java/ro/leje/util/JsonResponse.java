package ro.leje.util;

import java.util.Map;

/**
 * @author Danut Chindris
 * @since September 11th, 2015
 */
public class JsonResponse {

    private String status;
    private Map<String, String> errorsMap;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }
    public void setErrorsMap(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }
}
