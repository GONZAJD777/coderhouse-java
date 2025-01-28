package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartReducedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.handler.ErrorHandler;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.CartMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.CartService;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.ClientService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carts")
public class CartController {


    @Autowired
    private CartService mCartService;
    @Autowired
    private ClientService mClientService;

    public CartController(CartService pCartService, ClientService mClientService) {

        this.mCartService = pCartService;
        this.mClientService = mClientService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CartDTO>>> getAllCarts() {
        try {
            List<CartDTO> mCartDTOList = CartMapper.toDTO(mCartService.listAll());
            ApiResponse<List<CartDTO>> mApiResponse = new ApiResponse<>(true,"Listado de TODOS los clientes.",mCartDTOList,null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @GetMapping("/{pCartId}")
    public ResponseEntity<ApiResponse<CartDTO>> getCartById(@PathVariable Long pCartId){
        try {
            CartDTO mCartDTO = CartMapper.toDTO(mCartService.getCart(pCartId));
            ApiResponse<CartDTO> mApiResponse = new ApiResponse<>(true,"Listado de TODOS los clientes.",mCartDTO,null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        } catch (CustomException e) {
        return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                        List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @PutMapping("/{pCartId}")
    public ResponseEntity<ApiResponse<CartDTO>> addProductToCart(@PathVariable Long pCartId, @RequestBody Map<String, Object> pRequestBody) {

       try {
           if (pRequestBody.get("mCartDetailProduct") == null) {
               throw new CustomException(ErrorType.INPUT_ERROR, ErrorType.INPUT_ERROR.getFormattedMessage("mCartDetailProduct"));
           }
           if (pRequestBody.get("mCartDetailQuantity") == null) {
               throw new CustomException(ErrorType.INPUT_ERROR, ErrorType.INPUT_ERROR.getFormattedMessage("mCartDetailQuantity"));
           }

           Long pProductId = ((Number) pRequestBody.get("mCartDetailProduct")).longValue();
           int pItemQuantity = (int) pRequestBody.get("mCartDetailQuantity");


           CartDTO mCartDTO = CartMapper.toDTO(mCartService.addProductToCart(pCartId,pProductId,pItemQuantity));
           ApiResponse<CartDTO> mApiResponse = new ApiResponse<>(true,"Listado de TODOS los clientes.",mCartDTO,null);
           return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
       } catch (CustomException e) {
           return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                   body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                           List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
       }
    }

    @PostMapping("/llenarCarrito")
    public ResponseEntity<ApiResponse<CartReducedDTO>> updateCart(@RequestBody Map<String, Object> pRequestBody) {
        try {
            CartReducedDTO mCartDTO = new CartReducedDTO();
            ApiResponse<CartReducedDTO> mApiResponse = new ApiResponse<>();

            Long mClientId = ((Number) pRequestBody.get("cliente")).longValue();
            Object mCartDetailList = pRequestBody.get("detalleCarrito");
            Client mClient = mClientService.getClient(mClientId);

            if (!(mCartDetailList instanceof List<?>)) {
                throw new CustomException(ErrorType.CART_DETAIL_FORMAT_ERROR);
            } else {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> mCartDetailListRaw = (List<Map<String, Object>>) mCartDetailList;
                for (Map<String, Object> CartDetail : mCartDetailListRaw) {

                    Long mCartDetailProduct = ((Number) CartDetail.get("producto")).longValue();
                    int mCartDetailQuantity = (int) CartDetail.get("cantidad");
                    try {
                        mCartDTO = CartMapper.toReducedDTO(mCartService.addProductToCart(mClient.getmClientCart().getmCartId(), mCartDetailProduct, mCartDetailQuantity));
                    } catch (CustomException ce){
                        mApiResponse.addError(ce.getCustomExceptionText());
                    }
                }
            }
            if (mCartDTO.getmCartDetailList().isEmpty()){
                mApiResponse.setSuccess(false);
                mApiResponse.setMessage("Parece que no se pudieron agregar productos al carrito.");
                mApiResponse.setData(mCartDTO);
            } else {
                mApiResponse.setSuccess(true);
                mApiResponse.setMessage("El carrito ha sido modificado.");
                mApiResponse.setData(mCartDTO);
            }

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),
                                    e.getErrorType().toString())));
        }
    }

}
