package com.restapiexample.dummy.employeeinfo;


import com.restapiexample.dummy.testbase.TestBase;
import com.restapiexample.dummy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class EmployeeCurdTest extends TestBase {
    static String name = "Rachel" + TestUtils.getRandomValue();
    static String salary = "4000";
    static String age = "21";
    static int id = 25;
    static String image="string";
    static int employeeSalary = 10000;
    static  int employeeAge=22;
    static int employeeId;


    @Steps
    EmployeeSteps employeeSteps;

    @Title("Creating new employee record ")
    @Test
    public void test001() {
        ValidatableResponse response = employeeSteps.createEmployee(name, salary, age, id);
        response.log().all().statusCode(200);

        employeeId= response.extract().path("data.id");
        System.out.println(employeeId);


    }
    @Title("Getting single employee record ")
    @Test
    public void test002(){
        ValidatableResponse response =employeeSteps.getSingleEmployee(employeeId);
        response.log().all().statusCode(200);
        response.body("name",equalTo(name));

    }
    @Title("Update employee record")
    @Test
    public void test003(){
        ValidatableResponse response= employeeSteps.updateEmployee(employeeId,name,employeeSalary,employeeAge,image);
        response.log().all().statusCode(200);

    }
    @Title("Delete employee record")
    @Test
    public void test004(){
        ValidatableResponse response =employeeSteps.deleteSingleEmployee(employeeId);
        response.log().all().statusCode(200);

        ValidatableResponse response1 =employeeSteps.getSingleEmployee(employeeId);
        response1.log().all().statusCode(404);


    }
}