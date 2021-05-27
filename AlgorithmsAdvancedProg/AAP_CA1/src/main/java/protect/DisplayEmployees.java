/*
                                                    METHOD TO DISPLAY ARRAYLIST OF EMPLOYEES -- USED FOR TESTING

 */
package protect;
import java.util.ArrayList;
import java.util.List;

public class DisplayEmployees {


    public void displayEmployees(List<Employee> arrList) {

	int counter = 0; //for testing to delete
	System.out.println("EMPLOYEE LIST : ");
	for(Employee e : arrList) {
		System.out.println(counter + " " +  e.getEmpNo()+" " + e.getBirthDate()+ " "
                +e.getfName()+ " " +e.getlName()+" "+e.getGender()+" " +e.getHireDate());
		counter++; //for testing to delete

	}
    }

}
