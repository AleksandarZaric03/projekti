package com.example.demo.Controller;

import com.example.demo.DTO.CityDTO;
import com.example.demo.Model.City;
import com.example.demo.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/gradovi")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(path = "",method = RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> getAllCities(){
        List<CityDTO> gradovi = new ArrayList<CityDTO>();

        for(City c : cityService.findAll()){
            gradovi.add(c.toDto());
        }

        return new ResponseEntity<List<CityDTO>>(gradovi, HttpStatus.OK);
    }

    @RequestMapping(path = "",method = RequestMethod.POST)
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO noviGrad){

        City rezultat = cityService.save(noviGrad.toEntity());
        if(rezultat != null){
            return new ResponseEntity<CityDTO>(rezultat.toDto(),HttpStatus.CREATED);
        }

        return new ResponseEntity<CityDTO>(HttpStatus.OK);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<CityDTO> getCity(@PathVariable("id") Long id){

        Optional<City> city = cityService.findById(id);

        if(city.isPresent()){
            return new ResponseEntity<CityDTO>(city.get().toDto(),HttpStatus.OK);
        }
        return new ResponseEntity<CityDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CityDTO> updateCity(@PathVariable("id") Long id, @RequestBody CityDTO noviGrad){
        Optional<City> grad = cityService.findById(id);

        if(grad.isPresent()){
            grad.get().setId(id);
            grad.get().setName(noviGrad.getNaziv());
            cityService.save(grad.get());
            return new ResponseEntity<CityDTO>(grad.get().toDto(),HttpStatus.OK);
        }
        return new ResponseEntity<CityDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<CityDTO> deleteCity(@PathVariable("id") Long id){

        Optional<City> city = cityService.findById(id);

        if(city.isPresent()){
            cityService.delete(id);
            return new ResponseEntity<CityDTO>(city.get().toDto(),HttpStatus.OK);
        }

        return new ResponseEntity<CityDTO>(HttpStatus.NOT_FOUND);
    }
}
