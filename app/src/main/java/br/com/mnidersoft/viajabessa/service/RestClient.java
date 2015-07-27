package br.com.mnidersoft.viajabessa.service;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;

import java.util.List;

import br.com.mnidersoft.viajabessa.Constant;

@Rest(rootUrl = Constant.APIARY_URL, converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient extends RestClientErrorHandling {

    @Get("/packages?version={version}&brand={brand}&model={model}")
    public List<Object> getAllPackages(String version, String brand, String model) throws RestClientException;
}
