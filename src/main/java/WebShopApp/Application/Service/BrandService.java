package WebShopApp.Application.Service;

import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Entity.Category;
import WebShopApp.Application.Exceptions.BrandNotFoundException;
import WebShopApp.Application.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BrandService {

    public static final int BRAND_PER_PAGE = 5;

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> listAllBrands() {
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

    public void deleteBrand(Integer id) throws BrandNotFoundException {
        Long countById = brandRepository.countById(id);

        if (countById == null || countById == 0) {
            throw new BrandNotFoundException("Could not find any Brand with ID" + id);
        }

        brandRepository.deleteById(id);
    }

    public Page<Brand> listByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, BRAND_PER_PAGE);
        return brandRepository.findAll(pageable);
    }
}
