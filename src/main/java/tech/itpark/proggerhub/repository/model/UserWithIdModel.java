package tech.itpark.proggerhub.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserWithIdModel {

    private long id;
    private String login;
    private String passwordHash;
    private String secretPhraseHash;

}
