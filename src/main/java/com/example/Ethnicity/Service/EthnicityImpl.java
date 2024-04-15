package com.example.Ethnicity.Service;

import com.example.Ethnicity.EntityDto.EthnicityDto;
import com.example.Ethnicity.Repository.EthnicityRepository;
import com.example.Ethnicity.entity.Ethnicity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EthnicityImpl implements EthnicityService {
    @Autowired
    private EthnicityRepository ethnicityRepository;
    private Ethnicity ethnicity;

    @Override
    public List<Ethnicity> FindAll(){
        return ethnicityRepository.findAll();
    }
    @Override
    public EthnicityDto FindByEthnicityId(int id){
        Optional<Ethnicity> optionalEthnicity = ethnicityRepository.findById(id);
        if (optionalEthnicity.isPresent()){
            EthnicityDto ethnicityDto = new EthnicityDto();
            BeanUtils.copyProperties(optionalEthnicity,ethnicityDto);
            return ethnicityDto;
        }
        else {
            throw new EntityNotFoundException("Admit not found with id: " + id);
        }

    }

    @Override
    public  EthnicityDto CreateEthnicity(EthnicityDto ethnicityDto){
        Ethnicity ethnicity= new Ethnicity();
        BeanUtils.copyProperties(ethnicityDto,ethnicity);

        Ethnicity Savedethnicity  = ethnicityRepository.save(ethnicity);
        EthnicityDto SavedethnicityDto = new EthnicityDto();
        BeanUtils.copyProperties(Savedethnicity,SavedethnicityDto);
        return SavedethnicityDto;
    }
    @Override
    public String Delete(int id){
        if (ethnicityRepository.existsById(id)){
        ethnicityRepository.deleteById(id);
        return "this id successfully deleted";
        }
        else
            return "there is not any record with this id no" +id ;
    }
    @Override
    public EthnicityDto FindById(int Id) {
        Optional<Ethnicity> optionalEthnicity = ethnicityRepository.findById(Id);
        if (optionalEthnicity.isPresent()) {
            EthnicityDto ethnicityDto = new EthnicityDto();
            BeanUtils.copyProperties(optionalEthnicity.get(),ethnicityDto );
            return ethnicityDto;
        } else {
            throw new EntityNotFoundException("Admit not found with id: " + Id);
        }
    }

    @Override
    public EthnicityDto update(int id, EthnicityDto ethnicityDto) {
        Optional<Ethnicity> optionalEthnicity =ethnicityRepository.findById(id);
        if (optionalEthnicity.isPresent()){
            Ethnicity ethnicity = new Ethnicity();
            BeanUtils.copyProperties(ethnicityDto,ethnicity);
            //ethnicity.setName(ethnicityDto.getName());
            ethnicity.setId(id);
            Ethnicity updatedethnicity = ethnicityRepository.save(ethnicity);
            EthnicityDto updatedethnicityDto = new EthnicityDto();
            BeanUtils.copyProperties(updatedethnicity,updatedethnicityDto);
            return updatedethnicityDto;

        }
        else{
            throw new EntityNotFoundException("Admit not found with id: " + id);}


    }
}
