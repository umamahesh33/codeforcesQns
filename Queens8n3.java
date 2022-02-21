import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main{
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        int caseCount=1;
        while(scanner.hasNext()){
        String str=scanner.nextLine();
        String s1[]=str.split(" ");

        int board[][]=new int[8][8];
        List<List<Integer>> currentPos=new ArrayList<>();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                board[i][j]=0;
            }
        }

        for(int i=0;i<s1.length;i++){
            int row=Integer.parseInt(s1[i]);
            List<Integer> tmp=new ArrayList<>();
            tmp.add(row-1);
            tmp.add(i);
            currentPos.add(tmp);
        }
        int col=0;
        int count[]={0,Integer.MAX_VALUE};
        findMinMoves(col,board,currentPos,count);
        System.out.println("Case "+caseCount+": "+count[1]);
        caseCount++;
        }
    }

    private static void findMinMoves(int col, int[][] board, List<List<Integer>> currentPos,int[] count) {

        if(col==8){
            if(count[1]>count[0]){
                count[1]=count[0];
            }
            return;
        }

        for(int row=0;row<8;row++){
            if(isSafe(row, col, board)){
                int currRow=currentPos.get(col).get(0);
                if(currRow!=row){
                    count[0]++;
                }
                board[row][col]=1;
                findMinMoves(col+1, board, currentPos, count);
                if(currRow!=row){
                    count[0]--;
                }
                board[row][col]=0;
            }
        }
    }

    private static boolean isSafe(int row, int col, int[][] board) {
        int i=row-1;
        int j=col-1;
        while(i>=0 && j>=0){
            if(board[i][j]==1){
                return false;
            }
            i--;
            j--;
        }
        
        i=row;
        j=col-1;
        while(j>=0){
            if(board[i][j]==1){
                return false;
            }
            j--;
        }

        i=row+1;
        j=col-1;
        while(i<8 && j>=0){
            if(board[i][j]==1){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}