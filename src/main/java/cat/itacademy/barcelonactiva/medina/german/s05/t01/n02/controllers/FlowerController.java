package cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.dto.FlowerEntityDTO;
import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.exceptions.FlowerNotFound;
import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.services.FlowerEntityService;
import cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.services.FlowerEntityServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import java.util.List;

    @RestController
    @RequestMapping("/flower")
    public class FlowerController {

        @Autowired
        FlowerEntityService service;

        @PostMapping("/add")
        public ResponseEntity<FlowerEntityDTO> addFlower(@RequestBody FlowerEntityDTO flowerEntityDTO) {
            service.save(flowerEntityDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(flowerEntityDTO);
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<FlowerEntityDTO> updateFlower(@PathVariable int id, @RequestBody FlowerEntityDTO flowerEntityDTO) {
            try {
                service.update(id, flowerEntityDTO);
                return ResponseEntity.ok(flowerEntityDTO);
            } catch (FlowerNotFound e) {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteFlower(@PathVariable int id) {
            try {
                service.deleteById(id);
                return ResponseEntity.ok("Flower deleted successfully");
            } catch (FlowerNotFound e) {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/getOne/{id}")
        public ResponseEntity<FlowerEntityDTO> getOneFlower(@PathVariable int id) {
            try {
                FlowerEntityDTO flowerEntityDTO = service.findById(id);
                return ResponseEntity.ok(flowerEntityDTO);
            } catch (FlowerNotFound e) {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/getAll")
        public ResponseEntity<List<FlowerEntityDTO>> getAllFlowers() {
            List<FlowerEntityDTO> flowers = service.findAll();
            if (flowers.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(flowers);
            }
        }
}

