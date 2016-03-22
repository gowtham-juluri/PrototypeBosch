package com.silbury.model;

import com.silbury.model.am.iDashBoardAmImpl;

import com.silbury.model.vo.EmployeeVOImpl;

import com.silbury.model.vo.EmployeeVORowImpl;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.client.Configuration;
import oracle.jbo.client.remote.ApplicationModuleImpl;

public class TestBean {
    public TestBean() {
        super();
    }

    public static void main(String[] args) {
        
        TestBean testBean = new TestBean();
        try{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
        ApplicationModule appModule =
            Configuration.createRootApplicationModule("com.silbury.model.am.iDashBoardAm", "iDashBoardAmLocal");
        EmployeeVOImpl deptVo = (EmployeeVOImpl) appModule.findViewObject("EmployeeVO");
        Row[] emps = deptVo.getAllRowsInRange();
        for (Row empy : emps) {
            while (deptVo.hasNext()) {
                EmployeeVORowImpl deptRowVo = (EmployeeVORowImpl) deptVo.next();
                System.out.println("Department Name: " + deptRowVo.getAttribute("HireDate"));
                String dateInString = deptRowVo.getAttribute("HireDate").toString();
                Date date = sdf.parse(dateInString);
               dateInString = sdf1.format(date).toString();
                System.out.println(dateInString);
                date = sdf2.parse(dateInString);
                System.out.println(date);
                
            }
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
        
}
