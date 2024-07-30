package tugasakhir.library;

/**
 * @author Putri Mele
 * on 30/07/2024
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import tugasakhir.library.model.request.usermember.UserMemberRq;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJsonFormat {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        mapper.setDateFormat(dateFormat);

        UserMemberRq user = new UserMemberRq();
        user.setName("Putri");
        user.setUsername("putri2201");
        user.setPassword("yourPassword");
        user.setPhoneNumber("081122xxx");
        user.setPlaceOfBirth("Ciamis");
        user.setDateOfBirth(String.valueOf(dateFormat.parse("22-04-2000")));
        user.setGender("Perempuan");
        user.setAddress("Banjarsari, RT 08 RW 03");

        // Serialize to JSON
        String jsonString = mapper.writeValueAsString(user);
        System.out.println("Serialized JSON: " + jsonString);

        // Deserialize from JSON
        UserMemberRq deserializedUser = mapper.readValue(jsonString, UserMemberRq.class);
        System.out.println("Deserialized Date of Birth: " + deserializedUser.getDateOfBirth());
    }
}
