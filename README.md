Matrix Calculator SOAP Service
This project implements a matrix calculator as a SOAP web service, developed for the SOA and Microservices module at Université de Sfax, Institut Supérieur d'Informatique et de Multimédia. The service supports matrix operations (addition, subtraction, multiplication, transposition, determinant, inverse, scalar multiplication, trace, and square) with explicit error handling for invalid cases (e.g., null matrices, incompatible dimensions, non-square matrices, zero determinant). The project uses JAX-WS, JDK 21, and is tested with a Java client and SoapUI.
This README provides detailed steps to compile and execute the project, including setting up the environment, running the server, executing the client, and testing with SoapUI.
Prerequisites
Before compiling and running the project, ensure the following tools are installed:

JDK 21: Required for compatibility with project specifications.

Download from Oracle JDK 21 or use an open-source alternative like OpenJDK.
Install and set the JAVA_HOME environment variable to the JDK 21 directory (e.g., C:\Program Files\Java\jdk-21).
Verify with: java -version (should output java version "21").


JAX-WS Reference Implementation (RI) 3.0.2: Provides libraries and tools for SOAP web services.

Download from JAX-WS RI Releases or use the provided jaxws-ri-3.0.2.zip in the project deliverables.
Extract to a directory (e.g., D:\2ing\Sem2\Tp SOA\jaxws-ri-3.0.2).


Eclipse IDE: Used for project development and execution.

Download from Eclipse Downloads.
Install Eclipse IDE for Java Developers (version 2024-09 or later recommended).
Ensure Eclipse is configured to use JDK 21 (Window > Preferences > Java > Installed JREs > Add > Standard VM > Select JDK 21 directory).


SoapUI: Used for testing SOAP requests.

Download from SoapUI Downloads.
Install SoapUI (version 5.7.0 or later recommended).


Project Files: Ensure you have the following deliverables:

