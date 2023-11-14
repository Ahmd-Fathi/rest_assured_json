import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class HandleComplexJson {
    public static void main(String[] args) {

     //   Print No of courses returned by API
        JsonPath js=new JsonPath(PayLoad.complexJson());
        int CountNumOfCourses =js.getInt("courses.size()");

        System.out.println(CountNumOfCourses);

//.Print Purchase Amount


int amount =js.getInt("dashboard.purchaseAmount");

        System.out.println(amount);

js.getString("courses[0].title");
//. Print All course titles and their respective Prices

for (int i=0;i<CountNumOfCourses;i++)
{
   String courseTilts= js.getString("courses["+i+"].title");
    System.out.println(courseTilts);
    int coursPrice= js.getInt("courses["+i+"].price");
    System.out.println(coursPrice);

}
    }
}
