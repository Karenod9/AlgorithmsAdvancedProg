/*
                            IMPORT CSV & CREATE ARRAYLIST OF EMPLOYEES

 */
package protect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

    private List<Employee> arrList = new ArrayList<Employee>();

    public ReadFromFile() {
    }

    public List<Employee> getArrList() {
	return arrList;
    }

    public void FileReaderNew(String path) throws IOException {
        //set variable for line splitter
        final String COMMA_DELIMITER = ",";

        BufferedReader br = null;

        //create new bufferedreader, pass file path as parameter using filereader
        br = new BufferedReader(new FileReader(path));
        String line = "";

        br.readLine();
        //while there is still data on the next line
        //read the file and create a new employee by passing employee details to employee class
        //add employee to arraylist for processing
        while((line = br.readLine()) !=null) {
            String[] employeeDetails = line.split(COMMA_DELIMITER);

            if(employeeDetails.length > 0){
                Employee emp = new Employee(Integer.parseInt(employeeDetails[0]),
                employeeDetails[1],employeeDetails[2], employeeDetails[3],
                (employeeDetails[4]),(employeeDetails[5]));

                arrList.add(emp);
            }
        }
        br.close();
    }

}


