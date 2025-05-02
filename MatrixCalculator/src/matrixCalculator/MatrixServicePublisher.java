package matrixCalculator;

import jakarta.xml.ws.Endpoint;

/**
 * Publishes the MatrixServiceImpl as a SOAP web service at a specified URL.
 */
public class MatrixServicePublisher {
    /**
     * Main method to start the SOAP service.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Define the service URL
            String url = "http://localhost:8060/MatrixCalculator";
            // Publish the MatrixServiceImpl at the specified URL
            Endpoint.publish(url, new MatrixServiceImpl());
            // Confirm successful publication
            System.out.println("Matrix Calculator SOAP Service published at " + url);
        } catch (Exception e) {
            // Handle any errors during service publication
            e.printStackTrace();
        }
    }
}