package WebShopApp.Application.Service;

import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> listBrands() {
        return (List<Brand>) brandRepository.findAll();
    }
}
