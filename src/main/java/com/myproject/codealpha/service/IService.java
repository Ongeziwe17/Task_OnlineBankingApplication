package com.myproject.codealpha.service;

public interface IService <EntityDTO, Identifier>{

    EntityDTO create(EntityDTO entityDTO);
    EntityDTO read(Identifier identifier);
    EntityDTO update(EntityDTO entityDTO);
}
