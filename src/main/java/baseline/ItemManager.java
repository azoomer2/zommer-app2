package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        boolean result = valueIn.equals(" ");

        //if valueIn is empty, return false;

        double doubleVal = Double.parseDouble(valueIn);
        //Value must be >=0
        if(doubleVal < 0)
            result = true;
        //if valueIn<0 result = false;

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedValue = formatter.format(doubleVal);

        return result;
    }


    void addItem(String serial, String name, String value)
    {
        boolean duplicate = false;

        for(item i: list )
        {
            if (i.getSerialNum().equals(serial)) {
                duplicate = true;
                break;
            }
        }

        if(!duplicate)
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
    void saveListTSV(String path) throws IOException {
        //new filewriter
        FileWriter writer;
        writer = new FileWriter(path);
        //save to proper file format based on selection
        FileWriter finalWriter = writer;

        list.forEach(item->{
            try{
                finalWriter.write(item.getSerialNum()+"\t");
                finalWriter.write(item.getName()+"\t");
                finalWriter.write(item.getValue()+"\t");
                finalWriter.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
           finally {
                try {
                    finalWriter.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    void saveListHtml(String path) {
        //Future Implementation

    }
    void saveListJson(String path)
    {
        //new filewriter
        //save to proper file format based on selection
    }



    public void loadListTSV(String path) throws IOException {
        clearList();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\n");

                for (String str : values) {
                    ArrayList<String> tempList = new ArrayList<>(Arrays.asList(str.split("\t")));
                    addItem(tempList.get(0), tempList.get(1), tempList.get(2));
                }
            }
        }catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
    }

    public void loadListHtml(String path) {
        //Future Implementation
    }

    public void loadListJson(String path) {
        //Future Implementation
    }
}