MatrixCalculator project (source files: Matrix.java, MatrixException.java, MatrixService.java, MatrixServiceImpl.java, MatrixServicePublisher.java).
MatrixClient project (source files: generated classes from wsimport, MatrixClient.java).
WSDL file: MatrixCalculator.wsdl (downloaded from http://localhost:8060/MatrixCalculator?wsdl after running the server).



Project Setup
1. Import Projects into Eclipse

Open Eclipse IDE.
Import the MatrixCalculator project:
File > Import > General > Existing Projects into Workspace.
Select the MatrixCalculator project directory from the deliverables.
Click Finish.


Import the MatrixClient project:
Repeat the import process for the MatrixClient project directory.


Verify that both projects are configured to use JDK 21:
Right-click each project > Properties > Java Compiler > Ensure "Enable project specific settings" is checked and Compiler compliance level is set to 21.
Right-click each project > Properties > Java Build Path > Libraries > Ensure JRE System Library is set to JDK 21.



2. Add JAX-WS Libraries

Add JAX-WS libraries to the MatrixCalculator project:
Right-click MatrixCalculator > Properties > Java Build Path > Libraries > Add External JARs.
Navigate to the JAX-WS RI directory (e.g., D:\2ing\Sem2\Tp SOA\jaxws-ri-3.0.2\jaxws-ri\lib).
Select jaxws-rt.jar, jaxb-impl.jar, and other required JARs (e.g., gmbal.jar, management-api.jar, stax-ex.jar, streambuffer.jar).
Click Apply and Close.


Repeat for the MatrixClient project to ensure the client can consume the SOAP service.

Compilation
The projects are compiled automatically in Eclipse if "Build Automatically" is enabled (Project > Build Automatically). To manually compile:

MatrixCalculator Project:

In Eclipse, right-click MatrixCalculator > Build Project.
Verify that no compilation errors appear in the Problems view.
The compiled .class files are generated in the bin directory of the project.


MatrixClient Project:

Right-click MatrixClient > Build Project.
Ensure no compilation errors, especially for generated classes and MatrixClient.java.



If compiling outside Eclipse (e.g., via command line):

Navigate to the MatrixCalculator/src directory.
Run:javac -cp ".;D:\2ing\Sem2\Tp SOA\jaxws-ri-3.0.2\jaxws-ri\lib\*" matrixCalculator/*.java


Repeat for MatrixClient/src, adjusting the classpath to include generated classes and JAX-WS libraries.

Execution
1. Run the Server (MatrixCalculator)

Open MatrixServicePublisher.java in the MatrixCalculator project (located in src/matrixCalculator).
Right-click the file > Run As > Java Application.
Check the Eclipse Console for the output:Matrix Calculator SOAP Service published at http://localhost:8060/MatrixCalculator


Verify the service is running by opening a browser and navigating to:http://localhost:8060/MatrixCalculator?wsdl


The WSDL file should load, confirming the service is active.


Keep the server running for client and SoapUI tests.

2. Run the Client (MatrixClient)

Open MatrixClient.java in the MatrixClient project (located in src/test/MatrixClient).
Right-click the file > Run As > Java Application.
Observe the console output, which includes results for all operations:
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
Null matrix: "One or both matrices are null"
Incompatible addition: "Matrices have incompatible dimensions for addition"
Incompatible multiplication: "Matrices have incompatible dimensions for multiplication"
Non-square determinant: "Matrix must be square to calculate determinant"
Zero determinant inverse: "Matrix is not invertible (determinant is zero)"
Non-square trace: "Matrix must be square to calculate trace"
Non-square square: "Matrix must be square to calculate square"




Ensure the server is running before executing the client.

3. Test with SoapUI

Open SoapUI and create a new SOAP project:
File > New SOAP Project.
Enter the WSDL URL: http://localhost:8060/MatrixCalculator?wsdl.
Name the project (e.g., MatrixCalculatorTest).
Click OK to generate the project structure.


In the project tree, expand the operations (addMatrices, subtractMatrices, multiplyMatrices, transposeMatrix, determinant, inverse, multiplyByScalar, trace, squareMatrix).
Create test requests for valid operations:
Addition: Test [[1, 2], [3, 4]] + [[5, 6], [7, 8]]<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
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


Subtraction: Test [[1, 2], [3, 4]] - [[5, 6], [7, 8]]
Expected: [[-4.0, -4.0], [-4.0, -4.0]]


Multiplication: Test [[1, 2], [3, 4]] * [[5, 6], [7, 8]]
Expected: [[19.0, 22.0], [43.0, 50.0]]


Transpose: Test transpose of [[1, 2], [3, 4]]<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
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


Determinant: Test determinant of [[1, 2], [3, 4]]<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
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


Inverse: Test inverse of [[1, 2], [3, 4]]<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:inverse>
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
      </mat:inverse>
   </soapenv:Body>
</soapenv:Envelope>


Expected: [[-2.0, 1.0], [1.5, -0.5]]


Scalar Multiplication: Test [[1, 2], [3, 4]] * 2<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:multiplyByScalar>
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
         <arg1>2.0</arg1>
      </mat:multiplyByScalar>
   </soapenv:Body>
</soapenv:Envelope>


Expected: [[2.0, 4.0], [6.0, 8.0]]


Trace: Test trace of [[1, 2], [3, 4]]<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:trace>
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
      </mat:trace>
   </soapenv:Body>
</soapenv:Envelope>


Expected: 5.0


Square: Test square of [[1, 2], [3, 4]]<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:mat="http://matrixCalculator/">
   <soapenv:Header/>
   <soapenv:Body>
      <mat:squareMatrix>
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
      </mat:squareMatrix>
   </soapenv:Body>
</soapenv:Envelope>


Expected: [[7.0, 10.0], [15.0, 22.0]]




Create test requests for error cases:
Null Matrix Addition: Omit arg1 in addMatrices
Expected: <faultstring>One or both matrices are null</faultstring>


Incompatible Addition: Test [[1, 2], [3, 4]] + [[1, 2, 3], [4, 5, 6]]
Expected: <faultstring>Matrices have incompatible dimensions for addition</faultstring>


Incompatible Multiplication: Test [[1, 2], [3, 4]] * [[1, 2], [3, 4], [5, 6]]
Expected: <faultstring>Matrices have incompatible dimensions for multiplication</faultstring>


Non-Square Determinant: Test determinant of [[1, 2, 3], [4, 5, 6]]
Expected: <faultstring>Matrix must be square to calculate determinant</faultstring>


Zero Determinant Inverse: Test inverse of [[1, 2], [2, 4]]
Expected: <faultstring>Matrix is not invertible (determinant is zero)</faultstring>


Non-Square Trace: Test trace of [[1, 2, 3], [4, 5, 6]]
Expected: <faultstring>Matrix must be square to calculate trace</faultstring>


Non-Square Square: Test square of [[1, 2, 3], [4, 5, 6]]
Expected: <faultstring>Matrix must be square to calculate square</faultstring>




Save the SoapUI project for demonstration.

Demonstration for Evaluation
To demonstrate the project in front of the instructor:

Start the Server:
Run MatrixServicePublisher.java in Eclipse.
Show the console output and WSDL in a browser.


Run the Client:
Execute MatrixClient.java and display the console output, highlighting valid results and error messages for all operations.


Test with SoapUI:
Open the SoapUI project and run the test requests for all operations and error cases.
Show the XML requests and responses, emphasizing error handling.


Show Deliverables:
Present the source files (MatrixCalculator and MatrixClient).
Display the WSDL file (MatrixCalculator.wsdl).
Provide screenshots of key steps (included in the report).
Show the report PDF (MatrixCalculatorReport.pdf).



Troubleshooting

Server Fails to Start: Ensure port 8060 is free (netstat -a -n -o to check) and JDK 21 is correctly configured.
Client Errors: Verify JAX-WS libraries are added to MatrixClient and the server is running.
SoapUI Connection Issues: Confirm the WSDL URL is accessible and the server is active.
Compilation Errors: Check that all JAX-WS JARs are included in the build path and JDK 21 is used.

Deliverables

Source Files: MatrixCalculator and MatrixClient projects.
WSDL File: MatrixCalculator.wsdl.
Screenshots: Captures of Eclipse setup, console outputs, and SoapUI tests.
Report: MatrixCalculatorReport.pdf (includes detailed steps and screenshots).
README: This file (README.md) with compilation and execution instructions.

Contact
For issues or questions, contact:

Ben Amar Mohamed Aziz
Email: [Your email, if applicable]
Université de Sfax, ISSAT Sfax

