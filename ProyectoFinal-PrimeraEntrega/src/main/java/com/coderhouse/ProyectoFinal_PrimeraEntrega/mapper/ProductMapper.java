package com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductDTO toDTO(Product pProduct) {
        ProductDTO mProductDTO = new ProductDTO();
        mProductDTO.setmProductId(pProduct.getmProductId());
        mProductDTO.setmProductName(pProduct.getmProductName());
        mProductDTO.setmProductDescription(pProduct.getmProductDescription());
        mProductDTO.setmProductCategory(pProduct.getmProductCategory());
        mProductDTO.setmProductCode(pProduct.getmProductCode());
        mProductDTO.setmProductStock(pProduct.getmProductStock());
        mProductDTO.setmProductPrice(pProduct.getmProductPrice());
        mProductDTO.setmProductTaxPercent(pProduct.getmProductTaxPercent());
        mProductDTO.setmProductCreationDate(pProduct.getmProductCreationDate());
        return mProductDTO;
    }

    public static List<ProductDTO> toDTO(List<Product> pProductList) {
        List<ProductDTO> mProductDTOList = new ArrayList<>();
        for (Product mProduct : pProductList ) {
            mProductDTOList.add(toDTO(mProduct));
        }
        return mProductDTOList;
    }

}
