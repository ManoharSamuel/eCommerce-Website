package com.projects.ecommercewebsite.services;

import com.projects.ecommercewebsite.dtos.FakeStoreProductDTO;
import com.projects.ecommercewebsite.dtos.GenericProductDTO;
import com.projects.ecommercewebsite.exceptions.ProductDoesNotExistException;
import com.projects.ecommercewebsite.thirdpartyclients.FakeStoreClient.FakeStoreClient;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private final FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    private GenericProductDTO convertToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();

        genericProductDTO.setCategoryName(fakeStoreProductDTO.getCategory());
        genericProductDTO.setName(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImageURL(fakeStoreProductDTO.getImage());
        genericProductDTO.setId(Long.valueOf(fakeStoreProductDTO.getId()));

        return genericProductDTO;
    }

    private FakeStoreProductDTO convertToFakeStoreProductDTO(GenericProductDTO genericProductDTO) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();

        fakeStoreProductDTO.setPrice(genericProductDTO.getPrice());
        fakeStoreProductDTO.setDescription(genericProductDTO.getDescription());
        fakeStoreProductDTO.setTitle(genericProductDTO.getName());
        fakeStoreProductDTO.setImage(genericProductDTO.getImageURL());
        fakeStoreProductDTO.setCategory(genericProductDTO.getCategoryName());

        return fakeStoreProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(long id) {
        return convertToGenericProductDTO(fakeStoreClient.getProductById(id));
    }



    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<FakeStoreProductDTO> fakeStoreProductDTOList = fakeStoreClient.getAllProducts();
        List<GenericProductDTO> genericProductDTOList = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList) {
            genericProductDTOList.add(convertToGenericProductDTO(fakeStoreProductDTO));
        }
       return genericProductDTOList;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        FakeStoreProductDTO fakeStoreProductDTO = convertToFakeStoreProductDTO(genericProductDTO);
        return convertToGenericProductDTO(fakeStoreClient.createProduct(fakeStoreProductDTO));
    }

    @Override
    public GenericProductDTO deleteProductById(long id) throws ProductDoesNotExistException {
        return convertToGenericProductDTO(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(GenericProductDTO genericProductDTO, long id) 
                                                    throws ProductDoesNotExistException {
        FakeStoreProductDTO fakeStoreProductDTO = convertToFakeStoreProductDTO(genericProductDTO);
        return convertToGenericProductDTO(fakeStoreClient.updateProductById(fakeStoreProductDTO, id));
    }

    @Override
    public GenericProductDTO replaceProductById(GenericProductDTO genericProductDTO, long id) 
                                                throws ProductDoesNotExistException {
        FakeStoreProductDTO fakeStoreProductDTO = convertToFakeStoreProductDTO(genericProductDTO);
        return convertToGenericProductDTO(fakeStoreClient.updateProductById(fakeStoreProductDTO, id));
    }
}
