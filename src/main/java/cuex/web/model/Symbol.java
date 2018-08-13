package cuex.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Symbol {

    private String code;
    private String name;
}
