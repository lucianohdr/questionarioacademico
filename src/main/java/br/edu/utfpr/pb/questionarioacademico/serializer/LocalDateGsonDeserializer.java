package br.edu.utfpr.pb.questionarioacademico.serializer;

import java.lang.reflect.Type;

import javax.enterprise.context.Dependent;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 
 * Classe criada para fazer a conversão de data de forma específica pois o formato padrão não era o mais adequado para a view.
 *
 */
@Dependent
public class LocalDateGsonDeserializer implements JsonDeserializer<LocalDate> {

	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		String value = json.getAsString();

		DateTimeFormatter dtf = DateTimeFormat.forPattern(Controller.dateFormat);
		LocalDate localdate =  dtf.parseLocalDate(value);
		return localdate;
	}
}