package com.adilmx.clientservice.projection;

import com.adilmx.clientservice.entity.Client;
import org.springframework.data.rest.core.config.Projection;

//.../clients?projection=pc1
@Projection(name = "pc1", types = Client.class)
public interface ClientProjection {
    public Long getId();
    public String getName();
    public String getEmail();
}
