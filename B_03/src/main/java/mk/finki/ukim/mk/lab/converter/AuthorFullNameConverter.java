package mk.finki.ukim.mk.lab.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mk.finki.ukim.mk.lab.model.AuthorFullName;

import java.io.Serializable;

@Converter
public class AuthorFullNameConverter implements AttributeConverter<AuthorFullName, String>{
    private static final String separator = ", ";
    @Override
    public String convertToDatabaseColumn(AuthorFullName authorFullName) {
        if(authorFullName == null){
            return null;
        }

        StringBuilder sb = new StringBuilder();

        if(authorFullName.getSurname() != null && !authorFullName.getSurname().isEmpty()){
            sb.append(authorFullName.getSurname()).append(separator);
        }

        if(authorFullName.getName() != null && !authorFullName.getName().isEmpty()){
            sb.append(authorFullName.getName());
        }

        return sb.toString();
    }

    @Override
    public AuthorFullName convertToEntityAttribute(String dbAuthorFullName) {
        if(dbAuthorFullName == null || dbAuthorFullName.isEmpty()){
            return null;
        }
        String[] parts = dbAuthorFullName.split(separator);
        if(parts == null || parts.length == 0){
            return null;
        }
        AuthorFullName fullName = new AuthorFullName();

        if(dbAuthorFullName.contains(separator)){ // Ako go ima separatorot, znaci ima barem 2 dela od imeto vneseno
            fullName.setSurname(parts[0]); //Za prezimeto go zema prviot del
            if(parts.length >= 2 && parts[1] != null && !parts[1].isEmpty()){ // Proveruva dali ima barem 2 dela i dali vtoriot del ima nekoja vrednost
                fullName.setName(parts[1]); // dodeluva taa vrednost na imeto
            }
        } else { // Ako go nema separatorot, samo eden del od imeto go imame, pa ke go stavime toa da bide imeto.
            fullName.setName(parts[0]);
        }

        return fullName;
    }
}