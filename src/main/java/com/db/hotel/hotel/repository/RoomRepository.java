package com.db.hotel.hotel.repository;

import com.db.hotel.hotel.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {

    @Query("{number:'?0'}")
    Room findByNumber(Integer number);

    @Query(" { id: { $nin: ?0 } }")
    List<Room> findByConflicts(List<String> conflicts);

    public long count();
}