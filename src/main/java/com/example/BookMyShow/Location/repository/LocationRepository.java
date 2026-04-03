package com.example.BookMyShow.Location.repository;

import com.example.BookMyShow.Location.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

    public interface LocationRepository extends MongoRepository<Location,String> {
    }


