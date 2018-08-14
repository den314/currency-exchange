package cuex.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Rate {

    private String code;
    private Double value;
}
