package mcgillar.backend.repositories.user;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.user.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findAppUserByUsername(String username);
}
