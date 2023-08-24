package com.scott.stock.stockdataetltool.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "ETL-Tool-API",
        version = "1.0",
        description = "OpenAPI documentation for etl tool"
    ),
    servers = {
        @Server(description = "Local ENV", url = "http://localhost:9090")
    },
    security = {@SecurityRequirement(name = "bearer auth")}
)
@SecurityScheme(name = "bearer auth", scheme = "bearer", description = "JWT", type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT", in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
