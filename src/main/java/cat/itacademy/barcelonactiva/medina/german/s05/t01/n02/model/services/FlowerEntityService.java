package cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.dto.FlowerEntityDTO;
import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.exceptions.FlowerNotFound;
import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.repository.FlowerEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FlowerEntityService implements FlowerEntityServiceInterface {

    @Autowired
    FlowerEntityRepository repo;

    @Autowired
    ModelMapper flowerModelMapper;

    @Override
    public void save(FlowerEntityDTO flowerEntityDTO) {
        repo.save(getFlowerEntityFrom(flowerEntityDTO));
    }

    @Override
    public void update(int id, FlowerEntityDTO flowerEntityDTO) throws FlowerNotFound {
        FlowerEntityDTO existingFlower = findById(id);
        existingFlower.setName(flowerEntityDTO.getName());
        existingFlower.setCountry(flowerEntityDTO.getCountry());
        repo.save(getFlowerEntityFrom(existingFlower));
    }

    @Override
    public void deleteById(int id) throws FlowerNotFound {
        FlowerEntityDTO existingFlower = findById(id);
        repo.delete(getFlowerEntityFrom(existingFlower));
    }

    @Override
    public FlowerEntityDTO findById(int id) throws FlowerNotFound {
        return repo.findById(id)
                .map(this::getFlowerDTOFrom)
                .orElseThrow(() -> new FlowerNotFound("The flower does not exist"));
    }

    @Override
    public List<FlowerEntityDTO> findAll() {
        List<FlowerEntity> flowers = repo.findAll();
        return flowers.stream().map(this::getFlowerDTOFrom).collect(Collectors.toList());
    }

    private FlowerEntityDTO getFlowerDTOFrom(FlowerEntity flower) {
        return flowerModelMapper.map(flower, FlowerEntityDTO.class);
    }

    private FlowerEntity getFlowerEntityFrom(FlowerEntityDTO flowerDTO) {
        return flowerModelMapper.map(flowerDTO, FlowerEntity.class);
    }
}
