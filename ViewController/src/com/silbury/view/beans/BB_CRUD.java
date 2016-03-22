package com.silbury.view.beans;



import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;

import oracle.jbo.RowSetIterator;
import org.apache.myfaces.trinidad.model.RowKeySet;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.SelectionEvent;


public class BB_CRUD {
    private RichTable empDataTble;
    private RichTable empTble;
    private RichPopup editPopup;
    private RichPopup addPopup;
    private RichPopup duplicatePopup;
    private RichInputText dupliInpu;
    private RichSelectOneChoice pageSize;
    private RichPanelCollection panCol;

    public BB_CRUD() {
        super();
    }
    
    public void editPopupFetchListener(PopupFetchEvent popupFetchEvent) {
//        if (popupFetchEvent.getLaunchSourceClientId().contains("b1")) {
//            BindingContainer bindings = getBindings();
//            OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
//            operationBinding.execute();
//        }else{
//            System.out.println(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("employeeID"));
//            System.out.println(this.getEmpTble().getSelectedRowData());
//        }
    }
    
    public void editDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("Commit");
            operationBinding.execute();  
            refreshTable();
        }
//        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
//            BindingContainer bindings = getBindings();
//            OperationBinding operationBinding = bindings.getOperationBinding("Execute");
//            operationBinding.execute();
//        }
    }

    public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {
        refreshTable();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setEmpDataTble(RichTable empDataTble) {
        this.empDataTble = empDataTble;
    }

    public RichTable getEmpDataTble() {
        return empDataTble;
    }

    public void setEmpTble(RichTable empTble) {
        this.empTble = empTble;
    }

    public RichTable getEmpTble() {
        return empTble;
    }

    @SuppressWarnings("unchecked")
    public String editAction() {
        
        return null;
    }

    public void setEditPopup(RichPopup editPopup) {
        this.editPopup = editPopup;
    }

    public RichPopup getEditPopup() {
        return editPopup;
    }

    public void setDuplicatePopup(RichPopup duplicatePopup) {
        this.duplicatePopup = duplicatePopup;
    }

    public RichPopup getDuplicatePopup() {
        return duplicatePopup;
    }
    
    public void setAddPopup(RichPopup addPopup) {
        this.addPopup = addPopup;
    }

    public RichPopup getAddPopup() {
        return addPopup;
    }
    
    


    public void addAction(ActionEvent actionEvent) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        operationBinding.execute();
        
        showPopup(this.getAddPopup());
    }
    
    public void showPopup(RichPopup popupNm){
        try {
           RichPopup.PopupHints hints = new RichPopup.PopupHints();
           popupNm.show(hints);
       } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
    }
    
    public void refreshTable(){
        try {
           BindingContainer bindings = getBindings();
           OperationBinding operationBinding = bindings.getOperationBinding("Execute");
           operationBinding.execute();
           
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.getEmpTble());
       } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }


    public void DeleteDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {
            BindingContainer bindings = getBindings();
            OperationBinding DelBnd = bindings.getOperationBinding("Delete");
            OperationBinding ComBnd = bindings.getOperationBinding("Commit");
            DelBnd.execute(); 
            ComBnd.execute();  
            refreshTable();
        }
  
    }


    @SuppressWarnings("unchecked")
    public void editActionListner(ActionEvent actionEvent) {
        try {
           // System.out.println(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("employeeID"));
            String empID = AdfFacesContext.getCurrentInstance().getPageFlowScope().get("employeeID").toString();
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("executeValue");
            operationBinding.getParamsMap().put("empid", empID);
            operationBinding.execute();
             

            showPopup(this.getEditPopup());


           
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        } 
    }
    

    @SuppressWarnings("unchecked")
    public void DuplicateActListnr(ActionEvent actionEvent) {

//        try
//        {
//            RowKeySet selectedEmps = this.getEmpTble().getSelectedRowKeys();
//            Iterator selectedEmpIter = selectedEmps.iterator();
//
//            DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//            DCIteratorBinding empIter = dcBindings.findIteratorBinding("EmployeesEO_VIEW1Iterator");
//
//            RowSetIterator empRSIter = empIter.getRowSetIterator();
//
//            Row currentRow = null;
//            while(selectedEmpIter.hasNext())
//            {
//
//               // System.out.println("HERE IN WHILE LOOP");
//                Key key = (Key)((List)selectedEmpIter.next()).get(0);
//            //    System.out.println("key ------------" + key);
//                currentRow = empRSIter.getRow(key);
//            }
//
//            ViewObject vo = empIter.getViewObject();
//    //            System.out.println("Result VO " + vo);
//            Row nr = vo.createRow();
//
//            nr.setAttribute("FirstName", currentRow.getAttribute("FirstName"));
//            nr.setAttribute("LastName", currentRow.getAttribute("LastName"));
//            nr.setAttribute("Email", currentRow.getAttribute("Email"));
//            nr.setAttribute("PhoneNumber", currentRow.getAttribute("PhoneNumber"));
//            nr.setAttribute("HireDate", currentRow.getAttribute("HireDate"));
//            nr.setAttribute("JobId", currentRow.getAttribute("JobId"));
//            nr.setAttribute("Salary", currentRow.getAttribute("Salary"));
//            nr.setAttribute("CommissionPct", currentRow.getAttribute("CommissionPct"));
//            nr.setAttribute("ManagerId", currentRow.getAttribute("ManagerId"));
//            nr.setAttribute("DepartmentId", currentRow.getAttribute("DepartmentId"));
//
//            vo.insertRow(nr);
//            showPopup(this.getDuplicatePopup());
//
//        } catch (Exception e) {
//            // TODO: Add catch code
//            e.printStackTrace();
//        }
    }

    
    public void duplicateDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {
            BindingContainer bindings = getBindings();
            
            System.out.println("COMMIT: "+this.getDupliInpu().getValue());
            OperationBinding operationBinding = bindings.getOperationBinding("Commit");
            operationBinding.execute();  
            refreshTable();
        }
    //        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
    //            BindingContainer bindings = getBindings();
    //            OperationBinding operationBinding = bindings.getOperationBinding("Execute");
    //            operationBinding.execute();
    //        }
    }

    public void setDupliInpu(RichInputText dupliInpu) {
        this.dupliInpu = dupliInpu;
    }

    public RichInputText getDupliInpu() {
        return dupliInpu;
    }

    public String onDeptRowCreate() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
         //access the name of the iterator the table is bound to. Its "allDepartmentsIterator"
         //in this sample
         DCIteratorBinding dciter = (DCIteratorBinding) bindings.get("DepartmentsEO22View1Iterator");
         //access the underlying RowSetIterator
         RowSetIterator rsi = dciter.getRowSetIterator();
         //get handle to the last row
         Row lastRow = rsi.last();
         //obtain the index of the last row
         int lastRowIndex = rsi.getRangeIndexOf(lastRow);
         //create a new row
         Row newRow = rsi.createRow();
         //initialize the row
         newRow.setNewRowState(Row.STATUS_INITIALIZED);
         //add row to last index + 1 so it becomes last in the range set
         rsi.insertRowAtRangeIndex(lastRowIndex +1, newRow); 
         //make row the current row so it is displayed correctly
         rsi.setCurrentRow(newRow);                          
         return null;
    }
    
    
    private RichPanelBox treeBinding;


    public void setTreeBinding(RichPanelBox treeBinding) {
        this.treeBinding = treeBinding;
    }

    public RichPanelBox getTreeBinding() {
        return treeBinding;
    }
    


    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(elContext, el, Object.class, paramTypes);

        return exp.invoke(elContext, params);
    }

    @SuppressWarnings("unchecked")
    public void selectionListner1(SelectionEvent selectionEvent) {
        invokeEL("#{bindings.RegionsView.treeModel.makeCurrent}", new Class[] { SelectionEvent.class },
                 new Object[] { selectionEvent });
        RowKeySet rowKeyset = selectionEvent.getAddedSet();
        Iterator rksIterator = rowKeyset.iterator();

        String region = "";
        String country = "";
        String location = "";
        Integer count = 0;

        while (rksIterator.hasNext()) {

            List myKey = (List) rksIterator.next();
            count = myKey.size();

            if (count == 1) {
                region = (((Key) myKey.get(myKey.size() - 1)).getRowHandle()).toString();
            } else if (count == 2) {
                country = (myKey.get(myKey.size() - 1)).toString();
                country = country.substring(country.indexOf("[") + 1, country.indexOf("]"));
                country = country.replaceAll("\\s+", "");
            } else if (count == 3) {
                location = (myKey.get(myKey.size() - 1)).toString();
                location = location.substring(location.indexOf("[") + 1, location.indexOf("]"));
                location = location.replaceAll("\\s+", "");
            }
        }
        Map pageFlowScope = ADFContext.getCurrent().getPageFlowScope();
        pageFlowScope.put("regionId", region);

//        DCBindingContainer bindingContainer =
//            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCDataControl dc = bindingContainer.findDataControl("iDashBoardAmDataControl");
//        AppModuleImpl appM = (AppModuleImpl) dc.getDataProvider();
//        appM.executeSearchVo(region.toString(), country.toString(), location.toString());
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("executeSearchVo");
        operationBinding.getParamsMap().put("region", region.toString());
        operationBinding.getParamsMap().put("country", country.toString());
        operationBinding.getParamsMap().put("location", location.toString());
        operationBinding.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(empDataTble);
    }



    public void setPageSize(RichSelectOneChoice pageSize) {
        this.pageSize = pageSize;
    }

    public RichSelectOneChoice getPageSize() {
        return pageSize;
    }

    public void changeRangeSize(ValueChangeEvent valueChangeEvent) {
        try
        {
            if(valueChangeEvent.getNewValue()!=valueChangeEvent.getOldValue()){
       //    System.out.println("HERE 1");
                if (valueChangeEvent.getNewValue()!=null) {
        //            System.out.println("HERE 2 "+ valueChangeEvent.getNewValue().toString());
                    this.getEmpTble().resetStampState();
                    this.getEmpTble().setFetchSize(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
                }
        //  System.out.println(">>>>>>>>>>>>>>>>>>>" + this.getPanCol().isInView());
           AdfFacesContext.getCurrentInstance().addPartialTarget(this.getEmpTble());
    //           AdfFacesContext.getCurrentInstance().addPartialTarget(this.getPanCol());
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void setPanCol(RichPanelCollection panCol) {
        this.panCol = panCol;
    }

    public RichPanelCollection getPanCol() {
        return panCol;
    }
    }
