package com.hotelBooking.services;

import com.hotelBooking.models.Auth;
import com.hotelBooking.models.Booking;
import com.hotelBooking.models.BookingDates;
import com.hotelBooking.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest{


    public String createToken(){

        Auth authbody = new Auth("admin","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authbody)
                .post("/auth");
        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");
    }


    public BookingResponse createBooking(){

        BookingDates bookingDates = new BookingDates("2023-01-05","2023,01,08");

        Booking booking = new Booking("Furkan","Yilmaz",147,true,"free breakfast",bookingDates);

        Response response=given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(booking)
                .post("/booking");

        response.then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }

    public void deleteBookingTests(String token, int bookingId) {


        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId);

        response
                .then()
                .statusCode(201);
    }

    //1 token oluştur
    //2- rezervasyon olustur
    //3- rezervasyon sil

}
