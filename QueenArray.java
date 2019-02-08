import java.util.ArrayList;
public class QueenArray{
  public static void main(String[] args){
    //space for testing
  }
  private ArrayList<Integer> colVals;
  private int size;

  public QueenArray(int size){
    colVals = new ArrayList<Integer>();
    this.size = size;
  }
  public boolean solve(){
    return false;
  }
  public int countSolutions(){
    if(colVals.size() != 0) throw new IllegalStateException("queen array not empty");
    return counter();
  }
  private int counter(){
    if(colVals.size() == size) return 1;
    else{
      int out = 0;
      for(int i=0;i<size;i++){
        if(!(underAttack(i))){
          colVals.add(i);
          out += counter();
          colVals.remove(colVals.size()-1);
        }
      }
      return out;
    }
  }
  private boolean underAttack(int col){
    int row = colVals.size();
    for(int rowInd = 0; rowInd<colVals.size(); rowInd++){
      if(conflictBetween(row,col,rowInd,colVals.get(rowInd))) return true;
    }
    return false;
  }
  private boolean conflictBetween(int row1,int col1,int row2,int col2){
    return row1==row2 || col1==col2 ||
           row2-col2 == row1-col1 ||
           row2+col2 == row1+col1;
  }
}
