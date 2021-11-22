/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Alexander Zommer
 */


package baseline;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;


public class fxmlController implements Initializable {

    ItemManager itemMan = new ItemManager();
    final String  incorrectValue = "Incorrect Value Format";
    final String valueError = "Value Input Error";

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
                fxmlController.infoBox(incorrectValue,valueError);
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
            fxmlController.infoBox(incorrectValue,valueError);
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
    void loadAction(ActionEvent event) throws IOException {
        JButton load = new JButton();
        //new filechooser()
        JFileChooser fc = new JFileChooser();
        //set current directory for file chooser
        fc.setCurrentDirectory(new java.io.File(""));
        fc.setDialogTitle("Save List");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("TAB SEPARATED VALUES (TSV)", "txt"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("HTML", "html"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("JSON", "json"));

        // if filechooser.approveoption successful save
        if(fc.showOpenDialog(load)==JFileChooser.APPROVE_OPTION)
        {
            System.out.println("Successfully Loaded");
        }
        //String path =getSelectedfile.getpath
        String path = fc.getSelectedFile().getPath();
        //call saveList(path)
        if(path.endsWith("txt")) {
            itemMan.loadListTSV(path);
        }
        else if(path.endsWith("html")) {
            itemMan.loadListHtml(path);
        }
        else if(path.endsWith("json")) {
            itemMan.loadListJson(path);
        }
        else
            fxmlController.infoBox("Incorrect File Type","Save Error");


    }

    @FXML
    void saveAction(ActionEvent event) throws IOException {
        JButton save = new JButton();
        //new filechooser()
        JFileChooser fc = new JFileChooser();
        //set current directory for file chooser
        fc.setCurrentDirectory(new java.io.File(" "));
        fc.setDialogTitle("Save List");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("TAB SEPARATED VALUES (TSV)", "txt"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("HTML", "html"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("JSON", "json"));

        // if filechooser.approveoption successful save
        if(fc.showSaveDialog(save)==JFileChooser.APPROVE_OPTION)
        {
            System.out.println("Successfully Saved");
        }
        //String path =getSelectedfile.getpath
        String path = fc.getSelectedFile().getPath();
        //call saveList(path)
        if(path.endsWith("txt")) {
            itemMan.saveListTSV(path);
        }
        else if(path.endsWith("html")) {
            itemMan.saveListHtml(path);
        }
        else if(path.endsWith("json")) {
            itemMan.saveListJson(path);
        }
        else
            fxmlController.infoBox("Incorrect File Type","Save Error");


    }

    @FXML
    void searchAction(ActionEvent event) {
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> itemMan.filterList.setPredicate(item -> {
            if (newValue == null || newValue.isEmpty())
                return true;

            String lowercaseFilter = newValue.toLowerCase();
            if (item.getSerialNum().toLowerCase().indexOf(lowercaseFilter) > -1)
                return true;
            else if(item.getName().toLowerCase().indexOf(lowercaseFilter) > -1)
                return true;
            else
                return false;
        }));
        SortedList<item> sortedList = new SortedList<>(itemMan.filterList);
        sortedList.comparatorProperty().bind(dataTable.comparatorProperty());

        dataTable.setItems(itemMan.filterList);
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
                fxmlController.infoBox(incorrectValue,valueError);
                dataTable.refresh();
            }

            dataTable.refresh();
        });

        dataTable.setEditable(true);
    }


}
