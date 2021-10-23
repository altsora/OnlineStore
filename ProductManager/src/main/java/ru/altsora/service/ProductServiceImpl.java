package ru.altsora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.model.domain.Product;
import ru.altsora.model.dto.ProductDto;
import ru.altsora.model.request.ProductUpdateIn;
import ru.altsora.repository.ProductRepository;

import java.util.Collection;
import java.util.Optional;

import static ru.altsora.util.Functions.PRODUCT_DOMAIN_TO_DTO;
import static ru.altsora.util.RetMessage.PRODUCT_NOT_FOUND_ID;

@Primary
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto findById(long id) {
        return productRepository.findById(id)
                .map(PRODUCT_DOMAIN_TO_DTO)
                .orElseThrow(() -> new DomainNotFoundException(PRODUCT_NOT_FOUND_ID, id));
    }

    @Override
    public void setAvailable(long id) {
        if (!productRepository.existsById(id)) throw new DomainNotFoundException(PRODUCT_NOT_FOUND_ID, id);
        productRepository.setAvailable(id);
    }

    @Override
    public void setNoAvailable(long id) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new DomainNotFoundException(PRODUCT_NOT_FOUND_ID, id));
        product.setAvailable(false);
        productRepository.saveAndFlush(product);
    }

    @Override
    public void updatePrice(long id, Double price) {
        if (!productRepository.existsById(id)) throw new DomainNotFoundException(PRODUCT_NOT_FOUND_ID, id);
        productRepository.updatePrice(id, price);
    }

    @Override
    public void addSubcategories(long productId, Collection<Long> subcategoryIds) {
        final Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) throw new DomainNotFoundException(PRODUCT_NOT_FOUND_ID, productId);
        final Product product = productOpt.get().addSubcategories(subcategoryIds);
        productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteSubcategories(long productId, Collection<Long> subcategoryIds) {
        final Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) throw new DomainNotFoundException(PRODUCT_NOT_FOUND_ID, productId);
        final Product product = productOpt.get().deleteSubcategories(subcategoryIds);
        productRepository.saveAndFlush(product);
    }

    @Override
    public void update(ProductUpdateIn in) {
        final long id = in.getId();
        final Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) throw new DomainNotFoundException(PRODUCT_NOT_FOUND_ID, id);
        final Product product = productOpt.get();

        product.setName(in.getName());
        product.setPrice(in.getPrice());
        product.setAvailable(in.getIsAvailable());
        product.setDescription(in.getDescription());
        in.getSubcategories().ifPresent(product::addSubcategories);
        productRepository.saveAndFlush(product);
    }
}
