package cuex.web.service;

import cuex.web.model.dto.SymbolsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class RestTemplateTest {

    @Test
    void test() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Assertions.assertNotNull(restTemplate);

        final ResponseEntity<SymbolsDto> forEntity = restTemplate.getForEntity("http://data.fixer.io/api/symbols?access_key=092198e622b53437b74210f1dc91829e", SymbolsDto.class);
        System.out.println(forEntity);
        System.out.println(forEntity.getBody());
    }
}
