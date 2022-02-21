import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.script.ScriptContext;

class printMazePath{
    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner scanner=new Scanner(System.in);
        int T=scanner.nextInt();
        for(int t=0;t<T;t++){
            if(t>0){
                System.out.println();
            }
            int n=scanner.nextInt();
            int maze[][]=new int[n][n];

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    maze[i][j]=scanner.nextInt();
                }
            }
            List<Character> path=new ArrayList<>();
            int i=0;
            int j=0;
            printPaths(i,j,n,maze,path);
            
        }
    }

    private static void printPaths(int i, int j, int n,int[][] maze, List<Character> path) {

        if(i==j && j==n-1){
            String str="";
            for(int k=0;k<path.size();k++){
                str=str+path.get(k);
            }
            System.out.print(str+" ");
            return;
        }

        maze[i][j]=0;
        if(isSafe(i+1,j,n,maze)){
                path.add('D');
                printPaths(i+1, j, n, maze, path);
                path.remove(path.size()-1);
        }
        if(isSafe(i,j-1,n,maze)){
                path.add('L');
                printPaths(i, j-1, n, maze, path);
                path.remove(path.size()-1);
        }
        if(isSafe(i,j+1,n,maze)){
                path.add('R');
                printPaths(i, j+1, n, maze, path);
                path.remove(path.size()-1);
        }
        if(isSafe(i-1,j,n,maze)){
                path.add('U');
                printPaths(i-1, j, n, maze, path);
                path.remove(path.size()-1);
        }
        maze[i][j]=1;
    }

    private static boolean isSafe(int i, int j,int n, int[][] maze) {
        if(i<n && j<n && i>=0 && j>=0){
            if(maze[i][j]!=0){
                return true;
            }
        }
        return false;
    }
}