/*
                                   CHECK THAT FILE EXISTS

 */
package protect;

import java.io.File;

public class CheckFile  {

    public CheckFile(){
    }

    public void CheckFileDetails(String filePath) throws FileException {
        //check if the file exists
        //throw exception if does not exist
        File currentDirOrFile = new File(filePath);
        if(!currentDirOrFile.exists()){
            throw new FileException("ERROR: File does not exist. Please check file name and try running the program again");
        } else {
           System.out.println("File accessed successfully") ;
        }
    }
}
