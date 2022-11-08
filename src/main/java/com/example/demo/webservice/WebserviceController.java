package com.example.demo.webservice;

import com.example.demo.business.ReservationService;
import com.example.demo.business.RoomReservation;
import com.example.demo.data.Guest;
import com.example.demo.data.Room;
import com.example.demo.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping(value = "/guests")
    public  List<Guest> getGuests(){
        return this.reservationService.getHotelGuests();
    }

    @PostMapping(value = "/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public  void addGuest(@RequestBody Guest guest){
        this.reservationService.addHotelGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getRooms();
    }
}
