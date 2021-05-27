/*

                                            TESTING CLASS

 */
package protect;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class MainTesting {

    public static void main(String[] args) throws Exception{

/*---------------------------------------------------------------------------------------------------------------------------------------
 *  									READ FROM CSV FILE
-------------------------------------------------------------------------------------------------------------------------------------*/

        //ENTER FILE PATH TO READ FROM & CREATE LIST OF EMPLOYEES
        //csv file is saved in project folder
        String path = "src\\main\\resources\\empData.csv";

        //Check file path is correct - throws file exception and terminates the program if not - otherwise continues with program
        CheckFile checkFile = new CheckFile();
        try{

            checkFile.CheckFileDetails(path);

        }catch(FileException fe){
            System.out.println(fe.getMessage());
            System.exit(0);
        }


        //create list of employees
        //invoke readFromFile and pass in the path of the file
        // Read from csv file & create list of employees
        List<Employee> arrList = new ArrayList<Employee>();

        ReadFromFile rFF = new ReadFromFile();
        rFF.FileReaderNew(path);
        arrList = rFF.getArrList();

    /*---------------------------------------------------------------------------------------------------------------------------------------
    * 							PRINT UNSORTED ARRAYLIST OF EMPLOYEES
    -------------------------------------------------------------------------------------------------------------------------------------*/

        DisplayEmployees ds = new DisplayEmployees();
        System.out.println("UNSORTED EMPLOYEE LIST");
        ds.displayEmployees(arrList);
        System.out.println(" -------------------------------------------------------------- ");

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    * 									SORTING TESTING
    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/



        SortAlgorithmQ1 sorting = new SortAlgorithmQ1();
        List<Employee> sortedArray = new ArrayList<Employee>(arrList);

        long startTime = System.nanoTime();

        sortedArray = sorting.mergeSort(sortedArray);

        long endTime = System.nanoTime();
        long elapsedTime = (endTime-startTime);

        int counter2 = 0;
        System.out.println("SORTED EMPLOYEE LIST");
        for(Employee e : sortedArray) {
                System.out.println(counter2 + " " + "EmpNo: " + e.getEmpNo()+" "+ "BirthDate: " + e.getBirthDate()+ " "
        +"First Name: " + e.getfName()+ " " +"Last Name: " +e.getlName()+" " +"Gender: " +e.getGender()+" " + "Hire Date: " +e.getHireDate());
                counter2++; //for testing - to delete
        }
        System.out.println("ELAPSED TIME: " + elapsedTime);
        System.out.println(" -------------------------------------------------------------- ");
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    * 							SEARCHING TESTING
    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

        //Must call sort algorithm before using this searching method.

        SearchingAlgorithmQ3 search = new SearchingAlgorithmQ3();


        //ENTER FIRST NAME TO SEARCH FOR IT:
        String searchKey = "zVoNkO";

        long startTime2 = System.nanoTime();
        //call binarySearch method and pass in the sortedArray and search key and upperbound.
        search.binarySearch2(sortedArray, searchKey, sortedArray.size()-1);

        long endTime2 = System.nanoTime();
        long elapsedTime2 = (endTime2-startTime2);

        // get start index and end index of the search key
        int endIndex = search.getEndIndex();
        int startIndex = search.getStartIndex();

        //if start index is not equal to -1 and end index is not = to -1  -- employee record has been found
        //if equal to minus 1 - employee record has not been found.
        //traverse through the arraylist while start Index is less than or equal to end index
        //print out employee record located between start index and end index.
        if(startIndex != -1 && endIndex != -1) {
            System.out.println("Employee with first name " + searchKey + " " + "found : ");
            System.out.println("");
            for(int i=0; i+startIndex <= endIndex; i++) {
                if(i>0)
                    System.out.println("");
                    System.out.print("@ Index  : ");
                    System.out.println(i+startIndex);
                    System.out.println(sortedArray.get(i+startIndex));
                }
        }else {
            System.out.println(searchKey + " is Not an employee!");
        }
        System.out.println(" ");
        System.out.println("ELAPSED TIME: " + elapsedTime2);
        System.out.println(" -------------------------------------------------------------- ");
    /*---------------------------------------------------------------------------------------------------------------------------------------
    *                                              CREATE NEW EMPLOYEE, ADD TO UNSORTED ARRAYLIST & WRITE TO CSV FILE
    -------------------------------------------------------------------------------------------------------------------------------------*/
        int addEmps = 1;

        String birthDate = null;
        String fName, lName, gender, hireDate;

        try {
            //while addEmps = 1 continue adding employees. When user inputs another number other than 1 the program will terminate
            while (addEmps == 1) {
                Scanner input = new Scanner(System.in);
                Employee newEmp = new Employee();

                //automatically adds employee number to UNSORTED arraylist
                int lastEmpNo = 0;
                for(Employee e : arrList) {
                    lastEmpNo = e.getEmpNo();
                }
                int nextEmpNo = lastEmpNo + 1;
                int empNo = nextEmpNo;
                newEmp.setEmpNo(empNo);

                int dateValidate = -1;

                //Accept date of birth from user.
                //Validate input (setter in Employee class)
                do {

                    System.out.println("Enter date of birth in format dd/mm/yyyy: ");
                    if(input.hasNextLine()) {
                        birthDate = input.nextLine();
                        newEmp.setBirthDate(birthDate);
                        //update dateValidate variable from Employee class using getter
                        dateValidate = newEmp.getValidate();
                        if(dateValidate == 1) {
                                System.out.println("Error:");
                                System.out.println("A birth date must be supplied. Please enter a valid birth date");
                        }else if(dateValidate == 2) {
                                System.out.println("Invalid date format:");
                                System.out.println("Please enter a date in the following format - dd/mm/yyyy --- dates in mm/dd/yyyy not accepted");
                        }else if(dateValidate == 3) {
                                System.out.println("Invalid:");
                                System.out.println("Employee's age cannot be under 18. Please enter a correct birth date to continue: ");
                        } else if(dateValidate == 4) {
                                System.out.println("Invalid:");
                                System.out.println("Employee is over retirement age. Please enter a correct birth date to continue: ");
                        } else if (dateValidate == 0) {
                                break;
                        }
                    }
                }while (dateValidate != -1 || dateValidate != 0); //continue asking for input while date variable is not equal to -1 or 0

                //Accept first name from user
                //Validate input (setter in Employee class)
                int nameValid = -1;

                do {
                    try {
                        System.out.println("Enter First Name: ");
                        fName = input.nextLine();
                        newEmp.setfName(fName);
                        nameValid = newEmp.getNameValid();
                        //catch input exception
                    }catch(InputException ee) {
                        System.out.println(ee.getMessage());
                        //catch SQL injection exception
                    }catch(SQLInjectionException sql){
                        System.out.println(sql.getMessage());
                        System.exit(0);
                    }
                }while(nameValid != 0); //continue asking for input while nameValid is not = 0.

                //Accept surname from user
                //Validate input (setter in Employee class)
                System.out.println("Enter Surname: ");
                lName = input.nextLine();
                newEmp.setlName(lName);

                //Accept gender from user
                //Validate input (setter in Employee class)
                System.out.println("Is employee M or F? ");
                gender = input.nextLine();
                newEmp.setGender(gender.toUpperCase());

                //Accept hiredate from user
                //Validate input (Setter in Employee class)
                int hireValidate = -1;
                do {
                    System.out.println("Enter hire date: ");
                    hireDate = input.nextLine();
                    newEmp.setHireDate(hireDate);
                    hireValidate = newEmp.getHireValidate();
                    if(hireValidate == 1) {
                        System.out.println("Error : ");
                        System.out.println("Hire date cannot be left blank. Please enter a hire date : ");
                    }else if(hireValidate == 2) {
                        System.out.println("Error this is not a valid date: ");
                        System.out.println("Please enter a date in the following format - dd/mm/yyyy --- dates in mm/dd/yyyy not accepted");
                    }else {

                    }

                }while(hireValidate != 0);

                //create a temp list of new employees to be added to the csv file.
                //invoke writeToFile method in AddNewEmployee class and pass in the temp list
                List<Employee> tempArr = new ArrayList<Employee>();
                tempArr.add(newEmp);
                AddNewEmployee addNewEmployee = new AddNewEmployee();
                addNewEmployee.setPath(path);
                addNewEmployee.writeToFile(tempArr);

                //add the new employee to the list that was created when reading the file earlier in the program
                arrList.add(newEmp);

                //check if user wants to add another employee or quit
                Scanner in = new Scanner(System.in);
                System.out.println("Press 0 to finish or 1 to add more employees");
                addEmps = in.nextInt();
            }
        System.out.println(" -------------------------------------------------------------- ");
        System.out.println("New employees have been added to the system!");
        System.out.println(" -------------------------------------------------------------- ");
        //catch any other input exceptions
        }catch(InputException ex) {
            System.out.println(ex.getMessage());
        }
        //print to screen updated employee list
	//ds.displayEmployees(arrList);

    }

}

