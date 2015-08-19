package br.edu.utfpr.pb.questionarioacademico.serializer;

import java.lang.reflect.Type;

import javax.enterprise.context.Dependent;

import org.joda.time.LocalDate;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * Classe criada para fazer a conversão de data de forma específica pois o formato padrão não era o mais adequado para a view.
 *
 */

@Dependent
public class LocalDateGsonSerializer implements JsonSerializer<LocalDate> {
	
	@Override
	public JsonElement serialize(LocalDate localDateTime, Type typeOfSrc,
			JsonSerializationContext context) {
		String val = null;
		if (localDateTime != null) {
			val = localDateTime.toString();
		}
		return new JsonPrimitive(val);
	}
}