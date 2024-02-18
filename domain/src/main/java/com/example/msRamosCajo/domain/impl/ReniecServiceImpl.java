package com.example.msRamosCajo.domain.impl;

import com.example.msRamosCajo.domain.agregates.response.ResponseReniec;
import com.example.msRamosCajo.domain.ports.ReniecServiceIn;
import com.example.msRamosCajo.domain.ports.RestReniecOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReniecServiceImpl implements ReniecServiceIn {
    private final RestReniecOut restReniecOut;
    @Override
    public ResponseReniec getInfoIn(String numero) {
        return restReniecOut.getInfoReniec(numero);
    }
}
