package za.ac.cput.MotoRental.api.integrationtests;


import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.MotoRental.domain.SalesPerson;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by student on 2015/09/15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
//@WebAppConfiguration
//@IntegrationTest({"server.port=8080"})
public class SalespersonAPITest {

    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate template;

    //@Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

   // @Test
    public void testRead() throws Exception {
        ResponseEntity<String> response =template.getForEntity(BASE_URL+"api/salesperson/readall", String.class);

        System.out.println("The response is "+response.getBody());


    }

    public static final String REST_SERVICE_URI = "http://localhost:8080/api";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllSalespersons(){
        System.out.println("Testing listAllSubjects API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> SalespersonsMap = restTemplate.getForObject(REST_SERVICE_URI+"/salespersons/", List.class);

        if(SalespersonsMap!=null){
            for(LinkedHashMap<String, Object> map : SalespersonsMap){
                System.out.println("Salesperson : id="+map.get("id")+", Name="+map.get("firstName")+", Surname="+map.get("lastName")+", Hours="+map.get("hours"));;
            }
        }else{
            System.out.println("No Salesperson exist----------");
        }
    }

    /* GET */
    private static void getSubject(){
        System.out.println("Testing getSubject API----------");
        RestTemplate restTemplate = new RestTemplate();
        SalesPerson Salesperson = restTemplate.getForObject(REST_SERVICE_URI+"/salesperson/1", SalesPerson.class);
        System.out.println(Salesperson);
    }

    /* POST */
    private static void createSalesperson() {
        System.out.println("Testing create Subject API----------");
        RestTemplate restTemplate = new RestTemplate();
        SalesPerson Salesperson = new SalesPerson.Builder("Jabaar").firstName("Riyaaz").build();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/salesperson/create/", Salesperson, SalesPerson.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateSalesperson() {
        System.out.println("Testing update SalesPerson API----------");
        RestTemplate restTemplate = new RestTemplate();
        SalesPerson Salesperson  =  new SalesPerson.Builder("Jabaar").firstName("Fiazel").build();
        restTemplate.put(REST_SERVICE_URI+"/salesperson/update/1", Salesperson);
        System.out.println(Salesperson);
    }

    /* DELETE */
    private static void deleteSalesperson() {
        System.out.println("Testing delete Salesperson API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/salesperson/delete/3");
    }


    /* DELETE */
    private static void deleteAllSalesPersons() {
        System.out.println("Testing all delete Salespersons API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/salesperson/");
    }

}