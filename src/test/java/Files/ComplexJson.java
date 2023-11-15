package Files;
import io.restassured.path.json.JsonPath;

import  static io.restassured.RestAssured.*;
import  static      io.restassured.matcher.RestAssuredMatchers.*;
import  static      org.hamcrest.Matchers.*;
public class ComplexJson {
    public static void main(String[] args) {
//Print No of courses returned by API
        JsonPath js=new JsonPath(PayLoad.BodyComplexJson());

int count=js.getInt("courses.size()");
        System.out.println(count);

  //      Print Purchase Amount
     int purchaseAmount=   js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
//Print Title of the first course
       String FirstCourseTitle= js.getString("courses[0].title");
        System.out.println(FirstCourseTitle);

        // Print All course titles and their respective Prices

        for (int i=0;i<count;i++)
        {
           String courseTitles= js.getString("courses["+i+"].title");
            System.out.println(courseTitles);
              int CoursePrices= js.getInt("courses["+i+"].price");
            System.out.println(CoursePrices);

        }

///Print no of copies sold by RPA Course
        for (int i=0;i<count;i++)
        {
            String courseTitles= js.getString("courses["+i+"].title");
            if (courseTitles.equalsIgnoreCase("RPA"));
            {
                int copies=js.getInt("courses["+i+"].copies");
                System.out.println(copies);
            }

        }



    }
}
