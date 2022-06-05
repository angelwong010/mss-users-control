package com.cloudservices.mss.users.control.utility;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UtilityHelper {
    public static ModelMapper mapper = (new MappingConfig()).mapper();

    public <D, E> D mapToResource(E entity,Class<D> dtoClass) {return mapper.map(entity,dtoClass);}

}
