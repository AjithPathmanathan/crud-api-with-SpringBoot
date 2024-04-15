package com.example.Ethnicity.Service;

import com.example.Ethnicity.EntityDto.EthnicityDto;
import com.example.Ethnicity.entity.Ethnicity;

import java.util.List;
import java.util.Optional;

public interface EthnicityService {
      public List<Ethnicity> FindAll();
      public EthnicityDto FindByEthnicityId(int id);
      public EthnicityDto CreateEthnicity(EthnicityDto ethnicityDto);
      public String Delete (int id);
      public EthnicityDto FindById(int id);
      public EthnicityDto update (int id,EthnicityDto ethnicityDto);

}
