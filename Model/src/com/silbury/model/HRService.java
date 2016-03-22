//Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
package com.silbury.model;


import com.silbury.model.vo.DepartVOImpl;
import com.silbury.model.vo.EmployeeVOImpl;

import com.silbury.model.vo.EmployeeVORowImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.client.Configuration;


public class HRService {
    private String empFilter = "";
    private String deptFilter = "";
    protected List s_employees = new ArrayList();
    protected List s_departments = new ArrayList();
    private List filtered_employees = new ArrayList();
    private List dash_employees = new ArrayList();
    private List filtered_departments = new ArrayList();
    private Dashboard dash = null;
    private EmployeeVOImpl empVo = null;
    private DepartVOImpl depvo = null;
    Map sessionScope = null;
    ApplicationModule appModule =Configuration.createRootApplicationModule("com.silbury.model.am.iDashBoardAm", "iDashBoardAmLocal");
    
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private static ADFLogger _logger = ADFLogger.createADFLogger(HRService.class);

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    @SuppressWarnings("unchecked")
    public HRService() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int empId =0; String firstName= null; String lastName= null; String title= null; int deptId= 0; String deptName= null;
        String phone= null; String mobile= null; String email= null; String linkedIn= null; String twitter= null; String facebook= null;
        String google= null; String address= null; String city= null; String state= null; String country= null; String postal= null; int rating=0;
        int potential=0; int compRatio=0; double salary=0.0; double bonus=0.0; double commission=0.0; int mgrId=0;String dateInString = null;
        Date hireDate=null; String mgrFirstName= null; String mgrLastName= null;
        
        int deptId1 =0; String deptName1=null; String deptAddress=null; String deptCity=null; String deptState=null; String deptCountry=null;
                              String deptPostal=null; int deptMgrId = 0; String deptMgrFirst=null; String deptMgrLast=null; String latCoord=null;
                              String longCoord=null; String color =null;
        int count = 0;
        int loopCount =0;
        
