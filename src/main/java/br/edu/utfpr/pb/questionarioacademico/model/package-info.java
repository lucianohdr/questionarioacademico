@TypeDef(
    name="encryptedString", 
    typeClass=EncryptedStringType.class, 
    parameters= {
        @Parameter(name="encryptorRegisteredName", value="HibernateStringEncryptor"),
    }
)
package br.edu.utfpr.pb.questionarioacademico.model;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedStringType;