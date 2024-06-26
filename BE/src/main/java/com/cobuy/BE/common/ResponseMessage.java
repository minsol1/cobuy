package com.cobuy.BE.common;

public interface ResponseMessage {
    // 200
    String SUCCESS = "Success";


    String VALIDATION_FAIL = "Validation Failed";

    // 400
    String DUPLICATE_ID = "Duplicate id";

    String SIGN_IN_FAIL = "Login Information Mismatch";
    String CERTIFICATION_FAIL = "Certification Failed";

    String MAIL_FAIL = "Mail send failed.";

    // 500
    String DATABASE_ERROR = "Database Error";
}
