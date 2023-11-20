package com.hotelBooking.steps;

import com.hotelBooking.models.BookingResponse;
import com.hotelBooking.services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {
    ReservationService reservationService;
    String authKey;
    BookingResponse bookingResponse;

    @Given("User can create a new reservation")
    public void beginningOfResponse(){
       reservationService = new ReservationService();
    }

    @Given("User provides reservation information")
    public void createAuth(){
        authKey = reservationService.createToken();
    }

    @When("User creates the hotel reservation")
    public void createReservations(){
        bookingResponse = reservationService.createBooking();
    }

    @Then("Successful creation of the reservation")
    public void reservationAssertions(){
        Assertions.assertEquals("Furkan", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Yilmaz",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(147,bookingResponse.getBooking().getTotalprice());
        Assertions.assertTrue(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("free breakfast",bookingResponse.getBooking().getAdditionalneeds());

    }

    @Then("User deletes the created reservation")
    public void cancelReservation(){
      reservationService.deleteBookingTests(authKey,bookingResponse.getBookingid());
    }
}
