package cuex.web.repository;

import cuex.web.model.Rate;

import java.util.List;

public interface RateRepository {

    List<Rate> findAll();
}
