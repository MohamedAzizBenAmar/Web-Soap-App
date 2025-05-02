Matrix Calculator SOAP Service

This project implements a Matrix Calculator as a SOAP web service, developed for the SOA and Microservices module at Université de Sfax, Institut Supérieur d'Informatique et de Multimédia. The service supports various matrix operations with robust error handling for invalid inputs. It is built using JAX-WS, tested with a Java client and SoapUI, and runs on JDK 21.
Table of Contents

Overview
Supported Operations
Prerequisites
Project Setup
Compilation
Execution
Testing with SoapUI
Demonstration for Evaluation
Troubleshooting
Deliverables
Contact

Overview
The Matrix Calculator SOAP Service provides a web-based interface for performing matrix operations, including addition, subtraction, multiplication, transposition, determinant, inverse, scalar multiplication, trace, and square. The service handles errors such as null matrices, incompatible dimensions, non-square matrices, and zero determinants. The project consists of two main components:

MatrixCalculator: The server-side project implementing the SOAP service.
MatrixClient: The client-side project for testing the service.

The project uses JAX-WS 3.0.2 for SOAP communication, JDK 21 for development, Eclipse IDE for coding, and SoapUI for testing.
Supported Operations



Operation
Description
Error Cases Handled



Addition
Adds two matrices of the same dimensions
Null matrices, incompatible dimensions


Subtraction
Subtracts one matrix from another
Null matrices, incompatible dimensions


Multiplication
Multiplies two compatible matrices
Null matrices, incompatible dimensions


Transpose
Transposes a matrix
Null matrix


Determinant
Calculates the determinant of a square matrix
Null matrix, non-square matrix


Inverse
Computes the inverse of a square matrix
Null matrix, non-square matrix, zero determinant


Scalar Multiplication
Multiplies a matrix by a scalar
Null matrix


Trace
Calculates the trace of a square matrix
Null matrix, non-square matrix


Square
Computes the square of a square matrix
Null matrix, non-square matrix


Prerequisites
Before setting up the project, ensure the following tools are installed:

JDK 21:

Download from Oracle JDK 21 or OpenJDK.
Set the JAVA_HOME environment variable to the JDK 21 directory (e.g., C:\Program Files\Java\jdk-21).
Verify: java -version (should output java version "21").


JAX-WS Reference Implementation (RI) 3.0.2:

Download from JAX-WS RI Releases.
Extract to a directory (e.g., D:\2ing\Sem2\Tp SOA\jaxws-ri-3.0.2).


Eclipse IDE:

Download from Eclipse Downloads.
Install Eclipse IDE for Java Developers (version 2024-09 or later).
Configure Eclipse to use JDK 21: Window > Preferences > Java > Installed JREs > Add > Standard VM > Select JDK 21 directory.


SoapUI:

Download from SoapUI Downloads.
Install SoapUI 5.7.0 or later.


Project Files:

MatrixCalculator project (server-side source files).
MatrixClient project (client-side source files and generated classes).
WSDL file (MatrixCalculator.wsdl, obtained after running the server).



Project Setup

Import Projects into Eclipse:

Open Eclipse IDE.
Import MatrixCalculator:
File > Import > General > Existing Projects into Workspace.
Select the MatrixCalculator project directory.
Click Finish.


Repeat for MatrixClient.
Verify JDK 21 configuration:
Right-click each project > Properties > Java Compiler.
Enable "Enable project specific settings" and set Compiler compliance level to 21.
In Java Build Path > Libraries, ensure JRE System Library is JDK 21.




Add JAX-WS Libraries:

For MatrixCalculator:
Right-click project > Properties > Java Build Path > Libraries > Add External JARs.
Navigate to D:\2ing\Sem2\Tp SOA\jaxws-ri-3.0.2\jaxws-ri\lib.
Add: jaxws-rt.jar, jaxb-impl.jar, gmbal.jar, management-api.jar, stax-ex.jar, streambuffer.jar.
Click Apply and Close.


Repeat for MatrixClient.


Generate Client Classes:

