/*
                                        PART 1: QUESTION 3. BINARY SEARCH ALGORITHM

 */
package protect;
import java.util.ArrayList;
import java.util.List;

public class SearchingAlgorithmQ3 {

    //data members to store the index of first occurrence and last occurrence of the search key
    int startIndex;
    int endIndex;

    public int getStartIndex() {
            return startIndex;
    }

    public int getEndIndex() {
            return endIndex;
    }

     void binarySearch2 (List<Employee> empList, String searchKey, int upper) {
        //find the first occurrence of the value
        startIndex = -1;
        int l=0;
        int r = upper;

        while(l <= r) { //base case
            //System.out.println("binary search : " + l + " , " + r + " , " + searchKey + " ) length of input array = " + (r-l) + "Start index : " + startIndex);
            int mid = (r-l)/2 + l; //find the middle value

            if (empList.get(mid).getfName().compareToIgnoreCase(searchKey) > 0) {
                r = mid -1;	 //if value at mid is greater than searchkey (alphabetically) - focus on values less than mid and disregard anything bigger than mid

            }else if(empList.get(mid).getfName().compareToIgnoreCase(searchKey) == 0) {
                startIndex = mid; //if value at mid = searchKey -  the first occurrence of search key has been found at mid
                r = mid-1; //upperbound is equal to mid value minus 1

            }else
                l = mid+1; //if mid does not == searchkey, value has not been found - keep repeating until while loop is finished
        }
        if(startIndex != -1){
            //find the last occurrence of the value & repeat steps only if search key has been found above
            endIndex = -1;
            l = 0;
            int r2 = upper;
            //System.out.println("Start Index : " + startIndex);
            while(l <= r2) {//base case
               // System.out.println("binary search step 2 : " + l + " , " + r + " , " + searchKey + " ) length of input array = " + (r2-l) + "end index : " + endIndex);
                int mid = (r2-l)/2 + l; //find mid value

                if (empList.get(mid).getfName().compareToIgnoreCase(searchKey) > 0) { //if mid is greater than searchKey
                r2 = mid -1; //upper is equal to the mid minus 1.

                }else if(empList.get(mid).getfName().compareToIgnoreCase(searchKey) == 0) { //if mid is equal to search key
                    endIndex = mid;  // occurence found
                    l = mid+1; // lwr bound = mid  + 1

                }else
                    l = mid+1;
            }

        }
     }
}
