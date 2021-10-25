package com.epam.task2.service;

import com.epam.task2.domain.Secret;
import com.epam.task2.repository.SecretRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SecretService {

    private final SecretRepository secretRepository;

    public SecretService(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }

    public String sendSecret(String secretString) {
        Secret secret = new Secret();
        secret.setUuid(UUID.randomUUID().toString());
        secret.setSecretString(secretString);
        secretRepository.save(secret);

        return secret.getUuid();

    }

    public String readSecret(String uniqueId) {
        Optional<Secret> secretOpt = secretRepository.findById(uniqueId);
        if (secretOpt.isPresent()) {
            secretRepository.deleteById(uniqueId);
            return secretOpt.get().getSecretString();
        }

        //not found
        return null;
    }
}
