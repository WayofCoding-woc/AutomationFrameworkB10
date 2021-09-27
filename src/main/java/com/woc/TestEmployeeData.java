package com.woc;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestEmployeeData {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.setId(1001);
        e1.setName("A");
        e1.setEmail("a@gmail.com");
        e1.setAge(21);
        e1.setMobile(8957451230l);
        e1.setSalary(35000.00);
        e1.setDob(getDate("01-01-2000"));

        System.out.println("java object data = " + e1);

        System.out.println("_____________________");

        Gson gson = new Gson();
        String jsonData = gson.toJson(e1);
        System.out.println("jsonData = " + jsonData);

        System.out.println("++++++++++++++++++++++++++++++++++");

        String chetanData = "{\"id\":1002,\"name\":\"Chetan\",\"email\":\"chetan@gmail.com\",\"age\":21,\"dob\":\"Jan 1, 2000, 12:00:00 AM\",\"mobile\":8957451235,\"salary\":39000.0}";

        Employee employee = gson.fromJson(chetanData, Employee.class);
        System.out.println(employee);
        System.out.println(employee.getEmail());

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
