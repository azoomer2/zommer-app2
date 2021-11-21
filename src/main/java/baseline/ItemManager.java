package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemManager {

    //create empty observable list
    ObservableList<item> list = FXCollections.observableArrayList();

    FilteredList<item> filterList = new FilteredList<>(list);

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public ObservableList<item> getList()
    {
        //return list
        return this.list;
    }

    public FilteredList<item> getFilterList()
    {
        return this.filterList;
    }

    Boolean serialValidation(String serialIn)
    {
        //Serial Must have A-xxx-xxx-xxx format
        //A is a letter, x's are letter or digit
        //DOES NOT CHECK FOR DUPLICATE VALUES! (Done in addItem())
        //if serialIn is empty, return false;
        Pattern serialPattern = Pattern.compile("[a-z]-[a-z0-9][a-z0-9][a-z0-9]-[a-z0-9][a-z0-9][a-z0-9]-[a-z0-9][a-z0-9][a-z0-9]",Pattern.CASE_INSENSITIVE);
        Matcher matcher = serialPattern.matcher(serialIn);

        return !matcher.matches();
    }

    Boolean nameValidation(String nameIn)
    {
        boolean result;
        //only rules for name is between 2 and 256 chars

        //if nameIn is empty, return false;
        if(nameIn.equals(""))
            result = true;

        else result = nameIn.length() < 2 || nameIn.length() > 256;

        return result;
    }

    Boolean valueValidation(String valueIn)
    {
        boolean result = false;

        //if valueIn is empty, return false;
        if(valueIn.equals(" "))
            result = true;


        System.out.println(valueIn);
        double doubleVal = Double.parseDouble(valueIn);
        //Value must be >=0
        if(doubleVal < 0)
            result = true;
        //if valueIn<0 result = false;

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedValue = formatter.format(doubleVal);

        System.out.println(formattedValue);
        return result;
    }


    void addItem(String serial, String name, String value)
    {
        Boolean duplicate = false;

        for(item i: list )
        {
            if(i.getSerialNum().equals(serial))
                duplicate = true;
        }

        if(duplicate == false)
            list.add(new item(serial,name,value));
        else
            fxmlController.infoBox("Duplicate Serial Numbers Are Not Allowed","Duplication Error");


    }

    void clearList()
    {
        list.clear();
    }

    void deleteItem(item index)
    {
        list.remove(index);
    }
    void saveList(String path)
    {
        //new filewriter
        //save to proper file format based on selection
    }
    void loadList(String path)
    {
        //new buffered reader
        //read each line depending on file type
        //overwrite current list with new list
    }

}
