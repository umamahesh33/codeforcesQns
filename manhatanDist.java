import java.util.Arrays;

/*
manhatan distance is the distance between two pts in a coordinate system where we can only move 
horizentally or vertically to reach pt2 from pt1(no diagonal movement was allowed)

letsay pt1(2,4) pt2(4,8)

manhatan distance to reach pt2 to pt1 was-->abs(x2-x1)+abs(y2-y1)
(4-2)+(8-4)===6 was the manhatan distance
*/

public class manhatanDist {
    public static void main(String[] args) {
        int x[] = {1,-1,3,2};
        int y[] = { 5, 6, 5, 3};
        int n=x.length;
        //caluclation of manhatan distance
        Arrays.sort(x);
        Arrays.sort(y);
        int xTotal=0;
        for(int i=0;i<n;i++){
            xTotal+=x[i];
        }
        int yTotal=0;
        for(int i=0;i<n;i++){
            yTotal+=y[i];
        }

        int totalDist=0;
        for(int i=0;i<n;i++){
            xTotal=xTotal-x[i];
            totalDist+=xTotal-(n-1-i)*x[i];
        }

        for(int i=0;i<n;i++){
            yTotal=yTotal-y[i];
            totalDist+=yTotal-(n-1-i)*y[i];
        }

        System.out.println("old calc "+totalDist);

        System.out.println("new calc "+caluclateManhattanDistance(x, y));
    }

    static int caluclateManhattanDistance(int x[],int y[]){

        int xsum=0,ysum=0;
        Arrays.sort(x);
        Arrays.sort(y);

        int n = x.length;
        for(int i=0;i<n;i++){
            xsum+=i*x[i]-(n-i-1)*x[i];
            ysum+=i*y[i]-(n-i-1)*y[i];
        }

        return xsum+ysum;
    }
}
