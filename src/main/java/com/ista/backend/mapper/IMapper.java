package com.ista.backend.mapper;

public interface IMapper <I,O>{

    //mapear lo que entremos en in al objeto O;
    public O map(I in);
}
