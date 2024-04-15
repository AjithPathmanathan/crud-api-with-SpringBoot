package com.example.Ethnicity.Controller;

import com.example.Ethnicity.EntityDto.EthnicityDto;
import com.example.Ethnicity.Repository.EthnicityRepository;
import com.example.Ethnicity.Service.EthnicityService;
import com.example.Ethnicity.entity.Ethnicity;
import com.example.Ethnicity.util.Endpointbundal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EthnicityController {
    @Autowired
    private EthnicityService ethnicityService;

    @Autowired
    private EthnicityRepository ethnicityRepository;
    @GetMapping(Endpointbundal.Ethnicity)
    public List<Ethnicity> getall(){
       return  ethnicityService.FindAll();
    }
    @GetMapping(Endpointbundal.Ethnicity+Endpointbundal.Search)
    public EthnicityDto search(@PathVariable int Id){
        return ethnicityService.FindById(Id);
    }

    @PostMapping(Endpointbundal.Ethnicity+Endpointbundal.Create)
    public ResponseEntity<EthnicityDto> Createethnicity(@RequestBody EthnicityDto ethnicityDto){
        EthnicityDto CreatedEthnicityDto =ethnicityService.CreateEthnicity(ethnicityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreatedEthnicityDto);

    }
    @DeleteMapping(Endpointbundal.Ethnicity+Endpointbundal.Delete)
    public ResponseEntity<?> delete(@PathVariable int Id){
      String message = ethnicityService.Delete(Id);
      return ResponseEntity.ok(message);

    }
    @PutMapping(Endpointbundal.Ethnicity+Endpointbundal.Update)
    public ResponseEntity<?> updateData(@PathVariable int Id, @RequestBody EthnicityDto ethnicityDto){
        ethnicityService.update(Id,ethnicityDto);
        return ResponseEntity.ok("updated");



    }

}
