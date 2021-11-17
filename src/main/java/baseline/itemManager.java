package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class itemManager {

    //create empty observable list
    ObservableList<item> list = FXCollections.observableArrayList();
    //wrap observable list in filtered list
    FilteredList<item> filteredList = new FilteredList<>(list, p->true);

    public ObservableList<item> getList()
    {
        //return list
        return this.filteredList;
    }

    Boolean serialValidation(String serialIn)
    {
        //Serial Must have A-xxx-xxx-xxx format
        //A is a letter, x's are letter or digit
        //DOES NOT CHECK FOR DUPLICATE VALUES! (Done in addItem())
        //if serialIn is empty, return false;
        Pattern serialPattern = Pattern.compile("[a-z]-[a-z0-9][a-z0-9][a-z0-9]-[a-z0-9][a-z0-9][a-z0-9]-[a-z0-9][a-z0-9][a-z0-9]",Pattern.CASE_INSENSITIVE);
        Matcher matcher = serialPattern.matcher(serialIn);

        return matcher.matches();
    }

    Boolean nameValidation(String nameIn)
    {
        Boolean result = false;
        //only rules for name is between 2 and 256 chars

        //if nameIn is empty, return false;
        //if nameIn.length() < 2 || nameIn.length > 256
            //return 0
        //else return 1;

        return result;
    }

    Boolean valueValidation(String valueIn)
    {
        Boolean result = false;

        //if valueIn is empty, return false;
        //Value must be >=0

        //if valueIn<0 result = false;
        //if valueIn>0 && valueIn is [0-9] result = true;

        

        return result;
    }


    void addItem(String serial, String name, String value)
    {


    }
}
