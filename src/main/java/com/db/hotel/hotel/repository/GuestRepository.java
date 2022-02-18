package com.db.hotel.hotel.repository;

import com.db.hotel.hotel.model.Guest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuestRepository extends MongoRepository<Guest, String> {

    public long count();
}