package com.example.api;

import com.example.model.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by saroj on 3/7/16.
 */
@RestController
public class BookingController {

    private static BigInteger nextId;
    private static Map<BigInteger, Booking> bookingMap;

    private static Booking save(Booking booking){
        if(bookingMap == null){
            bookingMap = new HashMap<BigInteger, Booking>();
           nextId = BigInteger.ONE;
        }
        //for updating an existing
        if(booking.getId() !=null){
            Booking oldBooking = bookingMap.get(booking.getId());
            if(oldBooking  == null){
               return null;
            }
            bookingMap.remove(booking.getId());
            bookingMap.put(booking.getId(),booking);
            return booking;
        }
        // for creating new
        booking.setId(nextId);
        nextId = nextId.add(BigInteger.ONE);
        bookingMap.put(booking.getId(),booking);
        return booking;

    }

    static {
        Booking b1 = new Booking();
        b1.setText("Hello World");
        save(b1);

        Booking b2 = new Booking();
        b2.setText("holla");
        save(b2);
    }

    public static boolean delete(BigInteger id){
        Booking deleteBooking = bookingMap.remove(id);
        if(deleteBooking == null){
            return false;
        }
        return true;

    }
    @RequestMapping(value="/api/booking", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Booking>> getBooking(){
        Collection<Booking> bookings = bookingMap.values();
        return new ResponseEntity<Collection<Booking>>(bookings, HttpStatus.OK);
    }

    @RequestMapping(value="/api/booking/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") BigInteger id){
        Booking booking = bookingMap.get(id);
        if(booking == null){
            return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<Booking>(booking,HttpStatus.OK);
    }

    @RequestMapping(value="/api/bookings", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
        Booking savedBookings = save(booking);
        return new ResponseEntity<Booking>(savedBookings, HttpStatus.CREATED);
    }

    @RequestMapping(value="/api/bookings/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking){
        Booking updateBooking = save(booking);
        if(updateBooking == null){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Booking>(updateBooking, HttpStatus.OK);

    }

    @RequestMapping(value="/api/bookings/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> deleteBooking(@PathVariable("id") BigInteger id, @RequestBody Booking booking){
           boolean deleted = delete(id);
            if(!deleted){
                return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return new ResponseEntity<Booking>(HttpStatus.NO_CONTENT);

    }
}
