package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.handler.ErrorHandler;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.ProductMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.ProductService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService mProductService;

    public ProductController(ProductService mProductService) {
        this.mProductService = mProductService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        try {
            List<ProductDTO> mProductDTOList = ProductMapper.toDTO(mProductService.listAll());
            ApiResponse<List<ProductDTO>> mApiResponse = new ApiResponse<>(true,"Listado de TODOS los productos",mProductDTOList,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @GetMapping("/{pProductId}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable Long pProductId){
        try {
            ProductDTO mProductDTO = ProductMapper.toDTO(mProductService.getProduct(pProductId));
            ApiResponse<ProductDTO> mApiResponse = new ApiResponse<>(true,"Este es el producto que buscabas",mProductDTO,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody Product pProduct) {
        System.out.println("Client ProductId:" + pProduct.getmProductId());
        System.out.println("Client ProductName:" + pProduct.getmProductName());
        System.out.println("Client ProductDescription:" + pProduct.getmProductDescription());
        System.out.println("Client ProductCode:" + pProduct.getmProductCode());
        System.out.println("Client ProductStock:" + pProduct.getmProductStock());
        System.out.println("Client ProductPrice:" + pProduct.getmProductPrice());
        System.out.println("Client ProductTaxPercent:" + pProduct.getmProductTaxPercent());

        try {
            ProductDTO mProductDTO = ProductMapper.toDTO(mProductService.createProduct(pProduct));
            ApiResponse<ProductDTO> mApiResponse = new ApiResponse<>(true,"Se ha creado el producto correctamente",mProductDTO,null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @PutMapping("/{pProductId}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable Long pProductId, @RequestBody Map<String, Object> pRequestBody) {
        Product mProduct = new Product();

        if (pProductId != null) {
            mProduct.setmProductId(pProductId);
        }
        if (pRequestBody.get("mProductName") != null) {
            mProduct.setmProductName(pRequestBody.get("mProductName").toString());
        }
        if (pRequestBody.get("mProductDescription") != null) {
            mProduct.setmProductDescription(pRequestBody.get("mProductDescription").toString());
        }
        if (pRequestBody.get("mProductCategory") != null) {
            mProduct.setmProductCategory(pRequestBody.get("mProductCategory").toString());
        }
        if (pRequestBody.get("mProductCode") != null) {
            mProduct.setmProductCode(pRequestBody.get("mProductCode").toString());
        }
        if (pRequestBody.get("mProductStock") != null) {
            mProduct.setmProductStock(Integer.parseInt(pRequestBody.get("mProductStock").toString()));
        }
        if (pRequestBody.get("mProductPrice") != null) {
            mProduct.setmProductPrice(Float.parseFloat(pRequestBody.get("mProductPrice").toString()));
        }
        if (pRequestBody.get("mProductTaxPercent") != null) {
            mProduct.setmProductTaxPercent(Float.parseFloat(pRequestBody.get("mProductTaxPercent").toString()));
        }

        try {
            ProductDTO mProductDTO = ProductMapper.toDTO(mProductService.updateProduct(mProduct));
            ApiResponse<ProductDTO> mApiResponse = new ApiResponse<>(true, "Se ha actualizado el producto correctamente", mProductDTO, null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @DeleteMapping("/{pProductId}")
    public ResponseEntity<ApiResponse<ProductDTO>> deleteProduct(@PathVariable Long pProductId){
        try {
            ProductDTO mProductDTO = ProductMapper.toDTO(mProductService.deleteProduct(pProductId));
            ApiResponse<ProductDTO> mApiResponse = new ApiResponse<>(true,"Se ha eliminado el producto.",mProductDTO,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }


}
