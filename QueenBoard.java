public class QueenBoard{
  public static void main(String[] args){
    QueenBoard q = new QueenBoard(8);
  }

  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
    //TESTING ADDQUEEN,REMOVEQUEEN
    addQueen(2,3);
    System.out.println(this);
    addQueen(7,6);
    System.out.println(this);
    addQueen(7,7);
    System.out.println(this);
  }

  private boolean addQueen(int r,int c){
    //SPECIAL DESIGNATION: Queens marked by a negative value
    board[r][c] *= -1;
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[i].length;j++){
        if((r==i)||  //same row
           (c==j)||  //same column
           (Math.abs(r-c)==Math.abs(i-j))|| //same backslash diagonal
           (r+c == i+j)) //same forward slash diagonal
        {
          //SPECIAL DESIGNATION: for Queen marked locations, there are negative values
          //to compensate, statement x/abs(x) is used to add -1 to negatives and +1 to positives
          board[i][j] += (board[i][j] / Math.abs(board[i][j]));
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
           (Math.abs(r-c)==Math.abs(i-j))|| //same backslash diagonal
           (r+c == i+j)) //same forward slash diagonal
        {
          //SPECIAL DESIGNATION: for Queen marked locations, there are negative values
          //to compensate, statement x/abs(x) is used to add -1 to negatives and +1 to positives
          board[i][j] -= (board[i][j] / Math.abs(board[i][j]));
        }
      }
    }
    return true;
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
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return -1;
  }


}
