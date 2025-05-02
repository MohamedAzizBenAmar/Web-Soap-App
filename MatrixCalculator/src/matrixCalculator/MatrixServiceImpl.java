package matrixCalculator;

import jakarta.jws.WebService;

/**
 * Implementation of the MatrixService interface, providing logic for matrix operations.
 */
@WebService(endpointInterface = "matrixCalculator.MatrixService")
public class MatrixServiceImpl implements MatrixService {

    /**
     * Adds two matrices by summing corresponding elements.
     * @param m1 First matrix
     * @param m2 Second matrix
     * @return A new matrix containing the sum
     * @throws MatrixException If matrices are null or have incompatible dimensions
     */
    @Override
    public Matrix addMatrices(Matrix m1, Matrix m2) throws MatrixException {
        if (m1 == null || m2 == null) {
            throw new MatrixException("One or both matrices are null");
        }
        if (!m1.isCompatibleForAddition(m2)) {
            throw new MatrixException("Matrices have incompatible dimensions for addition");
        }

        double[][] result = new double[m1.getRows()][m1.getCols()];
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getCols(); j++) {
                result[i][j] = m1.getData()[i][j] + m2.getData()[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Subtracts the second matrix from the first by subtracting corresponding elements.
     * @param m1 First matrix
     * @param m2 Second matrix
     * @return A new matrix containing the difference
     * @throws MatrixException If matrices are null or have incompatible dimensions
     */
    @Override
    public Matrix subtractMatrices(Matrix m1, Matrix m2) throws MatrixException {
        if (m1 == null || m2 == null) {
            throw new MatrixException("One or both matrices are null");
        }
        if (!m1.isCompatibleForAddition(m2)) {
            throw new MatrixException("Matrices have incompatible dimensions for subtraction");
        }

        double[][] result = new double[m1.getRows()][m1.getCols()];
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getCols(); j++) {
                result[i][j] = m1.getData()[i][j] - m2.getData()[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Multiplies two matrices using the standard matrix multiplication algorithm.
     * @param m1 First matrix
     * @param m2 Second matrix
     * @return A new matrix containing the product
     * @throws MatrixException If matrices are null or have incompatible dimensions
     */
    @Override
    public Matrix multiplyMatrices(Matrix m1, Matrix m2) throws MatrixException {
        if (m1 == null || m2 == null) {
            throw new MatrixException("One or both matrices are null");
        }
        if (!m1.isCompatibleForMultiplication(m2)) {
            throw new MatrixException("Matrices have incompatible dimensions for multiplication");
        }

        double[][] result = new double[m1.getRows()][m2.getCols()];
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m2.getCols(); j++) {
                for (int k = 0; k < m1.getCols(); k++) {
                    result[i][j] += m1.getData()[i][k] * m2.getData()[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    /**
     * Transposes the given matrix by swapping rows and columns.
     * @param m Matrix to transpose
     * @return A new matrix containing the transpose
     * @throws MatrixException If the matrix is null
     */
    @Override
    public Matrix transposeMatrix(Matrix m) throws MatrixException {
        if (m == null) {
            throw new MatrixException("Matrix is null");
        }

        double[][] result = new double[m.getCols()][m.getRows()];
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                result[j][i] = m.getData()[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Calculates the determinant of the given matrix.
     * @param m Matrix to calculate determinant for
     * @return The determinant value
     * @throws MatrixException If the matrix is null or not square
     */
    @Override
    public double determinant(Matrix m) throws MatrixException {
        if (m == null) {
            throw new MatrixException("Matrix is null");
        }
        return m.determinant();
    }

    /**
     * Calculates the inverse of the given matrix.
     * @param m Matrix to invert
     * @return The inverse matrix
     * @throws MatrixException If the matrix is null, not square, or has a zero determinant
     */
    @Override
    public Matrix inverse(Matrix m) throws MatrixException {
        if (m == null) {
            throw new MatrixException("Matrix is null");
        }
        if (!m.isSquare()) {
            throw new MatrixException("Matrix must be square to calculate inverse");
        }
        double det = m.determinant();
        if (Math.abs(det) < 1e-10) { // Check for zero determinant
            throw new MatrixException("Matrix is not invertible (determinant is zero)");
        }
        Matrix adjugate = m.adjugate();
        double[][] result = new double[m.getRows()][m.getCols()];
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                result[i][j] = adjugate.getData()[i][j] / det;
            }
        }
        return new Matrix(result);
    }

    /**
     * Multiplies the given matrix by a scalar value.
     * @param m Matrix to scale
     * @param scalar The scalar value
     * @return The scaled matrix
     * @throws MatrixException If the matrix is null
     */
    @Override
    public Matrix multiplyByScalar(Matrix m, double scalar) throws MatrixException {
        if (m == null) {
            throw new MatrixException("Matrix is null");
        }
        double[][] result = new double[m.getRows()][m.getCols()];
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                result[i][j] = m.getData()[i][j] * scalar;
            }
        }
        return new Matrix(result);
    }

    /**
     * Calculates the trace of the given matrix (sum of main diagonal elements).
     * @param m Matrix to calculate trace for
     * @return The trace value
     * @throws MatrixException If the matrix is null or not square
     */
    @Override
    public double trace(Matrix m) throws MatrixException {
        if (m == null) {
            throw new MatrixException("Matrix is null");
        }
        if (!m.isSquare()) {
            throw new MatrixException("Matrix must be square to calculate trace");
        }
        double trace = 0;
        for (int i = 0; i < m.getRows(); i++) {
            trace += m.getData()[i][i];
        }
        return trace;
    }

    /**
     * Calculates the square of the given matrix (matrix multiplied by itself).
     * @param m Matrix to square
     * @return The squared matrix
     * @throws MatrixException If the matrix is null or not square
     */
    @Override
    public Matrix squareMatrix(Matrix m) throws MatrixException {
        if (m == null) {
            throw new MatrixException("Matrix is null");
        }
        if (!m.isSquare()) {
            throw new MatrixException("Matrix must be square to calculate square");
        }
        return multiplyMatrices(m, m);
    }
}