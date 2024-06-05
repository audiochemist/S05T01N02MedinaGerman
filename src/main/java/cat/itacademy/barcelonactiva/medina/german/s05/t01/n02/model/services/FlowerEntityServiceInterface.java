package cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.dto.FlowerEntityDTO;
import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.exceptions.FlowerNotFound;

import java.util.List;

public interface FlowerEntityServiceInterface {

    void save(FlowerEntityDTO flowerEntityDTO);
    void update (int id, FlowerEntityDTO flowerEntityDTO) throws FlowerNotFound;
    void deleteById(int id) throws FlowerNotFound;
    FlowerEntityDTO findById(int id) throws FlowerNotFound;
    List<FlowerEntityDTO> findAll();

}