Start the server (see Execution).
Run wsimport to generate client classes:D:\2ing\Sem2\Tp SOA\jaxws-ri-3.0.2\jaxws-ri\bin\wsimport.bat -keep -p test.MatrixClient http://localhost:8060/MatrixCalculator?wsdl


Copy the generated files (e.g., Matrix.java, DoubleArray.java, MatrixException_Exception.java) to MatrixClient/src/test/MatrixClient.



Compilation
Eclipse compiles projects automatically if Project > Build Automatically is enabled. To compile manually:

MatrixCalculator:

Right-click MatrixCalculator > Build Project.
Verify no errors in the Problems view.
Compiled .class files are in the bin directory.


MatrixClient:

Right-click MatrixClient > Build Project.
Ensure no errors, especially for MatrixClient.java and generated classes.



Command-Line Compilation (if needed):

For MatrixCalculator:cd MatrixCalculator/src
javac -cp ".;D:\2ing\Sem2\Tp SOA\jaxws-ri-3.0.2\jaxws-ri\lib\*" matrixCalculator/*.java


For MatrixClient:cd MatrixClient/src
javac -cp ".;D:\2ing\Sem2\Tp SOA\jaxws-ri-3.0.2\jaxws-ri\lib\*" test/MatrixClient/*.java



Execution

Run the Server:

Open MatrixServicePublisher.java in MatrixCalculator/src/matrixCalculator.
Right-click > Run As > Java Application.
Console output: Matrix Calculator SOAP Service published at http://localhost:8060/MatrixCalculator.
Verify the WSDL: Open http://localhost:8060/MatrixCalculator?wsdl in a browser.
Keep the server running.


Run the Client:

Open MatrixClient.java in MatrixClient/src/test/MatrixClient.
Right-click > Run As > Java Application.
Console output includes results for all operations and error cases (see Expected Client Output).
Ensure the server is running before executing the client.



Expected Client Output

Addition: [[1, 2], [3, 4]] + [[5, 6], [7, 8]] = [[6, 8], [10, 12]]
Subtraction: [[1, 2], [3, 4]] - [[5, 6], [7, 8]] = [[-4, -4], [-4, -4]]
Multiplication: [[1, 2], [3, 4]] * [[5, 6], [7, 8]] = [[19, 22], [43, 50]]
Transpose: Transpose of [[1, 2], [3, 4]] = [[1, 3], [2, 4]]
Determinant: Determinant of [[1, 2], [3, 4]] = -2.0
Inverse: Inverse of [[1, 2], [3, 4]] = [[-2, 1], [1.5, -0.5]]
Scalar Multiplication: [[1, 2], [3, 4]] * 2 = [[2, 4], [6, 8]]
Trace: Trace of [[1, 2], [3, 4]] = 5.0
Square: Square of [[1, 2], [3, 4]] = [[7, 10], [15, 22]]
Error Cases:
Null matrix: One or both matrices are null
Incompatible addition: Matrices have incompatible dimensions for addition
Incompatible multiplication: Matrices have incompatible dimensions for multiplication
Non-square determinant: Matrix must be square to calculate determinant
Zero determinant inverse: Matrix is not invertible (determinant is zero)
Non-square trace: Matrix must be square to calculate trace
Non-square square: Matrix must be square to calculate square



Testing with SoapUI

Create a SoapUI Project:

Open SoapUI.
File > New SOAP Project.
Enter WSDL URL: http://localhost:8060/MatrixCalculator?wsdl.
Name the project (e.g., MatrixCalculatorTest).
Click OK.


Test Valid Operations:

Expand operations in the project tree (addMatrices, subtractMatrices, etc.).
Create test requests using the provided XML (see SoapUI Test Cases).
Run each request and verify the response matches the expected output.


Test Error Cases:

Create requests for error scenarios (e.g., null matrix, incompatible dimensions).
Verify the <faultstring> in the response matches the expected error message.


Save the Project:

Save the SoapUI project for demonstration.



SoapUI Test Cases
Below are example test cases for valid operations and error cases:
Valid Operations

Addition:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:addMatrices>
         <arg0>
            <data>
               <item>1.0</item>
               <item>2.0</item>
            </data>
            <data>
               <item>3.0</item>
               <item>4.0</item>
            </data>
         </arg0>
         <arg1>
            <data>
               <item>5.0</item>
               <item>6.0</item>
            </data>
            <data>
               <item>7.0</item>
               <item>8.0</item>
            </data>
         </arg1>
      </mat:addMatrices>
   </soapenv:Body>
</soapenv:Envelope>

Expected: [[6.0, 8.0], [10.0, 12.0]]

Transpose:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:transposeMatrix>
         <arg0>
            <data>
               <item>1.0</item>
               <item>2.0</item>
            </data>
            <data>
               <item>3.0</item>
               <item>4.0</item>
            </data>
         </arg0>
      </mat:transposeMatrix>
   </soapenv:Body>
</soapenv:Envelope>

Expected: [[1.0, 3.0], [2.0, 4.0]]

Determinant:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:determinant>
         <arg0>
            <data>
               <item>1.0</item>
               <item>2.0</item>
            </data>
            <data>
               <item>3.0</item>
               <item>4.0</item>
            </data>
         </arg0>
      </mat:determinant>
   </soapenv:Body>
</soapenv:Envelope>

Expected: -2.0


(Similar requests for subtraction, multiplication, inverse, scalar multiplication, trace, and square are included in the project deliverables.)
Error Cases

Null Matrix Addition:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:addMatrices>
         <arg0>
            <data>
               <item>1.0</item>
               <item>2.0</item>
            </data>
            <data>
               <item>3.0</item>
               <item>4.0</item>
            </data>
         </arg0>
      </mat:addMatrices>
   </soapenv:Body>
</soapenv:Envelope>

Expected: <faultstring>One or both matrices are null</faultstring>

Non-Square Determinant:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:determinant>
         <arg0>
            <data>
               <item>1.0</item>
               <item>2.0</item>
               <item>3.0</item>
            </data>
            <data>
               <item>4.0</item>
               <item>5.0</item>
               <item>6.0</item>
            </data>
         </arg0>
      </mat:determinant>
   </soapenv:Body>
</soapenv:Envelope>

Expected: <faultstring>Matrix must be square to calculate determinant</faultstring>


(Additional error cases are included in the deliverables.)
Demonstration for Evaluation
To present the project to the instructor:

Start the Server:

Run MatrixServicePublisher.java.
Show the console output and WSDL in a browser (http://localhost:8060/MatrixCalculator?wsdl).


Run the Client:

Execute MatrixClient.java.
Display the console output, highlighting valid results and error messages.


Test with SoapUI:

Open the SoapUI project.
Run test requests for all operations and error cases.
Show XML requests and responses, emphasizing error handling.


Show Deliverables:

Present source files (MatrixCalculator, MatrixClient).
Display the WSDL file (MatrixCalculator.wsdl).
Show screenshots (included in the report).
Present the report PDF (MatrixCalculatorReport.pdf).



Troubleshooting

Server Fails to Start:

Ensure port 8060 is free: netstat -a -n -o.
Verify JDK 21 is configured: java -version.


Client Errors:

Ensure JAX-WS libraries are added to MatrixClient.
Verify the server is running before executing the client.


SoapUI Connection Issues:

Confirm the WSDL URL is accessible.
Ensure the server is active.


Compilation Errors:

Check JAX-WS JARs in the build path.
Verify JDK 21 is used.



Deliverables

Source Files: MatrixCalculator and MatrixClient projects.
WSDL File: MatrixCalculator.wsdl.
Screenshots: Eclipse setup, console outputs, SoapUI tests.
Report: MatrixCalculatorReport.pdf (includes steps and screenshots).
README: This file (README.md).

Contact
For issues or questions, contact:

Name: Ben Amar Mohamed Aziz
Email: [Your email, if applicable]
Institution: Université de Sfax, ISSAT Sfax
GitHub: [Your GitHub profile or repository link, if applicable]

