package edu.pdx.cs410J.deep;


/**
 * Check input options are valid options or not  <code>OptionValidity</code>
 * @author Deep
 */
public class OptionValidity {

    public boolean validInvalidOption(String[] args)
    {
        if(args[0] == "-README")
        {
            return true;
        }else if(args[0] == "-print"){
            return true;
        }else if(args[0] == "-textFile")
        {
            return true;
        }
        return false;
    }

}
