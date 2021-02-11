package modelTest;

// import eu.itswc.model.Coordinates;
import eu.itswc.model.ThankYouToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing the ThankYouToken model")
public class TyTokenTest {


    @Test
    @DisplayName("Testing the initialization of token objects")
    public void addTyToken(){
        String tokenId = "test_124";
        ThankYouToken tyToken = new ThankYouToken(tokenId);
        Assertions.assertEquals(tyToken.getId(), tokenId,
                "A TY Token should be creatable with giving it a String id.");
    }

    @Test
    @DisplayName("Testing the setters, and getters")
    public void updateTyToken(){

        ThankYouToken test123_Token = new ThankYouToken("test_Token_123");

        String updatedId = "test_125";
        String updatedDesc = "test_description updated";
        String updatedUserId = "test_User_001";
        // Coordinates updatedLoc = new Coordinates(40,50);

        Assertions.assertNotEquals(test123_Token.getId(), updatedId, "wrong test setup #1");
        Assertions.assertNotEquals(test123_Token.getInitialDescription(), updatedDesc, "wrong test setup #2");
        Assertions.assertNotEquals(test123_Token.getIntroductoryUserId(), updatedUserId, "wrong test setup #3");
        // Assertions.assertNotEquals(test123_Token.getInitialLocationId(), updatedLoc, "wrong test setup #4");

        test123_Token.setInitialDescription(updatedDesc);
        // test123_Token.setInitialLocationId(updatedLoc.getId());
        test123_Token.setIntroductoryUserId(updatedUserId);

        Assertions.assertEquals(test123_Token.getInitialDescription(), updatedDesc,
                "There is a problem with the description setter or getter.");
        Assertions.assertEquals(test123_Token.getIntroductoryUserId(), updatedUserId,
                "There is a problem with the userId setter or getter.");
        // Assertions.assertEquals(test123_Token.getInitialLocationId(), updatedLoc.getId(),
        //        "There is a problem with the locationId setter or getter.");

        // Assertions.assertEquals(test123_Token.getInitialLocationId(), updatedLoc.getId(),
        //        "There is a problem with the locationId setter or getter.");

        // NOTE: Unlike the others TokenId isn't updatable, but that only takes effect on the persistence layer.
    }


}
