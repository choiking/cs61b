/* SimpleBoard.java */

/**
 *  Simple class that implements an 8x8 game board with three possible values
 *  for each cell:  0, 1 or 2.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class SimpleBoard {
  private final static int DIMENSION = 8;
  private int[][] grid;

  /**
   *  Invariants:  
   *  (1) grid.length == DIMENSION.
   *  (2) for all 0 <= i < DIMENSION, grid[i].length == DIMENSION.
   *  (3) for all 0 <= i, j < DIMENSION, grid[i][j] >= 0 and grid[i][j] <= 2.
   **/

  /**
   *  Construct a new board in which all cells are zero.
   */

  public SimpleBoard() {
    grid = new int[DIMENSION][DIMENSION];
  }

  /**
   *  Set the cell (x, y) in the board to the given value mod 3.
   *  @param value to which the element should be set (normally 0, 1, or 2).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   **/

  public void setElementAt(int x, int y, int value) {
    grid[x][y] = value % 3;
    if (grid[x][y] < 0) {
      grid[x][y] = grid[x][y] + 3;
    }
  }//kind of like Compression function

  /**
   *  Get the valued stored in cell (x, y).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @return the stored value (between 0 and 2).
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   */

  public int elementAt(int x, int y) {
    return grid[x][y];
  }

  /**
   *  Returns true if "this" SimpleBoard and "board" have identical values in
   *    every cell.
   *  @param board is the second SimpleBoard.
   *  @return true if the boards are equal, false otherwise.
   */

  public boolean equals(Object board) {
    // Replace the following line with your solution.  Be sure to return false
    //   (rather than throwing a ClassCastException) if "board" is not
    //   a SimpleBoard.
    int i=0;
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
       if(grid[x][y]==((SimpleBoard)board).elementAt(x,y)) i++;
      }
    }
    if(i==64) {return true;}
    else { return false;}
  }

  /**
   *  Returns a hash code for this SimpleBoard.
   *  @return a number between Integer.MIN_VALUE and Integer.MAX_VALUE.
   */

  public int hashCode() {
    // Replace the following line with your solution.
    int hash_code=0;
    for (int i=0; i<DIMENSION; i++) {
      for (int j=0; j<DIMENSION; j++) {
        int position=DIMENSION*i+j;
        hash_code+=elementAt(i,j)*power(3,position);//base 3 to base 10(Polynominal Hash Code)
      }
    }
    return hash_code;
  }

  public int power(int number, int to_what_power){
    int num=number;
    for (int i=0; i<to_what_power-1; i++){
      num=number*num;
    }
    return num;
  }


 /* public int hashCode() {//Polynominal Hash Code
    int hash = 0;
    for (int x=0; x<DIMENSION; x++) {
      for (int y=0; y<DIMENSION; y++) {
        hash = hash * 3 + grid[x][y];
      }
    }
    return hash;
  }
  */
}