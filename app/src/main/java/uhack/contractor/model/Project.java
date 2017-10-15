package uhack.contractor.model;

import java.util.ArrayList;

import uhack.contractor.model.ContractorTemp;

/**
 * Created by piyush on 14/10/17.
 */

public class Project {
    private String name;
    private String address;
    private int totalBudget;
    private int currentExpenses;
    private String builderID, projectID;
    private ArrayList<String> inventoryIDs,workerIDs;
    private ArrayList<ContractorTemp> contractorData;

    public Project() {

    }


    public Project(String name, String address, int totalBudget, int currentExpenses, String builderID, String projectID, ArrayList<String> inventoryIDs, ArrayList<String> workerIDs, ArrayList<ContractorTemp> contractorData) {
        this.name = name;
        this.address = address;
        this.totalBudget = totalBudget;
        this.currentExpenses = currentExpenses;
        this.builderID = builderID;
        this.projectID = projectID;
        this.inventoryIDs = inventoryIDs;
        this.workerIDs = workerIDs;
        this.contractorData = contractorData;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {

        this.projectID = projectID;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(int totalBudget) {
        this.totalBudget = totalBudget;
    }

    public int getCurrentExpenses() {
        return currentExpenses;
    }

    public void setCurrentExpenses(int currentExpenses) {
        this.currentExpenses = currentExpenses;
    }

    public String getBuilderID() {
        return builderID;
    }

    public void setBuilderID(String builderID) {
        this.builderID = builderID;
    }


    public ArrayList<String> getInventoryIDs() {
        return inventoryIDs;
    }

    public void setInventoryIDs(ArrayList<String> inventoryIDs) {
        this.inventoryIDs = inventoryIDs;
    }

    public ArrayList<String> getWorkerIDs() {
        return workerIDs;
    }

    public void setWorkerIDs(ArrayList<String> workerIDs) {
        this.workerIDs = workerIDs;
    }


    public ArrayList<ContractorTemp> getContractorData() {

        return contractorData;
    }

    public void setContractorData(ArrayList<ContractorTemp> contractorData) {
        this.contractorData = contractorData;
    }
}
