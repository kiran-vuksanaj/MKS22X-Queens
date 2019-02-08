import java.util.*;
public class QueenBoard{
  public static void main(String[] args){
    QueenBoard q = new QueenBoard(8);
    q.solve();
    System.out.println(q);
  }

  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  private boolean addQueen(int r,int c){
    //SPECIAL DESIGNATION: Queens marked by a negative value
    board[r][c] *= -1;
    board[r][c] -= 1;
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[i].length;j++){
        if(((r==i)||  //same row
           (c==j)||  //same column
           (r-c==i-j)|| //same backslash diagonal
           (r+c == i+j)) //same forward slash diagonal
           && (!(r==i && c==j))) //not the queen itself
        {
          //SPECIAL DESIGNATION: for Queen marked locations, there are negative values
          //to compensate, statement x/abs(x) is used to add -1 to negatives and +1 to positives
          //if/else to avoid unintentional div by 0
          if(board[i][j]==0) board[i][j] = 1;
          else               board[i][j] += (board[i][j] / Math.abs(board[i][j]));
        }
      }
    }
    return true;
  }
  private boolean removeQueen(int r,int c){
    //SPECIAL DESIGNATION: Queens marked by a negative value; check and undo
    if(board[r][c] >= 0) throw new IllegalArgumentException("no queen at r:"+r+" c:"+c);
    board[r][c] *= -1;
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[i].length;j++){
        if((r==i)||  //same row
           (c==j)||  //same column
           (r-c == i-j)|| //same backslash diagonal
           (r+c == i+j)) //same forward slash diagonal
        {
          //SPECIAL DESIGNATION: for Queen marked locations, there are negative values
          //to compensate, statement x/abs(x) is used to add -1 to negatives and +1 to positives
          //issues of dividing by 0 should not arise here
          board[i][j] -= (board[i][j] / Math.abs(board[i][j]));
        }
      }
    }
    return true;
  }

  //for testing purposes
  private void printBoard(){
    for(int[] row:board){
      for(int n:row){
        if(n>=0) System.out.print(" ");
        System.out.print(n);
      }
      System.out.println("");
    }
  }
  public void testAddRemove(){
    //TESTING ADDQUEEN,REMOVEQUEEN
    addQueen(2,3);
    System.out.println(this);
    printBoard();
    addQueen(7,6);
    System.out.println(this);
    printBoard();
    addQueen(7,7);
    System.out.println(this);
    printBoard();
    removeQueen(7,6);
    System.out.println(this);
    printBoard();
    removeQueen(2,3);
    System.out.println(this);
    printBoard();
    removeQueen(7,7);
    System.out.println(this);
    printBoard();
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _

  *_ _ _ Q

  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String out = "";
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[i].length;j++){
        if(board[i][j] < 0) out += "Q";
        else                out += ".";
      }
      out += "\n";
    }
    return out;
  }



  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  public boolean solve(){
    return solver(0);
  }
  private boolean solver(int row){
    if(row==board.length){
      //a branch has reached the end of the board, meaning all spaces have been filled;
      return true;
    }else{
      for(int col=0;col<board[row].length;col++){
        if(board[row][col]==0){
          if(addQueen(row,col) && solver(row+1)) return true;
          else removeQueen(row,col);
        }
      }
      return false;
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return -1;
  }


}
