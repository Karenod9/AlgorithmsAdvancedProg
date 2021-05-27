/*
                                    PART 1: QUESTION 1- MERGE SORT ALGORITHM

 */
package protect;
import java.util.ArrayList;
import java.util.List;

public class SortAlgorithmQ1<T extends Comparable<T>> {

    /* Divide  */

   public List<T> mergeSort(List<T> empList){

            //create two temp arraylists to perform division of the arraylist
            List<T> left = new ArrayList<>();
            List<T> right = new ArrayList<>();
            int middle;

            //if the array size == 1 it's already sorted so return the arraylist
            if(empList.size() == 1) {
                    return empList;
            }else {
                    middle = empList.size()/2; //find the middle point

                    //while i is less that the middle value add values to the temp left arraylist
                    for(int i=0; i <middle; i++) {
                            left.add(empList.get(i));
                    }

                    //i = middle value - while i is less than the size of the arraylist add values to the temp right arraylist
                    for(int i=middle; i<empList.size(); i++) {
                            right.add(empList.get(i));
                    }

                    //sort the right and left arraylists
                    left = mergeSort(left);
                    right = mergeSort(right);

                    //merge the two halves
                    merge(left, right, empList);
            }
            return empList;
    }

                    /* merge (conquer) the divided arraylist */
   private void merge(List<T> left, List<T> right, List<T> empList) {
            int leftI = 0;
            int rightI = 0;
            int empListI = 0;

            //while left index is less than the left array size AND right index is less than right array size
            while (leftI < left.size() && rightI < right.size()) {
                    //compare the value in left index to value in right index
                    //if value at left index is less than right index (alphabetically a < b?) add value at left index to empList
                    if((left.get(leftI).compareTo(right.get(rightI)))<0){
                            empList.set(empListI, left.get(leftI));
                            leftI++;
                    //if not add the value at the right index to the empList
                    }else {
                            empList.set(empListI, right.get(rightI));
                            rightI++;
                    }
                    empListI++;
            }
            List<T> remainder; //check for any remaining values
            int remainI;

            if(leftI >= left.size()) {
                    remainder = right;
                    remainI = rightI;
            }else {
                    remainder = left;
                    remainI = leftI;
            }
            for(int i=remainI; i<remainder.size(); i++) {
                    empList.set(empListI, remainder.get(i));
                    empListI++;
            }
    }
}