/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Alexander Zommer
 */


package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.swing.*;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.input.KeyEvent;

public class fxmlController implements Initializable {

    ItemManager itemMan = new ItemManager();


    @FXML
    private TableView<item> dataTable;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Menu fileButton;

    @FXML
    private Menu helpButton;

    @FXML
    private MenuItem loadButton;

    @FXML
    private TableColumn<item,String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private MenuItem readmeButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private TextField searchBox;

    @FXML
    private TableColumn<item,String> serialColumn;

    @FXML
    private TextField serialField;


    @FXML
    private TableColumn<item,String> valueColumn;

    @FXML
    private TextField valueField;

    @FXML
    private Button searchButton;


    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }




    @FXML
    void addAction(ActionEvent event) {
        //read in string from serial field
        String serialIn = serialField.getText();
        //call serialValidation()
        Boolean serialFlag = itemMan.serialValidation(serialIn);
        if(Boolean.TRUE.equals(serialFlag))
        {
            fxmlController.infoBox("Incorrect Serial Format","Serial Input Error");
        }
        //read in string from name field
        String nameIn = nameField.getText();
        //call nameValidation()
        Boolean nameFlag = itemMan.nameValidation(nameIn);
        if(Boolean.TRUE.equals(nameFlag))
        {
            fxmlController.infoBox("Incorrect Name Format","Name Input Error");
        }
        //read in string from value field
        String valueIn = valueField.getText();
        //call valueValidation()
        try{
            Boolean valueFlag = itemMan.valueValidation(valueIn);
            if(Boolean.TRUE.equals(valueFlag))
            {
                fxmlController.infoBox("Incorrect Value Format","Value Input Error");
            }

                double doubleVal = Double.parseDouble(valueIn);
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                String formattedValue = formatter.format(doubleVal);
                if(Boolean.TRUE.equals(!serialFlag && !nameFlag && !valueFlag))
                {
                    itemMan.addItem(serialIn, nameIn, formattedValue);

                    serialField.clear();
                    nameField.clear();
                    valueField.clear();
                }
        } catch (Exception e)
        {
            fxmlController.infoBox("Incorrect Value Format","Value Input Error");
        }


        //call addItem(String serial, String name, BigDecimal value)

    }

    @FXML
    void clearAction(ActionEvent event) {
        //call clearList()
        itemMan.clearList();

    }

    @FXML
    void deleteAction(ActionEvent event) {
        //item delete =getSelectedItem from table
        item delete = dataTable.getSelectionModel().getSelectedItem();
        //call itemMan delete(Item delete)
        itemMan.deleteItem(delete);
    }

    @FXML
    void fileAction(ActionEvent event) {

    }

    @FXML
    void loadAction(ActionEvent event) {
        //new filechooser()
        //set current directory for file chooser
        //String path =getSelectedfile.getpath
        //call loadFile(path)
    }

    @FXML
    void saveAction(ActionEvent event) {
        //new filechooser()
        //set current directory for file chooser
        // if filechooser.approveoption successful save
        //String path =getSelectedfile.getpath
        //call saveList(path)
    }

    @FXML
    void searchAction(ActionEvent event) {
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            itemMan.filterList.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowercaseFilter = newValue.toLowerCase();
                if (item.getSerialNum().toLowerCase().indexOf(lowercaseFilter) != 1)
                    return true;
                else if (item.getName().toLowerCase().indexOf(lowercaseFilter) != 1)
                    return true;
                else
                    return false;

            });

        });
        SortedList<item> sortedList = new SortedList<>(itemMan.filterList);
        sortedList.comparatorProperty().bind(dataTable.comparatorProperty());

        dataTable.setItems(sortedList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();

    }




    private void initTable()
    {
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        serialColumn.setSortType(TableColumn.SortType.ASCENDING);

        editableCols();

        dataTable.setItems(itemMan.getList());
    }
    private void editableCols()
    {
        serialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        serialColumn.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTempString(e.getNewValue());
            String temp = e.getTableView().getItems().get(e.getTablePosition().getRow()).getTempString();

            if(!itemMan.serialValidation(temp))
            {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setSerialNum(e.getNewValue());
            }
            else {
                fxmlController.infoBox("Incorrect Serial Format","Serial Input Error");
                dataTable.refresh();
            }
        });

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(e-> {

            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTempString(e.getNewValue());
            String temp = e.getTableView().getItems().get(e.getTablePosition().getRow()).getTempString();

            if(!itemMan.nameValidation(temp))
            {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
            }
            else {
                fxmlController.infoBox("Incorrect Name Format","Name Input Error");
                dataTable.refresh();
            }
        });

        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTempString(e.getNewValue());
            String temp = e.getTableView().getItems().get(e.getTablePosition().getRow()).getTempString();

            if(!itemMan.valueValidation(temp))
            {
                double doubleVal = Double.parseDouble(temp);
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                String formattedValue = formatter.format(doubleVal);
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setValue(formattedValue);
            }
            else {
                fxmlController.infoBox("Incorrect Value Format","Value Input Error");
                dataTable.refresh();
            }

            dataTable.refresh();
        });

        dataTable.setEditable(true);



    }


}
