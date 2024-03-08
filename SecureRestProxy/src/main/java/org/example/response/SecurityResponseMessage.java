package org.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Security;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityResponseMessage extends ResponseMessage{
    @JsonProperty("securities")
    private List<Security> securityList;

    @JsonProperty("security")
    private  Security security;

    public SecurityResponseMessage(String status, String message, String code, List<Security> securityList, Security security) {
        super(status, message, code);
    }
}
