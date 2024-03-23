package com.turkcell.rentacar.businnes.concretes;


import com.turkcell.rentacar.businnes.abstracts.BrandService;
import com.turkcell.rentacar.businnes.abstracts.FuelService;
import com.turkcell.rentacar.businnes.abstracts.ModelService;
import com.turkcell.rentacar.businnes.abstracts.TransmissionService;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.dtos.reponses.CreateModelResponse;
import com.turkcell.rentacar.dtos.reponses.GetFuelResponse;
import com.turkcell.rentacar.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Model;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {

    private ModelRepository modelRepository;

    private ModelMapperService modelMapperService;


    @Override
    public CreateModelResponse add(CreateModelRequest createModelRequest) {
        Model model = this.modelMapperService.forRequest().map(createModelRequest,Model.class);
        model.setCreatedDate(LocalDateTime.now());
        Model createdModel = modelRepository.save(model);
        CreateModelResponse createdModelResponse =
                this.modelMapperService.forResponse().map(createdModel,CreateModelResponse.class);
        return createdModelResponse;
    }

    @Override
    public List<CreateModelResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<CreateModelResponse> modelResponses = new ArrayList<>();
        for (var model:models){
            CreateModelResponse createdModelResponse =
                    this.modelMapperService.forResponse().map(model,CreateModelResponse.class);
            modelResponses.add(createdModelResponse);
        }
        return modelResponses;
    }

    @Override
    public CreateModelResponse get(int id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        Model model = modelOptional.get();
        CreateModelResponse createdModelResponse =
                this.modelMapperService.forResponse().map(model,CreateModelResponse.class);
        return createdModelResponse;
    }

    @Override
    public CreateModelResponse update(int id, CreateModelRequest createModelRequest) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()){
            Model model = modelOptional.get();
            this.modelMapperService.forRequest().map(createModelRequest,model);
            model.setUpdatedDate(LocalDateTime.now());

            Model updatedModel = modelRepository.save(model);

            CreateModelResponse createdModelResponse =
                    this.modelMapperService.forResponse().map(updatedModel,CreateModelResponse.class);
            return createdModelResponse;
        }else {

            return null;
        }

    }

    @Override
    public void delete(int id) {

    }
/*
    @Override
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public Model getById(int id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    public Model update(int id, Model model) {
        Model updatedModel = modelRepository.findById(id).orElse(null);
        updatedModel.setName(model.getName());
        return modelRepository.save(updatedModel);
    }

    @Override
    public void delete(int id) {
        modelRepository.deleteById(id);
    }
 */
}
