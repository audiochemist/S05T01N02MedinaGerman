package cat.itacademy.barcelonactiva.medina.german.s05.t01.n02.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class FlowerNotFound extends Exception {
    private String message;
}
