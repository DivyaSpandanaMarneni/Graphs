import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Created by marne on 2/20/2017.
 */
public class FindFriends {

    public int getFriendCircles(char[][] friends){
        if(friends == null || friends.length < 1)
            return 0;

        int circles = 0;

        boolean[] visited = new boolean[friends.length];

        for(int i=0;i<friends.length;i++){
            if(!visited[i]){
                circles++;
                visited[i] = true;
                findFriends(friends,visited,i);
            }
        }

        return circles;
    }

    public void findFriends(char[][] friends, boolean[] visited, int label){

        for(int i=0;i<friends.length;i++){
            if(!visited[i] && i!= label && friends[label][i] == 'Y'){
                visited[i] = true;
                findFriends(friends,visited,i);
            }
        }
    }

    public static void main(String[] args){

        char[][] friends ;

        friends = new char[][]{ {'Y','Y','N','N'},{'Y','Y','Y','N'},{'N','Y','Y','N'},{'N','N','N','Y'}};
        FindFriends findFriends = new FindFriends();
        System.out.println("number of friends circle "+findFriends.getFriendCircles(friends));


    }
}
