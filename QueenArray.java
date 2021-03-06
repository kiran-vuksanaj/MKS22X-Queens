import java.util.ArrayList;
public class QueenArray{
  public static void main(String[] args){
    if(args.length >= 1){
      int size = Integer.parseInt(args[0]);
      QueenArray q = new QueenArray(size);
      System.out.print(size+": ");
      System.out.print(q.countSolutions()+", ");
      System.out.println(q.solve());
      System.out.println(q);
    }else{
      for(int i=0;i<20;i++){
        QueenArray q = new QueenArray(i);
        System.out.print(i+": ");
        System.out.print(q.countSolutions()+", ");
        System.out.println(q.solve());
        System.out.println(q);
      }
    }
  }
  private ArrayList<Integer> colVals;
  private int size;

  public QueenArray(int size){
    colVals = new ArrayList<Integer>();
    this.size = size;
  }
  public boolean solve(){
    if(colVals.size() != 0) throw new IllegalStateException("queen array not empty");
    return solver();
  }
  private boolean solver(){
    if(colVals.size() == size) return true;
    else{
      for(int i=0;i<size;i++){
        if(!(underAttack(i))){
          colVals.add(i);
          if(solver()) return true;
          else         colVals.remove(colVals.size()-1);
        }
      }
      return false;
    }
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
  public String toString(){
    String out = "";
    for(int i=0;i<size;i++){
      for(int j=0;j<size;j++){
        if(colVals.size() > i && colVals.get(i)==j){
          out += "Q";
        }else out += ".";
      }
      out += "\n";
    }
    return out;
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
