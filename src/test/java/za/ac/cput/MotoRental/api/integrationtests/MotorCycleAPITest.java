package za.ac.cput.MotoRental.api.integrationtests;

import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.MotoRental.domain.MotorCycle;

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
public class MotorCycleAPITest {

    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate template;

   // @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

   // @Test
    public void testRead() throws Exception {
        ResponseEntity<String> response =template.getForEntity(BASE_URL+"api/motorcycle/readall", String.class);

        System.out.println("The response is "+response.getBody());


    }

    public static final String REST_SERVICE_URI = "http://localhost:8080/api";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllMotorCycles(){
        System.out.println("Testing listAllMotorCycles API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> MotorCyclesMap = restTemplate.getForObject(REST_SERVICE_URI+"/motorcycles/", List.class);

        if(MotorCyclesMap!=null){
            for(LinkedHashMap<String, Object> map : MotorCyclesMap){
                System.out.println("MotorCycle : id="+map.get("id")+", Make="+map.get("Make")+", Model="+map.get("Model")+", Year="+map.get("year"));;
            }
        }else{
            System.out.println("No MotorCycle exist----------");
        }
    }

    /* GET */
    private static void getMotorCycle(){
        System.out.println("Testing getMotorCycle API----------");
        RestTemplate restTemplate = new RestTemplate();
        MotorCycle MotorCycle = restTemplate.getForObject(REST_SERVICE_URI+"/motorcycle/1", MotorCycle.class);
        System.out.println(MotorCycle);
    }

    /* POST */
    private static void createMotorCycle() {
        System.out.println("Testing create MotorCycle API----------");
        RestTemplate restTemplate = new RestTemplate();
        MotorCycle MotorCycle = new MotorCycle.Builder("100").Make("Suzuki").build();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/motorcycle/create/", MotorCycle, MotorCycle.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateMotorCycle() {
        System.out.println("Testing update MotorCycle API----------");
        RestTemplate restTemplate = new RestTemplate();
        MotorCycle MotorCycle  =  new MotorCycle.Builder("100").Make("Kawasaki").build();
        restTemplate.put(REST_SERVICE_URI+"/motorcycle/update/1", MotorCycle);
        System.out.println(MotorCycle);
    }

    /* DELETE */
    private static void deleteMotorCycle() {
        System.out.println("Testing delete MotorCycle API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/motorcycle/delete/3");
    }


    /* DELETE */
    private static void deleteAllMotorCycles() {
        System.out.println("Testing all delete MotorCycles API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/motorcycle/");
    }

}