        try {
            if(loopCount == 0){
            if(empVo == null){
                empVo = (EmployeeVOImpl)appModule.findViewObject("EmployeeVO");
                depvo = (DepartVOImpl)appModule.findViewObject("DepartVO");
            while(empVo.hasNext()){
                EmployeeVORowImpl empy = (EmployeeVORowImpl)empVo.next();
                if(empy.getAttribute("EmployeeId")!=null){
                   empId =  Integer.parseInt(empy.getAttribute("EmployeeId").toString());
                  
                        
                        }
                if(empy.getAttribute("FirstName")!=null){
                    firstName = empy.getAttribute("FirstName").toString();
                }
                if(empy.getAttribute("LastName")!=null){
                   lastName = empy.getAttribute("LastName").toString();
                }
                if(empy.getAttribute("JobTitle")!=null){
                   title = empy.getAttribute("JobTitle").toString();
                }
                if(empy.getAttribute("DepartmentId")!=null){
                   deptId = Integer.parseInt(empy.getAttribute("DepartmentId").toString());
                }
                if(empy.getAttribute("DepartmentName")!=null){
                   deptName = empy.getAttribute("DepartmentName").toString();
                }
                if(empy.getAttribute("PhoneNumber")!=null){
                   phone =  empy.getAttribute("PhoneNumber").toString();
                }
                if(empy.getAttribute("PhoneNumber1")!=null){
                   mobile = empy.getAttribute("PhoneNumber1").toString();
                }
                if(empy.getAttribute("Email")!=null){
                   email = empy.getAttribute("Email").toString();
                }
                if(empy.getAttribute("Linkedin")!=null){
                    linkedIn =  empy.getAttribute("Linkedin").toString();
                }
                if(empy.getAttribute("Twitter")!=null){
                   twitter =  empy.getAttribute("Twitter").toString();
                }
                if(empy.getAttribute("Facebook")!=null){
                   facebook=  empy.getAttribute("Facebook").toString();
                }
                if(empy.getAttribute("Google")!=null){
                   google = empy.getAttribute("Google").toString();
                }
                if(empy.getAttribute("StreetAddress")!=null){
                    address =  empy.getAttribute("StreetAddress").toString();
                }
                if(empy.getAttribute("City")!=null){
                   city =  empy.getAttribute("City").toString();
                }                
                if(empy.getAttribute("StateProvince")!=null){
                   state = empy.getAttribute("StateProvince").toString();
                }             
                if(empy.getAttribute("CountryName")!=null){
                   country = empy.getAttribute("CountryName").toString();
                }
                if(empy.getAttribute("PostalCode")!=null){
                   postal = empy.getAttribute("PostalCode").toString();
                }
                if(empy.getAttribute("Rating")!=null){
                   rating = Integer.parseInt(empy.getAttribute("Rating").toString());
                }
                if(empy.getAttribute("Potential")!=null){
                  potential  = Integer.parseInt(empy.getAttribute("RatiPotentialng").toString());
                }
                if(empy.getAttribute("Compratio")!=null){
                  compRatio  = Integer.parseInt(empy.getAttribute("Compratio").toString());
                }
                if(empy.getAttribute("Salary")!=null){
                 salary = Double.parseDouble(empy.getAttribute("Salary").toString());
                }
                if(empy.getAttribute("Bouns")!=null){
                  bonus  = Double.parseDouble(empy.getAttribute("Bouns").toString());
                }
                if(empy.getAttribute("CommissionPct")!=null){
                  commission  = Double.parseDouble(empy.getAttribute("CommissionPct").toString());
                }
                if(empy.getAttribute("ManagerId")!=null){
                  mgrId  = Integer.parseInt(empy.getAttribute("ManagerId").toString());
                }
                if(empy.getAttribute("HireDate")!=null){
                    
                    dateInString = empy.getAttribute("HireDate").toString();
                    Date date = sdf1.parse(dateInString);
                      hireDate = sdf.parse(sdf.format(date));


                    }
                if(empy.getAttribute("MgrFirstNm")!=null){
                   mgrFirstName = empy.getAttribute("MgrFirstNm").toString();
                }
                if(empy.getAttribute("MgrLastNm")!=null){
                   mgrLastName = empy.getAttribute("MgrLastNm").toString();
                }
                    s_employees.add(new Employee(empId,  firstName,  lastName,  title,  deptId,  deptName,
                             phone,  mobile,  email,  linkedIn,  twitter,  facebook,
                             google,  address,  city,  state,  country,  postal,  rating,
                             potential,  compRatio,  salary,  bonus,  commission,  mgrId,
                             hireDate,  mgrFirstName,  mgrLastName));
            }
                // Now that all employees are created, create all the child collections
                count = s_employees.size();
                for (int x = 0; x < count; x++) {
                    Employee e = (Employee) s_employees.get(x);
                    e.createChildren(this);
                }

                // Now that all child collections are created, calculate the group info for all employees
                for (int x = 0; x < count; x++) {
                    Employee e = (Employee) s_employees.get(x);
                    e.createGroupInfo();
                }
            }
           
         

            while(depvo.hasNext()){
                    Row depty = depvo.next();
                    if(depty.getAttribute("DepartmentId")!=null){
                       deptId1 = Integer.parseInt(depty.getAttribute("DepartmentId").toString());
                }
                if(depty.getAttribute("DepartmentName")!=null){
                       deptName1 = depty.getAttribute("DepartmentName").toString();
                }
                if(depty.getAttribute("StreetAddress")!=null){
                deptAddress = depty.getAttribute("StreetAddress").toString();
                }
                if(depty.getAttribute("City")!=null){
                deptCity = depty.getAttribute("City").toString();
                }
                if(depty.getAttribute("StateProvince")!=null){
                deptState = depty.getAttribute("StateProvince").toString();
                }
                if(depty.getAttribute("CountryName")!=null){
                       deptCountry = depty.getAttribute("CountryName").toString();
                }
                if(depty.getAttribute("PostalCode")!=null){
                deptPostal = depty.getAttribute("PostalCode").toString();
                }
                if(depty.getAttribute("ManagerId")!=null){
                deptMgrId = Integer.parseInt(depty.getAttribute("ManagerId").toString());
                }
                if(depty.getAttribute("FirstName")!=null){
                deptMgrFirst = depty.getAttribute("FirstName").toString();
                }
                if(depty.getAttribute("LastName")!=null){
                deptMgrLast = depty.getAttribute("LastName").toString();
                }
                if(depty.getAttribute("Latcoord")!=null){
                latCoord = depty.getAttribute("Latcoord").toString();
                }
                if(depty.getAttribute("Longcoord")!=null){
                longCoord = depty.getAttribute("Longcoord").toString();
                }
                if(depty.getAttribute("Color")!=null){
                color = depty.getAttribute("Color").toString();
                }


                           s_departments.add(new Department(deptId1 ,deptName1,deptAddress,deptCity,deptState,deptCountry,
                                                            deptPostal, deptMgrId ,deptMgrFirst, deptMgrLast,latCoord,
                                                            longCoord,color));
            }

           

            count = s_departments.size();
            for (int x = 0; x < count; x++) {
                Department d = (Department) s_departments.get(x);
                d.createChildren(this);
            }
            dashEmployees();
            filterEmployees();
            filterDepartments();
            loopCount = loopCount +1;
            dash = new Dashboard(this);
          
            }

        } catch (ParseException e) {
            _logger.severe(e.getMessage());
        }
    }


    public void setEmpFilter(String empFilter) {
        if (empFilter == null) {
            empFilter = "";
        }

        String oldEmpFilter = this.empFilter;
        this.empFilter = empFilter;
        propertyChangeSupport.firePropertyChange("empFilter", oldEmpFilter, empFilter);
    }

    public String getEmpFilter() {
        return empFilter;
    }

    public void setDeptFilter(String deptFilter) {
        if (deptFilter == null) {
            deptFilter = "";
        }

        String oldDeptFilter = this.deptFilter;
        this.deptFilter = deptFilter;
        propertyChangeSupport.firePropertyChange("deptFilter", oldDeptFilter, deptFilter);
    }

    public String getDeptFilter() {
        return deptFilter;
    }

    @SuppressWarnings("unchecked")
    public Employee[] getEmployees() {
        return (Employee[]) filtered_employees.toArray(new Employee[filtered_employees.size()]);
    }
    @SuppressWarnings("unchecked")
    public Employee[] getDashBoardEmployees() {
        int num  = this.getLogedInEmp(146);//Todo
        if(sessionScope ==null){
                 sessionScope = ADFContext.getCurrent().getSessionScope();
                 sessionScope.put("empId", 146);//Todo
             }
        return (Employee[]) dash_employees.toArray(new Employee[dash_employees.size()]);
    }

    @SuppressWarnings("unchecked")
    public Department[] getDepartments() {
        return (Department[]) filtered_departments.toArray(new Department[filtered_departments.size()]);
    }

    @SuppressWarnings("unchecked")
    public Employee[] getAllEmployees() {
        return (Employee[]) s_employees.toArray(new Employee[s_employees.size()]);
    }

    @SuppressWarnings("unchecked")
    public Department[] getAllDepartments() {
        return (Department[]) s_departments.toArray(new Department[s_departments.size()]);
    }

    public Dashboard getDashboard() {
        return dash;
    }

    public int getEmployeeCount() {
        return s_employees.size();
    }

    public int getDepartmentCount() {
        return s_departments.size();
    }

    @SuppressWarnings("unchecked")
    public void filterEmployees() {
        filtered_employees.clear();
        String filter = getEmpFilter().toLowerCase();

        boolean nofilter = (filter.length() == 0);
        for (int x = 0; x < s_employees.size(); x++) {
            Employee e = (Employee) s_employees.get(x);
            if (nofilter) {
                filtered_employees.add(e);
            } else {
                String first = e.getFirstName();
                String last = e.getLastName();
                if (first.toLowerCase().indexOf(filter) != -1 || last.toLowerCase().indexOf(filter) != -1) {
                    filtered_employees.add(e);
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    public void dashEmployees() {
        dash_employees.clear();
        String filter = getEmpFilter().toLowerCase();

        boolean nofilter = (filter.length() == 0);
        int x = this.getLogedInEmp(146);//Todo
       // for (int x = 0; x < 1; x++) {
            Employee e = (Employee) s_employees.get(x);
            if (nofilter) {
                dash_employees.add(e);
            } else {
                String first = e.getFirstName();
                String last = e.getLastName();
                if (first.toLowerCase().indexOf(filter) != -1 || last.toLowerCase().indexOf(filter) != -1) {
                    dash_employees.add(e);
                }
            }
       // }
    }

    @SuppressWarnings("unchecked")
    public void filterDepartments() {
        filtered_departments.clear();
        String filter = getDeptFilter().toLowerCase();

        boolean nofilter = (getDeptFilter().length() == 0);
        for (int x = 0; x < s_departments.size(); x++) {
            Department d = (Department) s_departments.get(x);
            if (nofilter) {
                filtered_departments.add(d);
            } else {
                String name = d.getDeptName();
                if (name.toLowerCase().indexOf(filter) != -1) {
                    filtered_departments.add(d);
                }
            }
        }
    }

    public void applyActiveEmpRow(int id) {
        Employee e = null;
        int count = filtered_employees.size();
        for (int x = 0; x < count; x++) {
            e = (Employee) filtered_employees.get(x);
            if (id > 0 && e.getEmpId() == id) {
                e.setActive(!(e.isActive()));
            } else {
                if (e.isActive()) {
                    e.setActive(false);
                }
            }
        }

    }
    public int getLogedInEmp(int id) {
        Employee e = null;
        int emplId = 0;
        int count = s_employees.size();
        for (int x = 0; x < count; x++) {
            e = (Employee) s_employees.get(x);
            if ( e.getEmpId() == id) {
                emplId = x;
            }
        }
        return emplId;

    }
    
}
