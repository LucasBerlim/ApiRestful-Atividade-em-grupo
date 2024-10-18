package org.serratec.ecommerce.pataMagica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCep(
    @SerializedName("logradouro") String rua,
    String bairro,
    @SerializedName("localidade") String cidade,
    String uf
) {}