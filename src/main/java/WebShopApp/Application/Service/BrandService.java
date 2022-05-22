package WebShopApp.Application.Service;

import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Exceptions.BrandNotFoundException;
import WebShopApp.Application.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> listBrands() {
        return (List<Brand>) brandRepository.findAll();
    }

    public Brand get(Integer id) throws BrandNotFoundException {
        try {
            return brandRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new BrandNotFoundException("Could not find any brand with ID" + id);
        }
    }

    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }
}
