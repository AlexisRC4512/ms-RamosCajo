package com.example.msRamosCajo.domain.ports;

import com.example.msRamosCajo.domain.agregates.response.ResponseReniec;

public interface RestReniecOut {
    ResponseReniec getInfoReniec(String numDoc);
}
