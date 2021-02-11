package eu.itswc.exception;

import java.util.HashMap;
import java.util.Map;

public class InvalidIDException extends RuntimeException {

    private static Map<ConversionFailedEnum, String> conversionFailedMap = new HashMap<ConversionFailedEnum, String>(){{
        put(ConversionFailedEnum.CAN_NOT_CONVERT_STRING_TO_LONG, "Invalid id. The id gotten is: %s, " +
                "which cannot be converted to type Long (probably not an integer, but might be too large to too small)");
    }};
    
    private static String elaborateReason(ConversionFailedEnum reasonEnum, String invalidId){
        return String.format(conversionFailedMap.get(reasonEnum), invalidId);
    }
    
    public InvalidIDException(String invalidId, ConversionFailedEnum e){
        super(elaborateReason(e, invalidId));
    }
}
