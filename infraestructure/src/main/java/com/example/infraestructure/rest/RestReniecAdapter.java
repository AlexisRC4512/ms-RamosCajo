package com.example.infraestructure.rest;

import com.example.infraestructure.rest.client.ClienteReniec;
import com.example.msRamosCajo.domain.agregates.response.ResponseReniec;
import com.example.msRamosCajo.domain.ports.RestReniecOut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestReniecAdapter implements RestReniecOut {

    private final ClienteReniec reniec;

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public ResponseReniec getInfoReniec(String numDoc) {
        String authorization = "Bearer " + tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numDoc,authorization);
        return responseReniec;
    }
}
