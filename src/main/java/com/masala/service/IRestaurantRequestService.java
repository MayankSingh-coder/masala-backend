package com.masala.service;

import com.masala.entity.Restaurants;
import com.masala.entity.User;

public interface IRestaurantRequestService {

    Restaurants createResturents(Restaurants res, User user);
}
