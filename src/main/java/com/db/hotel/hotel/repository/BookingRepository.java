package com.db.hotel.hotel.repository;

import com.db.hotel.hotel.model.Booking;
import com.db.hotel.hotel.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    @Query("{ $and: [  {room:{$ne:null}},  {  $nor: [ { begin : { $gt : ?1 } }, { end : { $lt : ?0 } } ] } ] }")
    List<Booking> findByRange(Date begin, Date end);

  //  @Query("{ room : ?0 , begin : {$lte : ?1 }, end : { $gte : ?1} }")
    @Query("{ \"room.$id\": ObjectId(\"?0\") , begin : {$lte : ?1 }, end : { $gte : ?1} }")
    Booking findByRoomDate(String room, Date date);

    public long count();
}