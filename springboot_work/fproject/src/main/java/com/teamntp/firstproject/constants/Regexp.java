package com.teamntp.firstproject.constants;

public class Regexp { // 정규표현식
    public static final String REGEXP_LOGIN_ID = "^[a-z0-9]{4,15}$";
    public static final String REGEXP_PASSWORD = "^[a-zA-Z0-9!@#$]{4,15}$";
    public static final String REGEXP_NAME = "^[가-힣]{2,10}$";
    public static final String REGEXP_BIRTHDATE = "^(19[0-9][0-9]|20[0-9][0-9])(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";
    public static final String REGEXP_MOBILE_NO = "^[0-9]{10,12}$";
    public static final String REGEXP_EMAIL = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

    public static final String REGEXP_ZIPCODE = "^[0-9]{5,5}$";
    public static final String REGEXP_ADDRESS = "^.{1,100}$";
    public static final String REGEXP_DETAIL_ADDRESS = "^.{1,100}$";

}
