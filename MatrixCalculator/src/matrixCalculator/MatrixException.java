package matrixCalculator;
import jakarta.xml.ws.WebFault;

@WebFault(name = "MatrixException", targetNamespace = "http://matrixCalculator/")
public class MatrixException extends Exception {
    private String faultInfo;

    public MatrixException(String message, String faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public MatrixException(String message, String faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public String getFaultInfo() {
        return faultInfo;
    }
}