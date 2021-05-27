/*
                                    PART 2: QUESTION 1: ADD NEW EMPLOYEE

 */
package protect;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddNewEmployee {

    private String path;
    private FileWriter fw = null;


    public AddNewEmployee() {

    }

    public void setPath(String path) {
            this.path = path;
    }

    //METHOD TO ADD NEW RECORD TO THE FILE
    public void writeToFile(List<Employee>arrList) throws IOException {

        //create new instance of FileWriter - with parameters location of file and 'true' so it appends to file instead of overwriting
        fw = new FileWriter (path, true);

        final String COMMA_DELIMITER = ",";
        final String LINE_SEPARATOR = "\n";

        //iterate through the arraylist of new employees
        Iterator it = arrList.iterator();
        while(it.hasNext()) {

        //add new employee details to the file/record
            Employee a = (Employee)it.next();
            fw.append(String.valueOf(a.getEmpNo()));
            fw.append(COMMA_DELIMITER);
            fw.append(String.valueOf(a.getBirthDate()));
            fw.append(COMMA_DELIMITER);
            fw.append(String.valueOf(a.getfName()));
            fw.append(COMMA_DELIMITER);
            fw.append(String.valueOf(a.getlName()));
            fw.append(COMMA_DELIMITER);
            fw.append(String.valueOf(a.getGender()));
            fw.append(COMMA_DELIMITER);
            fw.append(String.valueOf(a.getHireDate()));
            fw.append(LINE_SEPARATOR);
        }
        //push the information to the file and close the FileWriter
        fw.flush();
        fw.close();
    }

}
