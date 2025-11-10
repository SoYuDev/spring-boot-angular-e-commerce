package com.luis.ecommerce.config;

import com.luis.ecommerce.entity.Product;
import com.luis.ecommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//Clase config para establecer que nuestra API Rest sea READ ONLY (Solo métodos http GET)
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST};

        // Disable HTTP Methods for Product.
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedActions)));

        // Disable HTTP Methods for ProductCategory.
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedActions)));
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        // Llamamos al helper method.
        exposeIds(config);
    }

    // Exponer los entity ids
    private void exposeIds(RepositoryRestConfiguration config) {
        // Obtenemos una lista de todas las entity classes del entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // Crear un ArrayList de esas entidades
        List<Class> entityClasses = new ArrayList<>();

        // Obtenemos el tipo de la entidad
        for (EntityType entityType : entities) {
            entityClasses.add(entityType.getJavaType());
        }

        // Exponemos los entity ids a partir del Array
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
