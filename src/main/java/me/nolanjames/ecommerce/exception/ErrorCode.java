package me.nolanjames.ecommerce.exception;

public enum ErrorCode {
    GENERIC_ERROR("ECOM-0001", "The system is unable to complete the request. Contact system support."),
    HTTP_MEDIATYPE_NOT_SUPPORTED("ECOM-0002", "Requested media type is not supported. Please use application/json or application/xml as 'Content-Type' header value"),
    HTTP_MESSAGE_NOT_WRITABLE("ECOM-0003", "Missing 'Accept' header. Please add 'Accept' header."),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE("ECOM-0004", "Requested 'Accept' header value is not supported. Please use application/json or application/xml as 'Accept' value"),
    JSON_PARSE_ERROR("ECOM-0005", "Make sure request payload should be a valid JSON object."),
    HTTP_MESSAGE_NOT_READABLE("ECOM-0006", "Make sure request payload should be a valid JSON or XML object according to 'Content-Type'."),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED("ECOM-0007", "Request method not supported."),
    CONSTRAINT_VIOLATION("ECOM-0008", "Validation failed."),
    ILLEGAL_ARGUMENT_EXCEPTION("ECOM-0009", "Invalid data passed."),
    RESOURCE_NOT_FOUND("ECOM-0010", "Requested resource not found"),
    CUSTOMER_NOT_FOUND("ECOM-0011", "Requested customer not found"),
    ITEM_NOT_FOUND("ECOM-0012", "Requested item not found"),
    GENERIC_ALREADY_EXISTS("ECOM-0013", "Already exists.");

    private String errCode;
    private String errMsgKey;

    ErrorCode(final String errCode, final String errMsgKey) {
        this.errCode = errCode;
        this.errMsgKey = errMsgKey;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }
}
