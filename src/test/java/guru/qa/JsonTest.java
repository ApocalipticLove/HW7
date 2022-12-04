package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.User;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JsonTest {
    @Test
    void jsonTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File fileJson = new File("src/test/resources/sample2.json");
        User user = mapper.readValue(fileJson, User.class);
        assertThat(user.firstName).isEqualTo("Joe");
        assertThat(user.lastName).isEqualTo("Jackson");
        assertThat(user.gender).isEqualTo("male");
        assertThat(user.age).isEqualTo(28);
        assertThat(user.address.streetAddress).isEqualTo("101");
        assertThat(user.address.city).isEqualTo("San Diego");
        assertThat(user.address.state).isEqualTo("CA");
        assertThat(user.phoneNumbers.get(0)).isEqualTo("5678568567");
        assertThat(user.phoneNumbers.get(1)).isEqualTo("7349282382");


    }
}


