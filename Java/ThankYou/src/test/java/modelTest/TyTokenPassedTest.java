package modelTest;

import eu.itswc.model.ThankYouTokenPassed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing the TyTokenPassed model")
public class TyTokenPassedTest {



    @Test
    @DisplayName("Just making sure there is an empty constructor")
    public void assertThereIsEmptyConstructor() {
        Assertions.assertDoesNotThrow(() -> new ThankYouTokenPassed(),
                "there should be and empty constructor");
    }

    @Test
    @DisplayName("Testing setters and getters")
    public void testingSettersAndGetters() {
        ThankYouTokenPassed tytPassed = new ThankYouTokenPassed();

        Assertions.assertDoesNotThrow(() -> tytPassed.getId(),
                "id should be generated, therefore available before setting");

        String td = "testDescription";
        tytPassed.setDescription(td);
        Assertions.assertEquals(td, tytPassed.getDescription(),
                "there is something wrong with .setDescription or .getDescription");

        String tlid = "testLocationId";
        tytPassed.setLocationIdOfInteraction(tlid);
        Assertions.assertEquals(tlid, tytPassed.getLocationIdOfInteraction(),
                "there is something wrong with .setLocId or .getLocId" );

    }

}
