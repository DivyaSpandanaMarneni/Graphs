import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by marne on 2/20/2017.
 */
public class FindIslands {

    public int getIslandsCount(int[][] islands){
        boolean[][] visited = new boolean[islands.length][islands[0].length];
        int count = 0;
        for(int  i=0;i<islands.length;i++){
            for(int j=0;j<islands.length;j++){
                if(!visited[i][j] && islands[i][j] == 1){
                    //visit all cells in this island and increment the count of islands
                    //visiting all nodes in this island is done by deapth first search
                    DFS(islands,visited,i,j);
                    visited[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }

    public void DFS(int[][] islands,boolean[][] visited, int row, int col){


            //check if there is one in all the neighbours of the given node

            if(row<0 || row>= islands.length || col <0 || col >= islands[0].length )
                return;

            if( islands[row][col] != 1  || visited[row][col]==true)
                return;



            if((row == 0 && col == 0) ){
                if( islands[row][col] ==1 && visited[row][col] == false ) {
                    //visited[row][col] = true;
                    return;
                }

                else
                return;
            }

            visited[row][col] = true;

                DFS(islands, visited, row - 1, col - 1);
                DFS(islands, visited, row - 1, col);
                DFS(islands, visited, row - 1, col + 1);
                DFS(islands, visited, row, col - 1);
                DFS(islands, visited, row, col + 1);
                DFS(islands, visited, row + 1, col - 1);
                DFS(islands, visited, row + 1, col);
                DFS(islands, visited, row + 1, col + 1);



        return;


    }


    public static void main(String[] args){
        int[][] islands = new int[][]{{1,0,1,0,1},{1,1,1,1,0},{0,1,1,1,0},{1,0,1,0,0}};
        FindIslands findIslands = new FindIslands();
        System.out.println("number of islands  = "+findIslands.getIslandsCount(islands));

    }
}
