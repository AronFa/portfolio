package eu.itswc.exception;

import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class WrongHttpMethodException extends RuntimeException {

    private static Map<WrongHttpMethodReasonEnum, String> messageMap = new HashMap<WrongHttpMethodReasonEnum, String>() {{
        put(WrongHttpMethodReasonEnum.POST_USED_WHEN_UPDATING_A_TOKEN_PASSED, "Wrong Http method used. " +
                "When updating a TokenPassed event, please use Http method PUT instead of POST.");
        put(WrongHttpMethodReasonEnum.POST_USED_WHEN_UPDATING_A_TOKEN, "Wrong Http, method used. " +
                "When updating a thank you Token, please use Http Method PUT instead of POST. " +
                "Id of t");
    }};

    private static String WrongHttpMethodReasonEnumMapper(WrongHttpMethodReasonEnum e){
        return messageMap.get(e);
    }

    public WrongHttpMethodException(HttpMethod wrong, HttpMethod right, String additionalInformation) {

        super(String.format("Wrong Http method used. Http method used: %s, the correct method would be: %s. " +
                "%s", wrong, right, additionalInformation));
    }

    public WrongHttpMethodException(WrongHttpMethodReasonEnum enumOfReason){
        super(WrongHttpMethodReasonEnumMapper(enumOfReason));
    }
}