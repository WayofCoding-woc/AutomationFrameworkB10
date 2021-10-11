package com.woc.json.test;

import com.google.gson.Gson;
import com.woc.model.Employee;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeDataJSONTest {

    @Test
    public void convertJavaObjectToJson() {
        Employee employeeA = getEmployee();

        System.out.println("java object data = " + employeeA);

        System.out.println("_____________________");

        Gson gson = new Gson();
        String jsonData = gson.toJson(employeeA);
        System.out.println("jsonData = " + jsonData);

        Assert.assertNotNull(jsonData, "converted json data cannot be null");
    }

    @Test
    public void convertJsonToJavaObject() {
        String employeeData = "{\"id\":1002,\"name\":\"Chetan\",\"email\":\"chetan@gmail.com\",\"age\":21,\"dob\":\"Jan 1, 2000, 12:00:00 AM\",\"mobile\":8957451235,\"salary\":39000.0}";
        Gson gson = new Gson();
        Employee employee = gson.fromJson(employeeData, Employee.class);
        Assert.assertNotNull(employee, "converted java object cannot be null");
        Assert.assertEquals(employee.getEmail(), "chetan@gmail.com", "Unable to invoke getEmail() method on converted java object");
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(1001);
        employee.setName("A");
        employee.setEmail("a@gmail.com");
        employee.setAge(21);
        employee.setMobile(8957451230l);
        employee.setSalary(35000.00);
        employee.setDob(getDate("01-01-2000"));
        return employee;
    }

    private static Date getDate(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
