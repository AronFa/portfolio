package modelTest;

/*
import eu.itswc.model.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



@DisplayName("Testing the Coordinates(lat, long) model")
public class LocationTest {

    @Test
    @DisplayName("Testing the constructor.")
    public void testConstructor(){
        double lt = 70;
        double lg = 60;
        Coordinates loc = new Coordinates(lt, lg);
        assert(loc.getClass() == Coordinates.class);
        Assertions.assertEquals(loc.getClass(), Coordinates.class,
                "A pair of type 'double' variables should be an accepted way of constructing a Coordinates instance.");
    }

    @Test
    @DisplayName("Testing if illegal Latitude and Longitude values raise IllegalArgumentError pt.1 constructor")
    public void testValueValediction() {
        double lt_too_small = -100;
        double lt_ok = 30;
        double lt_too_LARGE = 120;

        double lg_too_small = -10;
        double lg_ok = 210;
        double lg_too_LARGE = 390;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Coordinates(lt_too_small, lg_too_small),
                "Invalid Lat and Long values should throw IllegalArgumentException.");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Coordinates(lt_too_small, lg_ok),
                "-90 < Lat should throw IllegalArgumentException");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Coordinates(lt_too_LARGE, lg_ok),
                "90 < Lat should throw IllegalArgumentException");

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Coordinates(lt_ok, lg_too_small),
                "0 < Lat should throw IllegalArgumentException");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Coordinates(lt_ok, lg_too_LARGE),
                "360 < Lat should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Testing if trying to set invalid values for Lat and Long values would raise the appropriate error.")
    public void testValueValediction2() {
        double lt_ok = 30;
        double lg_ok = 60;
        Coordinates loc = new Coordinates(lt_ok, lg_ok);

        Assertions.assertThrows(IllegalArgumentException.class, () -> loc.setLatitude(-90.01),
                "Setting the Lat < -90 should throw IllegalArgumentException");
        Assertions.assertThrows(IllegalArgumentException.class, () -> loc.setLatitude(90.01),
                "Setting the Lat > 90 should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Testing if the Lat and the Long can be set to a range of valid values.")
    public void testValueRange(){
        long lt_ok = 60;
        long lg_ok = 140;
        Coordinates loc = new Coordinates(lt_ok, lg_ok);

        for (int i=-90; i<90.1; i+=3){
            loc.setLatitude(i);
            Assertions.assertEquals(loc.getLatitude(), i,
                    "It could not set the Lat/Loc values to " + i + "/" + lg_ok);
        }

        loc.setLongitude(lg_ok);
        loc.setLatitude(lt_ok);
        for (int j=0; j<360.1; j+=3) {
            loc.setLongitude(j);
            Assertions.assertEquals(loc.getLongitude(), j,
                    "It could not set the Lat/Loc values to " + lt_ok + "/" + j);
        }
    }
}
 */
