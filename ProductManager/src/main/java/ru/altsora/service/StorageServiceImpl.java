package ru.altsora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.model.domain.Storage;
import ru.altsora.model.response.ProductStorageAvailable;
import ru.altsora.model.response.ProductStorageUpdate;
import ru.altsora.repository.StorageRepository;

import java.util.Optional;

import static ru.altsora.util.RetMessage.*;

@Primary
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;

    @Override
    public ProductStorageAvailable productInfo(long productId) {
        final Optional<Storage> productOpt = storageRepository.findByProductId(productId);
        if (productOpt.isEmpty())
            throw new DomainNotFoundException(String.format(STORAGE_PRODUCT_NOT_FOUND_ID, productId));
        final int amount = productOpt.get().getAmount();
        return ProductStorageAvailable.builder()
                .isAvailable(amount > 0)
                .amount(amount)
                .build();
    }

    @Override
    public ProductStorageUpdate plus(long productId, Integer amount) {
        final Optional<Storage> productOpt = storageRepository.findByProductId(productId);
        if (productOpt.isEmpty())
            throw new DomainNotFoundException(String.format(STORAGE_PRODUCT_NOT_FOUND_ID, productId));
        final Storage storage = productOpt.get();
        final int currentAmount = storage.getAmount();
        storage.setAmount(currentAmount + amount);
        storageRepository.saveAndFlush(storage);
        return ProductStorageUpdate.builder()
                .result(true)
                .message(String.format(STORAGE_PLUS_OK, currentAmount, amount))
                .build();
    }

    @Override
    public ProductStorageUpdate minus(long productId, Integer amount) {
        final Optional<Storage> productOpt = storageRepository.findByProductId(productId);
        if (productOpt.isEmpty())
            throw new DomainNotFoundException(String.format(STORAGE_PRODUCT_NOT_FOUND_ID, productId));
        final Storage storage = productOpt.get();
        final int currentAmount = storage.getAmount();
        if (currentAmount < amount)
            return ProductStorageUpdate.builder()
                    .result(false)
                    .message(String.format(STORAGE_MINUS_BAD, currentAmount, amount))
                    .build();
        storage.setAmount(currentAmount - amount);
        storageRepository.saveAndFlush(storage);
        return ProductStorageUpdate.builder()
                .result(true)
                .message(String.format(STORAGE_MINUS_OK, currentAmount, amount))
                .build();
    }

    @Override
    public void removeAll(long productId) {
        if (!storageRepository.productExists(productId))
            throw new DomainNotFoundException(String.format(STORAGE_PRODUCT_NOT_FOUND_ID, productId));
        storageRepository.removeAllByProductId(productId);
    }
}
