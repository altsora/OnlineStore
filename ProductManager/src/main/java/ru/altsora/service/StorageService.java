package ru.altsora.service;

import ru.altsora.model.response.ProductStorageAvailable;
import ru.altsora.model.response.ProductStorageUpdate;

public interface StorageService {
    ProductStorageAvailable productInfo(long productId);

    ProductStorageUpdate plus(long productId, Integer amount);

    ProductStorageUpdate minus(long productId, Integer amount);

    void removeAll(long productId);
}
