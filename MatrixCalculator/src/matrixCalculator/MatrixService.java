package matrixCalculator;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * SOAP web service interface defining matrix operations: addition, subtraction, multiplication, transpose,
 * determinant, inverse, scalar multiplication, trace, and square.
 */
@WebService
public interface MatrixService {
    /**
     * Adds two matrices and returns the result.
     * @param m1 First matrix
     * @param m2 Second matrix
     * @return Resulting matrix after addition
     * @throws MatrixException If matrices are null or have incompatible dimensions
     */
    @WebMethod
    Matrix addMatrices(Matrix m1, Matrix m2) throws MatrixException;

    /**
     * Subtracts the second matrix from the first and returns the result.
     * @param m1 First matrix
     * @param m2 Second matrix
     * @return Resulting matrix after subtraction
     * @throws MatrixException If matrices are null or have incompatible dimensions
     */
    @WebMethod
    Matrix subtractMatrices(Matrix m1, Matrix m2) throws MatrixException;

    /**
     * Multiplies two matrices and returns the result.
     * @param m1 First matrix
     * @param m2 Second matrix
     * @return Resulting matrix after multiplication
     * @throws MatrixException If matrices are null or have incompatible dimensions
     */
    @WebMethod
    Matrix multiplyMatrices(Matrix m1, Matrix m2) throws MatrixException;

    /**
     * Transposes the given matrix and returns the result.
     * @param m Matrix to transpose
     * @return Transposed matrix
     * @throws MatrixException If the matrix is null
     */
    @WebMethod
    Matrix transposeMatrix(Matrix m) throws MatrixException;

    /**
     * Calculates the determinant of the given matrix.
     * @param m Matrix to calculate determinant for
     * @return The determinant value
     * @throws MatrixException If the matrix is null or not square
     */
    @WebMethod
    double determinant(Matrix m) throws MatrixException;

    /**
     * Calculates the inverse of the given matrix.
     * @param m Matrix to invert
     * @return The inverse matrix
     * @throws MatrixException If the matrix is null, not square, or has a zero determinant
     */
    @WebMethod
    Matrix inverse(Matrix m) throws MatrixException;

    /**
     * Multiplies the given matrix by a scalar value.
     * @param m Matrix to scale
     * @param scalar The scalar value
     * @return The scaled matrix
     * @throws MatrixException If the matrix is null
     */
    @WebMethod
    Matrix multiplyByScalar(Matrix m, double scalar) throws MatrixException;

    /**
     * Calculates the trace of the given matrix (sum of main diagonal elements).
     * @param m Matrix to calculate trace for
     * @return The trace value
     * @throws MatrixException If the matrix is null or not square
     */
    @WebMethod
    double trace(Matrix m) throws MatrixException;

    /**
     * Calculates the square of the given matrix (matrix multiplied by itself).
     * @param m Matrix to square
     * @return The squared matrix
     * @throws MatrixException If the matrix is null or not square
     */
    @WebMethod
    Matrix squareMatrix(Matrix m) throws MatrixException;
}