package matrixCalculator;

/**
 * Represents a matrix with a 2D array of doubles and provides methods to manipulate and validate matrix dimensions.
 */
public class Matrix {
    private double[][] data; // 2D array storing matrix elements
    private int rows;        // Number of rows in the matrix
    private int cols;        // Number of columns in the matrix

    /**
     * Constructs a matrix with the specified number of rows and columns, initialized with zeros.
     * @param rows Number of rows
     * @param cols Number of columns
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    /**
     * Default constructor for an empty matrix.
     */
    public Matrix() {
        super();
    }

    /**
     * Constructs a matrix from a given 2D array, setting rows and columns based on the array dimensions.
     * @param data 2D array containing matrix elements
     */
    public Matrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = data;
    }

    /**
     * Gets the 2D array of matrix elements.
     * @return The matrix data
     */
    public double[][] getData() {
        return data;
    }

    /**
     * Sets the matrix data and updates the row and column counts.
     * @param data 2D array to set as matrix elements
     */
    public void setData(double[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }

    /**
     * Gets the number of rows in the matrix.
     * @return Number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the matrix.
     * @return Number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Checks if this matrix is compatible for addition with another matrix (same dimensions).
     * @param other The other matrix to compare
     * @return True if dimensions match, false otherwise
     */
    public boolean isCompatibleForAddition(Matrix other) {
        return this.rows == other.rows && this.cols == other.cols;
    }

    /**
     * Checks if this matrix is compatible for multiplication with another matrix (this.cols == other.rows).
     * @param other The other matrix to compare
     * @return True if compatible for multiplication, false otherwise
     */
    public boolean isCompatibleForMultiplication(Matrix other) {
        return this.cols == other.rows;
    }

    /**
     * Checks if the matrix is square (rows equal columns).
     * @return True if the matrix is square, false otherwise
     */
    public boolean isSquare() {
        return this.rows == this.cols;
    }

    /**
     * Calculates the determinant of the matrix.
     * @return The determinant value
     * @throws MatrixException If the matrix is not square
     */
    public double determinant() throws MatrixException {
        if (!isSquare()) {
            throw new MatrixException("Matrix must be square to calculate determinant");
        }
        return calculateDeterminant(data);
    }

    /**
     * Recursive helper method to calculate the determinant of a matrix.
     * @param matrix The matrix data
     * @return The determinant value
     */
    private double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double det = 0;
        for (int j = 0; j < n; j++) {
            det += matrix[0][j] * cofactor(0, j, matrix);
        }
        return det;
    }

    /**
     * Calculates the cofactor for a given element in the matrix.
     * @param row The row index
     * @param col The column index
     * @param matrix The matrix data
     * @return The cofactor value
     */
    private double cofactor(int row, int col, double[][] matrix) {
        return Math.pow(-1, row + col) * calculateDeterminant(getMinor(row, col, matrix));
    }

    /**
     * Gets the minor matrix by excluding the specified row and column.
     * @param row The row to exclude
     * @param col The column to exclude
     * @param matrix The matrix data
     * @return The minor matrix
     */
    private double[][] getMinor(int row, int col, double[][] matrix) {
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];
        int minorRow = 0, minorCol;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            minorCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    }

    /**
     * Calculates the adjugate (adjoint) matrix, which is the transpose of the cofactor matrix.
     * @return The adjugate matrix
     * @throws MatrixException If the matrix is not square
     */
    public Matrix adjugate() throws MatrixException {
        if (!isSquare()) {
            throw new MatrixException("Matrix must be square to calculate adjugate");
        }
        double[][] adj = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                adj[i][j] = cofactor(j, i, data); // Transpose of cofactor matrix
            }
        }
        return new Matrix(adj);
    }
}