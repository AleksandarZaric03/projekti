package com.example.demo.Service;

import com.example.demo.Model.City;
import com.example.demo.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public  Iterable<City> findAll(){
        return cityRepository.findAll();
    }

    public Optional<City> findById(Long id){

        return cityRepository.findById(id);
    }

    public City save(City city){

        return cityRepository.save(city);
    }

    public void delete(City city){

        cityRepository.delete(city);
    }

    public void delete(Long id){

        cityRepository.deleteById(id);
    }
}
