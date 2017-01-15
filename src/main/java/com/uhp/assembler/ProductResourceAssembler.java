package com.uhp.assembler;

import com.uhp.controller.ProductsController;
import com.uhp.entity.Product;
import org.springframework.stereotype.Component;

/**
 * @author Bogdan Kovalev.
 */
@Component
public class ProductResourceAssembler extends AbstractResourceAssembler<Product> {
    @Override
    String getPath() {
        return ProductsController.PATH;
    }
}
