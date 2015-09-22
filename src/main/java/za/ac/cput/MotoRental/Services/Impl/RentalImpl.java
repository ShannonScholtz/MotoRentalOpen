package za.ac.cput.MotoRental.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.MotoRental.Services.RentalService;
import za.ac.cput.MotoRental.domain.Rental;
import za.ac.cput.MotoRental.repository.RentalRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/12.
 */
@Service
public class RentalImpl implements RentalService {

    @Autowired
    private RentalRepository repository;

    @Override
    public Rental findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Rental save(Rental entity) {
        return repository.save(entity);
    }

    @Override
    public Rental update(Rental entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Rental entity) {
        repository.delete(entity);

    }

    @Override
    public List<Rental> findAll() {
        List<Rental> allRentals = new ArrayList();
        Iterable<Rental> rentals = repository.findAll();
        for (Rental rental : rentals) {
            allRentals.add(rental);
        }
        return allRentals;
    }
}
