/*
                        EMPLOYEE CLASS + BELOW PART 2: QUESTION 2 & 3 - DEFENSIVE PROGRAMMING & EXCEPTION HANDLING

 */
package protect;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee implements Comparable <Object> {

    //create data members
    private int empNo;
    private String birthDate;
    private String fName;
    private String lName;
    private String gender;
    private String hireDate;
    private int validate = -1;
    private String tempDate;
    private int nameValid = -1;
    private int hireValidate = -1;
    private String tempHire;


    public Employee() {

    }

    public Employee(int empNo, String birthDate, String fName, String lName, String gender, String hireDate) {
        super();
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.hireDate = hireDate;

    }

    public int getEmpNo() {
            return empNo;
    }

    public void setEmpNo(int empNo) {
            this.empNo = empNo;
    }

    public String getBirthDate() {
            return birthDate;
    }

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*                                       PART 2: Q2 - VALIDATE BIRTH_DATE
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /*
     * Validate date -
     *
     * -1 - initialization
     * 0 - correct date - break out of do..while loop
     * 1 - empty input - error
     * 2 - incorrect input format (e.g must be dd-mm-yyyy NOT MM/dd/yyyy - error try again
     * 3 - Under 18 - error try again
     * 4 - Over retirement age - error try again
     *
     */

    public int getValidate() {
        return validate;
    }

    //check if the format of the date is valid
    //valid date format = DD-MM-YYYY
    public boolean isDateFormatValid(String date){
        boolean res = false;
        //assign correct date pattern to String datePat
        final String datePat = "^((0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|2[0-9])[0-9]{2})$";
        //compile the pattern
        Pattern pat = Pattern.compile(datePat);
        //check if date matches this pattern
        Matcher matcher = pat.matcher(date);
        //if the date matches this pattern update res to true
        if(matcher.matches()) {
            res = true;
        }
        return res;
    }

    public void setBirthDate(String birthDate) throws ParseException{

        //check if birthdate is empty
        if(birthDate == null || birthDate.length() == 0) {
            //update validation variable to 1
            //("Birth Date is empty. Please enter a valid birth date");
            validate = 1;

        }else {
            //create temp variable to store the date for manipulation/validation
            // the actual input birthdate is kept unaltered so that when writing to the CSV file the formatting is correct
            this.tempDate = birthDate;

            //replace any "/" with "-"
            if(tempDate.contains("/")) {
                tempDate = tempDate.replace("/", "-");
            }
            //check that the date is a valid format (dd-mm-yyyy or dd/mm/yyyy).
            if(isDateFormatValid(tempDate)){

                //convert string into date
                String dateFormat = "DD-MM-YYYY";
                SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                Date bDate = formatter.parse(tempDate);

                //convert date input of dd-mm-yyyy to yyyy-mm-dd
                SimpleDateFormat convertFormat = new SimpleDateFormat("YYYY-MM-dd");
                tempDate = convertFormat.format(bDate);

                //set current date & retirement age date  & parse birthdate
                LocalDate correctBDate = LocalDate.parse(tempDate);
                LocalDate currentDate = LocalDate.now();
                LocalDate retirementDate = LocalDate.of(1950, 01, 01);

                //get current age
                int age = Period.between(correctBDate, currentDate).getYears();

                //if current age is less, throw error and ask for a valid date.
                if(age < 18) {
                    validate = 3;
                    //if input date is older than retirement date throw error and ask for a valid date
                }else if(correctBDate.isBefore(retirementDate)) {
                    validate = 4;
                }else{
                    validate = 0;
                    this.birthDate = birthDate;
                }
            }else if(!isDateFormatValid(tempDate)) {
                    validate = 2;
            }
        }
    }

    public String getfName() {
            return fName;
    }

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
* 					PART 2: Q3 - VALIDATE FIRST NAME
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    public int getNameValid() {
        return nameValid;
    }

    public void setfName(String fName) throws InputException, SQLInjectionException{
        //throw exception if fname is empty
        if (fName.equals("") || fName.equals(" ")) {
            nameValid = 1;
            throw new InputException("Employee first name cannot be empty. Please enter a first name ");
        }else{

            if (fName.matches("[0-9]+")) {
            //throw exception if fname is only digits - using regular expression .matches method
                nameValid = 2;
                throw new InputException("First Name cannot contain only digits! Please enter a valid first name ");

            }else if(fName.contains("=") || fName.contains ("/") || fName.contains("+") || fName.contains("*")){
                //throw exception if fname contains any illegal characters
                nameValid = 3;
                throw new InputException("Illegal characters used. Please enter a valid first name : ");

            }else if(fName.toLowerCase().contains(" or ") || fName.toLowerCase().contains (" and ") || fName.toLowerCase().contains (" not ")){
                //throw exception if fname contains or, and, not
                nameValid = 3;
                throw new InputException("Illegal characters used. Please enter a valid first name : ");

            }else if(fName.toLowerCase().contains(" update ") || fName.toLowerCase().contains(" select ") || fName.toLowerCase().contains(" insert ") ||
                    fName.toLowerCase().contains(" create ") || fName.toLowerCase().contains(" drop ") || fName.toLowerCase().contains(" alter ") ||
                    fName.toLowerCase().contains(" where ") || fName.toLowerCase().contains(" delete ")){
                //throw SQLInjectionException if any of the above sequence of characters are used.
                nameValid = 3;
                throw new SQLInjectionException("Illegal characters or sequence of characters used. This program will now exit. ");
            }else{
                //otherwise set the firstnamd and return nameValid = 0;
                nameValid = 0;
                this.fName = fName;
            }
        }
    }

    public String getlName() {
            return lName;
    }

    public void setlName(String lName) throws InputException {
        //throw exception if lName is empty
        if (lName.equals("") || lName.equals(" ")) {
            throw new InputException("Employee last name cannot be empty. Please enter a last name ");
        }else if (lName.matches("[0-9]+")) {
            //throw exception if lName is only digits - using regular expression .matches method
            throw new InputException("Last Name cannot contain only digits! Please enter a valid last name ");
        }else
            this.lName = lName;
    }

    public String getGender() {
            return gender;
    }

    public void setGender(String gender) {
        //set gender as M or F.
        //if "male" entered convert to M
        //if "female" entered convert to F
        if(gender.equalsIgnoreCase("male")) {
            this.gender = "M";
        }else if (gender.equalsIgnoreCase("female")) {
            this.gender = "F";
        }else if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) {
            this.gender = gender;
        }else{
        }
    }

    public String getHireDate() {
        return hireDate;
    }

    public int getHireValidate() {
        return hireValidate;
    }

    public void setHireDate(String hireDate) {
        if(hireDate == null || hireDate.length() == 0) {
            //update validation variable to 1
            hireValidate = 1;
        }else {
            this.tempHire = hireDate;
            if(tempHire.contains("/")) {
                tempHire = tempHire.replace("/", "-");
            }
            if(!isDateFormatValid(tempHire)) {
                hireValidate = 2;
            }else if(isDateFormatValid(tempHire)){
                hireValidate = 0;
                this.hireDate = hireDate;
            }
        }
    }

    @Override
    public String toString() {
            return "Employee [empNo = " + empNo + ", birthDate = " + birthDate +
                            ", firstName = " + fName + ", lastName = " + lName + ", gender = " + gender + ", hireDate = " + hireDate + "]";
    }

    @Override
    public int compareTo(Object obj) {
            Employee emp = (Employee)obj;
            return fName.compareToIgnoreCase(emp.getfName());
    }




}





