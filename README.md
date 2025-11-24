# 🧮 Matrix Calculator – SOAP Web Service

A lightweight SOAP-based Matrix Calculator built using **JAX-WS** and **JDK 21**.  
Developed for the **SOA & Microservices** module at **Université de Sfax (ISIMS)**.

---

## 🚀 Overview

This service exposes SOAP operations for common matrix computations with full error handling.

Supported features:

- Addition / Subtraction  
- Matrix Multiplication  
- Transpose  
- Determinant  
- Inverse  
- Scalar Multiplication  
- Trace  
- Matrix Square  

Errors handled: null matrices, incompatible dimensions, non-square matrices, zero determinant.

Two components:

- **MatrixCalculator** → SOAP server  
- **MatrixClient** → test client (Java + wsimport)  

---

## ⚙️ Tech Stack

| Tool | Usage |
|------|-------|
| **Java (JDK 21)** | Development |
| **JAX-WS RI 3.0.2** | SOAP |
| **Eclipse IDE** | Coding |
| **SoapUI** | Testing |
| **wsimport** | Client code generation |

---

## 📦 Setup

### 1️⃣ Requirements
- JDK 21 (JAVA_HOME configured)  
- JAX-WS RI 3.0.2  
- Eclipse IDE  
- SoapUI  

---

## 🛠️ Project Setup

### Import Projects
1. Open Eclipse  
2. **File → Import → Existing Projects**  
   - Import **MatrixCalculator**  
   - Import **MatrixClient**  
3. Ensure both projects use **JDK 21**  
4. Add JAX-WS JARs to each project’s *Build Path*

### Generate Client Classes
Start the server, then run:

```bash
wsimport -keep -p test.MatrixClient http://localhost:8060/MatrixCalculator?wsdl
